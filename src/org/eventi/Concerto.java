package org.eventi;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento {
    private LocalTime ora;
    private BigDecimal prezzo;


    public Concerto(String titolo, LocalDate data, int CAPIENZAMASSIMA, LocalTime ora, BigDecimal prezzo) throws InvalidParameterException {
        super(titolo, data, CAPIENZAMASSIMA);
        this.ora = ora;
        this.prezzo = prezzo;

    }


    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public String formattedPrice() {
        DecimalFormat df = new DecimalFormat("##0.00€");

        return df.format(prezzo);
    }

    public String formattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return ora.format(formatter);
    }

    @Override
    public String toString() {
        return super.toString() + "Concerto{" +
                "ora= " + formattedTime() +
                ", prezzoBiglietto= " + formattedPrice() +
                '}';
    }
}
