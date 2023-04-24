package com.eratostenethread;

import java.util.LinkedList;
import java.util.List;

public class Threadatostene {

    private static List<Thread> threadList;
    private static List<SieveWorker> sieveList;
    private static List<Integer> denumeratorList;
    public static Object sync_obj;
    static{
        threadList = new LinkedList<>();
        sieveList = new LinkedList<>();
        denumeratorList = new LinkedList<>();
        sync_obj = new Object();
    }


    public static void startThread( String topicName ){
        var new_sieve = new SieveWorker(topicName);
        var my_thread = new Thread(new_sieve);
        threadList.add(my_thread);
        my_thread.start();
        sieveList.add(new_sieve);
    }

    public static void stopThreads(){
        denumeratorList = new LinkedList<>();
        for (SieveWorker sieve : sieveList) {
            if(sieve.my_denominator!=null){
                denumeratorList.add(sieve.my_denominator);
            }
            sieve.running.set(false);
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                // Se non riusciamo a joinare un thread facciamo finta di nulla tanto verra' distrutto dallo scheduler dell'OS comunque.
            }
        }
        // Cleaning up old references
        sieveList = new LinkedList<>();
        threadList = new LinkedList<>();
    }

    public static List<Integer> getResult() {
        return denumeratorList;
    }

}
