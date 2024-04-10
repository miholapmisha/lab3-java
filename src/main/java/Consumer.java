public class Consumer implements Runnable {

    private final int itemNumbers;

    private final Manager manager;

    private final String name;

    public Consumer(int itemNumbers, Manager manager, String name) {
        this.itemNumbers = itemNumbers;
        this.manager = manager;
        this.name = name;

        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < itemNumbers; i++) {
            String item;
            try {
                Thread.sleep(1000);
                manager.getEmptySemaphore().acquire();
                manager.getAccessSemaphore().acquire();

                item = manager.getStorage().get(0);
                manager.getStorage().remove(0);
                System.out.println("Took " + item + " by " + name);

                manager.getAccessSemaphore().release();
                manager.getFullStorageSemaphore().release();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}