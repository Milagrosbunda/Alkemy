/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Miriam
 */
@Entity
public class Horario implements Serializable {
    
    @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
    @Basic
    int id_hora;
    int hora_inicio;
    int hora_fin;
    boolean disponible;
    @ManyToOne
    Alumno alumno;
    

    public int getId_hora() {
        return id_hora;
    }

    public void setId_hora(int id_hora) {
        this.id_hora = id_hora;
    }    
    public int getHora_inicio() {
        return hora_inicio;
    }
    public void setHora_inicio(int hora_inicio) {
        this.hora_inicio = hora_inicio;
    }
     public int getHora_fin() {
        return hora_fin;
    }
    public void setHora_fin(int hora_fin) {
        this.hora_fin = hora_fin;
    }
    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Horario(int hora_inicio, int hora_fin, Alumno alumno, boolean disponible) {
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.alumno = alumno;
        this.disponible = disponible;
    }

public Horario(){}
    
    
    
    
}
