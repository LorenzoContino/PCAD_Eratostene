package com.eratostenethread;

import java.util.LinkedList;
import java.util.List;

public class Threadatostene {

    private static List<Thread> threadList;
    private static List<SieveWorker> sieveList;
    private static List<Integer> denumeratorList;
    static{
        threadList = new LinkedList<>();
        sieveList = new LinkedList<>();
        denumeratorList = new LinkedList<>();
    }


    public static void startThread( String topicName ){
        var new_sieve = new SieveWorker(topicName);
        threadList.add(new Thread(new_sieve));
        sieveList.add(new_sieve);
        new_sieve.run();
    }

    public static void stopThreads(){
        for (SieveWorker sieve : sieveList) {
            denumeratorList.add(sieve.my_denominator);
            sieve.running.set(false);
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (Exception e) {
                // Se non riusciamo a joinare un thread facciamo finta di nulla tanto verra' distrutto dallo scheduler dell'OS comunque.
            }
        }
    }

}
