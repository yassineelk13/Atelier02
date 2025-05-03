package com.example.atelier02vf;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "factureServlet", value = "/factures")
public class FactureServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        // Retrieve or create the list of factures
        List<String> factures = (List<String>) session.getAttribute("factures");
        if (factures == null) {
            factures = new ArrayList<>();
            session.setAttribute("factures", factures);
        }

        // Get parameters from request
        String id = request.getParameter("id");
        String date = request.getParameter("date");
        String client = request.getParameter("client");
        String total = request.getParameter("total");

        // Add a facture if all fields are provided
        if (id != null && date != null && client != null && total != null &&
                !id.isEmpty() && !date.isEmpty() && !client.isEmpty() && !total.isEmpty()) {

            String factureStr = "ID: " + id + ", Date: " + date + ", Client: " + client + ", Total: " + total;
            factures.add(factureStr);
        }

        // Display the list of factures
        response.setContentType("text/plain");
        response.getWriter().println("Liste des factures dans la session :");
        for (String f : factures) {
            response.getWriter().println("- " + f);
        }
    }
}
