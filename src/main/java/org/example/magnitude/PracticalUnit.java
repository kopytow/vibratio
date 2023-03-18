package org.example.magnitude;


import java.util.Objects;
import java.util.UUID;

/**
 * Техническая единица измерения.
 */
public class PracticalUnit implements Measure {

    /**
     * Размерность.
     */
    private final String dimension;

    /**
     * Определение этой единицы измерения.
     */
    private final String definition;

    /**
     * Наименование этой единицы измерения.
     */
    private final String title;

    /**
     * Коэффициент пропорциональности к СИ.
     */
    private final double factor;

    /**
     * Уникальный идентификатор.
     */
    private String id;

    @Override
    public String getId() {
        return id;
    }

    /**
     * Возвращает наименование этой единицы измерения.
     *
     * @return наименование
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Возвращает определение этой единицы измерения.
     *
     * @return определение
     */
    @Override
    public String getDefinition() {
        return this.definition;
    }

    @Override
    public String getMainSymbol() {
        return null;
    }

    @Override
    public String getAlternateSymbol() {
        return null;
    }

    /**
     * Обозначение этой единицы измерения.
     * Основное обозначение.
     */
    private String mainSymbol;

    /**
     * Запасное обозначение.
     * <p>
     * <i>Может не иметь значения, а может иметь несколько значений. Требуется
     * реализация.</i>
     */
    private String alternateSymbol;

    /**
     * Конструктор создаёт экземпляр класса без идентификатора.
     */
    private PracticalUnit(String title, String mainSymbol, String alternateSymbol, String dimension, String definition, double factor) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.mainSymbol = mainSymbol;
        this.alternateSymbol = alternateSymbol;
        this.dimension = dimension;
        this.definition = definition;
        this.factor = factor;
    }

    public double getFactor() {
        return this.factor;
    }

    /**
     * Возвращает определение этой единицы измерения.
     *
     * @return определение
     */
    @Override
    public String getDimension() {
        return this.dimension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PracticalUnit that)) return false;
        return Double.compare(that.getFactor(), getFactor()) == 0 && Objects.equals(getDimension(), that.getDimension()) && Objects.equals(getDefinition(), that.getDefinition()) && Objects.equals(getTitle(), that.getTitle()) && getId().equals(that.getId()) && getMainSymbol().equals(that.getMainSymbol()) && Objects.equals(getAlternateSymbol(), that.getAlternateSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDimension(), getDefinition(), getTitle(), getFactor(), getId(), getMainSymbol(), getAlternateSymbol());
    }
}
