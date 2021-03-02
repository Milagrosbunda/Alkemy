/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Administrador;
import Logica.Alumno;
import Logica.Horario;
import Logica.Materia;
import Logica.Profesor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miriam
 */
public class ControladoraPersistencia {
    
    AlumnoJpaController alumnojpa = new AlumnoJpaController();
    AdministradorJpaController admjpa = new AdministradorJpaController();
    MateriaJpaController materiajpa = new MateriaJpaController();
    ProfesorJpaController profesorjpa = new ProfesorJpaController();
    HorarioJpaController horariojpa = new HorarioJpaController();
    
    
       public void nuevoHorario(Horario h){
           horariojpa.create(h);
       }
       
       public void mod_materia (Materia materia){
       
        try {
            materiajpa.edit(materia);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       }
       
       public void mod_alumno(Alumno alumno){
       
        try {
            alumnojpa.edit(alumno);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       
        public void mod_profesor (Profesor profe){
       
           
            
        try {
            profesorjpa.edit(profe);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       }
        
        public List<Materia> traerMaterias (){
        
        return materiajpa.findMateriaEntities();
        }
        
         public Materia traerMateria (int code){
        
        return materiajpa.findMateria(code);
        }
        
         public Alumno traerAlumno (int legajo){
        
        return alumnojpa.findAlumno(legajo);
        }
        
           public Administrador traerAdm (int id){
        
        return admjpa.findAdministrador(id);
        }
           
        public void nueva_Materia(String nombre, int cupo, int horario, Profesor profesor, String desc){
        
            Materia nueva = new Materia();
            nueva.setNombre(nombre);
            nueva.setCupo(cupo);
            nueva.setHorario(horario);
            nueva.setProfesor(profesor);
            nueva.setDescripcion(desc);
            materiajpa.create(nueva);
        }
        
        public void nuevo_Prof(int dni, String nombre, String apellido, boolean activo){
        
            Profesor nuevo = new Profesor();
            nuevo.setActivo(activo);
            nuevo.setNombre(nombre);
            nuevo.setApellido(apellido);
            nuevo.setDni(dni);
        try {
            profesorjpa.create(nuevo);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        public Profesor traerProf (int dni){
        return profesorjpa.findProfesor(dni);
        }
        
        public List<Profesor> traerProfes (){
        return profesorjpa.findProfesorEntities();
        }
}
