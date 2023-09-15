package ru.ad.lab1;


import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Класс для запуска программы
 */
public class Laba {

  public static void main(String[] args) {

    UI myUI = new UI();

    myUI.menu();
  }

}

/**
 * Интерфейс, реализующий нумерацию вариантов выбора для switch()
 */
interface menuEnum {

  int ADD_ENGINE = 1,
      DELETE_ENGINE = 2,
      PRINT_ENGINE = 3,
      COMPARE_ENGINE = 4,
      EXIT = 5;

  int ADD_JET_ENGINE = 2,
      ADD_DIEZ_ENGINE = 3,
      ADD_CONS_ENGINE = 4;

}

/**
 * Основной класс интерфейса
 */
class UI implements menuEnum {

  /**
   * Поток вывода, поддерживающий русские символы
   */
  private final PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

  /**
   * Экземпляр класса содержащего методы ввода разных типов
   */
  private final Inputer inp = new Inputer();

  /**
   * Основной список двигателей
   */
  private final ArrayList<Engine> engine = new ArrayList<>();

  /**
   * Метод основного меню
   */
  public void menu() {

    int choice;

    do {
      this.out.println("""
          1. Добавить двигатель
          2. Удалить двигатель
          3. Вывести все двигатели
          4. Сравнить два двигателя
          5. Выход
          """);

      choice = inp.getInt();
      switch (choice) {
        case ADD_ENGINE -> engineAdder();
        case DELETE_ENGINE -> deleteEngines();
        case PRINT_ENGINE -> printEngines();
        case COMPARE_ENGINE -> compareEngine();
        default -> {
          if (choice != EXIT) {
            this.out.println("Некорректный ввод");
          }
        }
      }

    } while (choice != EXIT);

  }


  /**
   * Метод меню добавления человека
   */
  private void engineAdder() {
    int choice;

    do {
      this.out.println("""
          1. Добавить двигатель
          2. Добавить реактивный двигатель
          3. Добавить дизельный двигатель
          4. Добавить двигатель внутреннего сгорания
          5. Выйти в главное меню
          """);

      choice = inp.getInt();
      switch (choice) {
        case ADD_ENGINE -> adderHandler(1);
        case ADD_JET_ENGINE -> adderHandler(2);
        case ADD_DIEZ_ENGINE -> adderHandler(3);
        case ADD_CONS_ENGINE -> adderHandler(4);
        default -> {
          if (choice != EXIT) {
            this.out.println("Некорректный ввод");
          }
        }
      }

    } while (choice != EXIT);
  }

  /**
   * Метод добавления двигателя выбранного типа
   *
   * @param engine_type выбранный тип двигателя
   */
  private void adderHandler(int engine_type) {
    String data_answer;

    while (true) {
      out.println("Добавлять с входными данными (y/n)?");
      data_answer = inp.getString();
      if (data_answer == null) {
        continue;
      }
      if (data_answer.equals("y") || data_answer.equals("n")) {
        break;
      }
    }
    String name = null;
    int power = -1;
    if (Objects.equals(data_answer, "y")) {
      this.out.println("Введите название двигателя");
      name = this.inp.getString();
      this.out.println("Введите мощность двигателя(1 - 1000000)");
      power = this.inp.getInt();

    }

    switch (engine_type) {
      case ADD_ENGINE -> addEngine(name, power);
      case ADD_JET_ENGINE -> addJetEngine(name, power);
      case ADD_DIEZ_ENGINE -> addConsEngine(name, power, true);
      case ADD_CONS_ENGINE -> addConsEngine(name, power, false);
      default -> this.out.println("???");
    }
  }

  /**
   * Метод добавления двигателя
   *
   * @param name имя добавляемого двигателя
   * @param power мощность добавляемого двигателя
   */
  private void addEngine(String name, int power) {
    this.engine.add(new Engine(power, name));
  }

