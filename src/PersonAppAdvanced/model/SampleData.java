package PersonAppAdvanced.model;

import javafx.collections.ObservableList;

import java.time.LocalDate;

public class SampleData {
    public static void fillSampleData(ObservableList<PersonAppAdvanced.model.Person> backingList) {
        backingList.add(new Person("Waldo", "Soller", "random notes 1", LocalDate.of(2020, 1, 8), "Male", "1.jpeg"));
        backingList.add(new Person("Herb", "Dinapoli", "random notes 2", LocalDate.of(2009, 1, 8), "Female", "2.jpeg"));
        backingList.add(new Person("Shawanna", "Goehring", "random notes 3", LocalDate.of(2001, 1, 8), "Male", "3.jpeg"));
        backingList.add(new Person("Flossie", "Goehring", "random notes 4", LocalDate.of(1988, 1, 8), "Male", "4.jpeg"));
        backingList.add(new Person("Magdalen", "Meadors", "random notes 5", LocalDate.of(2001, 1, 8), "Male", "5.jpeg"));
        backingList.add(new Person("Marylou", "Berube", "random notes 6", LocalDate.of(2001, 1, 8), "Male", "6.jpeg"));
        backingList.add(new Person("Ethan", "Nieto", "random notes 7", LocalDate.of(2001, 1, 8), "Male", "7.jpeg"));
        backingList.add(new Person("Elli", "Combes", "random notes 8", LocalDate.of(2001, 1, 8), "Male", "8.jpeg"));
        backingList.add(new Person("Andy", "Toupin", "random notes 9", LocalDate.of(2001, 1, 8), "Male", "9.jpeg"));
        backingList.add(new Person("Zenia", "Linwood", "random notes 10", LocalDate.of(2001, 1, 8), "Male", "10.jpeg"));
    }

    /*
    Glenn Marti
    Waldo Soller
    Herb Dinapoli
    Shawanna Goehring
    Flossie Slack
    Magdalen Meadors
    Marylou Berube
    Ethan Nieto
    Elli Combes
    Andy Toupin
    Zenia Linwood
    Alan Mckeithan
    Kattie Mellott
    Benito Kearns
    Lloyd Cundiff
    Karleen Westrich
    Jada Perrotta
    Teofila Holbert
    Moira Heart
    Mitsuko Earwood
     */
}