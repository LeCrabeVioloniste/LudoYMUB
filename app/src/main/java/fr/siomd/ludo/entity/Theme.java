package fr.siomd.ludo.entity;

import java.util.ArrayList;

/**
 * <b>La classe Theme représente un thème</b>
 * Un Theme est caractérisé par les informations suivantes :
 * <ul>
 *     <li>un nom</li>
 *     <li>une liste de mots</li>
 * </ul>
 */
public class Theme {
    /**
     * le nom
     */
    private String nom;
    /**
     * la liste de mots
     */
    private ArrayList<Mot> lesMots;

    /**
     * Le constructeur de la classe Theme
     *
     * @param pNom le nom du theme
     */
    public  Theme(String pNom) {
        nom = pNom;
        lesMots = new ArrayList<Mot>();
    }

    /**
     * Accesseur du nom du thème
     *
     * @return la chaîne de caractère du nom du thème
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modulateur du nom du thème
     *
     * @param unNom le nouveau nom du thème
     */
    public void setNom(String unNom) {
        nom = unNom;
    }

    /**
     * Accesseur des mots du thème
     *
     * @return la liste des mots du thème
     */
    public ArrayList<Mot> getLesMots() {
        return lesMots;
    }

    /**
     * Modulateur des mots du thème
     *
     * @param pLesMots la nouvelle liste de mots du thème
     */
    public void setLesMots(ArrayList<Mot> pLesMots) {
        lesMots = pLesMots;
    }

    /**
     * Ajout d'un mot au thème
     *
     * @param pMot le mot à ajouter à la liste de mots du thème
     */
    public void ajouterMot(Mot pMot) {
        lesMots.add(pMot);
    }

    /**
     * Accesseur du nombre de points total du thème
     *
     * @return la somme des points de tout les mots du thème
     */
    public int getValeur(){
        int sommeValeur = 0;
        for (Mot unMot : lesMots) {
            sommeValeur += unMot.getNbPoints();
        }
        return sommeValeur;
    }
}
