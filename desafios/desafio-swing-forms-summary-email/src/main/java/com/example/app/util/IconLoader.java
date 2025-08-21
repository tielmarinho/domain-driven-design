package com.example.app.util;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/** Utilitário para carregar ícones do classpath e escalonar suavemente. */
public final class IconLoader {
    private IconLoader(){}

    public static ImageIcon load(String path, int size) {
        URL url = IconLoader.class.getResource(path);
        if (url == null) return null;
        Image img = new ImageIcon(url).getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
