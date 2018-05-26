@SuppressWarnings("unchecked")

public abstract class ArbreBinaireAbstract<C extends Comparable<C>, V> implements ArbreBinaire<C,V> {

  public abstract V valeur() throws ArbreVideException;
  public abstract C clef() throws ArbreVideException;
  public abstract ArbreBinaire sag() throws ArbreVideException;
  public abstract ArbreBinaire sad() throws ArbreVideException;
  public abstract boolean estVide();


  public void ecrire(){
    ecrireR(this,0);
  }

  private void ecrireR(ArbreBinaire<C,V> a, int d){
    try{

      int offset = 3;

      ecrireR(a.sad(), d+offset);

      for (int i = 0; i<d; i++){
        System.out.print(" ");
      }
      System.out.println(a.valeur());

      ecrireR(a.sag(), d+offset);

    }catch(ArbreVideException e){}
  }

  public int hauteur() throws ArbreVideException {
    if(this.estVide()) throw new ArbreVideException();
    return hauteurR(this) -1 ;
  }

  private int hauteurR(ArbreBinaire a) throws ArbreVideException{
    if(a.estVide()) return 0;
    return Math.max(hauteurR(a.sad()),hauteurR(a.sag())) + 1;
  }

  public boolean mirroirs(ArbreBinaire b) throws ArbreVideException{
    if(this.valeur() != b.valeur()) return false;
    try{
      return mirroirsR(this.sad(), b.sag()) && mirroirsR(this.sag(), b.sad());
    }
    catch (ArbreVideException e) {
      return false;
    }
  }

  private boolean mirroirsR(ArbreBinaire a, ArbreBinaire b) throws ArbreVideException{
    if(a.estVide() && b.estVide()) return true;
    if(a.valeur() != b.valeur()) return false;
    return mirroirsR(this.sad(), b.sag()) && mirroirsR(this.sag(), b.sad());
  }


}
