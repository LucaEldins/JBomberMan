package observer;

import java.util.ArrayList;

public class Observable {

    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public void register(Observer o) {
        observers.add(o);
    }

    public void unregister(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(Object e) {
        for (Observer observer : observers) {
            observer.update(e);
        }
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

}
