/*
  * On définit ici la classe Element qui permet de
  * structurer les données de nos arbres.
  * Les objets de type Element ont une clef qui va permettre de
  * comparer plusieurs Elements afin de les trier dans l'arbre.
  * A chaque Element, une valeur est associée à cette clef.
*/

class Element<C extends Comparable<C>,V> {
  private C clef;
  private V valeur;

	Element(C c, V v) {
	   this.clef = c;
	   this.valeur = v;
	}

/*	public String toString() {
	    return "(" + this.clef + "," + this.valeur + ")";
	} */

	C clef() { return  this.clef; }
	V valeur() { return  this.valeur; }
}
