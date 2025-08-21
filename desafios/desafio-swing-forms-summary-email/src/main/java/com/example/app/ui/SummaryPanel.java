package com.example.app.ui;

import com.example.app.model.Message;
import com.example.app.util.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

/** Tela 2: Resumo do envio. */
public class SummaryPanel extends JPanel {

    private final JLabel lblUser = new JLabel();
    private final JLabel lblMail = new JLabel();
    private final JTextArea txtSummary = new JTextArea(8, 28);
    private final JButton btnBack = new JButton("Voltar");

    public SummaryPanel() {
        super(new BorderLayout(10,10));

        // Topo com ícones e dados básicos
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 8));
        ImageIcon userIcon = IconLoader.load("/icons/user.png", 20);
        ImageIcon mailIcon = IconLoader.load("/icons/mail.png", 20);
        if (userIcon != null) lblUser.setIcon(userIcon);
        if (mailIcon != null) lblMail.setIcon(mailIcon);
        lblUser.setText("—");
        lblMail.setText("—");
        top.add(lblUser);
        top.add(lblMail);

        // Área de resumo
        txtSummary.setEditable(false);
        txtSummary.setLineWrap(true);
        txtSummary.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(txtSummary);

        // Rodapé
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnBack.setMnemonic('V');
        bottom.add(btnBack);

        add(top, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    public void updateSummary(Message m) {
        lblUser.setText(" " + m.getName());
        lblMail.setText(" " + m.getEmail());
        String ts = m.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        txtSummary.setText("Enviado em: " + ts + "\n\n" + m.getBody());
        txtSummary.setCaretPosition(0);
    }

    public void setOnBack(ActionListener al) {
        for (var l : btnBack.getActionListeners()) btnBack.removeActionListener(l);
        btnBack.addActionListener(al);
    }

}