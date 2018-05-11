public interface ArbreBinaire<T> {
  public T valeur() throws ArbreVideException;
  public ArbreBinaire sag() throws ArbreVideException;
  public ArbreBinaire sad() throws ArbreVideException;
  public boolean estVide();
  public void ecrire();
}

/* ArbreBinaire est l'interface, donc elle définie les méthodes employées.

  La classe abstraite ArbreBinaireAbstract nous sert de définir "l'interface graphique"
  (les méthodes )
*/
 
