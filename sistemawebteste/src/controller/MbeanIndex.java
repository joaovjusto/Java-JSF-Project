package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entity.Produto;
import service.ProdutoService;

@SuppressWarnings("deprecation")
@ManagedBean(name = "mbeanIndex")
@RequestScoped
public class MbeanIndex {
	
	@EJB
	ProdutoService produtoService;
	
	private List<Produto> produtos;
	
	@PostConstruct
	public void listar() {
		setProdutos(produtoService.listar());
	}	

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	

}
