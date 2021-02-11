package orgeval.td2;

public interface ListeTriee extends Iterable<TexteConsultable> {

    /**
     * ajoutTrie()
     *
     * @param texte : élément à ajouter dans this
     * @return vrai si et seulement si texte était absent de this
     * @post texte est présent dans this
     * @post si this était trié avant, this demeure trié après
     */
    boolean ajoutTrie(TexteConsultable texte);

    /**
     * retrait()
     *
     * @param texte : élément à enlever de this
     * @return vrai si et seulement si texte était présent dans this
     * @post texte est absent dans this
     */
    boolean retrait(TexteConsultable texte);
}