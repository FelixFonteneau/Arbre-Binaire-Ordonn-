import PaD.*;


public class Test2 {
  public static void main(String[] args) throws ArbreVideException, NoeudPrincipalException,ElementDejaExistantException, ClefNonTrouveeException {
    ArbreBinaireOrdonne<Integer,String> b = new ArbreBinaireOrdonne<Integer,String>(new Element<Integer,String>(0,"zero"));
    System.out.println(b.estVide());
    System.out.println(b.sad().estVide());
    b.ajout(new Element<Integer,String>(2," le 2 "));

    b.ajout(new Element<Integer,String>(1," un "));
    System.out.println(b.sad().estVide());
  //  b.ajout(new Element<Integer,String>(3,"le trois"));
    b.ajout(new Element<Integer,String>(-1," le moins un"));
    b.ajout(new Element<Integer,String>(-4," le moins 4"));
    b.ajout(new Element<Integer,String>(4," le quatre "));

    b.ajout(new Element<Integer,String>(3," le 3 "));

    //b.ecrire();
    //System.out.println(b.recherche(1));
    //System.out.println(b.recherche(2));
    //System.out.println(b.recherche(5));
    //b.supprimer(2);
    //b.ecrire();

    PlancheADessin pad = new PlancheADessin(1000,500,true);
    b.dessinerArbre(pad);


  }
}
