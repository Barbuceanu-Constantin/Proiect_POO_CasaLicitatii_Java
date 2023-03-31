/**
 * Clasa PersoanaJuridica extinde clasa Client.
 */
public class PersoanaJuridica extends Client {
    private double capitalSocial;
    private companie c;

    /**
     * Cele 2 tipuri de companie.
     */
    enum companie {
        SRL,
        SA
    }

    /**
     * Constructor fara parametri.
     */
    public PersoanaJuridica() { super(); }

    /**
     * @param id = idul clientului
     * @param nrPart = nr-ul de participari la licitatie
     * @param nrLicCastig = nr-ul de licitatii castigate
     * @param nume = numele clientului persoana juridica
     * @param adr = adresa
     * @param capSoc = capital social
     * @param c = tipul companiei
     * Constructor cu parametri.
     */
    public PersoanaJuridica(int id, int nrPart, int nrLicCastig, String nume, String adr, double capSoc, companie c) {
        super(id, nrPart, nrLicCastig, nume, adr);
        this.capitalSocial = capSoc;
        this.c = c;
    }

    /**
     * @return capitalSocial
     * GETTER
     */
    public double getCapitalSocial() { return capitalSocial; }

    /**
     * @return c
     * GETTER
     */
    public companie getC() { return c; }

    /**
     * @param capitalSocial
     * SETTER
     */
    public void setCapitalSocial(double capitalSocial) { this.capitalSocial = capitalSocial; }

    /**
     * @param c
     * SETTER
     */
    public void setC(companie c) { this.c = c; }
}
