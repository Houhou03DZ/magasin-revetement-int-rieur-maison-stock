package magasin;

import java.util.ArrayList;

public class Magasin {

    private ArrayList<Article> stock = new ArrayList<>();

    public void chargerDepuisBDD() throws Exception {
        stock.clear();
        stock.addAll(ArticleDAO.getTous());
    }

    public void ajouter(Article a) throws Exception {
        ArticleDAO.ajouter(a);
        stock.add(a);
    }

    public void afficherTous() {
        if (stock.isEmpty()) {
            System.out.println("Stock vide.");
            return;
        }
        for (Article a : stock) a.afficher();
    }

    public Article rechercher(String code) {
        for (Article a : stock)
            if (a.getCode().equalsIgnoreCase(code)) return a;
        return null;
    }

    public boolean modifier(String code, double prix, int stockNouveau) throws Exception {
        Article a = rechercher(code);
        if (a == null) return false;

        ArticleDAO.modifier(code, a.getNom(), prix, stockNouveau);
        a.setPrix(prix);
        a.setStock(stockNouveau);
        return true;
    }

    public boolean supprimer(String code) throws Exception {
        Article a = rechercher(code);
        if (a == null) return false;

        ArticleDAO.supprimer(code);
        stock.remove(a);
        return true;
    }

    public boolean ajouterStock(String code, int qte) throws Exception {
        Article a = rechercher(code);
        if (a == null) return false;

        ArticleDAO.ajouterStock(code, qte);
        a.setStock(a.getStock() + qte);
        return true;
    }

    public boolean vendre(String code, int qte) throws Exception {
        Article a = rechercher(code);
        if (a == null) return false;

        boolean ok = ArticleDAO.vendre(code, qte);
        if (ok) a.setStock(a.getStock() - qte);
        return ok;
    }
}
