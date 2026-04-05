public class ConcreteHandlerC extends Handler {
    @Override
    public void handleRequest(int request) {
        if (request >= 20) {
            System.out.println("Handler C handled request " + request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}