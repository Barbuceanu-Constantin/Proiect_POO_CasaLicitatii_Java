import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 * Casa de licitatii este unica. Prin urmare ii voi aplica patternul Singleton.
 */
public class CasaDeLicitatii {
    private ArrayList<Produs> listaProduse;
    private ArrayList<Client> listaClienti;
    private ArrayList<Licitatie> listaLicitatiiActive;
    private ArrayList<Produs> listaProduseDeInserat;
    private static CasaDeLicitatii instantaUnica;

    /**
     * @param vectorPreturiOferite =    vectorul preturilor oferite de toti
     *                                  clientii participanti la licitatie
     * @param nrClientiInscrisi =   nrul de clienti inscrisi la licitatie
     * @return indexul clientului care ofera cel mai mult
     *
     * Metoda este apelata din Broker,
     * calculeaza oferta maxima si returneaza
     * indexul clientului brokerului.
     */
    public static int alegerePretMaxim(double[] vectorPreturiOferite, int nrClientiInscrisi) {
        int indexDeReturnat = 0;
        double pretMaximOferit = vectorPreturiOferite[0];
        for(int index = 1; index < nrClientiInscrisi; ++index) {
            //Se verifica inegalitatea stricta
            if(vectorPreturiOferite[index] > pretMaximOferit) {
                indexDeReturnat = index;
                pretMaximOferit = vectorPreturiOferite[index];
            }
        }
        return indexDeReturnat;
    }

