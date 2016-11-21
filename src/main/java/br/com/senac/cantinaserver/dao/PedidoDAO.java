/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.cantinaserver.dao;

import br.com.senac.cantinaserver.model.Pedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author antonio jr
 */
@Repository
@Transactional
public class PedidoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Pedido pedido) {
        entityManager.persist(pedido);
        return;
    }
    
    public void update(Pedido pedido) {
        entityManager.merge(pedido);
        return;
    }

    public void delete(Pedido pedido) {
        if (entityManager.contains(pedido)) {
            entityManager.remove(pedido);
        } else {
            entityManager.remove(entityManager.merge(pedido));
        }
        return;
    }

    public List<Pedido> getAll() {
        return entityManager.createQuery("from Pedido").getResultList();
    }

    public Pedido getById(long id) {
        return entityManager.find(Pedido.class, id);
    }
}
