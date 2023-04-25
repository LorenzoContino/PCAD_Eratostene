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
        SubscribeCons("end-"+my_id.toString());
    }

    private List<Integer> getResult(){
        var returnList = new ArrayList<Integer>();
        for(Integer i = consume(); i!=null; i=consume()){
            returnList.add(i);
        }
        return returnList;
    }

    public List<Integer> doSieve(Integer number){
        var number_list = new ArrayList<Integer>();
        for (var i=2; i<number; i++){
            number_list.add(i);
        }
        number_list.add(-1000);
        Threadatostene.startThread(getMyProducer().getTopic_name(), getMyConsumer().getTopic_name());
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
            return null;
        }
        Threadatostene.stopThreads();
        return getResult(); // Avendo joinato ogni thread sono sicuro che nel topic di arrivo sono presenti tutti i numeri 
    }

}


