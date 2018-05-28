import PaD.*;
import java.awt.*;

 public class NoeudDG{
	public static final PlancheADessin pad = new PlancheADessin(1000,500,true);

	public static double milieu_x = pad.getLargeur()/2;
	public static double milieu_y = pad.getHauteur()/2;

	public static double a1= pad.getLargeur()/4;
	public static double a2= pad.getHauteur()/4;

	public static double b1= pad.getLargeur()/8;
	public static double b2= pad.getLargeur()/8;


/* 	public void squeletteArbre (int n){ //n est le nombre de niveaux max
 		int i=0;

 		for (int i=1; i<=n; i++){
 			a1= pad.getLargeur()/Math.pow(2,i);
 			a2= pad.getHauteur()/Math.pow(2,i);
 		}




 	} */

	public static void noeud(int generation, String t, double x, double y, boolean sad, boolean sag){
		Color gris = new Color(200,200,200);
		Font f = new Font("TimesRoman", Font.PLAIN, 18);
		double lx= pad.getLargeur()/4 - 10; // projeté sur l'axe x de la branche
		double ly= pad.getLargeur()/4 - 10; // projeté sur l'axe y de la branche


		Dessinable brancheD = new Ligne(x,y,x+lx,y+ly,PlancheADessin.NOIR,3);
 		Dessinable brancheG = new Ligne(x,y,x-lx,y+ly,PlancheADessin.NOIR,3);

		Dessinable noeudPrinc  = new EllipsePleine(x,y,50/(1+generation),30/(1+generation),gris);

		Dessinable texte = new Texte(x-25/(1+generation),y-15/(1+generation),t,f);

		if (sad) pad.ajouter(brancheD);
		if (sag) pad.ajouter(brancheG);
		pad.ajouter(noeudPrinc,texte);
	}




 	public static void main(String [] args){



		/*
 		Dessinable BrancheD = new Ligne(milieu_x,c,milieu_x+a1,c+a2,PlancheADessin.NOIR,3);
 		Dessinable BrancheG = new Ligne(milieu_x,c,milieu_x-a1,c+a2,PlancheADessin.NOIR,3);
		//Ellipse​(double x, double y, double l, double h)
 		Dessinable NoeudOrig = new CerclePlein(milieu_x,c,c,PlancheADessin.VERT);

 		Dessinable NoeudD = new CerclePlein(milieu_x+a1,c+a2,c,PlancheADessin.ROUGE);
 		Dessinable NoeudG = new CerclePlein(milieu_x-a1,c+a2,c,PlancheADessin.ROUGE);


 		pad.ajouter(NoeudOrig,BrancheD,BrancheG,NoeudD,NoeudG);
		*/
		noeud(0,"blabla",milieu_x,milieu_y,true,true);
 	}
 }
