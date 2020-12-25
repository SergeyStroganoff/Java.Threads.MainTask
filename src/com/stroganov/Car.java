package com.stroganov;

public class Car implements Runnable {

    int gosNumber;
    int parkingNumber;
    Parking parking;


    public Car(int gosNumber, Parking parking) {
        this.gosNumber = gosNumber;
        this.parking = parking;
    }

    @Override
    public void run() {


        System.out.printf("Автомобиль №%d подъехал к парковке.\n", gosNumber);
        try {

             parking.getSemaphore().acquire();
            //Ищем свободное место и паркуемся
           parkingNumber = parking.parking(gosNumber); // паркуем авто
            if (parkingNumber==-1){
                System.out.println("Автомоблиь " + gosNumber + " не смог припарковаться");
            }
            Thread.sleep(5000);       //Stay at the parking
            parking.unParking(parkingNumber);
            //release(), напротив, освобождает ресурс
            parking.getSemaphore().release();
            System.out.printf("Автомобиль №%d покинул парковку.\n", gosNumber);
        } catch (InterruptedException e) {
        }
    }
}

/*
   try {
         if(lock.tryLock(1, TimeUnit.SECONDS)) {
         } catch (InterruptedException e) {
         e.printStackTrace();
         }
         return parkingNumber;
         }

    */