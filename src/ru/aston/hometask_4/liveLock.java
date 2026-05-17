public class liveLock {
    public static void main(String[] args) {
        final Person person1 = new Person("Человек 1");
        final Person person2 = new Person("Человек 2");

        Thread thread1 = new Thread(() -> person1.giveWay(person2));
        Thread thread2 = new Thread(() -> person2.giveWay(person1));

        thread1.start();
        thread2.start();
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void giveWay(Person other) {
        while (true) {
            System.out.println(name + " видит " + other.name + " и уступает дорогу.");

            if (Math.random() > 0.5) {
                System.out.println(name + " проходит.");
                break;
            }

            System.out.println(name + " все еще уступает.");
        }
    }
}