package com.example.pattern.observer_pattern.subject;


import com.example.pattern.observer_pattern.observer.Observer;

public interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
