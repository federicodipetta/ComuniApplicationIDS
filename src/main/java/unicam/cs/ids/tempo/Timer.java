package unicam.cs.ids.tempo;

import java.time.LocalDateTime;
import java.util.*;

public class Timer {
    private Set<ObserverTempo> observers;
    private LocalDateTime oraAttuale;
    private java.util.Timer timer;

    /**
     *
     * @param clock ogni quanti ms deve notificare gli observer
     * @param observers gli observer da notificare
     */
    public Timer(int clock, Collection<ObserverTempo> observers) {
        this.observers = new HashSet<>(observers);
        LocalDateTime oraAttuale = LocalDateTime.now();
        // i want to notify the observers every clock ms using threads
        this.timer = new java.util.Timer();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setOraAttuale(LocalDateTime.now());
            }
        }, 0, clock);
    }

    /**
     * Seleziona l'ora attuale e notifica gli observer
     * @param oraAttuale l'ora attuale
     */
    public void setOraAttuale(LocalDateTime oraAttuale) {
        this.oraAttuale = oraAttuale;
    }

    private void notifyObservers() {
            this.observers.parallelStream().forEach(observer -> observer.update(this.oraAttuale));
    }


    /**
     * Aggiunge un observer alla lista di observer
     * @param observer l'observer da aggiungere
     */
    public void subscribe(ObserverTempo observer) {
        observers.add(observer);
    }

    /**
     * Rimuove un observer dalla lista di observer
     * @param observer l'observer da rimuovere
     */
    public void unsubscribe(ObserverTempo observer) {
        observers.remove(observer);
    }


}
