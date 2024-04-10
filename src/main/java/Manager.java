import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Manager {

    private final Semaphore access;
    private final Semaphore fullStorage;
    private final Semaphore empty;

    private ArrayList<String> storage = new ArrayList<>();

    public Manager(int storageSize) {
        access = new Semaphore(1);
        fullStorage = new Semaphore(storageSize);
        empty = new Semaphore(0);
    }

    public Semaphore getAccessSemaphore() {
        return access;
    }

    public Semaphore getFullStorageSemaphore() {
        return fullStorage;
    }

    public Semaphore getEmptySemaphore() {
        return empty;
    }

    public ArrayList<String> getStorage() {
        return storage;
    }

    public void setStorage(ArrayList<String> storage) {
        this.storage = storage;
    }
}