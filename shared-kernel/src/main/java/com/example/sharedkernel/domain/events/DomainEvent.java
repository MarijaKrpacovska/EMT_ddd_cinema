package com.example.sharedkernel.domain.events;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

import java.time.Instant;

@Getter
public class DomainEvent {

    //TODO: fix instant problem
    private String topic;
   // private Instant occurredOn;

    public DomainEvent(String topic) {
      //  this.occurredOn = Instant.now();
        this.topic = topic;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String output = null;
        try {
            output = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.print("EXCEPTION"+e);
        }
        return output;
    }

    public String topic() {
        return topic;
    }

    public static <E extends DomainEvent> E fromJson(String json, Class<E> eventClass) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
     //  objectMapper.registerModule(new JavaTimeModule());

        try {
            DomainEvent de = objectMapper.readValue(json, eventClass);
        }catch (Exception e){
            System.out.print(e);
        }
        return objectMapper.readValue(json,eventClass);
    }
}

