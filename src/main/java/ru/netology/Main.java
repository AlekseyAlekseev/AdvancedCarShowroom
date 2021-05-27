package ru.netology;

public class Main {
    // Время нового визита покупателя
    private final static int buyerVisitTime = 7000;
    // Номер нового потока
    private static int nameCounter = 3;

    public static void main(String[] args) {
        CarShowroom carShowroom = new CarShowroom();

        new Thread(null, new Consumer(carShowroom), "Покупатель 1").start();
        getThreadSleep();
        new Thread(null, new Consumer(carShowroom), "Покупатель 2").start();
        getThreadSleep();
        new Thread(null, new Consumer(carShowroom), "Покупатель 3").start();
        getThreadSleep();
        Thread producer = new Thread(null, new Producer(carShowroom), "BMW");
        producer.setDaemon(true);
        producer.start();

        while (true) {
            if (carShowroom.getSales() < carShowroom.SALES_PLAN) {
                try {
                    Thread.sleep(buyerVisitTime);
                    new Thread(null, new Consumer(carShowroom), "Покупатель " + ++nameCounter).start();
                    getThreadSleep();
                    new Thread(null, new Consumer(carShowroom), "Покупатель " + ++nameCounter).start();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            } else {
                break;
            }
        }
    }
    /**
     * Обыкновенный таймаут потока
     * Создан для наглядности соблюдения порядка
     * реентерабельным локом в режиме честности.
     */
    private static void getThreadSleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
