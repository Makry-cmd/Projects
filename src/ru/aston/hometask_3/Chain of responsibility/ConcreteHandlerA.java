public class ConcreteHandlerA extends Handler {
    @Override
    public void handleRequest(int request) {
        if (request < 10) {
            System.out.println("Handler A handled request " + request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}