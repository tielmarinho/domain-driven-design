package Hospital;

import java.util.Arrays;

public abstract class Diagnostico {
    protected String[] sintomasComuns;

    public Diagnostico(String[] sintomasComuns) {
        this.sintomasComuns = sintomasComuns;
    }

    public void exibirSintomasComuns() {
        System.out.println("Sintomas comuns: " + String.join(", ", sintomasComuns));
    }

    public abstract String avaliarPaciente(String[] sintomasInformados);
}