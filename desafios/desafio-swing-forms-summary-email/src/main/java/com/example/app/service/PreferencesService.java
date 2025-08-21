package com.example.app.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/** Serviço simples para persistir preferências em um arquivo .properties no diretório do usuário. */
public class PreferencesService {

    private final Path file;

    public PreferencesService() {
        this.file = Path.of(System.getProperty("user.home"), ".swing_forms_summary.properties");
    }

    public String loadName() {
        Properties props = new Properties();
        if (Files.exists(file)) {
            try (InputStream in = Files.newInputStream(file)) {
                props.load(in);
            } catch (IOException ignored) { }
        }
        return props.getProperty("name", "");
    }

    public void saveName(String name) {
        Properties props = new Properties();
        props.setProperty("name", name == null ? "" : name);
        try (OutputStream out = Files.newOutputStream(file)) {
            props.store(out, "DDD - FIAP");
        } catch (IOException ignored) { }
    }
}
