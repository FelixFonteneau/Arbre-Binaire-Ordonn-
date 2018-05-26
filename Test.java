public class Test {
  public static void main(String[] args) throws ArbreVideException{
    ArbreBinaireOrdonne<Integer,String> b = new ArbreBinaireOrdonne<Integer,String>(new Element<Integer,String>(0,"0"));
    System.out.println(b.estVide());
    System.out.println(b.sad().estVide());
    b.ajout(new Element<Integer,String>(1,"1"));
    System.out.println(b.sad().estVide());
    b.ajout(new Element<Integer,String>(3,"3"));
    b.ajout(new Element<Integer,String>(2,"2"));
    b.ecrire();
  }
}
