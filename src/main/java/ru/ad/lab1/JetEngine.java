package ru.ad.lab1;


import java.util.Objects;

/**
 * Класс реактивного двигателя
 */
public class JetEngine extends Engine{
  /**
   * Тяга в тоннах
   */
  private int thrust;
  /**
   * Тип двигателя
   */
  private String type;

  /**
   * Границы для тяги двигателя
   */
  private final int MIN_THRUST = 1, MAX_THRUST = 100;

  /**
   * Конструктор JetEngine без параметров
   */
  public JetEngine() {
    this.thrust = -1;
    this.type = " - ";
  }

  /**
   * Конструктор JetEngine с параметрами
   *
   * @param power мощность двигателя в лошадинных силах
   * @param name название двигателя
   * @param thrust тяга двигателя
   * @param type тип двигателя
   */
  public JetEngine(int power, String name, int thrust, String type) {
    super(power, name);
    if (MIN_THRUST < thrust && thrust < MAX_THRUST & type != null) {
      this.thrust = thrust;
      this.type = type;
    } else {
      this.thrust = -1;
      this.type = " - ";
    }
  }

  /**
   * Метод получения типа двигателя
   *
   * @return private String type - тип двигателя
   */
  public String getType() {
    return type;
  }

  /**
   * Метод получения значения тяги двигателя
   *
   * @return private int thrust - тяга двигателя
   */
  public int getThrust() {
    return thrust;
  }

  /**
   * Метод установки типа двигателя
   *
   * @param type тип двигателя
   */
  public void setType(String type) {
    if (type != null) {
      this.type = type;
    }
  }

  /**
   * Метод установки значения тяги двигателя
   *
   * @param thrust тяга двигателя
   */
  public void setThrust(int thrust) {
    if (thrust == MIN_THRUST | thrust == MAX_THRUST) {
      this.thrust = thrust;
    }
  }

  /**
   * Метод строкового представления JetEngine
   */
  @Override
  public String toString() {
    return String.format("""
            Двигатель %s
            Мощность %d л.с
            Тип: %s
            Тяга: %d""",
        super.getName(),
        super.getPower(),
        this.getType(),
        this.getThrust());
  }

  /**
   * Метод сравнения JetEngine с Object
   *
   * @param obj сравниваемый Object
   * @return true при эквивалентности объектов, иначе false
   */
  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }
    JetEngine jetEngine = (JetEngine) obj;
    return this.getThrust() == jetEngine.getThrust() && this.getType()
        .equals(jetEngine.getType());
  }

  /**
   * Метод получения хэш-кода объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.type, this.thrust);
  }
}