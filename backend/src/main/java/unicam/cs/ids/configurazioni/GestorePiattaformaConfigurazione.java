package unicam.cs.ids.configurazioni;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import unicam.cs.ids.models.ruoli.GestorePiattaforma;

@Configuration
public class GestorePiattaformaConfigurazione {

    @Bean
    public GestorePiattaforma gestorePiattaforma() {
        return GestorePiattaforma.getInstance();
    }

}
