package unicam.cs.ids.models.punti;

import unicam.cs.ids.models.Comune;

/**
 * Questa interfaccia definisce i metodi per analizzare un punto fisico.
 */
public interface IAnalizzatorePuntoFisico {

    /**
     * Controlla se il punto fisico è all'interno del comune.
     * @param puntoFisico Il punto fisico da controllare.
     * @param comune Il comune in cui controllare.
     * @return True se il punto fisico è all'interno del comune, false altrimenti.
     */
    boolean controllaPuntoFisico(PuntoFisico puntoFisico, Comune comune);

    /**
     * Restituisce il nome del comune in cui si trova il punto fisico.
     */
    String getNomeComune(PuntoFisico puntoFisico);

}
