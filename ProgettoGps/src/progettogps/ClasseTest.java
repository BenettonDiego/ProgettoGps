package progettogps;

import java.text.DecimalFormat;

public class ClasseTest {

    public static void main(String[] args) {
        String nomeFile = "file_percorso.csv";
        Percorso p1 = new Percorso(nomeFile);
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        System.out.println("Il percorso e' durato " + p1.DurataPercorso() + " secondi");
        System.out.println("Sono stati percorsi " + numberFormat.format(p1.DistanzaPercorso()) + " KM");
        System.out.println("La velocita' media e' stata di " + numberFormat.format(p1.VelocitaMedia()) + " KM/h");
        System.out.println("La velocita' massima e' stata di " + numberFormat.format(p1.VelocitaMassima()) + " KM/h");
        System.out.println("Le rilevazioni totali sono state " + p1.NumeroRilevazioni());
        System.out.println("Il percorso e' iniziato il " + p1.DataInizioPercorso() + " ed e' finito il giorno " + p1.DataFinePercorso());
        System.out.println("Il dislivello totale del percorso è stato di " + p1.DislivelloTotale() + " gradi con un altezza minima di "
                + p1.AltezzaMinima() + " gradi e un altezza massima di " + p1.AltezzaMassima()+" gradi");
    }

}
