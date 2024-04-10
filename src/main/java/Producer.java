public class Producer implements Runnable{

    private final int itemNumbers;

    private final String name;

    private final Manager manager;

    public Producer(int itemNumbers, Manager manager, String name) {
        this.itemNumbers = itemNumbers;
        this.manager = manager;
        this.name = name;

        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < itemNumbers; i++) {
            try {
                Thread.sleep(1000);
                manager.getFullStorageSemaphore().acquire();
                manager.getAccessSemaphore().acquire();

                manager.getStorage().add("item " + i + " (" + name + ")");
                System.out.println("Added item " + i + " by " + name);

                manager.getAccessSemaphore().release();
                manager.getEmptySemaphore().release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}