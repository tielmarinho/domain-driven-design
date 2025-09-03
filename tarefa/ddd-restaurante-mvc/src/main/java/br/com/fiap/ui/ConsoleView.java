package br.com.fiap.ui;

import br.com.fiap.domain.Prato;

import java.util.*;
import java.util.stream.Collectors;

public class ConsoleView {
    private final Scanner in = new Scanner(System.in);

    public void listarCardapio(List<Prato> pratos) {
        System.out.println("=== CARDÁPIO ===");
        for (Prato p : pratos) {
            System.out.printf("%d - %s (R$ %.2f)%n", p.getId(), p.getNome(), p.getPreco());
        }
    }

    public List<Long> lerIdsPratos() {
        System.out.print("Informe IDs dos pratos (ex: 1,2,3): ");
        String[] parts = in.nextLine().split(",");
        return Arrays.stream(parts).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
    }

    public void mostrarTotal(double total) {
        System.out.printf("Total: R$ %.2f%n", total);
    }
}