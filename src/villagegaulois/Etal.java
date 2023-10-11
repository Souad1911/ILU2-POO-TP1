package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		if (!this.etalOccupe) {
			throw new IllegalStateException();
		}

		this.etalOccupe = false;
		StringBuilder chaine = new StringBuilder("Le vendeur " + this.vendeur.getNom() + " quitte son étal, ");
		int produitVendu = this.quantiteDebutMarche - this.quantite;
		if (produitVendu > 0) {
			chaine.append("il a vendu " + produitVendu + " parmi " + this.produit + ".\n");
		} else {
			chaine.append("il n'a malheureusement rien vendu.\n");
		}
		return chaine.toString();
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
		if (acheteur == null) {
			throw new NullPointerException();
		}
		if (!this.etalOccupe) {
			throw new IllegalStateException();
		}
		if (quantiteAcheter < 1) {
			throw new IllegalArgumentException();
		}

		StringBuilder chaine = new StringBuilder();
		try {
			chaine.append(acheteur.getNom() + " veut acheter " + quantiteAcheter + " " + this.produit + " à "
					+ this.vendeur.getNom());
			if (this.quantite == 0) {
				chaine.append(", malheureusement il n'y en a plus !");
				quantiteAcheter = 0;
			}
			if (quantiteAcheter > this.quantite) {
				chaine.append(", comme il n'y en a plus que " + this.quantite + ", " + acheteur.getNom()
						+ " vide l'étal de " + this.vendeur.getNom() + ".\n");
				quantiteAcheter = this.quantite;
				this.quantite = 0;
			}
			if (this.quantite != 0) {
				this.quantite -= quantiteAcheter;
				chaine.append(". " + acheteur.getNom() + ", est ravi de tout trouver sur l'étal de "
						+ this.vendeur.getNom() + "\n");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return chaine.toString();
	}

	public boolean contientProduit(String produit) {
		return this.produit.equals(produit);
	}

}
