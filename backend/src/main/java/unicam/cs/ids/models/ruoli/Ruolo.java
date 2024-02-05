package unicam.cs.ids.models.ruoli;

public enum Ruolo {
    TURISTA("TURISTA"),
    TURISTA_AUTENTICATO("TURISTA_AUTENTICATO"),
    ANIMATORE("ANIMATORE"),
    CONTRIBUTOR("CONTRIBUTOR"),
    CONTRIBUTOR_AUTORIZZATO("CONTRIBUTOR_AUTORIZZATO"),
    CURATORE("CURATORE"),
    GESTORE_PIATTAFORMA("GESTORE_PIATTAFORMA");

    public final String ruolo;

    Ruolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public static Ruolo getRuolo(String ruolo) {
        for(Ruolo r : Ruolo.values()) {
            if(r.ruolo.equalsIgnoreCase(ruolo)) return r;
        }
        return null;
    }

}
