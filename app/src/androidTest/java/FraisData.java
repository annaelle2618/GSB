public class FraisData {
    private String libelle;
    private String montant;
    private String Datefrais;

    public FraisData(String libelle, String montant, String Datefrais) {
        this.libelle = libelle;
        this.montant = montant;
        this.Datefrais = Datefrais;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }
    public String getDatefrais() {
        return Datefrais;
    }

    public void setDatefrais(String Datefrais) {
        this.Datefrais = Datefrais;
    }


}

