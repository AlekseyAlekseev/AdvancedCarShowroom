package ru.netology;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Manufacturer {

    ReentrantLock locker = new ReentrantLock();
    Condition condition = locker.newCondition();

    private final CarShowroom carShowroom;

    private final int CREATE_AUTO = 5000;

    public Manufacturer(CarShowroom carShowroom) {
        this.carShowroom = carShowroom;
    }

    /**
     * Производим автомобиль
     */
    public void carRelease() {
        try {
            while (carShowroom.getCars().size() > 0) {
                condition.await();
            }
            locker.lock();
            Thread.sleep(CREATE_AUTO);
            carShowroom.getCars().add(new Car());
            System.out.println("Производитель " + Thread.currentThread().getName() + " выпустил 1 авто");
            condition.signal();
        } catch (InterruptedException err) {
            err.printStackTrace();
        } finally {
            locker.unlock();
        }
    }


    /**
     * Проверяем есть ли автомобиль в наличии
     */
    public Car carSale() {
        try {
            locker.lock();
            while (carShowroom.getCars().size() == 0) {
                System.out.println("Автосалон: Запрошенного автомобиля для " +
                        Thread.currentThread().getName() + " нет в наличии");
                condition.await();
            }
        } catch (InterruptedException err) {
            err.printStackTrace();
        } finally {
            locker.unlock();
        }
        return carShowroom.getCars().remove(0);
    }

}
