import com.example.atelier02vf.LigneFactureServlet;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class LigneFactureServletTest {

    @Test
    public void testAjoutLigneAvecTousLesParametres() throws Exception {
        // Mocks
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        // Simuler une liste vide dans la session
        List<String> lignes = new ArrayList<>();
        when(session.getAttribute("lignes")).thenReturn(lignes);
        when(request.getSession()).thenReturn(session);

        // Simuler les paramètres de la requête
        when(request.getParameter("designation")).thenReturn("Ordinateur");
        when(request.getParameter("prix")).thenReturn("1500");
        when(request.getParameter("quantite")).thenReturn("2");

        // Simuler la réponse HTTP
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Appeler la méthode GET de la servlet
        LigneFactureServlet servlet = new LigneFactureServlet();
        servlet.doGet(request, response);

        // S'assurer que les données sont bien écrites
        writer.flush();
        String result = stringWriter.toString();

        // Affichage pour vérification manuelle
        System.out.println("Contenu retourné par la servlet :\n" + result);

        // Vérifications
        assertTrue(result.contains("Designation: Ordinateur"));
        assertTrue(result.contains("Prix: 1500"));
        assertTrue(result.contains("Quantité: 2"));
        assertTrue(lignes.contains("Designation: Ordinateur, Prix: 1500, Quantité: 2"));
    }
}

