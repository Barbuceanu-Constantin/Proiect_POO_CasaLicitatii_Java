/**
 * Clasa Mobila este o subclasa a clasei Produs.
 * Are constructori, getteri, setteri si campuri specifice.
 */
public class Mobila extends Produs {
    private String tip, material;

    /**
     * @param momentulInserarii
     *
     * Constructor apelat doar la instantierea unui produs Mobila care va fi
     * adaugata in listaProduse la finalul unei anumite licitatii.
     */
    public Mobila(int momentulInserarii) {
        super(momentulInserarii);
    }

    /**
     * Constructor fara parametri.
     */
    public Mobila() { super(); }

    /**
     * @param id = id-ul produsului
     * @param nume = numele produsului
     * @param pretMinim = pretulMinim
     * @param an = anul de fabricatie
     * @param t = tipul produsului
     * @param m = materialul din care e facut
     * Constructor cu parametri.
     */
    public Mobila(int id, String nume, double pretMinim, int an, String t, String m) {
        super(id, nume, pretMinim, an);
        this.tip = t;
        this.material = m;
    }

    /**
     * @return tip
     * GETTER
     */
    public String getTip() {
        return tip;
    }

    /**
     * @return material
     * GETTER
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param tip
     * SETTER
     */
    public void setTip(String tip) {
        this.tip = tip;
    }

    /**
     * @param material
     * SETTER
     */
    public void setMaterial(String material) {
        this.material = material;
    }
}
