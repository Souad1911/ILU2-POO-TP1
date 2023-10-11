package villagegaulois;

import personnages.Chef;
import exception.VillageSansChefException;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche ;

	public Village(String nom, int nbVillageoisMaximum,int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		
		this.marche = new Marche(nbEtal);
	}
	private static class Marche {
		private Etal[] etals;
		private int nbEtal;

		private Marche(int nbEtal) {
			super();
			this.nbEtal = nbEtal;
			this.etals = new Etal[nbEtal];
			for (int i = 0; i < nbEtal; i++) {
				this.etals[i] = new Etal();
			}
		}
	
		private void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit , int nbProduit){
			this.etals[indiceEtal].occuperEtal(vendeur,produit , nbProduit);
			
			}
		private int trouverEtalLibre() {
			int i = 0;
			while (this.etals[i].isEtalOccupe ()&& i<this.nbEtal) {
				i++;
			}
			if (i==this.nbEtal-1) {
				return -1;
				
			}else {
				return i;
			}
		}
		public Etal[] trouverEtals(String produit ) {
			int nbEtalprod=0;
			for (int i=0;i<nbEtal;i++) {
				if (etals [i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					nbEtalprod ++;
				}
			}
			Etal[] etalsProd = new Etal [nbEtalprod];
			for (int i=0; i<nbEtal ;i++) {
				if (etals[i].isEtalOccupe()&& etals[i].contientProduit(produit)) {
					etalsProd[i]=etals[i];
				}
			}
			return etalsProd;
			
			
		}
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0;i<this.nbEtal; i++){
				if (gaulois== this.etals[i].getVendeur()) {
					return this.etals[i];
				}
			}
			
			return null ;
			
	}
		private void afficherMarche () {
			int nbEtalVide = 0;
			for (int i =0; i<this.nbEtal;i++) {
				if (this.etals[i].isEtalOccupe()) {
					this.etals[i].afficherEtal();
				
				}
				else {
					nbEtalVide++;
				}
				
			}
			System.out.println("il reste " + nbEtalVide + "etals non utilisee dans le marche .\n");
			
			
			
			
		}
			
		}
		
		

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder(
				vendeur.getNom() + "cherche un encdroit pour vendre " + nbProduit + produit + "\n");
		int numEtalLibre = this.marche.trouverEtalLibre();
		if (numEtalLibre == -1) {
			return "Tout les étals du marché sont pleins";
		}
		chaine.append("Le vendeur " + vendeur.getNom() + "vends des " + produit + "à l'étal n°" + numEtalLibre);
		return chaine.toString();
	}
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder("Les vendeurs qui proposen des " + produit + "sont :\n");
		Etal etalProduit[] = this.marche.trouverEtals(produit);
		if (etalProduit.length == 0) {
			return "Aucun vendeur ne vend ce produit";
		}
		for (int i = 0; i < etalProduit.length; i++) {
			chaine.append("- " + etalProduit[i].getVendeur().getNom() + "\n");
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return this.marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		Etal etalVendeur = this.rechercherEtal(vendeur);
		try {
			chaine.append(etalVendeur.libererEtal());
		} catch (NullPointerException e) {
			e.printStackTrace();
			chaine.append("erreur etal vide");
		}
		return chaine.toString();
	}

	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder(
				"Le marché du village \"" + this.getNom() + "\" possède plusieurs étals :\n");
		for (int i = 0; i < this.marche.etals.length; i++) {
			if (this.marche.etals[i].isEtalOccupe()) {
				chaine.append(this.marche.etals[i].afficherEtal());
			}
		}
		return chaine.toString();
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}
	

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException {
		if (this.chef == null) {
			throw new VillageSansChefException();
		}
		StringBuilder chaine = new StringBuilder();
		if (this.nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + this.chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + this.chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < this.nbVillageois; i++) {
				chaine.append("- " + this.villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}
