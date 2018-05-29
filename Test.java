public class Test {
  public static void main(String[] args) throws ClefNonTrouveeException, ArbreVideException, NoeudPrincipalException,ElementDejaExistantException{
    ArbreBinaireOrdonne<Integer,String> b = new ArbreBinaireOrdonne<Integer,String>(new Element<Integer,String>(0,"0"));
    System.out.println(b.estVide());
    System.out.println(b.sad().estVide());
    b.ajout(new Element<Integer,String>(1,"jami"));
    System.out.println(b.sad().estVide());
    b.ajout(new Element<Integer,String>(3,"bite"));
    b.ajout(new Element<Integer,String>(2,"miaou"));
    b.ecrire();
    System.out.println(b.recherche(1));
    System.out.println(b.recherche(2));
    //System.out.println(b.recherche(5));
    b.supprimer(3);
    b.ecrire();
  }
}
