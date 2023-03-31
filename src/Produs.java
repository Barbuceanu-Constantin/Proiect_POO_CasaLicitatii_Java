/**
 * Clasa Produs are getteri, setteri, constructori si clasa toString().
 */
public class Produs {
    private int id;
    private String nume;
    private double pretVanzare;
    private double pretMinim;
    private int an;
    private int momentulInserarii;

    /**
     * @param p = produsul ale carui date se vrea sa fie asfisate
     * @return stringToReturn
     */
    public String toString(Produs p) {
        String stringToReturn = null;
        if (p instanceof Tablou) {
            stringToReturn = "(" + p.getId() + ") (" + p.getNume() + ") (" + p.getAn() +
                            ") (pretMinim = " + p.getPretMinim() +
                            ") (" + ((Tablou) p).getNumePictor() + ") (" + ((Tablou) p).getC() + ")\n";
        } else if (p instanceof Mobila) {
            stringToReturn = "(" + p.getId() + ") (" + p.getNume() + ") (" + p.getAn() +
                            ") (pretMinim = " + p.getPretMinim() +
                            ") (" + ((Mobila) p).getMaterial() + ") (" + ((Mobila) p).getTip() + ")\n";
        } else if (p instanceof Bijuterie) {
            stringToReturn = "(" + p.getId() + ") (" + p.getNume() + ") (" + p.getAn() +
                             ") (pretMinim = " + p.getPretMinim() +
                             ") (" + ((Bijuterie) p).getMaterial() + ") (piatraPretioasa = " +
                             ((Bijuterie)p).isPiatraPretioasa() + ")\n";
        }
        return stringToReturn;
    }

    /**
     * @param momentulInserarii
     *
     * Constructorul asta este apelat numai cand se creeaza un produs care
     * urmeaza sa fie adaugat in lista de produse, la finalul unei licitatii.
     * momentulInserarii apartine {0,1,2,3,...,nrLicitatii - 1}.
     */
    public Produs(int momentulInserarii) {
        this.momentulInserarii = momentulInserarii;
    }

    /**
     * Constructor fara parametri.
     */
    protected Produs() { }

    /**
     * @param id = id-ul produsului
     * @param nume = numele produsului
     * @param pretMinim = pretulMinim al produsului
     * @param an = anul de fabricatie
     *
     * Constructor cu parametri apelat in cazul instantierii produselor care
     * se adauga initial in lista inainte de licitatii. Pentru acestea
     * momentulInserarii este setat automat la -1.
     */
    protected Produs(int id, String nume, double pretMinim, int an) {
        this.id = id;
        this.nume = nume;
        this.pretVanzare = 0;
        this.pretMinim = pretMinim;
        this.an = an;
        this.momentulInserarii = -1;
    }

    /**
     * @return momentulInserarii
     * GETTER
     */
    public int getMomentulInserarii() { return momentulInserarii; }

    /**
     * @param momentulInserarii
     * SETTER
     */
    public void setMomentulInserarii(int momentulInserarii) { this.momentulInserarii = momentulInserarii; }

    /**
     * @return nume
     * GETTER
     */
    public String getNume() { return nume; }

    /**
     * @param nume
     * SETTER
     */
    public void setNume(String nume) { this.nume = nume; }

    /**
     * @return pretVanzare
     * GETTER
     */
    public double getPretVanzare() { return pretVanzare; }

    /**
     * @param pretVanzare
     * SETTER
     */
    public void setPretVanzare(double pretVanzare) { this.pretVanzare = pretVanzare; }

    /**
     * @return pretMinim
     * GETTER
     */
    public double getPretMinim() { return pretMinim; }

    /**
     * @param pretMinim
     * SETTER
     */
    public void setPretMinim(double pretMinim) { this.pretMinim = pretMinim; }

    /**
     * @return an
     * GETTER
     */
    public int getAn() { return an; }

    /**
     * @param an
     * SETTER
     */
    public void setAn(int an) { this.an = an; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
