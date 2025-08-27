
package com.example.clinica.ui.swing;

import com.example.clinica.dao.jdbc.JdbcConsultaDao;
import com.example.clinica.domain.Consulta;
import com.example.clinica.service.ConsultaService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SwingMain {

    public static void run() {
        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); } catch (Exception ignored) {}
        EventQueue.invokeLater(() -> {
            ConsultaService service = new ConsultaService(new JdbcConsultaDao());
            JFrame f = new JFrame("Agenda de Consultas (Swing)");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(850, 520);
            f.setLocationRelativeTo(null);

            JTabbedPane tabs = new JTabbedPane();
            tabs.add("Listar", new ListarPanel(service));
            tabs.add("Alterar", new AlterarPanel(service));
            tabs.add("Excluir", new ExcluirPanel(service));
            f.setContentPane(tabs);
            f.setVisible(true);
        });
    }

    /** Painel de listagem */
    static class ListarPanel extends JPanel {
        private final ConsultaService service;
        private final ConsultaTableModel model = new ConsultaTableModel();

        ListarPanel(ConsultaService service) {
            super(new BorderLayout(8,8));
            this.service = service;

            JTable table = new JTable(model);
            add(new JScrollPane(table), BorderLayout.CENTER);

            JButton btnRefresh = new JButton("Atualizar");
            btnRefresh.addActionListener(e -> carregar());
            JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            south.add(btnRefresh);
            add(south, BorderLayout.SOUTH);

            try { carregar(); } catch (Exception ignored) {}
        }

        void carregar() {
            try {
                model.setData(service.listar());
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this,
                        "Falha ao listar consultas. Verifique a conexão com o Oracle.\n" + ex.getMessage(),
                        "Erro de conexão", JOptionPane.ERROR_MESSAGE);
                model.setData(java.util.Collections.emptyList());
            }
        }
    }

    static class AlterarPanel extends JPanel {
        private final ConsultaService service;
        private final JTextField txtId = new JTextField(6);
        private final JTextField txtPacienteId = new JTextField(6);
        private final JTextField txtMedicoId = new JTextField(6);
        private final JTextField txtInicio = new JTextField(16);
        private final JTextField txtFim = new JTextField(16);
        private final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        AlterarPanel(ConsultaService service) {
            super(new BorderLayout(8,8));
            this.service = service;

            JPanel form = new JPanel(new GridLayout(5,2,8,8));
            form.add(new JLabel("ID Consulta:"));
            form.add(txtId);
            form.add(new JLabel("Paciente ID:"));
            form.add(txtPacienteId);
            form.add(new JLabel("Médico ID:"));
            form.add(txtMedicoId);
            form.add(new JLabel("Início (yyyy-MM-dd HH:mm):"));
            form.add(txtInicio);
            form.add(new JLabel("Fim (yyyy-MM-dd HH:mm):"));
            form.add(txtFim);

            JButton btn = new JButton("Salvar alterações");
            btn.addActionListener(e -> {
                try {
                    long id = Long.parseLong(txtId.getText().trim());
                    long pid = Long.parseLong(txtPacienteId.getText().trim());
                    long mid = Long.parseLong(txtMedicoId.getText().trim());
                    LocalDateTime ini = LocalDateTime.parse(txtInicio.getText().trim(), FMT);
                    LocalDateTime fim = LocalDateTime.parse(txtFim.getText().trim(), FMT);
                    service.alterar(new Consulta(id, pid, mid, ini, fim));
                    JOptionPane.showMessageDialog(this, "Consulta alterada.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Falha", JOptionPane.ERROR_MESSAGE);
                }
            });

            add(form, BorderLayout.CENTER);
            JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            south.add(btn);
            add(south, BorderLayout.SOUTH);
        }
    }

    static class ExcluirPanel extends JPanel {
        private final ConsultaService service;
        private final JTextField txtId = new JTextField(6);

        ExcluirPanel(ConsultaService service) {
            super(new BorderLayout(8,8));
            this.service = service;
            JPanel center = new JPanel(new GridLayout(1,2,8,8));
            center.add(new JLabel("ID Consulta:"));
            center.add(txtId);
            JButton btn = new JButton("Excluir");
            btn.addActionListener(e -> {
                try {
                    long id = Long.parseLong(txtId.getText().trim());
                    service.excluir(id);
                    JOptionPane.showMessageDialog(this, "Consulta excluída.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Falha", JOptionPane.ERROR_MESSAGE);
                }
            });
            add(center, BorderLayout.CENTER);
            JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            south.add(btn);
            add(south, BorderLayout.SOUTH);
        }
    }

    static class ConsultaTableModel extends AbstractTableModel {
        private final String[] cols = {"ID", "Paciente", "Médico", "Início", "Fim"};
        private final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        private List<Consulta> data = new ArrayList<>();

        public void setData(List<Consulta> list) {
            this.data = list != null ? list : new ArrayList<>();
            fireTableDataChanged();
        }

        @Override public int getRowCount() { return data.size(); }
        @Override public int getColumnCount() { return cols.length; }
        @Override public String getColumnName(int c) { return cols[c]; }

        @Override public Object getValueAt(int r, int c) {
            Consulta x = data.get(r);
            return switch (c) {
                case 0 -> x.getId();
                case 1 -> x.getPacienteId();
                case 2 -> x.getMedicoId();
                case 3 -> x.getInicio().format(FMT);
                case 4 -> x.getFim().format(FMT);
                default -> "";
            };
        }
    }
}