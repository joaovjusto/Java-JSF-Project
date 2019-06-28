package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.TransactionScoped;

import entity.Produto;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED) 
public class ProdutoServiceImpl implements ProdutoService {

	@PersistenceContext(unitName = "sistemaweb")
	EntityManager em;
	
	@Override
	@TransactionScoped()
	public void salvar(Produto produto) {
		if (produto.getId() == null) {
			em.persist(produto); 
		} else {
			em.merge(produto);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listar() {
		Query query = em.createQuery("select c from Produto c");
		return query.getResultList();
	}

	@Override
	public void excluir(Produto produto) {
		em.remove(em.merge(produto));
	}

	
	
}
