package br.com.fiap.factory;

import br.com.fiap.dao.jdbc.PratoJdbcDAO;
import br.com.fiap.dao.memory.PratoMemoryDAO;
import br.com.fiap.interfaces.IPrato;

public class DaoFactory {
    public static IPrato pratoDAO() {
        String useMem = System.getenv().getOrDefault("USE_INMEMORY", "false");
        if (useMem.equalsIgnoreCase("true")) {
            return new PratoMemoryDAO();
        }
        return new PratoJdbcDAO(ConnectionFactory.fromEnv());
    }
}