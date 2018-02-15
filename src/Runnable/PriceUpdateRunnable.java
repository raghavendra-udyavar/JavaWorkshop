package Runnable;

import java.util.*;

public class PriceUpdateRunnable implements Runnable {

    String name;
    int time;
    Random rand = new Random();

    public  PriceUpdateRunnable(String n){
        name = n;
        time = rand.nextInt(999);
    }

    @Override
    public void run() {
        try{
            System.out.printf("%s us sleeping for %d \n", name, time);
            Thread.sleep(time);
            System.out.printf("%s is done \n", name);
        }catch (Exception ex){

        }
    }
}
