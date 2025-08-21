package com.example.clinic;

import com.example.clinic.domain.model.Consulta;
import com.example.clinic.domain.service.AgendaService;
import com.example.clinic.infra.dao.ConsultaJdbcRepository;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class App {

    private static final DateTimeFormatter PADRAO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", new Locale("pt", "BR"));

    public static void main(String[] args) {
        String ui = parseArg(args, "--ui");

        if ("console".equalsIgnoreCase(ui)) {
            runConsole();
        } else if ("swing".equalsIgnoreCase(ui)) {
            runSwing();
        } else {
            System.out.println("""
                Uso:
                  java ... com.example.clinic.App --ui=console   # entrada via Scanner
                  java ... com.example.clinic.App --ui=swing     # entrada via Swing

                Formato de data/hora: dd/MM/yyyy HH:mm  (ex.: 21/08/2025 10:00)
                """);
        }
    }

    private static String parseArg(String[] args, String key) {
        if (args == null) return null;
        String prefix = key + "=";
        for (String a : args) {
            if (a != null && a.startsWith(prefix)) {
                return a.substring(prefix.length());
            }
        }
        return null;
    }

    /* =========================
       MODO CONSOLE (Scanner)
       ========================= */
    private static void runConsole() {
        var repo = new ConsultaJdbcRepository();
        var service = new AgendaService(repo);

        try (Scanner in = new Scanner(System.in)) {
            System.out.print("ID do paciente (ex.: 1): ");
            long pacienteId = Long.parseLong(in.nextLine().trim());

            System.out.print("ID do médico (ex.: 10): ");
            long medicoId = Long.parseLong(in.nextLine().trim());

            System.out.print("Início da consulta (dd/MM/yyyy HH:mm): ");
            LocalDateTime inicio = LocalDateTime.parse(in.nextLine().trim(), PADRAO);

            System.out.print("Duração em minutos (ex.: 30): ");
            int duracaoMin = Integer.parseInt(in.nextLine().trim());
            LocalDateTime fim = inicio.plusMinutes(duracaoMin);

            var consulta = new Consulta(null, pacienteId, medicoId, inicio, fim);

            try {
                Long id = service.agendar(consulta);
                System.out.println("Consulta agendada com sucesso! ID = " + id);
            } catch (Exception e) {
                System.err.println("Erro ao agendar consulta: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Falha na entrada/parsing: " + e.getMessage());
        }
    }

    /* =========================
   MODO SWING (JPanel)
   ========================= */
    private static void runSwing() {
        // Look & Feel Nimbus (opcional)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) { UIManager.setLookAndFeel(info.getClassName()); break; }
            }
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            var repo = new ConsultaJdbcRepository();
            var service = new AgendaService(repo);

            JFrame frame = new JFrame("Agendar Consulta");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12)); // padding geral

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(6, 6, 6, 6);
            c.fill = GridBagConstraints.HORIZONTAL;

            // Campos mais largos (15 colunas) + dicas
            JTextField txtPaciente = new JTextField(15);
            txtPaciente.setToolTipText("Ex.: 1");
            JTextField txtMedico = new JTextField(15);
            txtMedico.setToolTipText("Ex.: 10");
            JTextField txtInicio = new JTextField(15);   // dd/MM/yyyy HH:mm
            txtInicio.setToolTipText("Formato: dd/MM/yyyy HH:mm (ex.: 21/08/2025 10:00)");
            JTextField txtDuracao = new JTextField(15);  // minutos
            txtDuracao.setToolTipText("Duração em minutos (ex.: 30)");
            JButton btnAgendar = new JButton("Agendar");

            int row = 0;

            // Label
            c.gridx = 0; c.gridy = row; c.weightx = 0; c.gridwidth = 1;
            panel.add(new JLabel("ID Paciente:"), c);
            // Campo (expande)
            c.gridx = 1; c.gridy = row++; c.weightx = 1.0;
            panel.add(txtPaciente, c);

            c.gridx = 0; c.gridy = row; c.weightx = 0;
            panel.add(new JLabel("ID Médico:"), c);
            c.gridx = 1; c.gridy = row++; c.weightx = 1.0;
            panel.add(txtMedico, c);

            c.gridx = 0; c.gridy = row; c.weightx = 0;
            panel.add(new JLabel("Início (dd/MM/yyyy HH:mm):"), c);
            c.gridx = 1; c.gridy = row++; c.weightx = 1.0;
            panel.add(txtInicio, c);

            c.gridx = 0; c.gridy = row; c.weightx = 0;
            panel.add(new JLabel("Duração (min):"), c);
            c.gridx = 1; c.gridy = row++; c.weightx = 1.0;
            panel.add(txtDuracao, c);

            // Botão ocupa as 2 colunas
            c.gridx = 0; c.gridy = row; c.gridwidth = 2; c.weightx = 0;
            panel.add(btnAgendar, c);

            // Deixa Enter como "clique" do botão
            frame.getRootPane().setDefaultButton(btnAgendar);

            btnAgendar.addActionListener(ev -> {
                try {
                    long pacienteId = Long.parseLong(txtPaciente.getText().trim());
                    long medicoId = Long.parseLong(txtMedico.getText().trim());
                    LocalDateTime inicio = LocalDateTime.parse(txtInicio.getText().trim(), PADRAO);
                    int duracaoMin = Integer.parseInt(txtDuracao.getText().trim());
                    LocalDateTime fim = inicio.plusMinutes(duracaoMin);

                    var consulta = new Consulta(null, pacienteId, medicoId, inicio, fim);

                    btnAgendar.setEnabled(false);
                    try {
                        Long id = service.agendar(consulta);
                        JOptionPane.showMessageDialog(frame,
                                "Consulta agendada com sucesso!\nID = " + id,
                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                        // Limpa campos e foca no primeiro
                        txtPaciente.setText("");
                        txtMedico.setText("");
                        txtInicio.setText("");
                        txtDuracao.setText("");
                        txtPaciente.requestFocusInWindow();
                    } finally {
                        btnAgendar.setEnabled(true);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Erro ao agendar consulta:\n" + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });

            frame.setContentPane(panel);

            // Define um tamanho mínimo confortável e permite pack (layout bonito)
            frame.pack();
            frame.setMinimumSize(new Dimension(380, 260));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
