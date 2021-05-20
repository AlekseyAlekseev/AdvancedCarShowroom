package ru.netology;

public class Manufacturer {

    private final CarShowroom carShowroom;

    private final int CREATE_AUTO = 5000;

    public Manufacturer (CarShowroom carShowroom) {
        this.carShowroom = carShowroom;
    }

    /**
     * Производим автомобиль
     */
    public synchronized void carRelease() {
        try {
            Thread.sleep(CREATE_AUTO);
            carShowroom.getCars().add(new Car());
            System.out.println("Производитель " + Thread.currentThread().getName() + " выпустил 1 авто");
            notify();
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
    }


    /**
     * Проверяем есть ли автомобиль в наличии
     */
    public synchronized Car carSale() {
        try {
            while (carShowroom.getCars().size() == 0) {
                System.out.println("Автосалон: Запрошенного автомобиля нет в наличии");
                wait();
            }
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
        return carShowroom.getCars().remove(0);
    }

}
