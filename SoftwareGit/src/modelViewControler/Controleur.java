package modelViewControler;

import java.io.IOException;


/**
 * Classe serveant de controleur aux vues de recherhce de fenetre principale et d'ajout
 *
 */
public class Controleur {
	
	ModelInsertion model;
	//pour eviter les doublons dans les noms
	public boolean alerteDoublon=false;
	AffichageDuModele a;
	/**
	 * Constructeur de la calsse
	 * @param a
	 * pour communiquer avec la fentre principale
	 * 
	 * @param model
	 * Un  nouveau modele
	 */
	public Controleur(AffichageDuModele a,ModelInsertion model){
		
		this.model = model;
		this.a=a;
	}
	
	
	/**
	 * @param f
	 * une frame pour la popup
	 * 
	 * @param fichier
	 * pour recuperer le fichier
	 * 
	 * @param nomObjet
	 * nom de l'objet
	 * 
	 * @param auteur
	 *  auteur de l'objet
	 *  
	 * @param chemin
	 * pour verifier la validite du gts
	 * @throws IOException 
	 */
	public void envoieGts(String fichier,String nom) throws IOException{
					model.insertion(fichier,a, nom);
		}
		
		
	
	/**
	 * pour avertir le model
	 * @param nomObjet
	 * pour avertir le model
	 */
	public void affichageObjet(String nomObjet){
		model.appelFenetrePrincipale(nomObjet);
		
		
	}


	public void envoieGts2(String text, String text2) throws IOException {
		model.insertion2(text,a, text2);
		
	}
	
	
	
}
