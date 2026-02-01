package magasin;

public class Mur extends Article {

    private String finition;

    public Mur(String code, String nom, double prix, int stock, String finition) {
        super(code, nom, prix, stock);
        this.finition = finition;
    }

    @Override
    public String getType() {
        return "MUR";
    }

    @Override
    public void afficher() {
        System.out.println(code + " | " + nom + " | MUR | " +
                finition + " | " + prix + "$ | stock=" + stock);
    }
}
