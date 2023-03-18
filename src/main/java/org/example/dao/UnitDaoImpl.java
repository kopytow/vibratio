package org.example.dao;

import org.example.magnitude.Measure;
import org.example.magnitude.StandardMeasure;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UnitDaoImpl implements UnitDao {

    private DataSource dataSource;

    @Autowired
    public UnitDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Measure> findAll() {
        final String selectSql = "select * from measure";
        List<Measure> measures = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectSql)
        ) {
            while (rs.next()) {
                Measure measure = new StandardMeasure(
                        rs.getNString("id"),
                        rs.getString("title"), rs.getString("main_symbol"), rs.getString("alternate_symbol"),
                        rs.getString("definition"), rs.getString("dimension")
                );
                measures.add(measure);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return measures;
    }

    @Override
    public Measure save(@NotNull Measure measure) {
        final String insertSql = "insert into measure(id, title, main_symbol, alternate_symbol, dimension, definition) values(?, ?, ?, ?, ?, ?)";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertSql)
        ) {
            preparedStatement.setString(1,measure.getId());
            preparedStatement.setString(2,measure.getTitle());
            preparedStatement.setString(3,measure.getMainSymbol());
            preparedStatement.setString(4,measure.getAlternateSymbol());
            preparedStatement.setString(5,measure.getDimension());
            preparedStatement.setString(6,measure.getDefinition());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return measure;
    }

    @Override
    public Measure getById(String id) {
        final String selectSql = "select * from measure where id = ?";
        Measure measure = null;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)
        ) {
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                measure = new StandardMeasure(
                        rs.getNString("id"),
                        rs.getString("title"), rs.getString("main_symbol"), rs.getString("alternate_symbol"),
                        rs.getString("definition"), rs.getString("dimension")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return measure;
    }

    @Override
    public Measure update(@NotNull Measure measure) {
        final String updateSql = "update measure set title = ?, main_symbol = ?, alternate_symbol = ?, dimension = ?, definition = ? where id = ?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateSql)
        ) {
            preparedStatement.setString(1,measure.getTitle());
            preparedStatement.setString(2,measure.getMainSymbol());
            preparedStatement.setString(3,measure.getAlternateSymbol());
            preparedStatement.setString(4,measure.getDimension());
            preparedStatement.setString(5,measure.getDefinition());
            preparedStatement.setString(6,measure.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return measure;
    }

    public void delete(@NotNull Measure measure) {
        final String deleteSql = "delete from measure where id = ?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)
        ) {
            preparedStatement.setString(1, measure.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        final String deleteSql = "truncate table measure";
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(deleteSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
