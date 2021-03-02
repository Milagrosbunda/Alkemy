/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.Profesor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Miriam
 */
@WebServlet(name = "GestionProfesores", urlPatterns = {"/GestionProfesores"})
public class GestionProfesores extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GestionProfesores</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GestionProfesores at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       Controladora control = new Controladora();
       
        int dni = Integer.parseInt(request.getParameter("profesores"));
        request.getSession().setAttribute("dniBuscado", dni);
        
        Profesor p = control.traerProf(dni);
        
        
        request.getSession().setAttribute("nombreP", p.getNombre());
        request.getSession().setAttribute("apellidoP", p.getApellido());
        request.getSession().setAttribute("dniP", p.getDni());
        
        if(p.isActivo()){
            request.getSession().setAttribute("activoP","Activo" );
        }
        else{
        request.getSession().setAttribute("activoP", "Inactivo");}
        
        response.sendRedirect("GestionProf.jsp");
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         Controladora control = new Controladora();
       
        int dniBuscado = (int)(request.getSession().getAttribute("dniBuscado"));
        
        Profesor p = control.traerProf(dniBuscado);
        
        int dniNuevo = (int) control.reemplazo(request.getParameter("dniN"), p.getDni());
        String nombreNuevo = (String) control.reemplazo(request.getParameter("nombreN"), p.getNombre());
        String apellidoNuevo = (String) control.reemplazo(request.getParameter("apellidoN"), p.getApellido());
        
        boolean estadoNuevo; 
        
             if(Integer.parseInt(request.getParameter("estadoN"))==1){
            estadoNuevo = false;
             } else{
             estadoNuevo=true;}
        
        String status ="";
        try{
            p.setNombre(nombreNuevo);
            p.setApellido(apellidoNuevo);
            p.setActivo(estadoNuevo);
            p.setDni(dniNuevo);
        control.mod_profesor(p);
        request.getSession().setAttribute("nombreP", p.getNombre());
        request.getSession().setAttribute("apellidoP", p.getApellido());
        request.getSession().setAttribute("dniP", p.getDni());
        if(estadoNuevo){
        request.getSession().setAttribute("activoP", "Activo");
        }else{
        request.getSession().setAttribute("activoP", "Inactivo");
        }
        response.sendRedirect("GestionProf.jsp");
        status = "Se modifico con exito!";
        }
        catch(Exception a){
        status = "Ocurrio un error";
        response.sendRedirect("GestionProf.jsp");
        }
        finally{
        request.getSession().setAttribute("txtError", status);
        }
        
        
       }
        
        
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
