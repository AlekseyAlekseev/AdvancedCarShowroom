package ru.netology;

public class Producer implements Runnable {

    CarShowroom carShowroom;

    Producer(CarShowroom carShowroom) {
        this.carShowroom = carShowroom;
    }

    @Override
    public void run() {
        while (true) {
            if (carShowroom.getSales() == carShowroom.SALES_PLAN) {
                break;
            }
            carShowroom.createCar();
        }
    }
}

