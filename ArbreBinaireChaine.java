public class ArbreBinaireChaine<T> extends ArbreBinaireAbstract<T> implements ArbreBinaire<T> {

  @SuppressWarnings("unchecked")

  public static final ArbreBinaire arbreVide = new ArbreBinaireChaine();

  private T valeur;
  private ArbreBinaire<T> sag, sad;

//on définit cette fonction pour initiliser l'arbreVide
  private ArbreBinaireChaine(){
    this(null, null, null);
  }
 
//
  public ArbreBinaireChaine(T valeur){
    this(valeur, arbreVide, arbreVide);
  }

  public ArbreBinaireChaine(T noeud, ArbreBinaire<T> gauche, ArbreBinaire<T> droit){
    this.valeur = noeud;
    this.sag = (gauche == null) ? arbreVide : gauche;
    this.sad = (droit == null) ? arbreVide : droit;
  }

  public T valeur() throws ArbreVideException{
    if(estVide()) throw new ArbreVideException();
    return this.valeur;
  }

  public ArbreBinaire sag() throws ArbreVideException{
    if(estVide()) throw new ArbreVideException();
    return this.sag;
  }

  public ArbreBinaire sad() throws ArbreVideException{
    if(estVide()) throw new ArbreVideException();
    return this.sad;
  }

  public boolean estVide(){
    return (this == arbreVide);
  }
}
