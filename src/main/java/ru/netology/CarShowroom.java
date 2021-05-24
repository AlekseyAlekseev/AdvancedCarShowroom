package ru.netology;

import java.util.ArrayList;
import java.util.List;

public class CarShowroom {

    public CarShowroom() {
    }


    Manufacturer manufacturer = new Manufacturer(this);

    private final int SELL_TIME = 1000;

    List<Car> cars = new ArrayList<>();


    /**
     * Покупка автомобиля и проверка его наличия
     */
    public void carSale() {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            try {
                manufacturer.carSale();
                Thread.sleep(SELL_TIME);
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком автомобиле ");
        }


    /**
     * Запрос на изготовление автомобиля
     */
    public void gettingCar() {
            try {
                manufacturer.carRelease();
            } catch (Exception err) {
                err.printStackTrace();
            }
        }

    public List<Car> getCars() {
        return cars;
    }


}
