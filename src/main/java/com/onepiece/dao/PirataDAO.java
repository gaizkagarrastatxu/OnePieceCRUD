package com.onepiece.dao;

import com.onepiece.model.Pirata;
import com.onepiece.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class PirataDAO {

    // CREATE - Guardar un nuevo pirata
    public void guardar(Pirata pirata) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(pirata);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // READ - Buscar un pirata por ID
    public Pirata buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Pirata.class, id);
        } finally {
            em.close();
        }
    }

    // READ - Listar todos los piratas
    public List<Pirata> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pirata p", Pirata.class).getResultList();
        } finally {
            em.close();
        }
    }

    // UPDATE - Actualizar los datos de un pirata existente
    public void actualizar(Pirata pirata) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(pirata);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // DELETE - Eliminar un pirata por ID
    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Pirata pirata = em.find(Pirata.class, id);
            if (pirata != null) {
                em.remove(pirata);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}