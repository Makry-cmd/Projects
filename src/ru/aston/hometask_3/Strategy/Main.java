// Main.java

public class Main {
    public static void main(String[] args) {
        // Создаем контекст с первой стратегией
        Context context = new Context(new ConcreteStrategyA());
        context.executeStrategy(); // Вывод: Executing strategy A

        // Меняем стратегию на вторую
        context.setStrategy(new ConcreteStrategyB());
        context.executeStrategy(); // Вывод: Executing strategy B
    }
}
