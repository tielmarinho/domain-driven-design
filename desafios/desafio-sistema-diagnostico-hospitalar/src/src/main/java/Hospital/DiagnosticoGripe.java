package Hospital;

import java.util.Arrays;

public class DiagnosticoGripe extends Diagnostico {

    public DiagnosticoGripe() {
        super(new String[]{"febre", "tosse", "dor de garganta", "coriza"});
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
            return "Possível Gripe. Recomendação: repouso, hidratação e observação dos sintomas.";
        } else {
            return "Sintomas insuficientes para diagnóstico de Gripe.";
        }
    }
}