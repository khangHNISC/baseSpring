package com.example.pattern.observer_pattern.subject;

import com.example.pattern.observer_pattern.observer.Observer;
import com.example.pattern.observer_pattern.observer.Subscriber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherTest {

    @Test
    void givenSubscriber_thenDoSth() {
        Publisher sub = new Publisher();
        Observer obs = new Subscriber(sub);
        sub.registerObserver(obs);
        sub.notifyObservers();

        assertEquals(1, sub.getObservers().size());
    }

    @Test
    void givenUnRegisteredSubscriber_thenDoNothing() {
        Publisher sub = new Publisher();
        Observer obs = new Subscriber(sub);
        sub.registerObserver(obs);
        obs.unRegister();
        sub.notifyObservers();

        assertEquals(0, sub.getObservers().size());
    }
}