package org.example.magnitude;


import java.util.Objects;
import java.util.UUID;

/**
 * Вибрация
 * <p>Обозначения и единицы величин.
 * <p>ГОСТ 24347-80
 */
public class StandardMeasure implements Measure {

    /**
     * Уникальный идентификатор.
     */
    private final String id;

    /**
     * Наименование величины.
     */
    private String title;

    /**
     * Размерность.
     */
    private String dimension;

    /**
     * Определение этой единицы измерения.
     */
    private String definition;

    /**
     * Основной символ единицы измерения.
     */
    private String mainSymbol;

    /**
     * Альтернативный символ единицы измерения.
     */
    private String alternateSymbol;

    /**
     * Конструктор создаёт экземпляр этого класса без идентификатора.
     */
    public StandardMeasure(String title, String mainSymbol, String alternateSymbol, String definition, String dimension) {
        this.id = UUID.randomUUID().toString();
        this.mainSymbol = mainSymbol;
        this.alternateSymbol = alternateSymbol;
        this.title = title;
        this.dimension = dimension;
        this.definition = definition;
    }
    /**
     * Конструктор создаёт экземпляр этого класса с идентификатором.
     */
    public StandardMeasure(String id, String title, String mainSymbol, String alternateSymbol, String definition, String dimension) {
        this.id = id;
        this.mainSymbol = mainSymbol;
        this.alternateSymbol = alternateSymbol;
        this.title = title;
        this.dimension = dimension;
        this.definition = definition;
    }

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
    public String getDimension() {
        return this.dimension;
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

    /**
     * Возвращает осносной символ этой единицы измерения.
     *
     * @return осносной символ
     */
    @Override
    public String getMainSymbol() {
        return this.mainSymbol;
    }

    /**
     * Возвращает альтернативный символ этой единицы измерения.
     *
     * @return альтернативный символ
     */
    public String getAlternateSymbol() {
        return this.alternateSymbol;
    }

    public void setMainSymbol(String mainSymbol) {
        this.mainSymbol = mainSymbol;
    }

    public void setAlternateSymbol(String alternateSymbol) {
        this.alternateSymbol = alternateSymbol;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StandardMeasure that)) return false;
        return getId().equals(that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDimension(), that.getDimension()) && Objects.equals(getDefinition(), that.getDefinition()) && getMainSymbol().equals(that.getMainSymbol()) && Objects.equals(getAlternateSymbol(), that.getAlternateSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDimension(), getDefinition(), getMainSymbol(), getAlternateSymbol());
    }

    @Override
    public String toString() {
        return "StandardMeasure{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dimension='" + dimension + '\'' +
                ", definition='" + definition + '\'' +
                ", mainSymbol='" + mainSymbol + '\'' +
                ", alternateSymbol='" + alternateSymbol + '\'' +
                '}';
    }
}
