package ru.netology;

public class Consumer implements Runnable {

    CarShowroom carShowroom;

    Consumer(CarShowroom carShowroom) {
        this.carShowroom = carShowroom;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " зашёл в автосалон");
        if (carShowroom.getProduct() < 1) {
            System.out.println("Автосалон: Запрошенного автомобиля нет в наличии");
        }
        carShowroom.carSale();
        System.out.println(Thread.currentThread().getName() + " уехал на новеньком автомобиле");
        Thread.currentThread().interrupt();
    }
}

