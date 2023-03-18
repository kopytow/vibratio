package org.example;

import org.example.magnitude.Measure;
import org.example.magnitude.StandardMeasure;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

@SpringBootApplication
public class Main {
    @Bean
    public DataSource h2DataSource(@Value("${jdbcUrl}") String jdbcUrl){
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setUser("user");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    public CommandLineRunner cmd(DataSource dataSource) {
        return args -> {
            try (InputStream inputStream = this.getClass().getResourceAsStream("/initial.sql")){
                String sql = new String(inputStream.readAllBytes());
                try (
                        Connection connection = dataSource.getConnection();
                        Statement statement = connection.createStatement()
                ){
                    statement.executeUpdate(sql);

                    System.out.println("Insert measure to db");
                    String insertSql = "insert into measure(id, title, main_symbol, alternate_symbol, dimension, definition) values(?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                        preparedStatement.setString(1, UUID.randomUUID().toString());
                        preparedStatement.setString(2,"Безразмерная единица");
                        preparedStatement.setString(3,"du");
                        preparedStatement.setString(4,"бе");
                        preparedStatement.setString(5,"1");
                        preparedStatement.setString(6,"");
                        preparedStatement.executeUpdate();
                    }

                    System.out.println("Printing measure from db");
                    ResultSet rs = statement.executeQuery("select * from measure");
                    while (rs.next()) {
                        Measure measure = new StandardMeasure(
                                rs.getString("title"), rs.getString("main_symbol"), rs.getString("alternate_symbol"),
                                rs.getString("definition"), rs.getString("dimension")
                                );
                        System.out.println(measure);
                    }
                }
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}