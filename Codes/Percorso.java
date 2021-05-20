package percorso;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Percorso {

    private ArrayList<GpsRil> lista;

   //da sistemare
    public Percorso(String file) {
        readFile(file);
    }

    public void setLista(ArrayList<GpsRil> lista) {
        this.lista = new ArrayList<>();
    }

    public ArrayList<GpsRil> getLista() {
        return lista;
    }

    public void readFile(String pp) {
        GpsRil a = new GpsRil();
        BufferedReader riga;
        try {
            riga = new BufferedReader(new FileReader(pp));
            String linea = riga.readLine();
            while ((linea = riga.readLine()) != null) {
                String dati[] = linea.split(";");
                a.setUnixTime((long) Integer.parseInt(dati[0]));
                a.setTime(dati[1]);
                a.setLat((double) Integer.parseInt(dati[2]));
                a.setLongi((double) Integer.parseInt(dati[3]));
                a.setAlt((double) Integer.parseInt(dati[4]));
                a.setDist((double) Integer.parseInt(dati[5]));
                lista.add(a);
            }
            riga.close();
        } catch (Exception b) {
            System.out.println("file non trovato");
        }
    }

    public long DurataPercorso() {
        long durata = 0;
        if (1 < lista.size()) {
            durata = lista.get(lista.size() - 1).getUnixTime() - lista.get(0).getUnixTime();
        }
        return (durata);
    }

    public double DistanzaPercorso() {
        double distanza = 0;
        if (1 < lista.size()) {
            for (int i = 0; i < (lista.size() - 1); i++) {
                distanza += lista.get(i).distanza(lista.get(i + 1));
            }
        }
        return (distanza);
    }

    public double VelocitaMedia() {    //KM / h
        return (this.DistanzaPercorso() / (this.DurataPercorso()) / 3600);
    }

    public double VelocitaMassima() {  //KM / h
        double velocita = 0;
        double dist = 0;
        double tem = 0;
        double velocitamax = 0;
        if (1 < lista.size()) {
            for (int i = 0; i < (lista.size()) - 2; i++) {
                dist = lista.get(i + 1).distanza(lista.get(i));
                tem = (lista.get(i + 1).getUnixTime() - lista.get(i).getUnixTime());
                velocita = dist / (tem / 3600);
                if (velocitamax < velocita) {
                    velocitamax = velocita;
                }
            }
        }
        return (velocitamax);
    }

    public int NumeroRilevazioni() {
        return lista.size();
    }

    public String DataInizioPercorso() {
        return (lista.get(0).getTime());
    }

    public String DataFinePercorso() {
        return (lista.get(lista.size() - 1).getTime());
    }

    public double DislivelloTotale() {
        return (this.AltezzaMassima() - this.AltezzaMinima());
    }

    public double AltezzaMinima() {
        double ymin = 9999;
        if (1 < lista.size()) {
            for (int i = 0; i < lista.size() - 1; i++) {
                if (lista.get(i).getAlt() < ymin) {
                    ymin = lista.get(i).getAlt();
                }
            }
        }
        return (ymin);
    }

    public double AltezzaMassima() {
        double ymax = -9999;
        if (1 < lista.size()) {
            for (int i = 0; i < lista.size() - 1; i++) {
                if (ymax < lista.get(i).getAlt()) {
                    ymax = lista.get(i).getAlt();
                }
            }
        }
        return (ymax);
    }

    public static void main(String[] args) {
        //da sistemare
        String nomeFile = "file_percorso.csv";
        Percorso p1 = new Percorso(nomeFile);
        System.out.println(p1.DislivelloTotale());
    }
}
