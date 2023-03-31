import java.io.IOException;
import java.io.FileWriter;
import java.util.*;

/**
 *
 */
public class Licitatie {
    private int id, nrParticipanti, idProdus, nrPasiMaxim;

    /**
     * Constructor fara parametri
     */
    public Licitatie() { }

    /**
     * @param id = idul licitatiei, care ne da indexul din
     *             listaLicitatiiActive (cu -1) din casaDeLicitatii
     * @param nrParticipanti = nr-ul de participanti la licitatie
     * @param nrPasiMaxim = nr-ul maxim de pasi al licitatiei
     *
     * Constructor cu parametri.
     */
    public Licitatie(int id, int nrParticipanti, int nrPasiMaxim) {
        this.id = id;
        this.nrParticipanti = nrParticipanti;
        this.nrPasiMaxim = nrPasiMaxim;
    }

    /**
     * @param w = obiectul de tip Filewriter prin care se scrie in fisierul de output
     * @param i = indexul licitatiei in listaLicitatiiActive din casa de licitatii
     *
     * Metoda care afiseaza textul neschimbabil de la inceputul fiecarei licitatii.
     */
    private void afisareTextDeInceputLicitatie(FileWriter w, int i) {
        try {
            w.write("##############################\n");
            w.write("LICITATIA " + (i + 1) + " A INCEPUT:\n");
            w.write("##############################\n");
            w.write("\n");
        } catch (IOException e) {
            System.out.println("Fisierul nu a fost gasit");
            e.printStackTrace();
        }
    }

    /**
     * @param v = vectorAsociereClientiLaLicitatie; are dimensiunea listei de clienti
     *            din casa de licitatii si are elementul setat ca true daca clientul
     *            participa la licitatia curenta si false daca nu.
     *            Este creat la fiecare pas al forului din parcurgereLicitatii() din
     *            casa de licitatii si este transmis in executareLicitatie din clasa Licitatie.
     * @param casa = casa de licitatii
     * @param l = licitatia curenta
     * @param w = obiectul de tip Filewriter prin care se scrie in fisierul de output
     *
     * Metoda scrie in fisierul de output informatiile despre licitatia curenta.
     */
    private void afisareInformatiiLicitatie(boolean[] v, CasaDeLicitatii casa, Licitatie l, FileWriter w) {
        try {
            w.write("Numarul de participanti inscrisi la licitatie este " + nrParticipanti + ".\n");
            w.write("Numarul de pasi ai licitatiei este " + nrPasiMaxim + ".\n");
            w.write("Produsul pentru care se liciteaza este:\n");
            w.write("------------------------------\n");
            Produs p = casa.getListaProduse().get(idProdus - 1);
            w.write(p.toString(p));
            w.write("\n\nClientii participanti la licitatie sunt: \n");
            w.write("------------------------------\n");
            int nrClienti = casa.getListaClienti().size();
            for (int i = 0; i < nrClienti; ++i) {
                if (v[i]) {
                    Client client = casa.getListaClienti().get(i);
                    w.write(client.toString(client));
                }
            }
            w.write("\n\nNumarul maxim de pasi al licitatiei este: " + l.getNrPasiMaxim() + "\n");
            w.write("------------------------------\n");
        } catch (IOException e) {
            System.out.println("Eroare la scriere");
            e.printStackTrace();
        }
    }

    /**
     * @param w = obiectul de tip Filewriter prin care se scrie in fisierul de output
     * @param i = indexul licitatiei in listaLicitatiiActive din casa de licitatii
     *
     * Metoda care afiseaza textul neschimbabil de la finalul fiecarei licitatii.
     */
    private void afisareTextDeFinalLicitatie(FileWriter w, int i) {
        try {
            w.write("\n\n");
            w.write("##############################\n");
            w.write("LICITATIA " + (i+1) + " S-A INCHEIAT:\n");
            w.write("##############################\n");
            w.write("\n\n\n\n\n");
        } catch (IOException e) {
            System.out.println("Fisierul nu a fost gasit");
            e.printStackTrace();
        }
    }

    /**
     * @param w = obiectul de tip Filewriter prin care se scrie in fisierul de output
     * @param casa = casa de licitatii.
     *
     * Metoda afiseaza toate produsele din listaProduse din casa de licitatii,
     * dupa terminarea fiecarei licitatii si eliminarea (daca produsul a fost vandut)
     * produsului licitat din lista.
     */
    public void afisareProduseRamaseDupaLicitatie(FileWriter w, CasaDeLicitatii casa) {
        try {
            w.write("\n\nPRODUSELE RAMASE DUPA LICITATIE SUNT:\n");
            for(int index1 = 0; index1 < casa.getListaProduse().size(); ++index1) {
                Produs p = casa.getListaProduse().get(index1);
                w.write(p.toString(p));
            }
        } catch (IOException e) {
            System.out.println("Fisierul nu a fost gasit");
            e.printStackTrace();
        }
    }

