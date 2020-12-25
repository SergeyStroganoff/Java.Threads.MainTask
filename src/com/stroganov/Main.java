package com.stroganov;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        Parking centralParking = new Parking("Центральная парковка", 5);

        Random random = new Random();

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            service.submit(new Car(random.nextInt(1000), centralParking));
        }
        service.shutdown();

    }
}
