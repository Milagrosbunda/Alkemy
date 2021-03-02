/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Alumno;
import Logica.Controladora;
import Logica.Horario;
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

/**
 *
 * @author Miriam
 */
@WebServlet(name = "Inscripciones", urlPatterns = {"/Inscripciones"})
public class Inscripciones extends HttpServlet {

    Controladora control = new Controladora();

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
            out.println("<title>Servlet Inscripciones</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Inscripciones at " + request.getContextPath() + "</h1>");
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

        int code = Integer.parseInt(request.getParameter("materia"));
        Materia materia = control.traerMateria(code);
        request.getSession().setAttribute("profeSelec", materia.getProfesor().getNombre() + " " + materia.getProfesor().getApellido());
        request.getSession().setAttribute("horaSelec", materia.getHorario());
        request.getSession().setAttribute("materiaSelec", materia.getNombre());
        request.getSession().setAttribute("descSelec", materia.getDescripcion());
        request.getSession().setAttribute("materia", code);
        request.getSession().setAttribute("txtError", "");
        response.sendRedirect("Inscripcion.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int code = (Integer) request.getSession().getAttribute("materia");
        String status = " ";

        Materia materia = control.traerMateria(code);

        Alumno alumno = control.traerAlumno((int) request.getSession().getAttribute("legajoIn"));

        List<Alumno> nuevosAlumnos = new ArrayList<>();

        for (Alumno al : materia.getAlumnos()) {
            nuevosAlumnos.add(al);
        }

        List<Materia> nuevasMaterias = new ArrayList<>();
        for (Materia m : alumno.getMaterias()) {
            nuevasMaterias.add(m);
        }
        int contador = 0;
        for (Horario h : alumno.getHorarios()) {
            contador++;
            //si esta el horario, veo que este disponible
            if (h.getHora_inicio() == materia.getHorario()) {
                if (h.isDisponible()) {
                    h.setDisponible(false);
                    nuevasMaterias.add(materia);
                    alumno.setMaterias(nuevasMaterias);
                    materia.setCupo(materia.getCupo() - 1);
                    nuevosAlumnos.add(alumno);
                    materia.setAlumnos(nuevosAlumnos);
                    status = "Operacion exitosa: La inscripcion se completó.";
                    break;
                } else {
                    status = "Error: Ya posee otra materia en ese horario.";
                }
            }
        }

        if (contador >= alumno.getHorarios().size() && nuevasMaterias.size() == alumno.getMaterias().size()) {
            Horario nuevoHorario = new Horario(materia.getHorario(), materia.getHorario() + 2, alumno, false);
            control.nuevoHorario(nuevoHorario);
            List<Horario> horariosNuevos = new ArrayList<>();
            for (Horario h : alumno.getHorarios()) {
                horariosNuevos.add(h);
            }
            horariosNuevos.add(nuevoHorario);
            alumno.setHorarios(horariosNuevos);

            nuevosAlumnos.add(alumno);
            materia.setAlumnos(nuevosAlumnos);
            nuevasMaterias.add(materia);
            materia.setCupo(materia.getCupo() - 1);
            alumno.setMaterias(nuevasMaterias);
            control.mod_alumno(alumno);
            control.mod_materia(materia);

            status = "Se incribió con exito! ";
        }

        request.getSession().setAttribute("txtError", status);
        response.sendRedirect("Inscripcion.jsp");

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
