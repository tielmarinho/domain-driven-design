import Hospital.Diagnostico;
import Hospital.DiagnosticoGripe;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiagnosticoGripeTest {

    @Test
    public void testDiagnosticoGripeComSintomasCompatíveis() {
        Diagnostico diagnostico = new DiagnosticoGripe();
        String[] sintomas = {"febre", "dor de garganta"};
        String resultado = diagnostico.avaliarPaciente(sintomas);
        assertTrue(resultado.toLowerCase().contains("possível gripe"));
    }

    @Test
    public void testDiagnosticoGripeComPoucosSintomas() {
        Diagnostico diagnostico = new DiagnosticoGripe();
        String[] sintomas = {"dor de cabeça"};
        String resultado = diagnostico.avaliarPaciente(sintomas);
        assertTrue(resultado.toLowerCase().contains("sintomas insuficientes"));
    }
}