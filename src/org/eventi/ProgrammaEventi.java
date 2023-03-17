package org.eventi;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

public class ProgrammaEventi {
    private String titolo;
    private List<Evento> list;

    public ProgrammaEventi(String titolo) {
        this.titolo = titolo;
        this.list = new ArrayList<>();
    }

    public void addEvent(Evento ev) {
        list.add(ev);
    }

    @Override
    public String toString() {
        return "ProgrammaEventi{" +
                "titolo='" + titolo + '\'' +
                ", list=" + list +
                '}';
    }

    public List<Evento> searchByDate(LocalDate lc) {
        Iterator<Evento> iterator = list.iterator();
        List<Evento> filteredList = new ArrayList<>();
        while (iterator.hasNext()) {
            Evento ev = iterator.next();
            LocalDate date = ev.getData();
            if (date.isEqual(lc)) {
                filteredList.add(ev);
            }
        }
        return filteredList;
    }

    public int numberOfEvents() {
        return list.size();
    }

    public void dupmEvents() {
        list.clear();
    }

    public void sortedList() {
        Collections.sort(list, Comparator.comparing(Evento::getData));
        System.out.println(titolo);
        for (Evento evento : list) {
            System.out.println(evento.getData() + "==>" + evento.getTitolo());
        }
    }
}
