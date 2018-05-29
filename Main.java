import java.util.Scanner;
import PaD.*;


public class Main {


  private static boolean recommencer(Scanner sc){
    System.out.println("Recommencer ? (o/n)");
    String str3 = sc.nextLine();
    return str3.equals("o");
  }

  public static void main(String[] args) throws ArbreVideException, NoeudPrincipalException,ElementDejaExistantException, ClefNonTrouveeException {

    //initialisation de l'arbre.
    ArbreBinaireOrdonne<Integer,String> a = new ArbreBinaireOrdonne<Integer,String>(new Element<Integer,String>(0,"zero"));


    //initialisation de planche a dessin.
    PlancheADessin pad = new PlancheADessin(1300,800,true);

    boolean arret = false;
    Scanner sc = new Scanner(System.in);

    a.ajout(new Element<Integer,String>(2," le deux "));
    a.ajout(new Element<Integer,String>(1," un "));
    a.ajout(new Element<Integer,String>(-3," le moins 3"));
    a.ajout(new Element<Integer,String>(-4," le moins 4"));
    a.ajout(new Element<Integer,String>(5," le cinq "));
    a.ajout(new Element<Integer,String>(3," le 3 "));

    a.ajout(new Element<Integer,String>(4," le quatre "));
    a.ajout(new Element<Integer,String>(-1," le - un  "));

    a.dessinerArbre(pad);





    System.out.println("\n\n\n\n\n\n\nBienvenue sur l'interface graphique d'une arbre binaire ordonné.");
    sc.nextLine();
    System.out.println("\n\nL'arbre est composé d'élément comportant une clef (permetant de les ordonner) et d'une valeur associée à la clef.");
    System.out.println("Afin de simplifier l'utilisateur, la clef sera un entier relatif, et la valeur sera une chaine de caractères.");
    System.out.println("Vous êtes en capacité d'effectuer quatre actions : \n");
    sc.nextLine();

    while(!(arret)){
      System.out.println("\n\n\n\n\n");
      // on demande à l'utilisateur l'action à réaliser sur l'arbre
      System.out.println("-> ajouter un élément à l'arbre, pour celà, entrez 'a'; ");
      System.out.println("-> supprimer un élément de l'arbre, pour celà, entrez 's'; ");
      System.out.println("-> rechercher un élément de l'arbre, pour celà, entrez 'r'. ");
      System.out.println("-> Creer un nouvel arbre, pour celà, entrez 'n'.\n");
      System.out.println("entrez 'stop' pour arreter\n");
      String str = sc.nextLine();

      //cas d'un ajout
      if(str.equals("a")){
        boolean continuer = false;
        do{
          System.out.println("\n\n\nentrez la clef et la valeur de l'élément à insérer sous la forme suivant :  'clef,valeur' \nexemple :  3,le chiffre trois");
          String str2 = sc.nextLine();

          String[] spl;
          int clef;
          try{
            spl = str2.split(",");
            clef = Integer.parseInt(spl[0]);
          }catch(Exception e){
            System.out.println("\n\nErreur d'écriture de la valeur et de la clef,");
            continuer = recommencer(sc) ;
            continue;
          }
          String val = spl[1];
          try{
            a.ajout(new Element<Integer,String>(clef,val));



            //cas ou l'élement existe déjà.
          } catch (ElementDejaExistantException e) {
            System.out.println("\nATTENTION ! La clef de l'élément que vous avez entré apartient déjà à l'arbre");
            continuer = recommencer(sc) ;
          }
        } while(continuer);



        //cas d'une suppression
      }else if (str.equals("s")){
        boolean continuer = false;
        do{
          System.out.println("\n\n\nentrez la clef de l'élément à supprimer sous la forme suivant : 'clef' \nexemple :  17");
          String str2 = sc.nextLine();
          int clef;
          try{
            clef = Integer.parseInt(str2);
          }catch (Exception e) {
            System.out.println("\n\nErreur d'écriture de la clef,");
            continuer = recommencer(sc) ;
            continue;
          }
          try{
            a.supprimer(clef);


            //cas ou la clef est non trouvé
          }catch (ClefNonTrouveeException e) {
            System.out.println("\nAucun élément a été trouvé avec la clef : "+clef);
            continuer = recommencer(sc) ;

          }catch (NoeudPrincipalException e2){
            System.out.println("\nATTENTION ! Le noeud principal ne peut pas être supprimer !");
            continuer = recommencer(sc) ;
          }
        }while(continuer);

        // cas d'une recherche :
      }else if(str.equals("r")){
        boolean continuer = false;
        do{
          System.out.println("\n\n\nentrez la clef de l'élément à recherche sous la forme suivant : 'clef' \nexemple :  2");
          String str2 = sc.nextLine();
          int clef;
          try{
            clef = Integer.parseInt(str2);
          }catch (Exception e) {
            System.out.println("\n\nErreur d'écriture de la clef,");
            continuer = recommencer(sc) ;
            continue;
          }
          try{
            String val = a.recherche(clef);
            System.out.println("\n\nLa valeur de l'élément à la clef : "+clef+"\n est : "+val);
            //cas ou la clef est non trouvé
            sc.nextLine();
          }catch (ClefNonTrouveeException e) {
            System.out.println("\nAucun élément a été trouvé avec la clef : "+clef);
            continuer = recommencer(sc) ;
          }
        }while(continuer);


      }else if(str.equals("n")){
        boolean continuer = false;
        do {
          System.out.println("\n\nConstruction d'un nouvel arbre.");
          System.out.println("\nVeuillez indiquer la clef et la valeur du noeud principal du nouvel arbre.");
          System.out.println("(sous la forme : clef,valeur )");

          String str2 = sc.nextLine();

          String[] spl;
          int clef;
          try{
            spl = str2.split(",");
            clef = Integer.parseInt(spl[0]);
          }catch(Exception e){
            System.out.println("\n\nErreur d'écriture de la valeur et de la clef,");
            continuer = recommencer(sc) ;
            continue;
          }
          String val = spl[1];
          a = new ArbreBinaireOrdonne<Integer,String>(new Element<Integer,String>(clef,val));
        } while (continuer);



      }else if(str.equals("stop")){
        System.out.println("Arret du programe");
        System.exit(2);
      }

      pad.clear();
      a.dessinerArbre(pad);
    }



  }
}
