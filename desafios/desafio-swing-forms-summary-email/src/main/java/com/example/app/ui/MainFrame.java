package com.example.app.ui;

import com.example.app.model.Message;
import com.example.app.service.PreferencesService;
import com.example.app.util.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/** Janela principal com CardLayout (Form -> Summary). */
public class MainFrame extends JFrame {

    private final CardLayout cards = new CardLayout();
    private final JPanel root = new JPanel(cards);
    private final FormPanel formPanel;
    private final SummaryPanel summaryPanel;
    private final PreferencesService prefs = new PreferencesService();

    public MainFrame() {
        super("Contato");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(560, 440));
        setLocationRelativeTo(null);

        // Ícone da aplicação (se existir)
        ImageIcon appIcon = IconLoader.load("/icons/app.png", 32);
        if (appIcon != null) setIconImage(appIcon.getImage());

        // Banner superior customizado
        JPanel banner = new BannerPanel();
        banner.setPreferredSize(new Dimension(560, 90));

        // Instancia painéis
        formPanel = new FormPanel();
        summaryPanel = new SummaryPanel();

        // Listeners
        formPanel.setOnSubmit(this::handleSubmit);
        summaryPanel.setOnBack(e -> cards.show(root, "form"));

        // Pré-carrega nome do usuário nas preferências
        formPanel.prefillName(prefs.loadName());

        root.add(formPanel, "form");
        root.add(summaryPanel, "summary");

        setLayout(new BorderLayout(0, 0));
        add(banner, BorderLayout.NORTH);
        add(root, BorderLayout.CENTER);

        cards.show(root, "form");
        pack();
    }

    private void handleSubmit(Message msg) {
        // Salva nome nas preferências
        prefs.saveName(msg.getName());
        // Atualiza painel de resumo
        summaryPanel.updateSummary(msg);
        // Navega
        cards.show(root, "summary");
    }

    /** Banner desenhado com Graphics2D + antialiasing. */
    static class BannerPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            Color base = new Color(30, 136, 229);
            Color dark = base.darker();
            GradientPaint gp = new GradientPaint(0, 0, base, w, h, dark);
            g2.setPaint(gp);
            g2.fillRoundRect(10, 10, w - 20, h - 20, 24, 24);

            g2.setColor(Color.WHITE);
            g2.setFont(getFont().deriveFont(Font.BOLD, 20f));
            g2.drawString("Formulário de Contato", 26, 48);
            g2.dispose();
        }
    }
}
