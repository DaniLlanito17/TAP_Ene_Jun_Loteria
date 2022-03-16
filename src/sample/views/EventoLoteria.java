package sample.views;

import javafx.event.Event;
import javafx.event.EventHandler;

public class EventoLoteria implements EventHandler{
    int opc;
    public EventoLoteria(int opc){
        this.opc=opc;
    }

    @Override
    public void handle(Event event) {
        if (opc == 1) {
            System.out.println("Mi primer evento Fovifest");
        }
        else{
            System.out.println("Hola c:");
        }
    }
}
