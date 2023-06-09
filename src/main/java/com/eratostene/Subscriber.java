package com.eratostene;

public abstract class Subscriber {

    private Topic myProducer;
    private Topic myConsumer;

    public Topic getMyProducer() {
        return myProducer;
    }

    public Topic getMyConsumer() {
        return myConsumer;
    }

    public void SubscribeProd(String producer_name) {
        this.myProducer = Eratostene.SubscribeProd(producer_name);
    }

    public void SubscribeCons(String consumer_name) {
        this.myConsumer = Eratostene.SubscribeCons(consumer_name);
    }

    public void UnSubscribeCons(String consumer_name) {
        Eratostene.UnSubscribeCons(consumer_name);
        this.myConsumer = null;
    }

    public void UnSubscribeProd(String producer_name) {
        Eratostene.UnSubscribeProd(producer_name);
        this.myProducer = null;
    }

    public void produce(Integer number) {
        if (myProducer != null) {
            myProducer.getTopicData().add(number);
        }
    }

    public Integer consume() {
        if (myConsumer != null) {
            if (myConsumer.hasData()){
                return myConsumer.getTopicData().poll();
            }
        }
        return null; 
    }

    public boolean isProducer(){
        return myProducer!=null;
    }

    public boolean isConsumer(){
        return myConsumer!=null;
    }
}
