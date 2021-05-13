package com.example.pattern.observer_pattern.observer;

import com.example.pattern.observer_pattern.subject.Subject;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Subscriber implements Observer {

    private final Subject publisher; //optional

    @Override
    public String doSth() {
        return "Iam doing work";
    }

    @Override
    public void unRegister() {
        publisher.removeObserver(this);
    }
}
