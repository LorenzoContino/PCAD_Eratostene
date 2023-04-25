package com.eratostenethread;

import java.util.concurrent.atomic.AtomicBoolean;

import com.eratostene.*;

public class SieveWorker extends Subscriber implements Runnable {

    private AtomicBoolean running = new AtomicBoolean(false);
    private Integer my_denominator = null;
    private String topicEndpointName;
    private String topicProduceName;

    public SieveWorker(String topicName, String topicEndpointName) {
        this.SubscribeCons(topicName);
        this.topicEndpointName = topicEndpointName;
    }

    private boolean doAritmetics(Integer number) {
        return number % my_denominator == 0;
    }

    @Override
    public synchronized void run() {
        running.set(true);
        while (running.get()) {
            Integer number = consume();
          if (number != null) {
                if (my_denominator == null) {
                    if (number == -1000) {
                        synchronized(Threadatostene.sync_obj){
                            Threadatostene.sync_obj.notifyAll();
                        }
                        running.set(false);
                        continue;
                    }
                    my_denominator = number;
                    continue;
                }
                if (number != -1000) {
                    if (doAritmetics(number)) {
                        continue;
                    }
                }
                if (!isProducer()) {
                    topicProduceName = number.toString();
                    Threadatostene.startThread(topicProduceName, topicEndpointName);
                    SubscribeProd(topicProduceName);
                }
                produce(number);
                if(number == -1000){
                    UnSubscribeProd(topicProduceName);
                    for(;;){
                        try{
                            SubscribeProd(topicEndpointName);
                            break;
                        } catch (Exception e){
                            // Se qualcuno sta gia' producendo sul topic finale (cosa improbabile ma possibile)
                            // rimango in buisy waiting finche quello non ha finito
                        }
                    }
                    produce(my_denominator);
                    UnSubscribeProd(topicEndpointName);
                    running.set(false);
                }
            } else { // Se la pipe e' vuota aspetto un attimo per non fondere la CPU
                try {
                    Thread.sleep(2);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        if(getMyProducer() != null){
            UnSubscribeProd(getMyProducer().getTopic_name());
        }
        if(getMyConsumer() != null){
            UnSubscribeCons(getMyConsumer().getTopic_name());
        }
    }

}
