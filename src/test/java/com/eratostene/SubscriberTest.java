package com.eratostene;

import org.junit.Test;

public class SubscriberTest {

    @Test
    public void testSubscribeCons() {
        SubscriberStub sub = new SubscriberStub();
        sub.SubscribeCons("testTopic");
        assert(sub.isConsumer());
    }

    @Test
    public void testSubscribeProd() {
        SubscriberStub sub = new SubscriberStub();
        sub.SubscribeProd("testTopic");
        assert(sub.isProducer());
    }

    @Test
    public void testUnSubscribeCons() {
        SubscriberStub sub = new SubscriberStub();
        sub.SubscribeCons("testTopic");
        assert(sub.isConsumer());
        sub.UnSubscribeCons("testTopic");
        assert(!sub.isConsumer());
    }

    @Test
    public void testUnSubscribeProd() {
        SubscriberStub sub = new SubscriberStub();
        sub.SubscribeProd("testTopic");
        assert(sub.isProducer());
        sub.UnSubscribeProd("testTopic");
        assert(!sub.isProducer());
    }

    @Test
    public void testStream() {
        SubscriberStub sub_first = new SubscriberStub();
        SubscriberStub sub_second = new SubscriberStub();
        sub_first.SubscribeProd("testTopic");
        sub_second.SubscribeCons("testTopic");
        sub_first.produce(10);
        assert(sub_second.consume() == 10);
        sub_first.UnSubscribeProd("testTopic");
        sub_second.UnSubscribeCons("testTopic");
        sub_first.SubscribeProd("testTopic2");
        sub_second.SubscribeCons("testTopic2");
        assert(Eratostene.topicCount() == 2);
        sub_first.produce(10);
        sub_first.produce(20);
        sub_first.produce(30);
        sub_first.produce(40);
        sub_first.produce(50);
        assert(sub_second.consume() == 10);
        assert(sub_second.consume() == 20);
        assert(sub_second.consume() == 30);
        assert(sub_second.consume() == 40);
        assert(sub_second.consume() == 50);
    }
}
