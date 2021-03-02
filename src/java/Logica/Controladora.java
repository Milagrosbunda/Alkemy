/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.ControladoraPersistencia;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miriam
 */
public class Controladora {
    
      ControladoraPersistencia controlP = new ControladoraPersistencia();
    
   
      
      public void mod_materia (Materia materia){
       
        try {
            controlP.mod_materia(materia);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       }
      
      public void mod_alumno(Alumno alumno){
      controlP.mod_alumno(alumno);
      }
       
      public void nuevoHorario(Horario h){
           controlP.nuevoHorario(h);
       }
        public void mod_profesor (Profesor profe){
       
        try {
            controlP.mod_profesor(profe);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
      
           public List<Materia> traerMaterias (){
        
        return controlP.traerMaterias();
        }
             public Materia traerMateria (int code){
        
        return controlP.traerMateria(code);
        }
           
          public Alumno traerAlumno (int legajo){
        
        return controlP.traerAlumno(legajo);
        }
          public Administrador traerAdm (int id){
        
        return controlP.traerAdm(id);
        }
          
           public void nueva_Materia(String nombre, int cupo, int horario, Profesor profesor, String desc){
        
            controlP.nueva_Materia(nombre, cupo, horario, profesor, desc);
        }
           
           public void nuevo_Prof(int dni, String nombre, String apellido, boolean activo){
           
               controlP.nuevo_Prof(dni, nombre, apellido, activo);
           }
           
           public Profesor traerProf(int dni){
           return controlP.traerProf(dni);
           }
       public List<Profesor> traerProfes (){
        return controlP.traerProfes();
        }
    
       public Object reemplazo(Object obj, Object nuevo){
       if(obj == null || obj.equals("")){
           obj= nuevo;
       }
       return obj;
       }
      

}
