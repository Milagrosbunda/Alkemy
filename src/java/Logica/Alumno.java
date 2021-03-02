/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Miriam
 */
@Entity
public class Alumno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int legajo;
    @Basic
    String nombre;
    int dni;
    @ManyToMany
    List<Materia> materias;
    @OneToMany
    List<Horario> horarios;

    public Alumno() {
    }

    public Alumno(int legajo, String nombre, int dni, List<Materia> materias) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.dni = dni;
        this.materias = materias;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

}
