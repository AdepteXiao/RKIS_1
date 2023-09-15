package ru.ad.lab1;

import java.util.Objects;

/**
 * Класс двигателя внутреннего сгорания
 */
public class ConsEngine extends Engine {

  /**
   * Количество цилиндров
   */
  private int numbCylinder;
  /**
   * Система питания
   */
  private String system;

  /**
   * Границы для циклов работы
   */
  private final int MIN_CYLINDER = 1, MAX_CYLINDER = 64;

  /**
   * Конструктор ConsEngine без параметров
   */
  public ConsEngine() {
    this.numbCylinder = -1;
    this.system = " - ";
  }

  /**
   * Конструктор ConsEngine с параметрами
   *
   * @param power        мощность двигателя в лошадинных силах
   * @param name         название двигателя
   * @param numbCylinder к-во цилиндров двигателя
   * @param system       название системы питания
   */
  public ConsEngine(int power, String name, int numbCylinder, String system) {
    super(power, name);
    if (MIN_CYLINDER < numbCylinder && numbCylinder < MAX_CYLINDER && system != null) {
      this.numbCylinder = numbCylinder;
      this.system = system;
    } else {
      this.numbCylinder = -1;
      this.system = " - ";
    }
  }


  /**
   * Метод получения названия системы питания
   *
   * @return private String system - название системы питания
   */
  public String getSystem() {
    return system;
  }

  /**
   * Метод получения значения к-ва цилиндров двигателя
   *
   * @return private int NumbCylinder - к-во цилиндров двигателя
   */
  public int getNumbCylinder() {
    return numbCylinder;
  }

  /**
   * Метод установки названия системы питания
   *
   * @param system название системы питания
   */
  public void setSystem(String system) {
    if (system != null) {
      this.system = system;
    }
  }

  /**
   * Метод установки значения к-ва цилиндров двигателя
   *
   * @param numbCylinder к-во цилиндров двигателя
   */
  public void setNumbCylinder(int numbCylinder) {
    if (MIN_CYLINDER < numbCylinder && numbCylinder < MAX_CYLINDER) {
      this.numbCylinder = numbCylinder;
    }
  }

  /**
   * Метод строкового представления ConsEngine
   */
  @Override
  public String toString() {
    return String.format("""
            Двигатель:       %s
            Мощность:        %d
            Система питания: %s
            К-во цилиндров:  %d""",
        super.getName(),
        super.getPower(),
        this.getSystem(),
        this.getNumbCylinder());
  }

  /**
   * Метод сравнения ConsEngine с Object
   *
   * @param obj сравниваемый Object
   * @return true при эквивалентности объектов, иначе false
   */
  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }
    ConsEngine consEngine = (ConsEngine) obj;
    return this.getNumbCylinder() == consEngine.getNumbCylinder() && this.getSystem()
        .equals(consEngine.getSystem());
  }

  /**
   * Метод получения хэш-кода объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.system, this.numbCylinder);
  }
}


