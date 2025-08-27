
package com.example.clinica;

import com.example.clinica.ui.console.ConsoleMain;
import com.example.clinica.ui.swing.SwingMain;

/**
 * Camada de Interface (aplicação). Escolha modo via argumento:
 *  -Dmode=console  ou  -Dmode=swing  (padrão: swing)
 */
public class App {
    public static void main(String[] args) {
        String mode = System.getProperty("mode", "swing");
        if ("console".equalsIgnoreCase(mode)) {
            ConsoleMain.run();
        } else {
            SwingMain.run();
        }
    }
}
