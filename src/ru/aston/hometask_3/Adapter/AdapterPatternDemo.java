public class AdapterPatternDemo {
    public static void main(String[] args) {

        OldSystem oldSystem = new OldSystem();

        Target adapter = new Adapter(oldSystem);

        NewSystem newSystem = new NewSystem(adapter);

        newSystem.performRequest();
    }
}
