package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Logica.Administrador;
import Logica.Alumno;
import Logica.Controladora;
import Logica.Materia;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/LogIn"})
public class LogIn extends HttpServlet {

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
            out.println("<title>Servlet LogIn</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LogIn at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Controladora control = new Controladora();

        int idIn = Integer.parseInt(request.getParameter("id"));
        int contra = Integer.parseInt(request.getParameter("pass"));

        try {
            Administrador nuevo = control.traerAdm(idIn);

            if (idIn == nuevo.getId_admin() && contra == nuevo.getContra()) {

                response.sendRedirect("opcionAdm.jsp");
            } else {
                String status = "¡Error! El DNI y/o legajo son incorrectos.";
                request.setAttribute("txtErrorLin", status);
                response.sendRedirect("Adm.jsp");
            }
        } catch (IOException a) {
            String status = "¡Error! El DNI y/o legajo son incorrectos.";
            request.setAttribute("txtErrorLin", status);
            response.sendRedirect("Adm.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Controladora control = new Controladora();

        int dniIn = Integer.parseInt(request.getParameter("dni"));
        int legajoIn = Integer.parseInt(request.getParameter("legajo"));

        try {
            Alumno nuevo = control.traerAlumno(legajoIn);

            if (nuevo.getDni() == dniIn && nuevo.getLegajo() == legajoIn) {
                request.getSession().setAttribute("legajoIn", legajoIn);
                response.sendRedirect("TotalMaterias.jsp");

            } else {
                String status = "¡Error! El DNI y/o legajo son incorrectos.";
                request.getSession().setAttribute("txtErrorLI", status);
                response.sendRedirect("index.jsp");
            }
        } catch (IOException a) {
            String status = "¡Error! El DNI y/o legajo son incorrectos.";
            request.getSession().setAttribute("txtErrorLI", status);
            response.sendRedirect("index.jsp");
        }

    }

}
