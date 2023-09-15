package ru.ad.lab1;

import java.util.Objects;

/**
 * Класс дизельного двигателя
 */
public class DiezEngine extends ConsEngine {

  /**
   * Количество тактов
   */
  private int numbCycle;
  /**
   * Место установки
   */
  private String instPl;

  /**
   * Границы для количества тактов
   */
  private final int MIN_CYCLE = 1, MAX_CYCLE = 64;

  /**
   * Конструктор ConsEngine без параметров
   */
  public DiezEngine() {
    this.numbCycle = -1;
    this.instPl = " - ";
  }

  /**
   * Конструктор ConsEngine с параметрами
   *
   * @param power        мощность двигателя в лошадинных силах
   * @param name         название двигателя
   * @param numbCylinder к-во цилиндров двигателя
   * @param system       название системы питания
   * @param numbCycle    количество тактов
   * @param instPl       место установки
   */
  public DiezEngine(int power, String name, int numbCylinder, String system, int numbCycle,
      String instPl) {
    super(power, name, numbCylinder, system);
    if (MIN_CYCLE < numbCycle  && numbCycle < MAX_CYCLE && system != null) {
      this.numbCycle = numbCycle;
      this.instPl = instPl;
    } else {
      this.numbCycle = -1;
      this.instPl = " - ";
    }
  }

  /**
   * Метод получения названия места установки
   *
   * @return private String fuelIn - название места установки
   */
  public String getInstPl() {
    return instPl;
  }

  /**
   * Метод получения количество тактов
   *
   * @return private int NumbCycle - количество тактов
   */
  public int getNumbCycle() {
    return numbCycle;
  }

  /**
   * Метод установки название места установки
   *
   * @param instPl название места установки
   */
  public void setInstPl(String instPl) {
    if (instPl != null) {
      this.instPl = instPl;
    }
  }

  /**
   * Метод установки количество тактов
   *
   * @param numbCycle количество тактов
   */
  public void setNumbCycle(int numbCycle) {
    if (MIN_CYCLE < numbCycle  && numbCycle < MAX_CYCLE) {
      this.numbCycle = numbCycle;
    }
  }

  /**
   * Метод строкового представления DiezEngine
   */
  @Override
  public String toString() {
    return String.format("""
            Двигатель:       %s
            Мощность:        %d
            Система питания: %s
            К-во цилиндров:  %d
            Место установки: %s
            К-во тактов:     %d""",
        super.getName(),
        super.getPower(),
        super.getSystem(),
        super.getNumbCylinder(),
        this.getInstPl(),
        this.getNumbCycle());
  }

  /**
   * Метод сравнения DiezEngine с Object
   *
   * @param obj сравниваемый Object
   * @return true при эквивалентности объектов, иначе false
   */
  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }
    DiezEngine diezEngine = (DiezEngine) obj;
    return this.getNumbCycle() == diezEngine.getNumbCycle() && this.getInstPl()
        .equals(diezEngine.getInstPl());
  }

  /**
   * Метод получения хэш-кода объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.instPl, this.numbCycle);
  }
}



