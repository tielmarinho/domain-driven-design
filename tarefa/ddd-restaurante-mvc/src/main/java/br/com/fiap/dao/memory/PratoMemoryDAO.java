package br.com.fiap.dao.memory;

import br.com.fiap.domain.Prato;
import br.com.fiap.interfaces.IPrato;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class PratoMemoryDAO implements IPrato {
    private final Map<Long, Prato> db = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public PratoMemoryDAO() {
        salvar(new Prato("Executivo", 39.90));
        salvar(new Prato("Vegano", 42.00));
        salvar(new Prato("Kids", 29.00));
    }

    @Override
    public Long salvar(Prato p) {
        long id = (p.getId() == null) ? seq.incrementAndGet() : p.getId();
        p.setId(id);
        db.put(id, p);
        return id;
    }

    @Override
    public Prato buscarPorId(Long id) {
        return db.get(id);
    }

    @Override
    public List<Prato> listarTodos() {
        return new ArrayList<>(db.values());
    }

    @Override
    public void deletar(Long id) {
        db.remove(id);
    }
}