
public enum TypeTruc{
    CLE("clé"), ALCOOL("alcool"), EAU("eau"), GOODIES("trésor");

    private final String description;

    TypeTruc(String d){
        this.description = d;
    }

    @Override
    public String toString() {
        return this.description;
    }

    // pas nécessaire car c'est le comportement par défaut.
    // public String getDescription(){
    //     return this.toString();
    // }
    
}

