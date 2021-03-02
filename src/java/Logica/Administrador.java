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

/**
 *
 * @author Miriam
 */
@Entity
public class Administrador implements Serializable {
    
     @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
     int id_admin;
     
     @Basic
     String nombre;
     int contra;

    public Administrador() {
    }

    public Administrador(int id_admin, String nombre, int contra) {
        this.id_admin = id_admin;
        this.nombre = nombre;
        this.contra = contra;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getContra() {
        return contra;
    }

    public void setContra(int contra) {
        this.contra = contra;
    }
     
     
     
     
}
