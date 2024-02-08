package unicam.cs.ids.configurazioni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import unicam.cs.ids.models.ruoli.GestorePiattaforma;

@Configuration
public class GestorePiattaformaConfigurazione {
    private GestorePiattaformaBuilder gestorePiattaformaBuilder;
    @Autowired
    public GestorePiattaformaConfigurazione(GestorePiattaformaBuilder gestorePiattaformaBuilder){
        this.gestorePiattaformaBuilder = gestorePiattaformaBuilder;

    }
    @Bean
    public GestorePiattaforma gestorePiattaforma() {
        return GestorePiattaforma.getInstance(gestorePiattaformaBuilder);
    }

}
