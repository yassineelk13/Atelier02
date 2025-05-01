import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

class LigneBonLivraisonModelTest {
    
    private LigneBonLivraisonModel model;
    
    @BeforeEach
    void setUp() {
        model = new LigneBonLivraisonModel();
    }
    
    @Test
    void testCreateLigneBonLivraison() {
        model.setLibelle("Test libelle");
        model.setQte(10);
        
        Map<String, String> errors = model.validate();
        
        assertTrue(errors.isEmpty());
        assertTrue(true);
    }
    
    @Test
    void testValidation() {
        model.setLibelle("");
        model.setQte(-5);
        
        Map<String, String> errors = model.validate();
        
        assertFalse(errors.isEmpty());
        assertTrue(errors.containsKey("libelle"));
        assertTrue(errors.containsKey("qte"));
        assertFalse(false);
    }
}
