package com.stroganov;

import java.util.concurrent.Semaphore;

public class Parking {

    String name;
    int places;
    static int countPlaces;
    private final boolean[] PARKING_PLACES;
    //Устанавливаем флаг "справедливый", в таком случае метод
    //aсquire() будет раздавать разрешения в порядке очереди
    private final Semaphore semaphore;

    public Parking(String name, int places) {
        this.name = name;
        this.places = places;
        PARKING_PLACES = new boolean[places];
        semaphore = new Semaphore(places, true);
        countPlaces = places;
    }

    public int getPlaces() {
        return places;
    }

    public boolean[] getParkingPlaces() {
        return PARKING_PLACES;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public synchronized int parking(int gosNumber) {

        int parkingNumber = 0;

        if (countPlaces < 1) {
            System.out.println("Автомобиль " + gosNumber + " ожидает парковочное место");
            try {
                wait(5450);
            } catch (InterruptedException e) {
            }
        }
        for (int i = 0; i < places; i++)
            if (!PARKING_PLACES[i]) {      //Если место свободно
                PARKING_PLACES[i] = true;  //занимаем его
                parkingNumber = i;
                System.out.printf("Автомобиль №%d припарковался на месте %d.\n", gosNumber, i);
                countPlaces--;
                System.out.println("осталось парковочных мест" + countPlaces);
                return parkingNumber;
            }
        //      notify(); // ???
        return -1;
    }

    public synchronized void unParking(int parkingNumber) {
        PARKING_PLACES[parkingNumber] = false;

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countPlaces++;
        System.out.println("Парковочное место #: " + parkingNumber + " освободилось");
        notify();
    }

}



