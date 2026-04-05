public class Pizza {
    private final String size;
    private final String dough;
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean mushrooms;


    private Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.dough = builder.dough;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
        this.mushrooms = builder.mushrooms;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size='" + size + "'" +
        ", dough='" + dough + "'" +
        ", cheese=" + cheese +
                ", pepperoni=" + pepperoni +
                ", mushrooms=" + mushrooms +
                '}';
    }


    public static class PizzaBuilder {
        private final String size;
        private String dough;
        private boolean cheese;
        private boolean pepperoni;
        private boolean mushrooms;

        public PizzaBuilder(String size) {
            this.size = size;
        }

        public PizzaBuilder dough(String dough) {
            this.dough = dough;
            return this;
        }

        public PizzaBuilder cheese(boolean cheese) {
            this.cheese = cheese;
            return this;
        }

        public PizzaBuilder pepperoni(boolean pepperoni) {
            this.pepperoni = pepperoni;
            return this;
        }

        public PizzaBuilder mushrooms(boolean mushrooms) {
            this.mushrooms = mushrooms;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}