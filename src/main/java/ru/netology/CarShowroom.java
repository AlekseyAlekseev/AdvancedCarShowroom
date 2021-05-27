package ru.netology;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarShowroom {
    // Машин в наличие
    private int product=0;
    // План продаж
    public final int SALES_PLAN = 10;
    // Кол-во продаж
    private int sales = 0;
    // Время оформления автомобиля
    private final int carTimeRegistration = 500;
    // Время приёмки нового автомобиля
    private final int timePickUpCar = 1000;
    // Блокировка критической секции с включённой поддержкой честности
    private final Lock lock = new ReentrantLock(true);
    // Состояние блокировки
    private final Condition condition = lock.newCondition();

    public int getSales() {
        return sales;
    }

    public int getProduct() {
        return product;
    }
    /**
     * Покупка автомобиля
     */
    public void carSale() {
        try {
            lock.lock();
            while (product == 0) {
                condition.await();
            }
            Thread.sleep(carTimeRegistration);
            product--;
            sales++;
            condition.signal();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }

    }
    /**
     * Добавление нового автомобился в салон
     */
    public void createCar() {
        try {
            lock.lock();
            while (product > 0) {
                condition.await();
            }
            Thread.sleep(timePickUpCar);
            product++;
            System.out.println("Производитель выпустил 1 авто");
            condition.signal();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }

    }
}