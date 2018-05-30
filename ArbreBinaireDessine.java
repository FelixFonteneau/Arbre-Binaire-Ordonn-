import PaD.*;
import java.awt.*;

@SuppressWarnings("unchecked")


public abstract class ArbreBinaireDessine<C extends Comparable<C>, V> implements ArbreBinaire<C,V> {
  public abstract V valeur() throws ArbreVideException;
  public abstract C clef() throws ArbreVideException;
  public abstract ArbreBinaire sag() throws ArbreVideException;
  public abstract ArbreBinaire sad() throws ArbreVideException;
  public abstract boolean estVide();



    /*
      * La méthode noeud est une méthode élémentaire qui permet de tracer un noeud et ses branches,
      * en fonction de certains paramètres afin d'afficher un noeud.
    */
    private void noeud(int generation, V valeur, double x, double y, boolean sag, boolean sad, PlancheADessin pad){
  		Color gris = new Color(200,200,200);
  		Font f = new Font("TimesRoman", Font.PLAIN, (int) (18/(generation*0.2+1)) >= 10 ? (int) (18/(generation*0.2+1)) : 10 );
  		double lx = pad.getLargeur()/4.1/(Math.pow(2,generation)); // projeté sur l'axe x de la branche
  		double ly = pad.getHauteur()/4.1/(Math.pow(2,generation)); // projeté sur l'axe y de la branche


  		Dessinable brancheG = new Ligne(x,y,x-lx,y+ly,PlancheADessin.NOIR,2);
   		Dessinable brancheD = new Ligne(x,y,x+lx,y+ly,PlancheADessin.NOIR,2);

  		Dessinable noeudPrinc  = new EllipsePleine(x,y,50/(1+0.5*generation),30/(1+0.5*generation),gris);

  		Dessinable texte = new Texte(x-25/(1+0.5*generation),y-15/(1+0.5*generation),valeur == null ? "null" : valeur.toString(),f);

      //ajout des éléments à la PAD
  		if (sad) pad.ajouter(brancheD);
  		if (sag) pad.ajouter(brancheG);
  		pad.ajouter(noeudPrinc,texte);
  	}


    /*
      * Cette méthode permet de dessiner un arbre récursivement en dessinant
      * chaque noeud. Elle appelle un méthode secondaire qui permet de passer
      * des paramètres à la recursion.
      *
      * Chaque noeud est dessiné grâce à la méthode noeud.
    */

    public void dessinerArbre(PlancheADessin pad){
      double milieu_x = pad.getLargeur()/2;
      double y = pad.getHauteur()/10;

      boolean arbreG;
      boolean arbreD;
      V valeur;


      //Les try et catch ci-dessous ne sont pas esthétiques mais nécessaires afin d'éviter les exceptions.
      try {
        arbreG = sag().estVide();
      }catch (ArbreVideException e) {
        arbreG = false;
      }

      try {
        arbreD = sad().estVide();
      }catch (ArbreVideException e) {
        arbreD = false;
      }

      try{
        valeur = valeur();
      }catch (ArbreVideException e) {
        valeur = null;
      }


      noeud(0,valeur, milieu_x,y, !(arbreG), !(arbreD), pad);

      if( !(arbreG) ){
        try{
          dessinerArbreRecu(sag(),1,milieu_x - pad.getLargeur()/4.1, pad.getHauteur()/10+pad.getHauteur()/4.1, pad);
        }catch (ArbreVideException e) {}
      }
      if( !(arbreD) ){
        try{
          dessinerArbreRecu(sad(),1,milieu_x + pad.getLargeur()/4.1, pad.getHauteur()/10+pad.getHauteur()/4.1, pad);
        }catch (ArbreVideException e) {}
      }

    }


    private void dessinerArbreRecu(ArbreBinaire a,int generation, double x, double y , PlancheADessin pad){
      boolean arbreG;
      boolean arbreD;
      V valeur;

      try {
        arbreG = a.sag().estVide();
      }catch (ArbreVideException e) {
        arbreG = false;
      }

      try {
        arbreD = a.sad().estVide();
      }catch (ArbreVideException e) {
        arbreD = false;
      }

      try{
        valeur = (V) a.valeur();
      }catch (ArbreVideException e) {
        valeur = null;
      }

      noeud(generation, valeur, x, y, !(arbreG), !(arbreD), pad);


      if( !(arbreG) ){
        try{
          dessinerArbreRecu(a.sag(), generation+1, x - pad.getLargeur()/4.1/(Math.pow(2,generation)), y + pad.getHauteur()/4.1/(Math.pow(2,generation)), pad );
        }catch(ArbreVideException e){}
      }

      if( !(arbreD) ){
        try{
          dessinerArbreRecu( a.sad(),generation+1, x + pad.getLargeur()/4.1/(Math.pow(2,generation)), y + pad.getHauteur()/4.1/(Math.pow(2,generation)),pad );
        }catch(ArbreVideException e){}
      }
    }


}
