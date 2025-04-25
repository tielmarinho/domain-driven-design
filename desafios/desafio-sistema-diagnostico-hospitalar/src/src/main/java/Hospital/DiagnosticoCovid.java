package Hospital;

import java.util.Arrays;

public class DiagnosticoCovid extends Diagnostico {

    public DiagnosticoCovid() {
        super(new String[]{"febre", "tosse seca", "cansaço", "perda de olfato"});
    }

    @Override
    public String avaliarPaciente(String[] sintomasInformados) {
        int coincidencias = 0;
        for (String sintoma : sintomasInformados) {
            if (Arrays.asList(sintomasComuns).contains(sintoma.toLowerCase())) {
                coincidencias++;
            }
        }

        if (coincidencias >= 2) {
            return "Possível Covid-19. Recomendação: isole-se, use máscara e procure orientação médica.";
        } else {
            return "Sintomas insuficientes para diagnóstico de Covid-19.";
        }
    }
}