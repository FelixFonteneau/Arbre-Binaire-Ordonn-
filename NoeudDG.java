import PaD.*;

public class NoeudDG{
	double milieu_x = pad.getLargeur()/2;
	double milieu_y = pad.getHauteur()/2;
	double a1,a2,b1,b2;
	
	public void squeletteArbre (int n){ //n est le nombre de niveaux max
		int i=0;

		for (int i=1, i<=n){
			a1= pad.getLargeur()/2**i;
			a2= pad.getHauteur()/2**i;
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