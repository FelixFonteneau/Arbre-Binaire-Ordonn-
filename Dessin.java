import PaD.*;

public class Dessin{
	PlancheADessin pad = new PlancheADessin();
	private static c=20;

	double milieu_x = pad.getLargeur()/2;
	double milieu_y = pad.getHauteur()/2;

	public void dessinerBranche(int étage, double x1, double y1, double x2, double y2){
		new Ligne(milieu_x,c,milieu_x+a1,c+a2,PlancheADessin.NOIR,3);
		Dessinable BrancheG = new Ligne(milieu_x,c,milieu_x-a1,c+a2,PlancheADessin.NOIR,3);
	}
	
	public Arbre<C,V> dessiner(PlancheADessin pad, Arbre<E,V> arbre, int étage, double x, double y){ 

		Dessinable NoeudOrig = new CerclePlein(x,y,c,PlancheADessin.VERT);
		pad.ajouter(NoeudOrig);

		if (!sArbreDroit.estVide()){
			Dessinable BrancheD = new Ligne(milieu_x,c,milieu_x+a1,c+a2,PlancheADessin.NOIR,3);
			dessinerNoeud();

		}
	}	
	



	public static void main(String [] args){
		PlancheADessin pad = new PlancheADessin();
		double milieu_x = pad.getLargeur()/2;
		double milieu_y = pad.getHauteur()/2;
		
		double a1= pad.getLargeur()/4;
		double a2= pad.getHauteur()/4;

		double b1= pad.getLargeur()/8;
		double b2= pad.getLargeur()/8;

		double c = 20; //diametre des noeuds

		Dessinable BrancheD = new Ligne(milieu_x,c,milieu_x+a1,c+a2,PlancheADessin.NOIR,3);
		Dessinable BrancheG = new Ligne(milieu_x,c,milieu_x-a1,c+a2,PlancheADessin.NOIR,3);

		Dessinable NoeudOrig = new CerclePlein(milieu_x,c,c,PlancheADessin.VERT);

		Dessinable NoeudD = new CerclePlein(milieu_x+a1,c+a2,c,PlancheADessin.ROUGE);
		Dessinable NoeudG = new CerclePlein(milieu_x-a1,c+a2,c,PlancheADessin.ROUGE);
		

		pad.ajouter(NoeudOrig,BrancheD,BrancheG,NoeudD,NoeudG);
	}
}