import java.util.*;

@SuppressWarnings("unchecked")
/*
  * Classe pricipale des objects de type arbre binnaire
  * Elle implémente les méthodes d'Arbre binnaire
  * Puis elle dévellope des méthode d'ajout, de recherche et de suppression.
  *
  * Elle ne peut pas heriter de ArbreBinaireChaine car les méthodes citées précédemments
  * Utilisent de la récursion et nécessitent que sad et sag soient ordonnés.
  *
  * Contrairement à la classe que nous avons vu en td, notre structure d'Arbres
  * Se construit du tronc jusqu'aux feuilles et non pas des feuilles
  * jusqu'au tronc.
*/

public class ArbreBinaireOrdonne<C extends Comparable<C>, V> extends ArbreBinaireAbstract<C,V> implements ArbreBinaire<C, V>{

  //On déffinit la constante arbreVide
  public static final ArbreBinaireOrdonne arbreVide = new ArbreBinaireOrdonne();

  private Element<C,V> element;
  private ArbreBinaireOrdonne<C,V> sag, sad;



//--- constructeur de classe ---//
  //ce constructeur permet d'initiliser l'arbreVide.
  private ArbreBinaireOrdonne(){
    this.element = null;
    this.sag = null;
    this.sad = null;
  }

  // c'est le seul constructeur public afin de creer un abre avec un seul noeud
  public ArbreBinaireOrdonne(Element<C,V> element){
    this(element, arbreVide, arbreVide);
  }


   //On met ce constructeur en privé car on ne veut pas que l'utilisateur
   //créer des ArbresBinairOrdonne non ordonnés avec ce constructeur;
  private ArbreBinaireOrdonne(Element<C,V> noeud, ArbreBinaireOrdonne<C,V> gauche, ArbreBinaireOrdonne<C,V> droit){
    this.element = noeud;
    this.sag = (gauche == null) ? arbreVide : gauche;
    this.sad = (droit == null) ? arbreVide : droit;
  }


//--- Méthodes des arbres binnaire ordonnés ---//


  /*
    * Dans la méthode ajout on prend un élément en entré et on l'insert dans l'arbre courant.
    * Cette méthode est récursive et va placer l'élément en fonction de sa clef.
    * On compare la clef de l'élément à ajouter à la clef de l'élément du noeud,
    * Si elle est supérieure, on ajoute l'élément à l'arbre droit sinon à l'arbre gauche.
  */
  public void ajout(Element<C,V> e) throws ElementDejaExistantException{
    if(e.clef().compareTo(this.element.clef())>0){

        if(sad.estVide()){
            this.sad = new ArbreBinaireOrdonne(e);

        }else{
          this.sad.ajout(e);
        }

    }else if(e.clef().compareTo(this.element.clef())<0){

        if(sag.estVide()){
          this.sag = new ArbreBinaireOrdonne(e);

        }else{
          this.sag.ajout(e);
        }

    }else{
      //si l'élément qu'on veut ajouter à la meme clef qu'un element dans l'arbre
      //on envoi une exception
      throw new ElementDejaExistantException();
    }
  }



  /*
    * Cette méthode recursive recherche un élément dans l'arbre ordonné.
    * Si l'élément courant correspond à la clef donnée, on retourne cet élément
    * Si la clef donnée est supérieur à la clef de l'élément courant on va
    * rechercher dans le sad si il est non vide (si il est vide on envoi une exception)
    * De même pour le sag si la clef est inférieure.
  */
  public V recherche(C clef) throws ClefNonTrouveeException{
    try{

      if(clef.compareTo(this.clef()) == 0) return this.valeur();

      if(clef.compareTo(this.clef())>0) return sad.recherche(clef);

      else return sag.recherche(clef);

    }catch(ArbreVideException ex){
      //si on recherche dans un arbre vide il n'y a pas la clef dans l'arbre
      //on envoi une exception.
      throw new ClefNonTrouveeException();
    }
  }

  public void supprimer(C clef) throws ClefNonTrouveeException, NoeudPrincipalException{
    try{
      System.out.println(" valeur :"+this.valeur());
      if(clef.compareTo(this.clef())>0){
        if( clef.compareTo(sad.clef()) == 0 ){
          sad = arbreVide;
          return;
        }
        sad.supprimer(clef);
      }

      if(clef.compareTo(this.clef())<0){
        if( clef.compareTo(sag.clef()) == 0 ){
          sag = arbreVide;
          return;
        }
        sag.supprimer(clef);
      }

    }catch(ArbreVideException ex){
      //si on recherche dans un arbre vide il n'y a pas la clef dans l'arbre
      //on envoi une exception.
      throw new ClefNonTrouveeException();
    }

    //impossible de supprimer le premier noeud de l'arbre car on ne peut pas
    //lui assigner une valeur finale. On renvoi une exception.
    throw new NoeudPrincipalException();
  }




//--- Ici on implémente les fonctions de base d'un arbre binnaire ---//
  public V valeur() throws ArbreVideException{
    if(estVide()) throw new ArbreVideException();
    return this.element.valeur();
  }

  public C clef() throws ArbreVideException{
    if(estVide()) throw new ArbreVideException();
    return this.element.clef();
  }

  public boolean estVide(){
    return (this == arbreVide);
  }

  public ArbreBinaire sag() throws ArbreVideException{
    if(estVide()) throw new ArbreVideException();
    return this.sag;
  }

  public ArbreBinaire sad() throws ArbreVideException{
    if(estVide()) throw new ArbreVideException();
    return this.sad;
  }


}
