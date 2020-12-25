package com.stroganov;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        Parking centralParking = new Parking("Центральная парковка",5);

        Random random = new Random();

        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i=0; i<10; i++) {
            service.submit(new Car(random.nextInt(1000), centralParking));

        }

        service.shutdown();


      //  for (int i = 1; i <= 7; i++) {
      //      new Thread(new Car(random.nextInt(1000),centralParking)).start();
      //      try {
      //          Thread.sleep(400);
      //      } catch (InterruptedException e) {
      //          e.printStackTrace();
      //      }
      //  }
    }
}
