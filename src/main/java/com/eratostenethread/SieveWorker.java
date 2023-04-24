package com.eratostenethread;

import java.util.concurrent.atomic.AtomicBoolean;

import com.eratostene.*;

public class SieveWorker extends Subscriber implements Runnable {

    protected AtomicBoolean running = new AtomicBoolean(false);
    protected Integer my_denominator = null;

    public SieveWorker(String topicName) {
        this.SubscribeCons(topicName);
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
                    String topic_name = number.toString();
                    Threadatostene.startThread(topic_name);
                    SubscribeProd(topic_name);
                }
                produce(number);
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
