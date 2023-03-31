import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Brokerul este o subclasa a lui Angajat. Acesta este intermediar
 * intre client si casa de licitatii. Atfel patternurile Observer si
 * Command sunt implementate intre el si client si intre el si casa.
 * Sarcinile lui sunt:
 ** sa elimine produsul din lista de produse a casei odata ce a fost licitat,
 ** sa solicite sumele clientilor participanti la fiecare pas al licitatiei
 *     si sa le dea mai departe casei ca aceasta din urma sa intoarca indexul
 *     clientului care a oferit maxim, brokerului, iar brokerul sa notifice clientii
 ** sa aplice comisioanele specifice pe ofertele finale ale clientilor
 */
public class Broker extends Angajat {
    ArrayList<Client> listaClienti;

    /**
     * @param produs = produsul care a fost licitat si trebuie eliminat
     * @param casa = instanta casei de licitatii
     *
     * Se elimina produsul licitat din lista de produse a casei.
     */
    public static void eliminareProdus(Produs produs, CasaDeLicitatii casa) {
        ArrayList<Produs> listaProduse = casa.getListaProduse();
        //Dupa eliminarea unui produs, idurile produselor urmatoare trebuie scazute cu 1
        for(int index = 0; index < listaProduse.size(); ++index) {
            if(index > produs.getId() - 1) {
                listaProduse.get(index).setId(listaProduse.get(index).getId() - 1);
            }
        }
        listaProduse.remove(produs.getId() - 1);
    }

    /**
     * @param listaClientiInscrisiLaLicitatie = lista clientilor inscrisi in licitatie
     * @return indexul clientului care ofera cel mai mult
     *
     * Brokerul solicita sumele clientilor si
     * returneaza indexul clientului care ofera maxim.
     */
    public static int solicitaSumeleClientilor(ArrayList<Client> listaClientiInscrisiLaLicitatie) {
        int nrClientiInscrisi = listaClientiInscrisiLaLicitatie.size();
        double[] vectorSumeOferite = new double[nrClientiInscrisi];
        int indexulClientuluiCuOfertaMaxima;
        for(int index = 0; index < nrClientiInscrisi; ++index) {
            vectorSumeOferite[index] = listaClientiInscrisiLaLicitatie.get(index).getPretOferitLaPasulCurent();
        }
        /*
            Vectorul cu sumele oferite de clienti este transmis mai departe
            din Broker in CasaDeLicitatii pentru a se returna indexul clientului
            care ofera cel mai mult la pasul curent.
        */
        indexulClientuluiCuOfertaMaxima = CasaDeLicitatii.alegerePretMaxim(vectorSumeOferite, nrClientiInscrisi);
        return indexulClientuluiCuOfertaMaxima;
    }

    /**
     * @param listaClientiInscrisi = lista clientilor inscrisi la licitatie
     * @param myWriter = obiectul de tip FileWriter cu ajutorul caruia se scrie in fisierul de output
     *
     * Brokerul aplica pe ofertele finale ale clientilor comisioanele specifice.
     */
    public static void aplicareComision(ArrayList<Client> listaClientiInscrisi, FileWriter myWriter) {
        try {
            myWriter.write("\n\nOfertele clientilor dupa ce s-au aplicat comisioanele: \n");
            myWriter.write("------------------------------\n");
            for (Client client : listaClientiInscrisi) {
                double pretOferit = client.getPretOferitLaPasulCurent();
                //20% pentru persoane fizice care au licitat de mai putin de 5 ori.
                if (client instanceof PersoanaFizica) {
                    //Daca a licitat de mai putin sau egal cu 5 ori.
                    if (client.getNrParticipari() <= 5) {
                        pretOferit -= 0.2 * pretOferit;
                    } else {
                        //Daca a licitat de mai mult de 5 ori.
                        pretOferit -= 0.15 * pretOferit;
                    }
                } else if (client instanceof PersoanaJuridica) {
                    if (client.getNrParticipari() <= 25) {
                        pretOferit -= 0.25 * pretOferit;
                    } else {
                        pretOferit -= 0.1 * pretOferit;
                    }
                }
                client.setPretOferitLaPasulCurent(pretOferit);
                myWriter.write("*****Clientul " + client.getNume() +
                                " ofera " + client.getPretOferitLaPasulCurent() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Fisierul nu a fost gasit");
            e.printStackTrace();
        }
    }

    /**
     * @param listaClienti = lista de clienti ai brokerului
     * Constructor cu parametri.
     */
    public Broker(ArrayList<Client> listaClienti) { this.listaClienti = listaClienti; }

    /**
     * @return lista de clienti ai brokerului
     * GETTER
     */
    public ArrayList<Client> getListaClienti() { return listaClienti; }

    /**
     * @param listaClienti  = lista de clienti a brokerului
     * SETTER
     */
    public void setListaClienti(ArrayList<Client> listaClienti) { this.listaClienti = listaClienti; }
}
