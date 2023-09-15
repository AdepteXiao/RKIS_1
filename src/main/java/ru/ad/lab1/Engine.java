package ru.ad.lab1;


import java.util.Objects;

/**
 * Класс двигателя
 */
public class Engine {

  /**
   * Границы для мощности двигателя в лошадинных силах
   */
  private final int MIN_POWER = 1, MAX_POWER = 1000000;

  /**
   * Мощность двигателя в лошадинных силах
   */
  private int power;
  /**
   * Название двигателя
   */
  private String name;

  /**
   * Конструктор Engine без параметров
   */
  public Engine() {
    this.power = -1;
    this.name = " - ";
  }

  /**
   * Конструктор Engine с параметрами
   *
   * @param power мощность двигателя в лошадинных силах
   * @param name  название двигателя
   */
  public Engine(int power, String name) {

    if (power >= MIN_POWER & power < MAX_POWER & name != null) {
      this.power = power;
      this.name = name;
    } else {
      this.power = -1;
      this.name = " - ";
    }
  }

  /**
   * Метод получения названия двигателя
   *
   * @return private String name - название двигателя
   */
  public String getName() {
    return name;
  }

  /**
   * Метод получения мощности двигателя в лошадинных силах
   *
   * @return private int cylinders - мощность двигателя в лошадинных силах
   */
  public int getPower() {
    return power;
  }

  /**
   * Метод установки названия двигателя
   *
   * @param name названия двигателя
   */
  public void setName(String name) {
    if (name != null) {
      this.name = name;
    }
  }

  /**
   * Метод установки мощности двигателя в лошадинных силах
   *
   * @param power мощность двигателя в лошадинных силах
   */
  public void setPower(int power) {
    if (power >= MIN_POWER & power < MAX_POWER) {
      this.power = power;

    }
  }

  /**
   * Метод строкового представления Engine
   */
  @Override
  public String toString() {
    return String.format("""
        Двигатель %s
        %d л.с""", this.name, this.power);
  }

  /**
   * Метод сравнения Engine с Object
   *
   * @param obj сравниваемый Object
   * @return true при эквивалентности объектов, иначе false
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Engine engine = (Engine) obj;
    return this.power == engine.power && this.name.equals(engine.name);
  }

  /**
   * Метод получения хэш-кода объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, power);
  }
}

