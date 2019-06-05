package video.hc.com.jsonex.observer;

import java.util.Observable;

/**
 * Created by ly on 2019/6/5.
 */

public class MyObservable extends Observable {
    static MyObservable myObservable;

    public static MyObservable getIncetance() {
        if (myObservable == null) {
            myObservable = new MyObservable();

        }
        return myObservable;
    }

    public void notifyObserver(String source) {
        myObservable.setChanged();
        myObservable.notifyObservers(source);
    }
}
