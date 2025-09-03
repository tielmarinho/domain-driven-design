package br.com.fiap.service;

import br.com.fiap.domain.Prato;
import br.com.fiap.interfaces.IPrato;
import br.com.fiap.singleton.LoggerSingleton;
import br.com.fiap.domain.Estoque;
import br.com.fiap.interfaces.IPoliticaPromocao;
import br.com.fiap.domain.PromocaoDiaSemana;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private final IPrato pratoDAO;
    private final LoggerSingleton log = LoggerSingleton.getInstance();
    private final Estoque estoque = new Estoque();
    private IPoliticaPromocao promocao = new PromocaoDiaSemana(DayOfWeek.MONDAY, 0.10);

    public PedidoService(IPrato pratoDAO) {
        this.pratoDAO = pratoDAO;
        for (Prato p : pratoDAO.listarTodos()) {
            estoque.definirSaldo(p.getId(), 10);
        }
    }

    public void setPromocao(IPoliticaPromocao promo) { this.promocao = promo; }
    public Estoque getEstoque() { return estoque; }

    public List<Prato> montarPedidoPorIds(List<Long> ids) {
        List<Prato> itens = new ArrayList<>();
        for (Long id : ids) {
            if (!estoque.possui(id, 1)) throw new IllegalStateException("Sem estoque para prato " + id);
            Prato p = pratoDAO.buscarPorId(id);
            if (p == null) throw new IllegalArgumentException("Prato inexistente: " + id);
            estoque.reservar(id, 1);
            itens.add(p);
        }
        log.info("Pedido montado com " + itens.size() + " itens.");
        return itens;
    }

    public double calcularTotal(List<Prato> itens) {
        return itens.stream().mapToDouble(Prato::getPreco).sum();
    }

    public double aplicarPromocao(double total) {
        return promocao.aplicar(total);
    }
}