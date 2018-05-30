/*
  * ArbreBinaire est l'interface, donc elle définit les méthodes employées.
  *
  * La classe abstraite ArbreBinaireAbstract nous sert à définir "l'interface graphique".
*/

public interface ArbreBinaire<C extends Comparable<C>, V> {
  public V valeur() throws ArbreVideException;
  public C clef() throws ArbreVideException;
  public ArbreBinaire sag() throws ArbreVideException;
  public ArbreBinaire sad() throws ArbreVideException;
  public boolean estVide();
}
