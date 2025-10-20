package com.example.Homework.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Connected to Database. Running a test query...");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users(id INT PRIMARY KEY, name VARCHAR(50))");
        System.out.println("Table 'users' created.");

        jdbcTemplate.update("INSERT INTO users(id, name) VALUES (?, ?)", 1, "Alice");
        jdbcTemplate.update("INSERT INTO users(id, name) VALUES (?, ?)", 2, "Bob");
        System.out.println("Inserted users into 'users' table.");

        jdbcTemplate.query("SELECT * FROM users", (rs) -> {
            System.out.println("User ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
        });
    }

}
