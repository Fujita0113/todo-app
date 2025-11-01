package com.example.todo_app.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    private final JdbcTemplate jdbc;

    public TestController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/init")
    public String init() {
        jdbc.execute("CREATE TABLE IF NOT EXISTS users(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50))");
        jdbc.update("INSERT INTO users(name) VALUES ('Alice')");
        jdbc.update("INSERT INTO users(name) VALUES ('Bob')");
        return "Data inserted!";
    }

    @GetMapping("/show")
    public List<Map<String, Object>> show() {
        return jdbc.queryForList("SELECT * FROM users");
    }
}
