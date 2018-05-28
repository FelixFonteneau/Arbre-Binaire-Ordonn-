import PaD.*;
import java.awt.*;

public abstract class ArbreBinaireDessine<C extends Comparable<C>, V> implements ArbreBinaire<C,V> {
  public abstract V valeur() throws ArbreVideException;
  public abstract C clef() throws ArbreVideException;
  public abstract ArbreBinaire sag() throws ArbreVideException;
  public abstract ArbreBinaire sad() throws ArbreVideException;
  public abstract boolean estVide();

  //on déffinit la plache à dessin, notre interface graphique de nos arbres.
    public static final PlancheADessin pad = new PlancheADessin(1000,500,true);

    public double milieu_x = pad.getLargeur()/2;
    public double milieu_y = pad.getHauteur()/2;


    public void noeud(int generation, V valeur, double x, double y, boolean sad, boolean sag, PlancheADessin pad){
  		Color gris = new Color(200,200,200);
  		Font f = new Font("TimesRoman", Font.PLAIN, 18);
  		double lx= pad.getLongueur()/4 - 10; // projeté sur l'axe x de la branche
  		double ly= pad.getLargeur()/4 - 10; // projeté sur l'axe y de la branche


  		Dessinable brancheD = new Ligne(x,y,x+lx,y+ly,PlancheADessin.NOIR,3);
   		Dessinable brancheG = new Ligne(x,y,x-lx,y+ly,PlancheADessin.NOIR,3);

  		Dessinable noeudPrinc  = new EllipsePleine(x,y,50/(1+generation),30/(1+generation),gris);

  		Dessinable texte = new Texte(x-25/(1+generation),y-15/(1+generation),valeur.toString(),f);

  		if (sad) pad.ajouter(brancheD);
  		if (sag) pad.ajouter(brancheG);
  		pad.ajouter(noeudPrinc,texte);
  	}






}
