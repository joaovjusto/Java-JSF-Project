package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entity.Produto;
import entity.Item;

@SuppressWarnings("deprecation")
@ManagedBean(name = "mbeanCarrinho")
@SessionScoped
public class MBeanCarrinho {

	private List<Item> itens = new ArrayList<>();
	
	public String adicionar(Produto produto) {
		Item item = new Item();
		item.setQuantidade(1);
		item.setProduto(produto);
		itens.add(item);
		return "carrinho.jsf";
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

}
