public class Main {

    public static void main(String[] args) {
        int storageSize = 3;
        int producersNumber = 5;
        int consumersNumber = 5;
        int itemToProduce = 50;
        int itemToConsume = 50;
        int itemsPerProducer = itemToProduce / producersNumber;
        int itemsPerConsumer = itemToConsume / consumersNumber;

        Manager manager = new Manager(storageSize);
        for (int i = 0; i < producersNumber; i++) {
            int itemsToProduceForProducer = (i == producersNumber - 1)
                    ? (itemToProduce - (itemsPerProducer * (producersNumber - 1)))
                    : itemsPerProducer;
            new Producer(itemsToProduceForProducer, manager, "Producer #" + i);
        }

        for (int j = 0; j < consumersNumber; j++) {
            int itemsToConsumeForConsumer = (j == consumersNumber - 1)
                    ? (itemToConsume - (itemsPerConsumer * (consumersNumber - 1)))
                    : itemsPerConsumer;
            new Consumer(itemsToConsumeForConsumer, manager, "Consumer #" + j);
        }

    }
}