package com.springMVCMaven.DAO;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springMVCMaven.Model.SpringJpaDemo;

import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;
import java.util.List;

@Service
public class SpringJpaDemoService {

    @PersistenceContext //or @Autowired but persistencecontext specially designed for jpa
    private EntityManager entityManager;

    @Transactional
    public void save(SpringJpaDemo entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public SpringJpaDemo findById(Long id) {
        return entityManager.find(SpringJpaDemo.class, id);
    }

    @Transactional
    public void delete(Long id) {
        SpringJpaDemo entity = entityManager.find(SpringJpaDemo.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Transactional(readOnly = true)
    public List<SpringJpaDemo> findAll() {
        return entityManager.createQuery("SELECT e FROM SpringJpaDemo e", SpringJpaDemo.class).getResultList();
    }
}
