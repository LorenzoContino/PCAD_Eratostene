package com.eratostene_sieve;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.eratostene.*;
import com.eratostenethread.*;

public class Sieve extends Subscriber{
    
    private static final AtomicInteger id = new AtomicInteger(0);
    private final Integer my_id;
    private Topic topicStart;
    private Topic topicEnd;

    public Sieve(){
        my_id = id.incrementAndGet();
        SubscribeProd("start-"+my_id.toString());
        SubscribeCons("end-"+my_id.toString());
    }    

    public ArrayList<Integer> doSieve(ArrayList<Integer> list_to_sieve){
        // Facciamo i controlli sulla lista
        list_to_sieve.add(-1000);
        for (var number: list_to_sieve) {
            produce(number);
        }
    }

}
