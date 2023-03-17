package org.eventi;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Evento {
    private String titolo;
    private LocalDate data;
    private final int CAPIENZAMASSIMA;
    private int postiPrenotati;

    public Evento(String titolo, LocalDate data, int CAPIENZAMASSIMA) throws InvalidParameterException {
        checkData(data);
        checkCapienzaMassima(CAPIENZAMASSIMA);
        this.titolo = titolo;
        this.data = data;
        this.CAPIENZAMASSIMA = CAPIENZAMASSIMA;
        this.postiPrenotati = 0;
    }

    //************* GETTER & SETTER************
    public int getCAPIENZAMASSIMA() {
        return CAPIENZAMASSIMA;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {

        return "Evento{" +
                "titolo='" + titolo + '\'' +
                ",Data=" + data +
                ", CAPIENZAMASSIMA=" + CAPIENZAMASSIMA +
                ", postiPrenotati=" + postiPrenotati +
                '}';
    }

    public int getPostiDisponibili() {
        return this.CAPIENZAMASSIMA - this.postiPrenotati;
    }

    //    **************CUSTOM METHODS*************
    private void checkData(LocalDate data) throws InvalidParameterException {
        LocalDate now = LocalDate.now();
        if (data.isBefore(now)) {
            throw new InvalidParameterException("Hai inserito una data passata");
        }
    }

    private void checkCapienzaMassima(int capienzaMassima) throws InvalidParameterException {
        if (capienzaMassima <= 0) {
            throw new InvalidParameterException("La capienza massima deve essere superire a 0");
        }
    }

    public void checkPrenotazioni(int numeroPrenotazioni) throws InvalidParameterException {
        String message = "";

        if (numeroPrenotazioni > getPostiDisponibili()) {
            if (getPostiDisponibili() == 0) {
                message = "Non ci sono posti disponibili";
            } else if (getPostiDisponibili() < numeroPrenotazioni) {
                message = "Ci sono " + getPostiDisponibili() + " posti disponibili";
            }
            throw new InvalidParameterException(message);
        }
    }

    public void checkDisdette(int numeroDisdette) throws InvalidParameterException {
        String message = "";
        if (numeroDisdette > this.postiPrenotati) {
            message = "Non puoi disdire più di " + this.postiPrenotati + " posti";
            if (this.postiPrenotati == 0) {
                message = "Non ci sono posti prenotati";
            }
            throw new InvalidParameterException(message);
        }
    }

    public void prenota() throws InvalidParameterException {
        checkData(this.data);
        checkPrenotazioni(1);
        this.postiPrenotati++;
    }

    public void disdici() throws InvalidParameterException {
        checkData(this.data);
        checkDisdette(1);
        this.postiPrenotati--;
    }


}



















