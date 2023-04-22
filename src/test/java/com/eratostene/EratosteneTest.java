package com.eratostene;



import org.junit.Test;

public class EratosteneTest {
    
    Topic tConsumer, tProducer;

    @Test
    public void testSubscribeCons() {
        tConsumer= Eratostene.SubscribeCons("testTopicCons");
        assert(tConsumer.getHasConsumer());
        
    }

    @Test (expected = Exception.class)
    public void testSubscribeConsException(){
        Eratostene.SubscribeCons("testTopicCons");   
    }



    @Test
    public void testUnSubscribeCons() {
        Eratostene.UnSubscribeCons("testTopicCons");
        assert(!tConsumer.getHasConsumer());

    }

    @Test
    public void testSubscribeProd() {
        tProducer= Eratostene.SubscribeProd("testTopicProd");
        assert(tProducer.getHasConsumer());
    }

    @Test (expected = Exception.class)
    public void testSubscribeProdException(){
        Eratostene.SubscribeProd("testTopicProd");   
    }


    @Test
    public void testUnSubscribeProd() {
        Eratostene.UnSubscribeProd("testTopicProd");
        assert(!tProducer.getHasProducer());

    }
}
