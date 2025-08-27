
package com.example.clinica.ui.console;

import com.example.clinica.dao.jdbc.JdbcConsultaDao;
import com.example.clinica.domain.Consulta;
import com.example.clinica.service.ConsultaService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleMain {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void run() {
        ConsultaService service = new ConsultaService(new JdbcConsultaDao());
        Scanner in = new Scanner(System.in);
        int op;
        do {
            System.out.println("\n=== Agenda de Consultas (Console) ===");
            System.out.println("1) Listar");
            System.out.println("2) Agendar (criar)");
            System.out.println("3) Alterar");
            System.out.println("4) Excluir");
            System.out.println("0) Sair");
            System.out.print("> ");
            op = safeInt(in);
            switch (op) {
                case 1 -> listar(service);
                case 2 -> agendar(service, in);
                case 3 -> alterar(service, in);
                case 4 -> excluir(service, in);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

    private static int safeInt(Scanner in) {
        try { return Integer.parseInt(in.nextLine()); }
        catch (Exception e) { return -1; }
    }

    private static long safeLong(Scanner in) {
        try { return Long.parseLong(in.nextLine()); }
        catch (Exception e) { return -1; }
    }

    private static LocalDateTime readDateTime(Scanner in, String label) {
        System.out.print(label + " (yyyy-MM-dd HH:mm): ");
        return LocalDateTime.parse(in.nextLine().trim(), FMT);
    }

    private static void listar(ConsultaService service) {
        List<Consulta> list = service.listar();
        System.out.println("\nID | Paciente | Médico | Início           | Fim");
        System.out.println("-----------------------------------------------");
        for (Consulta c : list) {
            System.out.printf("%2d | %9d | %6d | %s | %s%n",
                c.getId(), c.getPacienteId(), c.getMedicoId(),
                c.getInicio().format(FMT), c.getFim().format(FMT));
        }
    }

    private static void agendar(ConsultaService service, Scanner in) {
        System.out.print("Paciente ID: "); long pid = safeLong(in);
        System.out.print("Médico ID: "); long mid = safeLong(in);
        LocalDateTime ini = readDateTime(in, "Início");
        LocalDateTime fim = readDateTime(in, "Fim");
        Long id = service.agendar(pid, mid, ini, fim);
        System.out.println("Consulta criada com ID: " + id);
    }

    private static void alterar(ConsultaService service, Scanner in) {
        System.out.print("ID da consulta: "); long id = safeLong(in);
        System.out.print("Novo Paciente ID: "); long pid = safeLong(in);
        System.out.print("Novo Médico ID: "); long mid = safeLong(in);
        LocalDateTime ini = readDateTime(in, "Novo início");
        LocalDateTime fim = readDateTime(in, "Novo fim");
        Consulta c = new Consulta(id, pid, mid, ini, fim);
        service.alterar(c);
        System.out.println("Consulta alterada.");
    }

    private static void excluir(ConsultaService service, Scanner in) {
        System.out.print("ID da consulta a excluir: "); long id = safeLong(in);
        service.excluir(id);
        System.out.println("Consulta excluída.");
    }
}
