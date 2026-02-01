package magasin;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Magasin magasin = new Magasin();

        try {
            magasin.chargerDepuisBDD();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int choix;
        do {
        	System.out.println("======Bienvenue dans le système de gestion de stock du magasin de revêtements Hanafi KH======");
            System.out.println("\n=== MAGASIN REVÊTEMENT ===");
            System.out.println("1. Ajouter SOL");
            System.out.println("2. Ajouter MUR");
            System.out.println("3. Afficher");
            System.out.println("4. Modifier");
            System.out.println("5. Supprimer");
            System.out.println("6. Ajouter stock");
            System.out.println("7. Vendre");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");

            choix = Integer.parseInt(sc.nextLine());

            try {
                switch (choix) {

                    case 1 -> {
                        System.out.print("Code : ");
                        String code = sc.nextLine();
                        System.out.print("Nom : ");
                        String nom = sc.nextLine();
                        System.out.print("Prix : ");
                        double prix = Double.parseDouble(sc.nextLine());
                        System.out.print("Stock : ");
                        int stock = Integer.parseInt(sc.nextLine());
                        System.out.print("Matériau : ");
                        String mat = sc.nextLine();
                        magasin.ajouter(new Sol(code, nom, prix, stock, mat));
                    }

                    case 2 -> {
                        System.out.print("Code : ");
                        String code = sc.nextLine();
                        System.out.print("Nom : ");
                        String nom = sc.nextLine();
                        System.out.print("Prix : ");
                        double prix = Double.parseDouble(sc.nextLine());
                        System.out.print("Stock : ");
                        int stock = Integer.parseInt(sc.nextLine());
                        System.out.print("Finition : ");
                        String fin = sc.nextLine();
                        magasin.ajouter(new Mur(code, nom, prix, stock, fin));
                    }

                    case 3 -> magasin.afficherTous();

                    case 4 -> {
                        System.out.print("Code : ");
                        String code = sc.nextLine();
                        System.out.print("Prix : ");
                        double prix = Double.parseDouble(sc.nextLine());
                        System.out.print("Stock : ");
                        int stock = Integer.parseInt(sc.nextLine());
                        System.out.println(magasin.modifier(code, prix, stock) ? "✅ Modifié" : "❌ Introuvable");
                    }

                    case 5 -> {
                        System.out.print("Code : ");
                        String code = sc.nextLine();
                        System.out.println(magasin.supprimer(code) ? "🗑️ Supprimé" : "❌ Introuvable");
                    }

                    case 6 -> {
                        System.out.print("Code : ");
                        String code = sc.nextLine();
                        System.out.print("Quantité : ");
                        int qte = Integer.parseInt(sc.nextLine());
                        System.out.println(magasin.ajouterStock(code, qte) ? "✅ Stock ajouté" : "❌ Erreur");
                    }

                    case 7 -> {
                        System.out.print("Code : ");
                        String code = sc.nextLine();
                        System.out.print("Quantité : ");
                        int qte = Integer.parseInt(sc.nextLine());
                        System.out.println(magasin.vendre(code, qte) ? "✅ Vente OK" : "❌ Vente impossible");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } while (choix != 0);

        sc.close();
    }
}
