package com.eratostenethread;

import java.util.concurrent.atomic.AtomicBoolean;

import com.eratostene.*;

public class SieveWorker extends Subscriber implements Runnable{

    protected AtomicBoolean running = new AtomicBoolean(false);
    protected Integer my_denominator = null;


    public SieveWorker(String topicName){
        this.SubscribeCons(topicName);
    }

    private boolean doAritmetics(Integer number){
        return number%my_denominator == 0;
    }

    @Override
    public void run() {
        running.set(true);
        while (running.get()) {
            Integer number = consume();
            if (number != null) {
                if(my_denominator==null){
                    if(number == -1000){
                        Threadatostene.stopThreads();
                    }
                    my_denominator = number;
                    continue;
                }
                if(doAritmetics(number)){
                    continue;
                }
                if(!isProducer()){
                    String topic_name = number.toString();
                    Threadatostene.startThread(topic_name);
                    SubscribeProd(topic_name);
                }
                produce(number);
            } else { // Se la pipe e' vuota aspetto un attimo per non fondere la CPU
                try{
                    Thread.sleep(20);
                } catch(Exception e){

                }
            }
        }
    }
 
}
