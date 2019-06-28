package service;

import java.util.List;

//import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import javax.persistence.Query;
import javax.transaction.TransactionScoped;

import entity.User;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED) 
public class UserServiceImpl implements UserService {

	@PersistenceContext(unitName = "sistemaweb")
	EntityManager em;
	
	@Override
	@TransactionScoped()
	public void salvar(User user) {
		if (user.getId() == null) {
			em.persist(user); 
		} else {
			em.merge(user);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listar() {
		Query query = em.createQuery("select c from User c");
		return query.getResultList();
	}

	@Override
	public void excluir(User user) {
		em.remove(em.merge(user));
	}

	
	
}
