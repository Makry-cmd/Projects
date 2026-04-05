public class ConcreteHandlerB extends Handler {
    @Override
    public void handleRequest(int request) {
        if (request >= 10 && request < 20) {
            System.out.println("Handler B handled request " + request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}