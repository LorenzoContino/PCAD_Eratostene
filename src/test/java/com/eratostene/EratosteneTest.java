package com.eratostene;



import org.junit.Test;

public class EratosteneTest {
    

    //  !!!NOTA BENISSIMISSIMO!!!
    // Siccome Eratostene e' una classe statica i test vanno lanciati singolarmente e non tutti assieme
    // in modo che venga istanziato un nuovo Eratostene per ogni singolo test e non uno per tutti!!!
    // ALTRIMENTI ESPLODE TUTTO!!!

    Topic tConsumer, tProducer;

    @Test
    public void testCons() {
        assert(Eratostene.topicCount() == 0);
        tConsumer= Eratostene.SubscribeCons("testTopicCons");
        assert(Eratostene.topicCount() == 1);
        assert(tConsumer.getHasConsumer()); 
        Eratostene.UnSubscribeCons("testTopicCons");
        assert(!tConsumer.getHasConsumer());
        Eratostene.SubscribeCons("testTopicCons");
        assert(Eratostene.topicCount() == 1);
        assert(tConsumer.getHasConsumer()); 
    }

    @Test (expected = Exception.class)
    public void testSubscribeConsException(){
        Eratostene.SubscribeCons("testTopicCons");
        Eratostene.SubscribeCons("testTopicCons");
        assert(false);  
    }

    @Test
    public void testProd() {
        assert(Eratostene.topicCount() == 0);
        tProducer= Eratostene.SubscribeProd("testTopicProd");
        assert(Eratostene.topicCount() == 1);
        Eratostene.UnSubscribeProd("testTopicProd");
        tProducer= Eratostene.SubscribeProd("testTopicProd");
        assert(Eratostene.topicCount() == 1);
    }

    @Test (expected = Exception.class)
    public void testSubscribeProdException(){
        Eratostene.SubscribeProd("testTopicProd");   
        Eratostene.SubscribeProd("testTopicProd");   
    }

}