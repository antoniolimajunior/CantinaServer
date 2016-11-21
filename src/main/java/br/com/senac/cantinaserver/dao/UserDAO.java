package br.com.senac.cantinaserver.dao;

import br.com.senac.cantinaserver.model.User;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public User login(String matricula, String senha) {
        User user = null;
        try {
            user = (User) entityManager.createNamedQuery("User.findByLogin")
                    .setParameter("matricula", matricula)
                    .setParameter("senha", senha)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findById(Long id) {
        User user = null;
        try {
            user = (User) entityManager.createNamedQuery("User.findById")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public void initDB() {
        User user1 = new User();
        user1.setMatricula("631120054");
        user1.setNome("Antonio de Lima Jr");
        user1.setSenha("123");
        user1.setSaldo(new BigDecimal(50));
        entityManager.persist(user1);

        User user2 = new User();
        user2.setMatricula("12345");
        user2.setNome("Luis Ries");
        user2.setSenha("123");
        user2.setSaldo(new BigDecimal(100));
        entityManager.persist(user2);
    }

    public void create(User user) {
        entityManager.persist(user);
        return;
    }

    public void delete(User user) {
        if (entityManager.contains(user)) {
            entityManager.remove(user);
        } else {
            entityManager.remove(entityManager.merge(user));
        }
        return;
    }

    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    public void update(User user) {
        entityManager.merge(user);
        return;
    }
}
