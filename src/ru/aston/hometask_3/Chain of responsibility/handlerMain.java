public class handlerMain {
    public static void main(String[] args) {

        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        Handler handlerC = new ConcreteHandlerC();

        handlerA.setNextHandler(handlerB);
        handlerB.setNextHandler(handlerC);

        handlerA.handleRequest(5);
        handlerA.handleRequest(15);
        handlerA.handleRequest(25);
        handlerA.handleRequest(30);
    }
}