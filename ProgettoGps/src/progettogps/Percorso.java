package progettogps;

import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.math.RoundingMode;

public class Percorso {

    private ArrayList<GpsRil> lista;

    public Percorso(String file) {
        lista = new ArrayList<>();
        readFile(file);
    }

    public void setLista(ArrayList<GpsRil> lista) {
        this.lista = new ArrayList<>();
    }

    public ArrayList<GpsRil> getLista() {
        return lista;
    }

    public void readFile(String pp) {

        BufferedReader riga;
        try {
            riga = new BufferedReader(new FileReader(pp));
            String linea;
            linea = riga.readLine();
            while ((linea = riga.readLine()) != null) {
                linea = linea.replace(",", ".");
                String dati[] = linea.split(";");
                GpsRil a = new GpsRil(Long.parseLong(dati[0]),
                        dati[1],
                        Double.parseDouble(dati[2]),
                        Double.parseDouble(dati[3]),
                        Double.parseDouble(dati[4]),
                        Double.parseDouble(dati[5]));
                lista.add(a);
            }
            riga.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    public long DurataPercorso() { //Secondi
        long durata = 0;
        if (1 < lista.size()) {
            durata = lista.get(lista.size() - 1).getUnixTime() - lista.get(0).getUnixTime();
        }
        return (durata);
    }

    public double DistanzaPercorso() { //Distanza totale percorsa (KM)
        double distanza = 0;
        if (1 < lista.size()) {
            for (int i = 0; i < (lista.size() - 1); i++) {
                distanza += lista.get(i).distanza(lista.get(i + 1));
            }
        }
        return (distanza);
    }

    public double VelocitaMedia() {    //KM / h
        return (this.DistanzaPercorso() / (this.DurataPercorso() / (double) 3600));
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
        String giorno = (lista.get(0).getTime()).split("T")[0];
        return (giorno);
    }

    public String DataFinePercorso() {
        String giorno = "";
        if (lista.size() != 0) {
            giorno = (lista.get(lista.size() - 1).getTime()).split("T")[0];
        }
        return (giorno);
    }

    public double DislivelloTotale() {
        return (this.AltezzaMassima() - this.AltezzaMinima());
    }

    public double AltezzaMinima() {
        double ymin = Integer.MAX_VALUE;
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
        double ymax = Integer.MIN_VALUE;
        if (1 < lista.size()) {
            for (int i = 0; i < lista.size() - 1; i++) {
                if (ymax < lista.get(i).getAlt()) {
                    ymax = lista.get(i).getAlt();
                }
            }
        }
        return (ymax);
    }
}
