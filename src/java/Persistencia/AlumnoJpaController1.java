/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Alumno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Horario;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Miriam
 */
public class AlumnoJpaController1 implements Serializable {

    public AlumnoJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumno alumno) {
        if (alumno.getHorarios() == null) {
            alumno.setHorarios(new ArrayList<Horario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Horario> attachedHorarios = new ArrayList<Horario>();
            for (Horario horariosHorarioToAttach : alumno.getHorarios()) {
                horariosHorarioToAttach = em.getReference(horariosHorarioToAttach.getClass(), horariosHorarioToAttach.getId_hora());
                attachedHorarios.add(horariosHorarioToAttach);
            }
            alumno.setHorarios(attachedHorarios);
            em.persist(alumno);
            for (Horario horariosHorario : alumno.getHorarios()) {
                Alumno oldAlumnoOfHorariosHorario = horariosHorario.getAlumno();
                horariosHorario.setAlumno(alumno);
                horariosHorario = em.merge(horariosHorario);
                if (oldAlumnoOfHorariosHorario != null) {
                    oldAlumnoOfHorariosHorario.getHorarios().remove(horariosHorario);
                    oldAlumnoOfHorariosHorario = em.merge(oldAlumnoOfHorariosHorario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumno alumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno persistentAlumno = em.find(Alumno.class, alumno.getLegajo());
            List<Horario> horariosOld = persistentAlumno.getHorarios();
            List<Horario> horariosNew = alumno.getHorarios();
            List<Horario> attachedHorariosNew = new ArrayList<Horario>();
            for (Horario horariosNewHorarioToAttach : horariosNew) {
                horariosNewHorarioToAttach = em.getReference(horariosNewHorarioToAttach.getClass(), horariosNewHorarioToAttach.getId_hora());
                attachedHorariosNew.add(horariosNewHorarioToAttach);
            }
            horariosNew = attachedHorariosNew;
            alumno.setHorarios(horariosNew);
            alumno = em.merge(alumno);
            for (Horario horariosOldHorario : horariosOld) {
                if (!horariosNew.contains(horariosOldHorario)) {
                    horariosOldHorario.setAlumno(null);
                    horariosOldHorario = em.merge(horariosOldHorario);
                }
            }
            for (Horario horariosNewHorario : horariosNew) {
                if (!horariosOld.contains(horariosNewHorario)) {
                    Alumno oldAlumnoOfHorariosNewHorario = horariosNewHorario.getAlumno();
                    horariosNewHorario.setAlumno(alumno);
                    horariosNewHorario = em.merge(horariosNewHorario);
                    if (oldAlumnoOfHorariosNewHorario != null && !oldAlumnoOfHorariosNewHorario.equals(alumno)) {
                        oldAlumnoOfHorariosNewHorario.getHorarios().remove(horariosNewHorario);
                        oldAlumnoOfHorariosNewHorario = em.merge(oldAlumnoOfHorariosNewHorario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = alumno.getLegajo();
                if (findAlumno(id) == null) {
                    throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno;
            try {
                alumno = em.getReference(Alumno.class, id);
                alumno.getLegajo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.", enfe);
            }
            List<Horario> horarios = alumno.getHorarios();
            for (Horario horariosHorario : horarios) {
                horariosHorario.setAlumno(null);
                horariosHorario = em.merge(horariosHorario);
            }
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumno> findAlumnoEntities() {
        return findAlumnoEntities(true, -1, -1);
    }

    public List<Alumno> findAlumnoEntities(int maxResults, int firstResult) {
        return findAlumnoEntities(false, maxResults, firstResult);
    }

    private List<Alumno> findAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alumno.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Alumno findAlumno(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alumno> rt = cq.from(Alumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
