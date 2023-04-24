package com.eratostene_sieve;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.eratostene.*;
import com.eratostenethread.*;

public class Sieve extends Subscriber{
    
    private static final AtomicInteger id = new AtomicInteger(0);
    private final Integer my_id;

    public Sieve(){
        my_id = id.incrementAndGet();
        SubscribeProd("start-"+my_id.toString());
    }    

    public List<Integer> doSieve(Integer number){
        var number_list = new ArrayList<Integer>();
        for (var i=2; i<number; i++){
            number_list.add(i);
        }
        number_list.add(-1000);
        Threadatostene.startThread(getMyProducer().getTopic_name());
        for (var num : number_list) {
            produce(num);
        }
        try {
            synchronized(Threadatostene.sync_obj){
                Threadatostene.sync_obj.wait();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Threadatostene.stopThreads();
        }
        Threadatostene.stopThreads();
        return Threadatostene.getResult();
    }

}


