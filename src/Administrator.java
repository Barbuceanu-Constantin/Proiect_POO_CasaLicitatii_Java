import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * Clasa Administrator se ocupa de inserarea produselor in listaProduse
 * din CasaDeLicitatii, in urma terminarii unei licitatii. Practic la
 * finalul fiecarei licitatii se verifica daca exista produse care sa
 * fie adaugate dupa licitatia respectiva.
 */
public class Administrator extends Angajat {

    /**
     * Constructor fara parametri.
     */
    public Administrator() {  }

    /**
     * @param casa = instanta unica a casei de licitatii
     * @param licitatie = licitatia care tocmai s-a terminat
     * @param w = obiectul FileWriter prin care se scrie in fisierul de output
     *
     * Metoda verifica daca dupa licitatia care tocmai
     * s-a terminat trebuie adaugate produse. Daca exista produse de adaugat se adauga.
     */
    public void adaugareProdus(CasaDeLicitatii casa, Licitatie licitatie, FileWriter w) {
        Scanner sc = new Scanner(System.in);

        try {
            //indexul licitatiei in listaLicitatii
            int indexLicitatie = licitatie.getId() - 1;
            //lista produselor care trebuie inserate in lista generala de produse
            ArrayList<Produs> listaProduse = casa.getListaProduseDeInserat();
            //presupun ca dupa licitatia care tocmai s-a incheiat nu se introduce niciun produs
            boolean saInserat = false;
            //Folosesc random doar ca sa aleg aleator intre ACRILIC, ULEI si TEMPERA la tablouri
            Random rand = new Random();

            /*
                Parcurg lista de produse care trebuie inserate. Acestea au
                momentulInserarii setat diferit de -1. -1 il au elementele care
                au fost de la inceput bagate in lista.
            */
            for (Produs produs : listaProduse) {
                /*
                    Daca am gasit un produs cu un moment al inserarii egal cu indexul licitatiei care
                    tocmai s-a terminat, atunci citesc din consola datele produsului si il adaug in
                    lista de produse. Dupa inserare setez saInserat la true.
                */
                if (produs.getMomentulInserarii() == indexLicitatie) {
                    //Daca produsul este de tip Tablou citesc datele conform cu campurile specifice
                    if (produs instanceof Tablou) {
                        System.out.println();
                        System.out.println("Finalul licitatiei " + licitatie.getId());
                        System.out.println("Introduceti datele corespunzatoare tabloului.");
                        int id = casa.getListaProduse().size() + 1;
                        String nume;
                        System.out.print("nume = ");
                        nume = sc.next();
                        double pretMinim;
                        System.out.print("pretMinim = ");
                        pretMinim = sc.nextDouble();
                        int an;
                        System.out.print("an = ");
                        an = sc.nextInt();
                        String numePictor;
                        System.out.print("Nume pictor = ");
                        numePictor = sc.next();
                        int tipCuloare;
                        tipCuloare = rand.nextInt(3);
                        Tablou.culori culoare = switch (tipCuloare) {
                            case 0 -> Tablou.culori.ACRILIC;
                            case 1 -> Tablou.culori.ULEI;
                            case 2 -> Tablou.culori.TEMPERA;
                            default -> Tablou.culori.ACRILIC;
                        };
                        w.write("\n\nADMINISTRATORUL INTRODUCE TABLOUL CU URMATOARELE SPECIFICATII.\n");
                        produs.setId(id);
                        produs.setNume(nume);
                        produs.setPretMinim(pretMinim);
                        produs.setAn(an);
                        ((Tablou) produs).setNumePictor(numePictor);
                        ((Tablou) produs).setC(culoare);
                        w.write(produs.toString(produs));
                    }
                    //Daca produsul este de tip Mobila citesc datele conform cu campurile specifice
                    if (produs instanceof Mobila) {
                        System.out.println();
                        System.out.println("Finalul licitatiei " + licitatie.getId());
                        System.out.println("Introduceti datele corespunzatoare mobilei.");
                        int id = casa.getListaProduse().size() + 1;
                        String nume;
                        System.out.print("nume = ");
                        nume = sc.next();
                        double pretMinim;
                        System.out.print("pretMinim = ");
                        pretMinim = sc.nextDouble();
                        int an;
                        System.out.print("an = ");
                        an = sc.nextInt();
                        String t, m;
                        System.out.print("tip_mobila = ");
                        t = sc.next();
                        System.out.print("material = ");
                        m = sc.next();
                        w.write("\n\nADMINISTRATORUL INTRODUCE MOBILA CU URMATOARELE SPECIFICATII.\n");
                        produs.setId(id);
                        produs.setNume(nume);
                        produs.setPretMinim(pretMinim);
                        produs.setAn(an);
                        ((Mobila) produs).setMaterial(m);
                        ((Mobila) produs).setTip(t);
                        w.write(produs.toString(produs));
                    }
                    //Daca produsul este de tip Bijuterie citesc datele conform cu campurile specifice
                    if (produs instanceof Bijuterie) {
                        System.out.println();
                        System.out.println("Finalul licitatiei " + licitatie.getId());
                        System.out.println("Introduceti datele corespunzatoare bijuteriei.");
                        int id = casa.getListaProduse().size() + 1;
                        String nume;
                        System.out.print("nume = ");
                        nume = sc.next();
                        double pretMinim;
                        System.out.print("pretMinim = ");
                        pretMinim = sc.nextDouble();
                        int an;
                        System.out.print("an = ");
                        an = sc.nextInt();
                        String material;
                        String piatraPretioasa;
                        System.out.print("material = ");
                        material = sc.next();
                        System.out.print("piatraPretioasa = ");
                        piatraPretioasa = sc.next();
                        w.write("\n\nADMINISTRATORUL INTRODUCE BIJUTERIA CU URMATOARELE SPECIFICATII.\n");
                        produs.setId(id);
                        produs.setNume(nume);
                        produs.setPretMinim(pretMinim);
                        produs.setAn(an);
                        ((Bijuterie) produs).setMaterial(material);
                        if (piatraPretioasa.equals("true")) {
                            ((Bijuterie) produs).setPiatraPretioasa(true);
                        } else if (piatraPretioasa.equals(("false"))) {
                            ((Bijuterie) produs).setPiatraPretioasa(false);
                        }
                        w.write(produs.toString(produs));
                    }
                    casa.getListaProduse().add(produs);
                    saInserat = true;
                }
            }
            //Daca s-a inserat cel putin un produs se reafiseaza toate produsele din momentul curent.
            if(saInserat) {
                licitatie.afisareProduseRamaseDupaLicitatie(w, casa);
            }
        } catch (IOException e) {
            System.out.println("Eroare la scriere in fisier.");
            e.printStackTrace();
        }
    }
}
