public class Test {
  public static void main(String[] args) throws ArbreVideException{

    ArbreBinaire<String> b = new ArbreBinaireChaine<String> ("x", new ArbreBinaireChaine<String>("y"), ArbreBinaireChaine.arbreVide);
    ArbreBinaire<String> a = new ArbreBinaireChaine<String> ("a", new ArbreBinaireChaine<String>("b"), b);
    ArbreBinaireChaine<String> c = new ArbreBinaireChaine<String> ("i", a, new ArbreBinaireChaine<String>("j"));


    if(b != ArbreBinaireChaine.arbreVide){
    //  System.out.println(b.valeur());
    }

    c.ecrire();

    int h = c.hauteur();
    System.out.println(h);

    System.out.println(c.mirroirs(c));
  }
}
 
