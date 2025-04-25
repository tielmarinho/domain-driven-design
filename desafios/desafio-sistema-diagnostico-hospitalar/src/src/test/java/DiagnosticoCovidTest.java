import Hospital.Diagnostico;
import Hospital.DiagnosticoCovid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiagnosticoCovidTest {

    @Test
    public void testDiagnosticoCovidComSintomasCompatíveis() {
        Diagnostico diagnostico = new DiagnosticoCovid();
        String[] sintomas = {"febre", "perda de olfato"};
        String resultado = diagnostico.avaliarPaciente(sintomas);
        assertTrue(resultado.toLowerCase().contains("possível covid"));
    }

    @Test
    public void testDiagnosticoCovidComPoucosSintomas() {
        Diagnostico diagnostico = new DiagnosticoCovid();
        String[] sintomas = {"dor de cabeça"};
        String resultado = diagnostico.avaliarPaciente(sintomas);
        assertTrue(resultado.toLowerCase().contains("sintomas insuficientes"));
    }
}