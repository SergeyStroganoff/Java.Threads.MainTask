package com.stroganov;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parking {

    String name;
    int places;
    private final boolean[] PARKING_PLACES;
    //Устанавливаем флаг "справедливый", в таком случае метод
    //aсquire() будет раздавать разрешения в порядке очереди
    private final Semaphore semaphore;

    public Parking(String name, int places) {
        this.name = name;
        this.places = places;
        PARKING_PLACES = new boolean[places];
        semaphore = new Semaphore(places, true);
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

    public    int parking(int gosNumber) {
     //   Lock lock = new ReentrantLock();
        int parkingNumber=0;

                for (int i = 0; i < places; i++)
                    if (!PARKING_PLACES[i]) {      //Если место свободно
                        PARKING_PLACES[i] = true;  //занимаем его
                        parkingNumber = i;
                        System.out.printf("Автомобиль №%d припарковался на месте %d.\n", gosNumber, i);
                        return parkingNumber;
                    }
               return -1;
            }

    public synchronized void unParking(int parkingNumber){
        PARKING_PLACES[parkingNumber]=false;
    }

}



