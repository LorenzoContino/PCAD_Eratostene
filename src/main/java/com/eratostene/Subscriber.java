package com.eratostene;


public abstract class Subscriber {
    
    private Topic myProducer;
    private Topic myConsumer;


    public void SubscribeProd(String producer_name){};
    public void SubscribeCons(String consumer_name){};
    public void UnSubscribeCons(String consumer_name){};
    public void UnSubscribeProdS(String consumer_name){};

    public void produce(){};
    public void consume(){};
}
