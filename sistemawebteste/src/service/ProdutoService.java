package service;

import java.util.List;

import javax.ejb.Local;

import entity.Produto;

@Local
public interface ProdutoService {

	public void salvar(Produto produto);
	public List<Produto> listar();
	public void excluir(Produto produto);
	
}
