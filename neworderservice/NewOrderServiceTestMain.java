package neworderservice;

import java.util.concurrent.ExecutionException;

public class NewOrderServiceTestMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String orderNo = "Order#1234";
        NewOrderService orderService = new NewOrderService();
        orderService.order(orderNo);
    }
}
