package fr.siomd.ludo.entity;

import java.util.Locale;

/**
 * <b>La classe Mot représente un mot</b>
 * Un mot est caractérisé par les informations suivantes :
 * <ul>
 *     <li>un contenu</li>
 *     <li>un nombre de points</li>
 * </ul>
 *
 * @version 1.0
 */
public class Mot {
    /**
     * le contenu
     */
    private  String contenu;
    /**
     * le nombre de points
     */
    private int nbPoints;

    /**
     * Le constructeur de la classe Mot
     *
     * @param pContenu le contenu du mot
     * @param pNbPoints le nombre de points du mot
     */
    public Mot(String pContenu, int pNbPoints) {
        contenu = pContenu;
        nbPoints = pNbPoints;
    }

    /**
     * Accesseur du contenu du mot
     *
     * @return la chaîne de caractère du contenu du mot
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * Modulateur du contenu du mot
     *
     * @param unContenu le nouveau contenu du mot
     */
    public void setContenu(String unContenu) {
        contenu = unContenu;
    }

    /**
     * Accesseur du contenu du mot en majuscule
     *
     * @return la chaîne de caractère du contenu du mot en majuscule
     */
    public String getContenuMaj(){
        return contenu.toUpperCase(Locale.ROOT);
    }

    /**
     * Accesseur du nombre de points du mot
     *
     * @return l'entier du nombre de points du mot
     */
    public int getNbPoints() {
        return nbPoints;
    }

    /**
     * Modulateur du nombre de points du mot
     *
     * @param nbpoints le nouveau nombre de points du mot
     */
    public void setNbPoints(int nbpoints) {
        nbPoints = nbpoints;
    }
}
