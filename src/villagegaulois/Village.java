package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
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

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}