  /**
   * Метод добавления реактивного двигателя
   *
   * @param name имя исходного двигателя
   * @param power мощность добавляемого двигателя
   */
  private void addJetEngine(String name, int power) {
    if (power == -1 && name == null) {
      this.engine.add(new JetEngine());
      return;
    }
    int thrust;
    String type;
    this.out.println("Введите тип двигателя");
    type = this.inp.getString();
    this.out.println("Введите тягу двигателя (1-100)");
    thrust = this.inp.getInt();
    this.engine.add(new JetEngine(power, name, thrust, type));
  }

  /**
   * Метод добавления двс
   *
   * @param name имя исходного двигателя
   * @param power мощность добавляемого двигателя
   * @param is_diez создается дизельный двигатель?
   */
  private void addConsEngine(String name, int power, boolean is_diez) {
    if (power == -1 && name == null) {
      if (is_diez) {
        addDiezEng(null, -1, null, -1);
      } else {
        this.engine.add(new ConsEngine());
      }
      return;
    }

    String system;
    int numbCylinder;

    if (is_diez) {
      system = "Дизельная";
    } else {
      this.out.println("Введите систему двигателя");
      system = this.inp.getString();
    }
    this.out.println("Введите количество цилиндров (1 - 64)");
    numbCylinder = this.inp.getInt();
    if (is_diez) {
      addDiezEng(name, power, system, numbCylinder);
    } else {
      this.engine.add(new ConsEngine(power, name, numbCylinder, system));
    }

  }

  /**
   * Метод добавления дизельного двигателя
   *
   * @param name имя исходного двигателя
   * @param power мощность добавляемого двигателя
   * @param system система двигателя
   * @param numbCylinder кол-во цилиндров двигателя
   */
  private void addDiezEng(String name, int power, String system, int numbCylinder) {
    if (name == null && power == -1 && system == null && numbCylinder == -1) {
      this.engine.add(new DiezEngine());
      return;
    }

    String instPL;
    int numbCycle;
    this.out.println("Введите систему дизельного двигателя");
    instPL = this.inp.getString();
    this.out.println("Введите количество тактов дизельного двигателя (1 - 64)");
    numbCycle = this.inp.getInt();
    this.engine.add(new DiezEngine(power, name, numbCylinder, system, numbCycle, instPL));
  }

  /**
   * Метод удаления двигателя из списка
   */
  private void deleteEngines() {
    out.println("Введите индекс двигателя в списке:");
    int index = inp.getInt();
    if (index < 1 || index > engine.size()) {
      out.println("Некорректный индекс");
    } else {
      this.out.println(engine.get(index - 1).getName() + " удален из списка");
      engine.remove(index - 1);
    }
  }

  /**
   * Метод вывода всех двигателей в списке
   */
  private void printEngines() {
    if (engine.isEmpty()) {
      out.println("Ни одного двигателя не добавлено");
      return;
    }

    int num = 1;
    for (Engine engine : engine) {
      out.printf("№%d\n", num++);
      out.println(engine);
      out.println("************************");
    }
  }

  /**
   * Метод сравнения двух двигателей из списка
   */
  private void compareEngine() {
    int index1 = 1, index2 = 1;
    boolean isIndex1 = false, isIndex2 = false;

    if (engine.size() < 2){
      out.println("Элементов в списке меньше двух");
      return;
    }

    while (!(isIndex1 & isIndex2)) {
      if (!isIndex1) {
        this.out.println("Введите индекс первого двигателя");
        index1 = inp.getInt();
        if (index1 >= 1 && index1 <= engine.size()) {
          isIndex1 = true;
        } else {
          out.println("Некорректный индекс");
        }
      }

      if (!isIndex2) {
        this.out.println("Введите индекс второго двигателя");
        index2 = inp.getInt();
        if (index2 >= 1 && index2 <= engine.size()) {
          isIndex2 = true;
        } else {
          out.println("Некорректный индекс");
        }
      }
    }
    if (engine.get(index1 - 1).equals(engine.get(index2 - 1))) {
      out.printf("Двигатели под номером %d и %d индентичны друг другу\n", index1, index2);
    } else {
      out.printf("Двигатели под номером %d и %d отличаются друг от друга\n", index1,
          index2);
    }
  }
}
