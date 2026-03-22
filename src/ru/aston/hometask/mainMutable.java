// В коде использовал глубокое копирование clone(), для возможности работы с полем-класом в Immutable
public final class mainMutable{
    public static void main(String[] args) {
    //Инициализируем и проверяем изменяемый класс:
        Mutable mutable = new Mutable(32, "Aston");

        Immutable immutable = new Immutable(mutable);

        System.out.println("immutable.Mutable.age = "+immutable.getMutable().getAge()); // 32
        System.out.println("immutable.Mutable.name = "+immutable.getMutable().getName()); //Aston
        //Присваиваем другие значения класу mutable для проверки изменений в изменяемом и неизменяемом класе.
        mutable.setAge(9);
        mutable.setName("Nesty");
        System.out.println("\nmutable.Age = "+mutable.getAge());// 9
        System.out.println("mutable.name  = "+mutable.getName()+"\n"); // Nesty
        System.out.println("immutable.Mutable.Age = "+immutable.getMutable().getAge()); // 32
        System.out.println("immutable.Mutable.name = "+immutable.getMutable().getName()); // Aston
        //Из отображаемых значений понятно,что immutable не изменился, и возвращаемая копия mutable создана успешно.
    }
}
