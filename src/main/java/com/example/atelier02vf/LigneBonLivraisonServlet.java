/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.atelier02vf;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mouad
 */
@WebServlet(name = "LigneBonLivraisonServlet", urlPatterns = {"/LigneBonLivraisonServlet"})
public class LigneBonLivraisonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String libelle = request.getParameter("libelle");
        int qte = Integer.parseInt(request.getParameter("qte"));
        
        LigneBonLivraisonModel ligne = new LigneBonLivraisonModel();
        ligne.setLibelle(libelle);
        ligne.setQte(qte);
        
        Map<String, String> errors = ligne.validate();
        
        if (errors.isEmpty()) {
        
            response.getWriter().write("Enregistrement réussi!");
        } else {
            
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}