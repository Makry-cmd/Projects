public class NewSystem {
    private Target target;

    public NewSystem(Target target) {
        this.target = target;
    }

    public void performRequest() {
        target.request();
    }
}
