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

public class ArbreBinaireOrdonne<C extends Comparable<C>, V> extends ArbreBinaireDessine<C,V> implements ArbreBinaire<C, V>{

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

  /*
    * La méthode supprimer supprime un élément grace à ça clef.
    * Si l'élément est trouvé, on le supprime. Si il n'est pas trouvé
    * on  renvoie une ClefNonTrouveeException.
    * Si l'élément est le noeud principal de l'arbre, il ne peut pas etre
    * détruit donc on envoie une NoeudPrincipalException.
  */
  public void supprimer(C clef) throws ClefNonTrouveeException, NoeudPrincipalException{
    try{
      //dans le cas ou l'utilisateur veut supprimer le noeud pricipal.
      if ( clef.compareTo(this.clef()) == 0 ) throw new NoeudPrincipalException();
    }catch(ArbreVideException e){} // ce catch est nécessaire pour le compilateur mais l'utilisateur ne peut pas créer d'abre vide donc le premier noeud existe.

    supprimerRecu(clef);
  }

  //méthode récursive pour supprimer
  private void supprimerRecu(C clef) throws ClefNonTrouveeException{
    try{
      if(clef.compareTo(this.clef())>0){
        if( clef.compareTo(sad.clef()) == 0 ){

          try{
            if(sad.sad().estVide() && sad.sag().estVide()){
              sad = arbreVide;

            }else if(sad.sad().estVide()){
              sad = sad.sag();
            }else if(sad.sag().estVide()){
              sad = sad.sad();
            }else{
              try{
                ArbreBinaireOrdonne succ = sad.sad();
                while (!(succ.sag().estVide()) ){
                  succ = succ.sag();
                }
                sad.element = new Element(succ.clef(),succ.valeur());
                if (!(succ.sad().estVide())){
                  succ = succ.sad();
                }else {
                  succ = arbreVide;
                }

              }catch (ArbreVideException e2) {
                ArbreBinaireOrdonne succ = sad.sag();
                int i = 0;
                while (!(succ.sad().estVide()) ){
                  succ = succ.sad();
                  i++;
                }
                sad.element = new Element(succ.clef(),succ.valeur());
                if (!(succ.sag().estVide())){
                  succ.element = new Element(succ.sag().clef(),succ.sag().valeur());
                  //succ = succ.sag();
                }else {
                  succ = arbreVide;
                }
              }
            }

          }catch (ArbreVideException e1) {
            sad = arbreVide;
          }
          return;
        }
        sad.supprimerRecu(clef);
      }

      if(clef.compareTo(this.clef())<0){
        if( clef.compareTo(sag.clef()) == 0 ){
          sag = arbreVide;
          return;
        }
        sag.supprimerRecu(clef);
      }

    }catch(ArbreVideException ex){
      //si on recherche dans un arbre vide il n'y a pas la clef dans l'arbre
      //on envoie une exception.
      throw new ClefNonTrouveeException();
    }
  }




//--- Ici on implémente les fonctions de base d'un arbre binnaire ---//

  //on renvoie ici la valeur du noeud pricipal de l'arbre.
  public V valeur() throws ArbreVideException{
    if(estVide()) throw new ArbreVideException();
    return this.element.valeur();
  }

  //on renvoie ici la clef du noeud pricipal de l'arbre.
  public C clef() throws ArbreVideException{
    if(estVide()) throw new ArbreVideException();
    return this.element.clef();
  }

  //verifie si l'arbre est vide.
  public boolean estVide(){
    return (this == arbreVide);
  }

  //donne le sous-arbre droit.
  public ArbreBinaireOrdonne sag() throws ArbreVideException{
    if(estVide()) throw new ArbreVideException();
    return this.sag;
  }

  //donne le sous-arbre gauche.
  public ArbreBinaireOrdonne sad() throws ArbreVideException{
    if(estVide()) throw new ArbreVideException();
    return this.sad;
  }


}
