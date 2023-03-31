import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

/**
 * Clasa Test este clasa principala din care se incepe rularea programului.
 * Contine metoda main() care creeaza casa de licitatii, instantiind-o dupa
 * citirea datelor din fisierul de input si crearea listelor.
 */
public class Test {
    /**
     * @param args = argumente in linia de comanda (nu se folosesc aici)
     * Metoda citeste datele din fisier, creaza listele stocate in casa de
     * licitatii si apeleaza parcurgereLicitatii().
     */
    public static void main(String[] args) {
        ArrayList<Produs> listaProduse = new ArrayList<>();
        ArrayList<Client> listaClienti = new ArrayList<>();
        ArrayList<Licitatie> listaLicitatiiActive = new ArrayList<>();
        ArrayList<Broker> listaBrokeri = new ArrayList<>();
        ArrayList<Produs> listaProduseDeInserat = new ArrayList<>();
        int nrClienti, nrBrokeri, nrProduse, nrLicitatii;
        int nrProduseDeInserat;
        Random rand = new Random();

        try {
            File f = new File("Tests/date4.in");
            Scanner myReader = new Scanner(f);
            nrClienti = myReader.nextInt();
            nrBrokeri = myReader.nextInt();
            nrProduse = myReader.nextInt();
            nrLicitatii = myReader.nextInt();
            nrProduseDeInserat = myReader.nextInt();

            //Creez listaClienti
            for (int i = 1; i <= nrClienti; ++i) {
                int id = myReader.nextInt();
                int nrPart = myReader.nextInt();
                int nrLicCastig = myReader.nextInt();
                String nume = myReader.next();
                String adresa = myReader.next();
                String tip_persoana = myReader.next();
                switch (tip_persoana) {
                    case "persoana_fizica" -> {
                        String dataNasterii = myReader.next();
                        Client client = new PersoanaFizica(id, nrPart, nrLicCastig, nume, adresa, dataNasterii);
                        listaClienti.add(client);
                    }
                    case "persoana_juridica" -> {
                        String tip_firma = myReader.next();
                        PersoanaJuridica.companie companie = switch (tip_firma) {
                            case "SRL" -> PersoanaJuridica.companie.SRL;
                            case "SA" -> PersoanaJuridica.companie.SA;
                            default -> PersoanaJuridica.companie.SA;
                        };
                        double capital_social = myReader.nextDouble();
                        Client client = new PersoanaJuridica(id, nrPart, nrLicCastig, nume, adresa,
                                                            capital_social, companie);
                        listaClienti.add(client);
                    }
                }

            }

            //Creez listaBrokeri
            for (int i = 1; i <= nrBrokeri; ++i) {
                ArrayList<Client> lCl = new ArrayList<>();
                Broker broker = new Broker(lCl);
                listaBrokeri.add(broker);
            }

            //Creez listaProduse
            for (int i = 1; i <= nrProduse; ++i) {
                int id = myReader.nextInt();
                String nume = myReader.next();
                //Generez aleator pretul minim de pornire al produsului ca un procent din 50000.
                double pretMinim = Math.random() * rand.nextInt(50000);
                int an = myReader.nextInt();
                String tipProdus = myReader.next();
                switch (tipProdus) {
                    case "B" -> {
                        String material = myReader.next();
                        int piatraPr = myReader.nextInt();
                        boolean piatraPretioasa = true;
                        switch (piatraPr) {
                            case 1:
                                break;
                            case 0:
                                piatraPretioasa = false;
                                break;
                        }
                        listaProduse.add(new Bijuterie(id, nume, pretMinim, an, material, piatraPretioasa));
                    }
                    case "M" -> {
                        String tip = myReader.next();
                        String material = myReader.next();
                        listaProduse.add(new Mobila(id, nume, pretMinim, an, tip, material));
                    }
                    case "T" -> {
                        String numePictor = myReader.next();
                        String culoare = myReader.next();
                        Tablou.culori c = switch (culoare) {
                            case "ULEI" -> Tablou.culori.ULEI;
                            case "TEMPERA" -> Tablou.culori.TEMPERA;
                            case "ACRILIC" -> Tablou.culori.ACRILIC;
                            default -> Tablou.culori.ULEI;
                        };
                        listaProduse.add(new Tablou(id, nume, pretMinim, an, numePictor, c));
                    }
                }
            }

            //Creez listaLicitatiiActive
            for (int i = 1; i <= nrLicitatii; ++i) {
                int id = myReader.nextInt();
                int nrParticipanti = myReader.nextInt();
                int nrPasiMaxim = myReader.nextInt();
                listaLicitatiiActive.add(new Licitatie(id, nrParticipanti, nrPasiMaxim));
            }

            //Creez listaProduseDeInserat
            for (int i = 0; i < nrProduseDeInserat; ++i) {
                Produs produs = null;
                //Aleg aleator tipul produsului.
                int tip = 1 + rand.nextInt(3);
                int momentulInserarii;
                /*
                    momentulInserarii = 0 => inserarea se face la finalul licitatiei 1
                    momentulInserarii = 1 => inserarea se face la finalul licitatiei 2
                    momentulInserarii = 2 => inserarea se face la finalul licitatiei 3
                    ...
                 */
                momentulInserarii = rand.nextInt(nrLicitatii);
                switch (tip) {
                    case 1 -> {
                        produs = new Tablou(momentulInserarii);
                        System.out.println("momentul_inserarii = " + momentulInserarii +
                                            " ; tip_produs = " + produs.getClass());
                    }
                    case 2 -> {
                        produs = new Mobila(momentulInserarii);
                        System.out.println("momentul_inserarii = " + momentulInserarii +
                                            " ; tip_produs = " + produs.getClass());
                    }
                    case 3 -> {
                        produs = new Bijuterie(momentulInserarii);
                        System.out.println("momentul_inserarii = " + momentulInserarii +
                                            " ; tip_produs = " + produs.getClass());
                    }
                }
                listaProduseDeInserat.add(produs);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit");
            e.printStackTrace();
        }

        CasaDeLicitatii casa = CasaDeLicitatii.Instanta(listaProduse, listaClienti, listaLicitatiiActive,
                                                        listaProduseDeInserat);
        casa.parcurgereLicitatii(listaBrokeri);
    }
}
