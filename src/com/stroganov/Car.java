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

            parkingNumber = parking.parking(gosNumber); // паркуем авто
            if (parkingNumber == -1) {
                System.out.println("Автомоблиь " + gosNumber + " не дождался свободного места и едет на другую парковку");
                return;
            }
            Thread.sleep(5000);       //Stay at the parking
            parking.unParking(parkingNumber);
        } catch (InterruptedException e) {
        }
    }
}
