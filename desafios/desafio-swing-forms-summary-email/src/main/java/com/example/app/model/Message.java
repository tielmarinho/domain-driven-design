package com.example.app.model;

import java.time.LocalDateTime;

/** Modelo de dados do envio. */
public class Message {
    private String name;
    private String email;
    private String body;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Message(String name, String email, String body) {
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getBody() { return body; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}