/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.cantinaserver.dao;

import br.com.senac.cantinaserver.model.Product;
import java.math.BigDecimal;
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
public class ProductDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void initDB() {
        
        Product product1 = new Product();
        product1.setNome("Pastel de Queijo");
        product1.setValor(new BigDecimal(6.5));
        entityManager.persist(product1);
        
        Product product2 = new Product();
        product2.setNome("Coca-Cola");
        product2.setValor(new BigDecimal(3.3));
        entityManager.persist(product2);
        
        Product product3 = new Product();
        product3.setNome("Fanta Laranja");
        product3.setValor(new BigDecimal(3.0));
        entityManager.persist(product3);
        
        Product product4 = new Product();
        product4.setNome("Pastel de Carne");
        product4.setValor(new BigDecimal(5.5));
        entityManager.persist(product4);
        
        Product product5 = new Product();
        product5.setNome("Salgadinho");
        product5.setValor(new BigDecimal(2.5));
        entityManager.persist(product5);
    }
    
    public void create(Product product) {
        entityManager.persist(product);
        return;
    }
    
    public void update(Product product) {
        entityManager.merge(product);
        return;
    }

    public void delete(Product product) {
        if (entityManager.contains(product)) {
            entityManager.remove(product);
        } else {
            entityManager.remove(entityManager.merge(product));
        }
        return;
    }

    public List<Product> getAll() {
        return entityManager.createQuery("from Product").getResultList();
    }

    public Product getById(long id) {
        return entityManager.find(Product.class, id);
    }
}
