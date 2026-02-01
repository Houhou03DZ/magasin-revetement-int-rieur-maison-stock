package magasin;

public class Sol extends Article {

    private String materiau;

    public Sol(String code, String nom, double prix, int stock, String materiau) {
        super(code, nom, prix, stock);
        this.materiau = materiau;
    }

    @Override
    public String getType() {
        return "SOL";
    }

    @Override
    public void afficher() {
        System.out.println(code + " | " + nom + " | SOL | " +
                materiau + " | " + prix + "$ | stock=" + stock);
    }
}
