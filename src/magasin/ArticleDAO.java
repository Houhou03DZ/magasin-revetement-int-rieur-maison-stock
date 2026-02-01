package magasin;

import java.sql.*;
import java.util.ArrayList;

public class ArticleDAO {

    // AJOUTER
    public static void ajouter(Article a) throws Exception {
        String sql = "INSERT INTO article(code, nom, type, prix, stock) VALUES (?,?,?,?,?)";
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, a.getCode());
            ps.setString(2, a.getNom());
            ps.setString(3, a.getType());
            ps.setDouble(4, a.getPrix());
            ps.setInt(5, a.getStock());
            ps.executeUpdate();
        }
    }

    // LIRE TOUS (IMPORTANT : Sol / Mur)
    public static ArrayList<Article> getTous() throws Exception {
        ArrayList<Article> liste = new ArrayList<>();
        String sql = "SELECT code, nom, type, prix, stock FROM article";

        try (Connection c = Db.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String code = rs.getString("code");
                String nom = rs.getString("nom");
                String type = rs.getString("type");
                double prix = rs.getDouble("prix");
                int stock = rs.getInt("stock");

                Article a;
                if (type.equalsIgnoreCase("SOL")) {
                    a = new Sol(code, nom, prix, stock, "N/A");
                } else {
                    a = new Mur(code, nom, prix, stock, "N/A");
                }
                liste.add(a);
            }
        }
        return liste;
    }

    // MODIFIER
    public static void modifier(String code, String nom, double prix, int stock) throws Exception {
        String sql = "UPDATE article SET nom=?, prix=?, stock=? WHERE code=?";
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nom);
            ps.setDouble(2, prix);
            ps.setInt(3, stock);
            ps.setString(4, code);
            ps.executeUpdate();
        }
    }

    // SUPPRIMER
    public static void supprimer(String code) throws Exception {
        String sql = "DELETE FROM article WHERE code=?";
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, code);
            ps.executeUpdate();
        }
    }

    // AJOUTER STOCK
    public static void ajouterStock(String code, int qte) throws Exception {
        String sql = "UPDATE article SET stock = stock + ? WHERE code=?";
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, qte);
            ps.setString(2, code);
            ps.executeUpdate();
        }
    }

    // VENDRE
    public static boolean vendre(String code, int qte) throws Exception {
        String sql = "SELECT stock FROM article WHERE code=?";
        int stockActuel;

        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return false;
            stockActuel = rs.getInt("stock");
        }

        if (qte <= 0 || qte > stockActuel) return false;

        String sqlUpdate = "UPDATE article SET stock = stock - ? WHERE code=?";
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sqlUpdate)) {

            ps.setInt(1, qte);
            ps.setString(2, code);
            ps.executeUpdate();
        }
        return true;
    }
}
