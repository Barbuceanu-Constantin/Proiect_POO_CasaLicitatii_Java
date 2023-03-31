/**
 * Clasa PersoanaFizica extinde clasa Client.
 */
public class PersoanaFizica extends Client {
    private String dataNastere;

    /**
     * Constructor fara parametri
     */
    public PersoanaFizica() { super(); }

    /**
     * @param id = id-ul persoanei fizice
     * @param nrPart = nr-ul de particpanti
     * @param nrLicCastig = nr-ul licitatiilor castigatoare
     * @param nume = numele clientului
     * @param adr = adresa
     * @param dataN = data Nasterii
     * Constructor cu parametri.
     */
    public PersoanaFizica(int id, int nrPart, int nrLicCastig, String nume, String adr, String dataN) {
        super(id, nrPart, nrLicCastig, nume, adr);
        this.dataNastere = dataN;
    }

    /**
     * @return dataNasterii
     * GETTER
     */
    public String getDataNastere() { return dataNastere; }

    /**
     * @param dataNastere
     * SETTER
     */
    public void setDataNastere(String dataNastere) { this.dataNastere = dataNastere; }
}
