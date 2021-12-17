package fr.siomd.ludo.entity;

import java.util.ArrayList;

/**
 * <b>La classe Bourreau représente un bourreau</b>
 * Un bourreau est caractérisé par les informations suivantes :
 * <ul>
 *     <li>un nombre max de lettre au rebut</li>
 *     <li>un juge</li>
 *     <li>un mot a chercher de type Mot</li>
 *     <li>un contenu du mot a chercher en majuscule</li>
 *     <li>un mot en cours avec les lettres cachées non trouvé</li>
 *     <li>une liste de lettre au rebut</li>
 *     <li>une liste de lettre trouvée</li>
 * </ul>
 *
 * @version 1.0
 */
public class Bourreau {
    /**
     * le  nombre max de lettre au rebut
     */
    public static final int MAX_REBUT = 8;    // nombre maximum de lettres au rebut

    /**
     * le juge
     */
    private Juge leJuge;
    /**
     * le mot à chercher de type Mot
     */
    private Mot leMot;
    /**
     * le contenu du mot à chercher en majuscules
     */
    private String leMotCherche;
    /**
     * le mot avec des _ à la place de chaque lettre non trouvée
     */
    private String leMotEnCours;  //
    private ArrayList<Character> lesLettresAuRebut = new ArrayList<Character>();  // liste des lettres au rebut
    private ArrayList<Character> lesLettresTrouvees = new ArrayList<Character>(); // liste des lettres trouvées

    // vrai si toutes les lettres du mot ont été trouvées
    public boolean isGagne() {
        return (leMotCherche == leMotEnCours);
    }

    // vrai si maximum de lettres au rebut atteint
    public boolean isPerdu() {
        return ( lesLettresAuRebut.size() == MAX_REBUT );
    }

    // retourne le mot en cours (avec des _ ) qui est à afficher
    public String getLeMotEnCours() {
        String leMotEnCoursEspace;
        leMotEnCoursEspace= leMotEnCours.replace(""," ").trim();
        return leMotEnCoursEspace;
    }

    public ArrayList<Character> leMotChercheTableau(){
        ArrayList<Character> lesLettresMotCherche = new ArrayList<Character>();

        for (int i = 0; i < leMotCherche.length(); i++){
            lesLettresMotCherche.add(leMotCherche.charAt(i));
        }
        return lesLettresMotCherche;
    }

    // retourne les lettres au rebut (qui peut être affichée)
    public String getLesLettresAuRebut () {
        String lettresAuRebut = "";
        for (Character uneLettre : lesLettresAuRebut){
            lettresAuRebut = lettresAuRebut + uneLettre + " ";
        }
        return lettresAuRebut;
    }

    public Bourreau(Juge unJuge) {
        leJuge = unJuge;
        demarrer();
    }

    // initialisation des variables de travail utilisées pour la gestion d'un mot
    public void demarrer() {
        leMot =  leJuge.donnerMot();
        leMotCherche = leMot.getContenuMaj();
        lesLettresAuRebut.clear();
        leMotEnCours = "";
        // définir leMotEnCours : autant de caractères _  que de lettres dans le mot à trouver
        for (int i = 0; i < leMotCherche.length(); i++) {
            leMotEnCours += "_";
        }
    }

    // chercher la lettre dans le mot à trouver
    //     si la lettre est trouvée, le mémoriser
    //     sinon, mettre la lettre au rebut
    // si mot entièrement trouvé, alors mettre à jour le score
    public void executer(Character uneLettre) {
        if (leMotCherche.indexOf(uneLettre) == -1){
            lesLettresAuRebut.add(uneLettre);
        } else if (leMotCherche.indexOf(uneLettre) != 1){
            lesLettresTrouvees.add(uneLettre);
        } else if (isGagne()){
            leJuge.ajouterScore(leMot.getNbPoints());
        }
    }

    // chercher les positions de la lettre
    public ArrayList<Integer> getPositionLettre(Character laLettre){
        ArrayList<Integer> lesPositions = new ArrayList<Integer>();
        for(Character uneLettre: leMotChercheTableau()){
            if (uneLettre == laLettre){
                lesPositions.add(leMotChercheTableau().indexOf(uneLettre));
            }
        }
        return lesPositions;
    }
}
