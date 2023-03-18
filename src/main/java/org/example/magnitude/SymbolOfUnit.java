package org.example.magnitude;


/**
 * Обозначение единицы измерения.
 *
 * @author Bazil Kopytow <kopytow@iss-reshetnev.ru>
 */
public class SymbolOfUnit {

    /**
     * Основное обозначение.
     */
    private final String main;

    /**
     * Запасное обозначение.
     * <p>
     * <i>Может не иметь значения, а может иметь несколько значений. Требуется
     * реализация.</i>
     */
    private final String alternate;

    public SymbolOfUnit(String main, String alternate) {
        this.main = main;
        this.alternate = alternate;
    }

    public String getMain() {
        return main;
    }

    public String getAlternate() {
        return alternate;
    }

    @Override
    public String toString() {
        return "SymbolOfUnit{" +
                "main='" + main + '\'' +
                ", alternate='" + alternate + '\'' +
                '}';
    }
}
