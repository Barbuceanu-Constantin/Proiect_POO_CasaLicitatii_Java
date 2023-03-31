/**
 * Clasa client contine implementarea metodei toString(), constructori, getteri si setteri.
 */
public class Client {
    private int id, nrParticipari, nrLicitatiiCastigate;
    private int idProdusLicitat;
    private double pretMaximPentruProdus;   //Pretul maxim pe care este dispus un client sa il ofere pentru un produs.
    private double pretOferitLaPasulCurent;
    private String nume, adresa;
    private Broker broker;

    /**
     * @param client = clientul ale carui campuri se vor a fi afisate
     * @return stringToReturn = stringul cu informatia despre client
     *
     * Metoda intoarce un string care contine informatiile specifice
     * ale unui client in functie de tipul lui.
     */
    public String toString(Client client){
        String stringToReturn;
        if(client instanceof PersoanaFizica) {
            stringToReturn = "(" + client.getNume() + ") (" + client.getAdresa() + ") (" +
                            ((PersoanaFizica) client).getDataNastere() + ") (nrParticipari = "
                            + client.getNrParticipari() + ") (nrLicitatiiCastigate = " +
                            client.getNrLicitatiiCastigate() + ")\n";
        } else {
            stringToReturn = "(" + client.getNume() + ") (" + client.getAdresa() +
                            ") (" + ((PersoanaJuridica) client).getCapitalSocial() + ") ("
                            + ((PersoanaJuridica) client).getC() + ") (nrParticipari = "
                            + client.getNrParticipari() + ") (nrLicitatiiCastigate = " +
                            client.getNrLicitatiiCastigate() + ")\n";
        }
        return stringToReturn;
    }

    /**
     * Constructor fara parametri
     */
    public Client() { }

    /**
     * @param id = idul clientului
     * @param nrParticipari = nrul de particpari la licitatii ale clientului
     * @param nrLicitatiiCastigate = nrul de licitatii castigate ale clientului
     * @param nume = numele clientului
     * @param adresa = adresa clientului
     * Constructor cu parametri
     */
    public Client(int id, int nrParticipari, int nrLicitatiiCastigate, String nume, String adresa) {
        this.id = id;
        this.nrParticipari = nrParticipari;
        this.nrLicitatiiCastigate = nrLicitatiiCastigate;
        this.nume = nume;
        this.adresa = adresa;
        this.broker = null;
        this.pretMaximPentruProdus = 0;
        this.idProdusLicitat = 0;
        this.pretOferitLaPasulCurent = 0;
    }

    /**
     * @return pretOferitLaPasulCurent
     * GETTER
     */
    public double getPretOferitLaPasulCurent() { return pretOferitLaPasulCurent; }

    /**
     * @param pretOferitLaPasulCurent
     * SETTER
     */
    public void setPretOferitLaPasulCurent(double pretOferitLaPasulCurent) { this.pretOferitLaPasulCurent = pretOferitLaPasulCurent; }

    /**
     * @param idProdusLicitat
     * SETTER
     */
    public void setIdProdusLicitat(int idProdusLicitat) { this.idProdusLicitat = idProdusLicitat; }

    /**
     * @param pretMaximPentruProdus
     * SETTER
     */
    public void setPretMaximPentruProdus(double pretMaximPentruProdus) { this.pretMaximPentruProdus = pretMaximPentruProdus; }

    /**
     * @return idProdusLicitat
     * GETTER
     */
    public int getIdProdusLicitat() { return idProdusLicitat; }

    /**
     * @return pretMaximPentruProdus
     * GETTER
     */
    public double getPretMaximPentruProdus() { return pretMaximPentruProdus; }

    /**
     * @return broker
     * GETTER
     */
    public Broker getBroker() { return broker; }

    /**
     * @param broker
     * SETTER
     */
    public void setBroker(Broker broker) { this.broker = broker; }

    /**
     * @return id
     * GETTER
     */
    public int getId() { return id; }

    /**
     * @return nrParticpari
     * GETTER
     */
    public int getNrParticipari() { return nrParticipari; }

    /**
     * @return nrLicitattiiCastigate
     * GETTER
     */
    public int getNrLicitatiiCastigate() { return nrLicitatiiCastigate; }

    /**
     * @return nume
     * GETTER
     */
    public String getNume() { return nume; }

    /**
     * @return adresa
     * GETTER
     */
    public String getAdresa() { return adresa; }

    /**
     * @param id
     * SETTER
     */
    public void setId(int id) { this.id = id; }

    /**
     * @param nrParticipari
     * SETTER
     */
    public void setNrParticipari(int nrParticipari) { this.nrParticipari = nrParticipari; }

    /**
     * @param nrLicitatiiCastigate
     * SETTER
     */
    public void setNrLicitatiiCastigate(int nrLicitatiiCastigate) { this.nrLicitatiiCastigate = nrLicitatiiCastigate; }

    /**
     * @param nume
     * SETTER
     */
    public void setNume(String nume) { this.nume = nume; }

    /**
     * @param adresa
     * SETTER
     */
    public void setAdresa(String adresa) { this.adresa = adresa; }
}
