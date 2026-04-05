public class Adapter implements Target {
    private OldSystem oldSystem;

    public Adapter(OldSystem oldSystem) {
        this.oldSystem = oldSystem;
    }

    @Override
    public void request() {
        oldSystem.specificRequest();
    }
}
