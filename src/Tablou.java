/**
 * Clasa Tablou este o subclasa a clasei Produs. Are constructori,
 * getteri, setteri si campuri specifice.
 */
public class Tablou extends Produs {
    private String numePictor;
    private culori c;

    /**
     * Tipul de culoare folosita la pictarea tabloului
     */
    enum culori {
        ULEI,
        TEMPERA,
        ACRILIC
    }

    /**
     * @param momentulInserarii
     * Constructor cu paramteru apelat doar la instantierea unui Tablou
     * care va fi adaugat de administrator dupa licitatie.
     */
    public Tablou(int momentulInserarii) {
        super(momentulInserarii);
    }

    /**
     * Constructor fara parametri.
     */
    public Tablou() { super(); }

    /**
     * @param id = idul tabloului
     * @param nume = numele tabloului
     * @param pretMinim = pretul minim de la care incepe licitatia
     * @param an = anul crearii
     * @param numePictor = numele pictorului
     * @param c = tipul de culoare folosita
     */
    public Tablou(int id, String nume, double pretMinim, int an, String numePictor, culori c) {
        super(id, nume, pretMinim, an);
        this.numePictor = numePictor;
        this.c = c;
    }

    /**
     * @return
     * GETTER
     */
    public String getNumePictor() { return numePictor; }

    /**
     * @return
     * GETTER
     */
    public culori getC() { return c; }

    /**
     * @param numePictor
     * SETTER
     */
    public void setNumePictor(String numePictor) { this.numePictor = numePictor; }

    /**
     * @param c
     * SETTER
     */
    public void setC(culori c) { this.c = c; }
}
