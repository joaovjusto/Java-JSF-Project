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
import javax.transaction.TransactionScoped;
import entity.Produto;
import entity.Item;
import entity.Pedido;

@SuppressWarnings("deprecation")
@ManagedBean(name = "mbeanCarrinho")
@SessionScoped
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED) 
public class MBeanCarrinho {
	
	@PersistenceContext(unitName = "sistemaweb")
	EntityManager em;

	private List<Item> itens = new ArrayList<>();
	private List<Item> pedidos = new ArrayList<>();
	
	public String adicionar(Produto produto) {
		Item item = new Item();
		item.setQuantidade(1);
		item.setProduto(produto);
		itens.add(item);
		return "carrinho.jsf";
	}
	public String finalizar() {
		pedidos = itens;
		itens = new ArrayList<>();;
		return "pedidos.jsf";
	}
	
	@TransactionScoped()
	public void finalizareee() {
	      for (int counter = 0; counter <= itens.size(); counter++) { 
	          Item item = new Item();
	          item = itens.get(counter);
	          
	          System.out.println(item);
	          
	          em.persist(item); 
	      }
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public List<Item> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Item> pedidos) {
		this.pedidos = pedidos;
	}

}