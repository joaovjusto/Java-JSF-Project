package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entity.Produto;
import service.ProdutoService;

@SuppressWarnings("deprecation")
@ManagedBean(name = "mbeanDetalhe")
@SessionScoped
public class MbeanDetalhe {

	@EJB
	ProdutoService produtoService;

	private Produto produto;
	
	public String carregar(Produto produto) {
		this.produto = produto;
		return "detalhe.jsf";
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
