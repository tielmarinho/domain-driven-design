import Transporte.Bicicleta;
import Transporte.Metro;
import Transporte.Onibus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransporteUrbanoTest {

    @Test
    public void testCriacaoOnibus() {
        Onibus onibus = new Onibus("Ônibus Municipal", 40);
        assertEquals("Ônibus Municipal", onibus.getNome());
        assertEquals(40, onibus.getCapacidade());
    }

    @Test
    public void testCriacaoMetro() {
        Metro metro = new Metro("Metrô Linha Azul", 200);
        assertEquals("Metrô Linha Azul", metro.getNome());
        assertEquals(200, metro.getCapacidade());
    }

    @Test
    public void testCriacaoBicicleta() {
        Bicicleta bike = new Bicicleta("Bike Compartilhada", 1);
        assertEquals("Bike Compartilhada", bike.getNome());
        assertEquals(1, bike.getCapacidade());
    }

    @Test
    public void testEhEcologicoBicicleta() {
        Bicicleta bike = new Bicicleta("Bike", 1);
        assertTrue(bike.ehEcologico());
    }

    @Test
    public void testCalculoCustoViagemOnibus() {
        Onibus onibus = new Onibus("Ônibus", 40);
        assertEquals(4.50, onibus.calcularCustoViagem(), 0.01);
    }

    @Test
    public void testCalculoCustoViagemBike() {
        Bicicleta bike = new Bicicleta("Bike", 1);
        assertEquals(0.00, bike.calcularCustoViagem(), 0.01);
    }
}