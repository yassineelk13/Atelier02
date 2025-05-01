package com.example.atelier02vf;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ligneFactureServlet", value = "/lignes")
public class LigneFactureServlet extends HttpServlet {

    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        // Récupérer ou créer la liste des lignes de facture
        List<String> lignes = (List<String>) session.getAttribute("lignes");
        if (lignes == null) {
            lignes = new ArrayList<>();
            session.setAttribute("lignes", lignes);
        }

        // Récupérer les paramètres (désignation, prix, quantité)
        String designation = request.getParameter("designation");
        String prix = request.getParameter("prix");
        String quantite = request.getParameter("quantite");

        // Ajouter une ligne si tous les champs sont fournis
        if (designation != null && prix != null && quantite != null &&
                !designation.isEmpty() && !prix.isEmpty() && !quantite.isEmpty()) {
            String ligneStr = "Designation: " + designation + ", Prix: " + prix + ", Quantité: " + quantite;
            lignes.add(ligneStr);
        }

        // Afficher la liste des lignes de facture
        response.setContentType("text/plain");
        response.getWriter().println("Liste des lignes de facture dans la session :");
        for (String ligne : lignes) {
            response.getWriter().println("- " + ligne);
        }
    }
}
