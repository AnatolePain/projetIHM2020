/**
 * Le jeu étant plutôt orienté texte, toute instance d'une classe intéressante doit pouvoir proposer une description.
 */
public interface Descriptible{

    public default String getDescription(){
        return this.toString();
    }
}

