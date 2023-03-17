package org.eventi;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        LocalDate d = LocalDate.of(2023, 3, 10);
        LocalDate now = LocalDate.now();

        System.out.println(d);
        System.out.println(now);
        LocalDate n = LocalDate.now();


        Evento event = null;
        try {
            event = new Evento("gigi", n, 2);
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(event);
        event.prenota();
        event.prenota();
        System.out.println(event);
        event.disdici();
        event.disdici();
        System.out.println(event);
    }
}
