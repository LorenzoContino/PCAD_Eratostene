package com.eratostenethread;

import java.util.LinkedList;
import java.util.List;

public class Threadatostene {

    private static List<Thread> threadList;
    public static Object sync_obj;
    static{
        threadList = new LinkedList<>();
        sync_obj = new Object();
    }


    public static void startThread( String topicName, String topicEndpointName){
        var new_sieve = new SieveWorker(topicName, topicEndpointName);
        var my_thread = new Thread(new_sieve);
        threadList.add(my_thread);
        my_thread.start();
    }

    public static void stopThreads(){
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                // Se non riusciamo a joinare un thread facciamo finta di nulla tanto verra' distrutto dallo dall'OS comunque quando muore JVM.
            }
        }
        threadList = new LinkedList<>();
    }
}
