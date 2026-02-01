package magasin;

public abstract class Article implements Affichable {

    protected String code;
    protected String nom;
    protected double prix;
    protected int stock;

    public Article(String code, String nom, double prix, int stock) {
        this.code = code;
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
    }

    public String getCode() { return code; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }
    public int getStock() { return stock; }

    public void setPrix(double prix) { this.prix = prix; }
    public void setStock(int stock) { this.stock = stock; }

    public abstract String getType();
}
