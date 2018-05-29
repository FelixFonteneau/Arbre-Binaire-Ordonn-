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
  public ArbreBinaireOrdonne<C,V> sag, sad;



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

    //Cherche du noeud à supprimer dans le sous arbre droit.
    try{
      if(clef.compareTo(this.clef())>0){

        //si c'est l'élément à supprimer, on regarde si il a des enfant
        if( clef.compareTo(sad.clef()) == 0 ){

          try{
            //on regarde si l'élément à supprimer n'a pas d'enfant.
            if(sad.sad().estVide() && sad.sag().estVide()){
              this.sad = arbreVide;

              //si il en a on les remplaces.
            }else if(sad.sad().estVide()){
              this.sad = sad.sag();
            }else if(sad.sag().estVide()){
              this.sad = sad.sad();
            }else{
              supEnfant(this.sad);
            }

          }catch (ArbreVideException e1) {
            sad = arbreVide;
          }
          return;
        }
        sad.supprimerRecu(clef);
      }



      //recherche de l'élément à supprimer dans le sous arbre gauche.
      if(clef.compareTo(this.clef())<0){

        //si le sous arbre gauche est a supprimer, on regarde si il a des enfant
        if( clef.compareTo(sag.clef()) == 0 ){

          try{
            if(sag.sad().estVide() && sag.sag().estVide()){
              this.sag = arbreVide;

            }else if(sag.sad().estVide()){
              this.sag = sag.sag();
            }else if(sag.sag().estVide()){
              this.sag = sag.sad();
            }else{
              supEnfant(this.sag);
            }

          }catch (ArbreVideException e1) {
            sad = arbreVide;
          }
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


  private void supEnfant(ArbreBinaireOrdonne a){
    try{
      ArbreBinaireOrdonne succ = a.sad();
      while (!(succ.sag().estVide()) ){
        succ = succ.sag;
      }
      a.element = new Element(succ.clef(),succ.valeur());
      if (!(succ.sad().estVide())){
        succ = succ.sad;
        //remplacerV(a.sad, succ.sad());
      }else {
        succ = arbreVide;
        System.out.println("r av");
        //remplacerV(a.sad, arbreVide);
      }

    }catch (ArbreVideException e2) {    }
  }

  private void remplacerV(ArbreBinaireOrdonne a, ArbreBinaireOrdonne b){
    try{
      if(!(a.sag().estVide())){
        remplacerV(a.sag,b);
        System.out.println("passe ");
      }
      else {
        System.out.println("remplaces");
        a = b;
      }
    }catch (ArbreVideException e) {
      a = b;
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
