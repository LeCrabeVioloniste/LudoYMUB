package fr.siomd.ludo.entity;

/**
 * <b>Carte est la classe représentant une carte.</b>
 * <p>
 *     Une carte est caractérisé par les informations suivantes :
 *     <ul>
 *         <li>Une figure : As, Roi, Dame, Valet, 10, 9, 8, 7, 6, 5, 4, 3, 2</li>
 *         <li>Une couleur : Coeur, Carreau, Pique, Trèfle</li>
 *     </ul>
 * </p>
 *
 * @author Marianne
 * @version 1.0
 */
public class Carte {
    /**
     * La figure de la carte
     */
    private String figure;
    /**
     * La couleur de la carte
     */
    private String couleur;

    /**
     * Retourne la couleur de la carte.
     *
     * @return la couleur de la carte
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * Retourne la figure de la carte.
     *
     * @return la figure de la carte.
     */
    public String getFigure() {
        return figure;
    }

    /**
     * Constructeur Carte
     * <p>
     * A la construction d'un objet carte, la figure et la couleur sont valorisés
     * avec les paramètres.
     * </p>
     *
     * @param pCouleur La couleur de la carte.
     * @param pFigure  La figure de la carte.
     */
    public Carte(String pCouleur, String pFigure) {
        couleur = pCouleur;
        figure = pFigure;
    }

    /**
     * Retourne la valeur de la carte.
     *
     * @return la valeur de la carte sous forme d'un entier de 14 à 1.
     */
    public int getValeur(){
        int laValeur;
        switch (figure) {
            case "As": laValeur = 14; break;
            case "Roi": laValeur = 13; break;
            case "Dame": laValeur = 12; break;
            case "Valet": laValeur = 11; break;
            default: laValeur = Integer.parseInt(figure); break;
        }
        return laValeur;
    }

    /**
     * Retourne le nom complet de la carte.
     *
     * @return le nom de la carte sous forme d'une chaine de caractère avec figure et couleur
     */
    public String getNom(){
        return String.format("%s de %s", getFigure(), getCouleur());
    }

    /**
     * Retourne le nom du fichier image représentant la carte (sans l'extension)
     *
     * @return Le nom du fichier image représentant la carte sous forme d'une chaîne de caractère.
     */
    public String getNomImg(){
        String leNomImg = "";
        switch (couleur) {
            case "Coeur": leNomImg = String.format("co%d", getValeur()); break;
            case "Carreau": leNomImg = String.format("ca%d", getValeur());break;
            case "Pique": leNomImg = String.format("p%d", getValeur()); break;
            case "Trèfle": leNomImg = String.format("t%d", getValeur()); break;
        }
        return leNomImg;
    }

    /**
     * indique si la carte correspond à l'atout donné en paramètre
     *
     * @return booléen indiquant si la carte correspond à l'atout en paramètre
     */
    public boolean isAtout(String pCouleur) {
        return (couleur.equals(pCouleur));
    }
}