    /**
     * @param listaBrokeri = lista de Brokeri primita din clasa Test
     *
     * Aceasta este metoda principala din CasaDeLicitatii deoarece in interiorul
     * ei se parcurge listaLicitatii, si pentru fiecare licitatie se aplica metoda
     * licitatie.executareLicitatie(). executareLicitatie() se aplica la finalul fiecarui
     * pas al forului dupa ce au fost indeplinite un set de cerinte prealabile descrise in comentarii.
     */
    public void parcurgereLicitatii(ArrayList<Broker> listaBrokeri) {
        int nrClienti = listaClienti.size();
        int nrLicitatii = listaLicitatiiActive.size();
        int nrBrokeri = listaBrokeri.size();
        boolean []vectorAsociereClientLaLicitatie = new boolean[nrClienti];
        Random rand = new Random();

        try {
            FileWriter myWriter = new FileWriter("Output/output_file");
            //Afisez produsele initiale.
            myWriter.write("LISTA INITIALA A PRODUSELOR ESTE:\n");
            for (Produs p : listaProduse) {
                myWriter.write(p.toString(p));
            }
            myWriter.write("\n\n");

            for(int index1 = 0; index1 < nrLicitatii; ++index1) {
                int nrProduse = listaProduse.size();

                //Setez abia aici random idul produsului pentru care se face licitatia.
                listaLicitatiiActive.get(index1).setIdProdus(rand.nextInt(nrProduse) + 1);

                /*
                    Setez la null toti brokerii clientilor, astfel incat fiecare client
                    sa nu aiba niciun broker. vectorAsociereClientiLaLicitatie imi va da indecsii
                    clientilor care participa la licitatie, stiind ca valoarea lor e true.
                    In plus, setez la 0 id-ul produsului pentru care clientul poate va licita
                    si mai setez la 0 pretul maxim pe care e dispus sa il ofere.
                */
                for (int index2 = 0; index2 < nrClienti; ++index2) {
                    vectorAsociereClientLaLicitatie[index2] = false;
                    listaClienti.get(index2).setBroker(null);
                    listaClienti.get(index2).setIdProdusLicitat(0);
                    listaClienti.get(index2).setPretMaximPentruProdus(0);
                }

                //Reinitializez listele de clienti ale brokerilor cu o lista vida.
                for (Broker broker : listaBrokeri) {
                    broker.setListaClienti(new ArrayList<>());
                }

                //Numarul de participanti inscrisi trebuie sa fie mai mic decat numarul de clienti !!!
                int nrParticipantiInscrisi = 0;
                while (nrParticipantiInscrisi < listaLicitatiiActive.get(index1).getNrParticipanti()) {
                    int indexClient = rand.nextInt(nrClienti);                  //Generez aleator indexul unui client din lista de clienti.
                    if (!vectorAsociereClientLaLicitatie[indexClient]) { //Daca clientul nu a fost asociat la licitatie.
                        vectorAsociereClientLaLicitatie[indexClient] = true;
                        int indexBroker = rand.nextInt(nrBrokeri);              //Generez aleator indexul unui broker
                        listaClienti.get(indexClient).setBroker(listaBrokeri.get(indexBroker));                         //Am setat brokerul clientului
                        listaBrokeri.get(indexBroker).getListaClienti().add(listaClienti.get(indexClient));             //Am adaugat clientul in lista brokerului
                        int indexProdus = rand.nextInt(nrProduse);              //Generez aleator indexul unui produs pe care clientul vrea sa il liciteze
                        listaClienti.get(indexClient).setIdProdusLicitat(listaProduse.get(indexProdus).getId());        //Setez idul produsului solicitat de client
                        /*
                            Stabilesc pretul maxim pe care il poate oferi clientul,
                            pornind de la pretul minim al produsului la care adaug
                            un procent dintr-un milion. Pretul maxim pe care il poate oferi
                            clientul trebuie sa fie evident mai mare decat pretul minim al produsului.
                         */
                        double pretMaximPtClient = listaProduse.get(indexProdus).getPretMinim();
                        pretMaximPtClient = pretMaximPtClient + (Math.random() * rand.nextInt(1000000));
                        listaClienti.get(indexClient).setPretMaximPentruProdus(pretMaximPtClient);
                        ++nrParticipantiInscrisi;
                    }
                }

                Licitatie licitatie = listaLicitatiiActive.get(index1);
                licitatie.executareLicitatie(vectorAsociereClientLaLicitatie, instantaUnica, index1,
                                            listaBrokeri, licitatie, myWriter);
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Eroare la deschiderea fisierului de output");
        }
    }

    /**
     * Constructor fara parametri.
     */
    public CasaDeLicitatii() { }

    /**
     * @param lP = listaProduse
     * @param lC = listaClienti
     * @param lLicAct = listaLicitatiiActive
     * @param listaProduseDeInserat = lista produselor de inserat
     * Constructor cu parametri
     */
    public CasaDeLicitatii(ArrayList<Produs> lP, ArrayList<Client> lC, ArrayList<Licitatie> lLicAct,
                           ArrayList<Produs> listaProduseDeInserat) {
        this.listaProduse = lP;
        this.listaClienti = lC;
        this.listaLicitatiiActive = lLicAct;
        this.listaProduseDeInserat = listaProduseDeInserat;
    }

    /**
     * @param lP = listaProduse
     * @param lC = listaClienti
     * @param lLicAct = listaLicitatiiActive
     * @param listaProduseDeInserat = listaProduselorDeInserat
     * @return instantaUnica
     *
     * Metoda specifica design-patternului Singleton care returneza instanta unica a casei de licitatii.
     */
    public static CasaDeLicitatii Instanta(ArrayList<Produs> lP, ArrayList<Client> lC, ArrayList<Licitatie> lLicAct,
                                           ArrayList<Produs> listaProduseDeInserat) {
        if (instantaUnica == null)
            instantaUnica = new CasaDeLicitatii(lP, lC, lLicAct, listaProduseDeInserat);
        return instantaUnica;
    }

    /**
     * @return listaProduseDeInserat
     * GETTER
     */
    public ArrayList<Produs> getListaProduseDeInserat() { return listaProduseDeInserat; }

    /**
     * @param listaProduseDeInserat
     * SETTER
     */
    public void setListaProduseDeInserat(ArrayList<Produs> listaProduseDeInserat) {
        this.listaProduseDeInserat = listaProduseDeInserat;
    }

    /**
     * @return listaProduse
     * GETTER
     */
    public ArrayList<Produs> getListaProduse() { return listaProduse; }

    /**
     * @return listaClienti
     * GETTER
     */
    public ArrayList<Client> getListaClienti() { return listaClienti; }

    /**
     * @return listaLicitatiiActive
     * GETTER
     */
    public ArrayList<Licitatie> getListaLicitatiiActive() { return listaLicitatiiActive; }

    /**
     * @param listaProd
     * SETTER
     */
    public void setListaProduse(ArrayList<Produs> listaProd) { this.listaProduse = listaProd; }

    /**
     * @param listaCl
     * SETTER
     */
    public void setListaClienti(ArrayList<Client> listaCl) { this.listaClienti = listaCl; }

    /**
     * @param listaLAct
     * SETTER
     */
    public void setListaLicitatiiActive(ArrayList<Licitatie> listaLAct) { this.listaLicitatiiActive = listaLAct; }
}
