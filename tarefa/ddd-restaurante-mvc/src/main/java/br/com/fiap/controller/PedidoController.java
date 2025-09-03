package br.com.fiap.controller;

import br.com.fiap.domain.Prato;
import br.com.fiap.interfaces.IPrato;
import br.com.fiap.service.PedidoService;
import br.com.fiap.ui.ConsoleView;

import java.util.List;

public class PedidoController {
    private final IPrato pratoDAO;
    private final PedidoService service;
    private final ConsoleView view;

    public PedidoController(IPrato pratoDAO, PedidoService service, ConsoleView view) {
        this.pratoDAO = pratoDAO; this.service = service; this.view = view;
    }

    public void fluxo() {
        view.listarCardapio(pratoDAO.listarTodos());
        List<Long> ids = view.lerIdsPratos();
        List<Prato> itens = service.montarPedidoPorIds(ids);
        view.mostrarTotal(service.calcularTotal(itens));
    }
}