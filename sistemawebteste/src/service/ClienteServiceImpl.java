package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.TransactionScoped;

import entity.Cliente;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED) 
public class ClienteServiceImpl implements ClienteService {

	@PersistenceContext(unitName = "sistemaweb")
	EntityManager em;
	
	@Override
	@TransactionScoped()
	public void salvar(Cliente cliente) {
		if (cliente.getId() == null) {
			em.persist(cliente); 
		} else {
			em.merge(cliente);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listar() {
		Query query = em.createQuery("select c from Cliente c");
		return query.getResultList();
	}

	@Override
	public void excluir(Cliente cliente) {
		em.remove(em.merge(cliente));
	}

	
	
}
