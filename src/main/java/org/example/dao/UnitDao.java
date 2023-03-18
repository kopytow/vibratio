package org.example.dao;

import org.example.magnitude.Measure;

import java.util.List;

public interface UnitDao {
    List<Measure> findAll();
    Measure save(Measure measure);
    Measure getById(String id);
    Measure update(Measure measure);
    void delete(Measure measure);
    void deleteAll();
}
