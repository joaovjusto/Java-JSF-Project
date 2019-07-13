package controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import entity.Produto;
import entity.Item;

@SuppressWarnings("deprecation")
@ManagedBean(name = "mbeanCarrinhoda")
@SessionScoped
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED) 
public class MBeanCarrinhoda {
	
	@PersistenceContext(unitName = "sistemaweb")
	EntityManager em;

	private List<Item> itens = new ArrayList<>();
	Boolean adiciona = true;
	
	public String adicionar(Produto produto) {
		
		
		for (int counter = 0; counter < itens.size(); counter++) { 
			System.out.println(counter);
			if (produto.getId() == itens.get(counter).getId()) {
				adiciona = true;
			}
		}
		
		if(adiciona) {			
			Item item = new Item();
			item.setQuantidade(1);
			item.setProduto(produto);
			itens.add(item);
			return "carrinho.jsf";
		}else {
			System.out.println("Id já existente nos pedidos");
			return "carrinho.jsf";
		}
		
	}
	
	@Transactional
	public void finalizar() {
	      for (int counter = 0; counter < itens.size(); counter++) { 
	          Item item = new Item();
	          item = itens.get(counter);
			    em.createNativeQuery("INSERT INTO TAB_PEDIDO (id, quantidade, produto) VALUES (?,?,?)")
			      .setParameter(1, item.getId())
			      .setParameter(2, item.getQuantidade())
			      .setParameter(3, item.getProduto())
			      .executeUpdate();
	      }
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

}
