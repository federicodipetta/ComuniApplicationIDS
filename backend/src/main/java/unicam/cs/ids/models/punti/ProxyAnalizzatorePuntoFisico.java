package unicam.cs.ids.models.punti;

import unicam.cs.ids.models.Comune;

import java.util.HashMap;
import java.util.Map;

/**
 * Questa classe viene usata come proxy per analizzare un punto fisico.
 */
public class ProxyAnalizzatorePuntoFisico implements IAnalizzatorePuntoFisico {

    private final IAnalizzatorePuntoFisico analizzatorePuntoFisico;

    private final Map<PuntoFisico, String> cache;

    public ProxyAnalizzatorePuntoFisico(IAnalizzatorePuntoFisico analizzatorePuntoFisico) {
        this.analizzatorePuntoFisico = analizzatorePuntoFisico;
        this.cache = new HashMap<>();
    }

    @Override
    public boolean controllaPuntoFisico(PuntoFisico puntoFisico, Comune comune) {
        if(cache.containsKey(puntoFisico)) // Se il punto è in cache...
            return cache.get(puntoFisico).equals(comune.nome()); // Vedo se il comune è corretto.
        String nomeComune = getNomeComune(puntoFisico);
        cache.put(puntoFisico, nomeComune); // Salva il comune in cache.
        return nomeComune.equals(comune.nome());
    }

    @Override
    public String getNomeComune(PuntoFisico puntoFisico) {
        return analizzatorePuntoFisico.getNomeComune(puntoFisico);
    }
}