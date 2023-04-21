package com.eratostene;

import java.util.List;
import java.util.Map;

public final class Eratostene {

   
    private Map<String, Topic> topicMap;

    public Eratostene() {

    }

    protected Topic SubscribeProd(String topicName) {

        return null;

    }
    protected void UnSubscribeProd(String topicName) {
            
    }

    protected Topic SubscribeCOns(String topicName) {

        return null;

    }

    private void generateTopic(String topic_name){

        topicMap.put(topic_name, new Topic(topic_name));
        
    
    
    }
}