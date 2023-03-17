package org.eventi;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("*****Creazione nuovo evento******");
        System.out.println("Inserire titolo evento");
        String titolo = scan.nextLine();
        LocalDate d = null;
        boolean correctDate = true;
        do {
            System.out.println("Inserire data formato yyyy-mm-dd");
            String data = scan.nextLine();
            try {
                d = LocalDate.parse(data);
                correctDate = true;
            } catch (Exception e) {
                correctDate = false;
                System.out.println("Formato data non valido, riprova");
            }
        } while (!correctDate);
        System.out.println("Inserire totale posti evento");
        int postiEvento = 0;
        try {
            postiEvento = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input non valido");

        }

        Evento ev = null;
        try {
            System.out.println("È un concerto? y n ");
            boolean concerto = scan.nextLine().equalsIgnoreCase("y");
            if (concerto) {
                System.out.println("inserisci prezzo biglietto. Formato xx.xx");
                BigDecimal bg = new BigDecimal(scan.nextLine());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                System.out.println("Inserisci l'orario formato HH:mm");
                LocalTime time = LocalTime.parse(scan.nextLine(), formatter);
                ev = new Concerto(titolo, d, postiEvento, time, bd);
            } else ev = new Evento(titolo, d, postiEvento);

            System.out.println("Inserire numero prenotazioni da effettuare, 0 per uscire. Max prenotazioni disponibili " + ev.getPostiDisponibili());
            int numeroPrenotazioni = Integer.parseInt(scan.nextLine());
            if (numeroPrenotazioni == 0 || numeroPrenotazioni < 0) {
                System.out.println("Nessuna prenotazione effettuata");
            } else {
                if (numeroPrenotazioni > ev.getPostiDisponibili()) {
                    System.out.println("Ci sono solo " + ev.getPostiDisponibili() + " posti disponibili. Li vuoi prenotare? y n");
                    boolean confirm = scan.nextLine().equalsIgnoreCase("y");
                    if (confirm) {
                        numeroPrenotazioni = ev.getPostiDisponibili();
                    } else numeroPrenotazioni = 0;
                }
                for (int i = 0; i < numeroPrenotazioni; i++) {
                    ev.prenota();
                }
            }


            System.out.println("Posti prenotati: " + ev.getPostiPrenotati());
            System.out.println("Posti disponibili: " + ev.getPostiDisponibili());

            System.out.println("Inserire numero di disdette da effettuare, 0 per uscire.");
            int numeroDisdette = Integer.parseInt(scan.nextLine());

            if (numeroDisdette > ev.getPostiPrenotati()) {
                System.out.println("Ci sono solo " + ev.getPostiPrenotati() + " posti prenotati. Li vuoi cancellare? y n");
                boolean scelta = scan.nextLine().equalsIgnoreCase("y");
                if (scelta) {
                    numeroDisdette = ev.getPostiPrenotati();
                } else numeroDisdette = 0;
                for (int i = 0; i < numeroDisdette; i++) {
                    ev.disdici();
                }
            }

            System.out.println("Posti prenotati: " + ev.getPostiPrenotati());
            System.out.println("Posti disponibili: " + ev.getPostiDisponibili());

            boolean menuChoice = true;
            do {
                try {
                    System.out.println("Singole operazioni: 1 aggiungi una prenotazione, 2 elimina una prenotazione, 3 esci");
                    switch (Integer.parseInt(scan.nextLine())) {
                        case 1:
                            ev.prenota();
                            break;
                        case 2:
                            ev.disdici();
                            break;
                        case 3:
                            menuChoice = false;
                            break;
                        default:
                            break;
                    }
                } catch (InvalidParameterException e) {
                    System.out.println(e.getMessage());
                } finally {

                    System.out.println("Posti prenotati: " + ev.getPostiPrenotati());
                    System.out.println("Posti disponibili: " + ev.getPostiDisponibili());
                }
            } while (menuChoice);
            System.out.println(ev);
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }
        scan.close();
    }
}
