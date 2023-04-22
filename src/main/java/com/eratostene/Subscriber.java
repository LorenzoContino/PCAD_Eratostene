package com.eratostene;

public abstract class Subscriber {

    private Topic myProducer;
    private Topic myConsumer;

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

    public void UnSubscribeProdS(String producer_name) {
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
            return myConsumer.getTopicData().poll();
        }
        return null; 

    }

}
