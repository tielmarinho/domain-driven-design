package br.com.fiap;

import br.com.fiap.factory.DaoFactory;
import br.com.fiap.interfaces.IPrato;
import br.com.fiap.service.PedidoService;
import br.com.fiap.ui.ConsoleView;
import br.com.fiap.controller.PedidoController;

public class App {
    public static void main(String[] args) {
        IPrato pratoDAO = DaoFactory.pratoDAO();
        PedidoService service = new PedidoService(pratoDAO);
        ConsoleView view = new ConsoleView();
        new PedidoController(pratoDAO, service, view).fluxo();
    }
}