public class BuilderPatternDemo {
    public static void main(String[] args) {
        Pizza pizza1 = new Pizza.PizzaBuilder("Large")
                .dough("Thin")
                .cheese(true)
                .pepperoni(true)
                .mushrooms(false)
                .build();

        Pizza pizza2 = new Pizza.PizzaBuilder("Medium")
                .dough("Thick")
                .cheese(true)
                .mushrooms(true)
                .build();

        System.out.println(pizza1);
        System.out.println(pizza2);
    }
}