    /**
     * @param w = obiectul de tip Filewriter prin care se scrie in fisierul de output
     * @param client = clientul care a castigat licitatia
     * @param ofertaMaxima = oferta maxima (a clientului care a castigat licitatia)
     *                       dupa aplicarea comisionului.
     *
     * Metoda care afiseaza mesajul castigator.
     */
    private void afisareMesajCastigator(FileWriter w, Client client, double ofertaMaxima) {
        try {
            w.write("\n\n (!!!!!!!!!!) CLIENTUL CARE A CASTIGAT LICITATIA ESTE " + client.getNume() +
                    " CU SUMA DE " + ofertaMaxima + " (!!!!!!!!!!)\n");
        } catch(IOException e) {
            System.out.println("Fisierul nu a fost gasit");
            e.printStackTrace();
        }
    }

    /**
     * @param vectorAsociereClientiLaLicitatie = vector de dimensiunea listei de clienti din casa
     *                                           de licitatii care are elementul true daca clientul
     *                                           participa la licitatie si false in caz contrar
     * @param casa = casa de licitatii
     * @param i este indexul licitatiei, adica la a cata licitatie s-a ajuns
     * @param listaBrokeri = lista de brokeri din CasaDeLicitatii
     * @param licitatie = licitatia la care s-a ajuns si care urmeaza sa fie executata
     *
     ***** Metoda principala din clasa Licitatie care afiseaza tot parcursul licitatiei.
     ***** Afiseaza ofertele clientilor participanti pentru fiecare pas al licitatiei.
     ***** Afiseaza clientul cu oferta maxima pentru fiecare pas.
     ***** Ofertele pornesc de la pretMinim si sunt calculate la fiecare pas cu random
     *  astfel incat pentru fiecar client suma pe care o ofera sa fie cuprinsa intre
     *  pretul maxim anterior si pretul maxim personal pe care este dispus sa il ofere.
     ***** Se afiseaza mesajul castigator cu datele clientului castigator.
     ***** Se elimina produsul licitat.
     ***** Se afiseaza listaDeProduse dupa eliminare.
     ***** In cazul in care exista produse care sa fie inserate la finalul licitatiei
     *  curente, se insereaza prin apelul metodei din Administrator, iar apoi se
     *  afiseaza din nou lista de produse.
     */
    public void executareLicitatie(boolean[] vectorAsociereClientiLaLicitatie, CasaDeLicitatii casa, int i,
                                   ArrayList<Broker> listaBrokeri, Licitatie licitatie,
                                   FileWriter myWriter) {
        try {
            licitatie.afisareTextDeInceputLicitatie(myWriter, i);
            licitatie.afisareInformatiiLicitatie(vectorAsociereClientiLaLicitatie, casa,
                                                licitatie, myWriter);

            //Creez o lista doar cu clientii inscrisi la licitatie
            ArrayList<Client> listaClientiInscrisi = new ArrayList<>();
            int nrClienti = casa.getListaClienti().size();
            for(int index = 0; index < nrClienti; ++index) {
                if (vectorAsociereClientiLaLicitatie[index]) {
                    listaClientiInscrisi.add(casa.getListaClienti().get(index));
                }
            }

            int nrPasi = licitatie.getNrPasiMaxim();
            double pretMinim = casa.getListaProduse().get(licitatie.getIdProdus() - 1).getPretMinim();

            /*
                Odata intrat in licitatie, fiecare client isi initializeaza
                pretOferitLaPasulCurent cu pretMinim.
            */
            nrClienti = listaClientiInscrisi.size();
            for(int index = 0; index < nrClienti; ++index) {
                listaClientiInscrisi.get(index).setPretOferitLaPasulCurent(pretMinim);
            }
            //Rulez pasii licitatiei
            myWriter.write("Pretul minim de la care se porneste licitatia = " + pretMinim + "\n");
            for(int index = 0; index < nrPasi; ++index) {
                myWriter.write("PAS" + (index + 1) + ":\n");
                /*
                    Teoretic in punctul asta, brokerii ar trebui sa aiba lista de clienti
                    fie vida, fie cu clientii asociati din licitatie.
                    Parcurg brokerii.
                */
                for (Broker broker : listaBrokeri) {
                    /*
                        Daca brokerul are asociati clienti le cere suma pe care o ofera
                        pentru pasul curent al licitatiei.
                    */
                    int nrClientiBroker = broker.getListaClienti().size();
                    if (nrClienti != 0) {
                        //Parcurg clientii din lista brokerului
                        for (int index2 = 0; index2 < nrClientiBroker; ++index2) {
                            Client client = broker.getListaClienti().get(index2);
                            double margineInferioara = client.getPretOferitLaPasulCurent();
                            double margineSuperioara = client.getPretMaximPentruProdus();
                            /*
                                Calculez pretul pe care il ofera clientul tot cu ajutorul lui random,
                                tinand cont de faptul ca la fiecare pas marginea inferioara este initializata
                                cu pretul maxim oferit la pasul anterior.
                            */
                            double pretOferit = (Math.random() * (margineSuperioara - margineInferioara)) + margineInferioara;
                            client.setPretOferitLaPasulCurent(pretOferit);
                        }
                    }
                }

                //Afisez ofertele clientilor
                for(int index1 = 0; index1 < nrClienti; ++index1) {
                    Client client = listaClientiInscrisi.get(index1);
                    String nume = client.getNume();
                    myWriter.write("*****Clientul " + nume + " ofera " +
                                    client.getPretOferitLaPasulCurent() + "\n");
                }

                /*
                    Daca s-a ajuns la ultimul pas al licitatiei, aplic comisionul brokerului
                    pe preturile oferite de clienti.
                 */
                if(index == nrPasi - 1) {
                    Broker.aplicareComision(listaClientiInscrisi, myWriter);
                }

                /*
                    In acest punct, toti clientii din listaClientiInscrisi vor avea
                    pretulOferitLaPasulCurent setat. Aflu si afisez clientul cu oferta maxima.
                */
                int indexClientCuOfertaMaxima;
                double ofertaMaxima;
                indexClientCuOfertaMaxima = Broker.solicitaSumeleClientilor(listaClientiInscrisi);
                ofertaMaxima = listaClientiInscrisi.get(indexClientCuOfertaMaxima).getPretOferitLaPasulCurent();
                Produs produs = casa.getListaProduse().get(listaClientiInscrisi.get(indexClientCuOfertaMaxima).getIdProdusLicitat() - 1);
                produs.setPretVanzare(ofertaMaxima);
                Client client = listaClientiInscrisi.get(indexClientCuOfertaMaxima);
                myWriter.write("**********Clientul cu oferta maxima la pasul curent este " + client.getNume()
                                + " ---> " + ofertaMaxima + "\n");

                //Setez viitoarele margini inferioare cu valoarea maxima oferita la pasul curent
                for (Client value : listaClientiInscrisi) {
                    value.setPretOferitLaPasulCurent(ofertaMaxima);
                }

                //Daca s-a terminat ultimul pas afisez clientul care a castigat licitatia.
                if(index == nrPasi - 1) {
                    //Daca oferta maxima este mai mica decat pretulMinim pt. produs => nu se vinde
                    if(ofertaMaxima < produs.getPretMinim()) {
                        myWriter.write("\n OFERTA MAXIMA ESTE MAI MICA DECAT PRETUL MINIM PENTRU" +
                                            "PRODUS => PRODUSUL NU SE VINDE.");
                    } else {
                        int nrMaximParticipari = client.getNrParticipari();
                        for (Client value : listaClientiInscrisi) {
                            //Daca clientul inscris ofera egal cu oferta maxima aflata.
                            if (value.getPretOferitLaPasulCurent() == ofertaMaxima) {
                                /*
                                    Daca clientul care ofera la fel de mult are mai
                                    multe participari, atunci el castiga licitatia.
                                */
                                if (value.getNrParticipari() > nrMaximParticipari) {
                                    client = value;
                                    //In variabila client se retine clientul castigator
                                }
                            }
                        }
                        //Afisez mesajul castigator
                        licitatie.afisareMesajCastigator(myWriter, client, ofertaMaxima);

                        //Brokerul elimina din lista de produse, produsul vandut.
                        produs = casa.getListaProduse().get(licitatie.getIdProdus() - 1);
                        Broker.eliminareProdus(produs, casa);

                        //Afisez produsele ramase dupa eliminarea de catre broker a produsului licitat.
                        licitatie.afisareProduseRamaseDupaLicitatie(myWriter, casa);

                        //Incrementez nrParticipari si nrLicitatiiCastigate
                        for (Client clientAux : listaClientiInscrisi) {
                            int nrParticipari = clientAux.getNrParticipari() + 1;
                            int nrLicCastigate = clientAux.getNrLicitatiiCastigate() + 1;
                            if (clientAux == client) {
                                clientAux.setNrLicitatiiCastigate(nrLicCastigate);
                            }
                            clientAux.setNrParticipari(nrParticipari);
                        }
                    }
                }
            }
            Administrator administrator = new Administrator();
            administrator.adaugareProdus(casa, licitatie, myWriter);
            licitatie.afisareTextDeFinalLicitatie(myWriter, i);
        } catch (IOException e) {
            System.out.println("Fisierul nu a fost gasit");
            e.printStackTrace();
        }
    }

    /**
     * @return id
     * GETTER
     */
    public int getId() { return id; }

    /**
     * @return nrParticipanti
     * GETTER
     */
    public int getNrParticipanti() { return nrParticipanti; }

    /**
     * @return idProdus
     * GETTER
     */
    public int getIdProdus() { return idProdus; }

    /**
     * @return nrPasiMaxim
     * GETTER
     */
    public int getNrPasiMaxim() { return nrPasiMaxim; }

    /**
     * @param id
     * SETTER
     */
    public void setId(int id) { this.id = id; }

    /**
     * @param nrParticipanti
     * SETTER
     */
    public void setNrParticipanti(int nrParticipanti) { this.nrParticipanti = nrParticipanti; }

    /**
     * @param idProdus
     * SETTER
     */
    public void setIdProdus(int idProdus) { this.idProdus = idProdus; }

    /**
     * @param nrPasiMaxim
     * SETTER
     */
    public void setNrPasiMaxim(int nrPasiMaxim) { this.nrPasiMaxim = nrPasiMaxim; }
}
