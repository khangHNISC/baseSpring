package com.example.pattern.observer_pattern.subject;

import com.example.pattern.observer_pattern.observer.Observer;
import lombok.Getter;

import java.util.ArrayList;

public class Publisher implements Subject {
    @Getter
    private final ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) observers.remove(i);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.doSth();
        }
    }
}
