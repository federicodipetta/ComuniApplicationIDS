package unicam.cs.ids.punti;

import org.json.JSONException;
import unicam.cs.ids.Comune;
import unicam.cs.ids.servizi.Coppia;
import unicam.cs.ids.servizi.ListaCircolare;

import java.io.IOException;
import java.util.List;

/**
 * Questa classe viene usata come proxy per analizzare un punto fisico.
 */
public class ProxyAnalizzatorePuntoFisico implements IAnalizzatorePuntoFisico {

    private IAnalizzatorePuntoFisico analizzatorePuntoFisico;

    private List<Coppia<PuntoFisico, String>> cache;

    public ProxyAnalizzatorePuntoFisico() {
        this.analizzatorePuntoFisico = new AnalizzatorePuntoFisico();
        this.cache = new ListaCircolare<>(); // Cache di 10 elementi.
    }

    @Override
    public boolean controllaPuntoFisico(PuntoFisico puntoFisico, Comune comune) throws IOException, JSONException {
        for(Coppia<PuntoFisico, String> coppia : cache) {
            if(coppia.primo().equals(puntoFisico))
                return coppia.secondo().trim().equals(comune.nome().trim());
        } // Se arriviamo fino a qui, il risultato non è in cache.
        String nomeComune = getNomeComune(puntoFisico);
        cache.add(new Coppia<>(puntoFisico, getNomeComune(puntoFisico))); // Salva il comune corretto in cui si trova.
        return nomeComune.equals(comune.nome().trim());
    }

    @Override
    public String getNomeComune(PuntoFisico puntoFisico) throws IOException, JSONException {
        return analizzatorePuntoFisico.getNomeComune(puntoFisico);
    }
}
