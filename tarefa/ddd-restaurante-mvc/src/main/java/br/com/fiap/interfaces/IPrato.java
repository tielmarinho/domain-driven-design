package br.com.fiap.interfaces;

import br.com.fiap.domain.Prato;
import java.util.List;

public interface IPrato {
    Long salvar(Prato p);
    Prato buscarPorId(Long id);
    List<Prato> listarTodos();
    void deletar(Long id);
}