package org.example.dao;

import org.example.magnitude.Measure;
import org.example.magnitude.StandardMeasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"jdbcUrl=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UnitDaoImplTest {

    @Autowired
    private UnitDao unitDao;

    @Test
    void findAll() {
    }

    @Test
    void saveTest() {
        Measure measure = unitDao.save(new StandardMeasure("Частота периодических колебаний","T", "", "T", "час"));
        assertThat(measure.getId()).isNotBlank();
        assertThat(unitDao.findAll())
                .containsOnlyOnce(measure);
    }

    @Test
    void getByIdTest() {
        Measure measure = unitDao.save(new StandardMeasure("Уровень звукового дваления относительно p=2*10<sup>-5</sup> Па", "SPL", "", "1",  "дБ"));
        assertThat(measure.getId()).isNotBlank();
        assertThat(unitDao.getById(measure.getId())).isEqualTo(measure);
    }

    @Test
    void updateTest() {
        StandardMeasure measure = (StandardMeasure) unitDao.save(new StandardMeasure("Частота периодических колебаний","T", "", "T", "час"));
        measure.setDimension("Гц");
        unitDao.update(measure);
        assertThat(unitDao.findAll())
                .extracting("dimension")
                .contains("Гц", "дБ");
    }

    @Test
    void deleteTest() {
        Measure measure = unitDao.save(new StandardMeasure("Время в секундах", "T", "", "T", "c"));
        assertThat(unitDao.findAll())
                .containsOnlyOnce(measure);
        unitDao.delete(measure);
        assertThat(unitDao.findAll()).doesNotContain(measure);
    }

    @Test
    void deleteAllTest() {
//        unitDao.save(new StandardMeasure("Частота периодических колебаний","T", "", "T", "час"));
        assertThat(unitDao.findAll()).isNotEmpty();
        unitDao.deleteAll();
        assertThat(unitDao.findAll()).isEmpty();
    }
}