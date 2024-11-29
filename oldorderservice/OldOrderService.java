package thread.executor.test;

import java.util.List;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class OldOrderService {

    public void order(String orderNo) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Callable<Boolean> inventoryWork = new InventoryWork(orderNo);
        Callable<Boolean> shippingWork = new ShippingWork(orderNo);
        Callable<Boolean> accountingWork = new AccountingWork(orderNo);

        List<Callable<Boolean>> works = List.of(inventoryWork, shippingWork, accountingWork);
        List<Future<Boolean>> results = executor.invokeAll(works);

        // Check results
        try {
            for (Future<Boolean> result : results) {
                if (!result.get()) {
                    log("Some tasks have failed.");
                    return;
                }
            }
        } finally {
            executor.close();
        }
        log("All order processing tasks were completed successfully.");
    }

    static class InventoryWork implements Callable<Boolean> {
        private final String orderNo;

        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public Boolean call() {
            log("Inventory update: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class ShippingWork implements Callable<Boolean> {
        private final String orderNo;
        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }
        public Boolean call() {
            log("Shipping system notification: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class AccountingWork implements Callable<Boolean> {
        private final String orderNo;
        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }
        public Boolean call() {
            log("Accounting system update: " + orderNo);
            sleep(1000);
            return true;
        }
    }
}
