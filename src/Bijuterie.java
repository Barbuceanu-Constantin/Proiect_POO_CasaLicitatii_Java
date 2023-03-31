/**
 * Clasa Bijuterie este subclasa a clasei Produs.
 * Are constructori, geteri, seteri si campuri specifice.
 */
public class Bijuterie extends Produs {
    private String material;
    private boolean piatraPretioasa;

    /**
     * @param momentulInserarii
     *
     * Constructorul asta e folosit daca se instantiaza
     * o bijuterie care va fi adaugata dupa o anumita licitatie.
     */
    public Bijuterie(int momentulInserarii) {
        super(momentulInserarii);
    }

    /**
     * Cosntructor fara parametrii
     */
    public Bijuterie() { super(); }

    /**
     * @param id = idul bijuteriei
     * @param nume = numele bijuteriei
     * @param pretMinim = pretul minim de la care porneste licitatia
     * @param an = anul fabricatiei
     * @param m = material
     * @param pP = piatra pretioasa
     *
     * Constructor cu parametri care se foloseste pentru initializarea campurilor obiectului.
     */
    public Bijuterie(int id, String nume, double pretMinim, int an, String m, boolean pP) {
        super(id, nume, pretMinim, an);
        this.material = m;
        this.piatraPretioasa = pP;
    }

    /**
     * @return material
     * GETER
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @return piatraPretioasa
     * GETER
     */
    public boolean isPiatraPretioasa() {
        return piatraPretioasa;
    }

    /**
     * @param material
     * SETER
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @param piatraPretioasa
     * SETER
     */
    public void setPiatraPretioasa(boolean piatraPretioasa) {
        this.piatraPretioasa = piatraPretioasa;
    }
}
