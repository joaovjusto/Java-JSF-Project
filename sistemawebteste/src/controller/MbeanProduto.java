package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

import entity.Produto;
import service.ProdutoService;

@SuppressWarnings("deprecation")
@ManagedBean(name = "mbeanProduto")
@RequestScoped
public class MbeanProduto {

	@EJB
	ProdutoService produtoService;

	private List<Produto> produtos;
	private Integer id;
	private String nome;	
	private String preco;
	private String descricao;

	private Part foto;

	@PostConstruct
	public void listar() {
		produtos = produtoService.listar();
	}

	public void salvar() {
		try {
			String caminhoDaImagem = salvarFoto(foto);
			
			 Produto produto = new Produto();
			 produto.setId(id);
			 produto.setNome(nome);
			 produto.setPreco(preco);
			 produto.setDescricao(descricao);
			 produto.setCaminhoDaImagem(caminhoDaImagem);

			 produtoService.salvar(produto);
			 
			 id = null;
			 nome = null;
			 preco = null;		
			 descricao = null;	
			 
			 listar();
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}

	private String salvarFoto(Part foto) throws Exception {
		if (foto != null && foto.getSubmittedFileName() != null) {
			byte [] arquivo = new byte [(int) foto.getSize()];
			foto.getInputStream().read(arquivo);
			
			String storage = "/Users/joaojusto/desktop/storage/";
			///Users/joaojusto/desktop/storage/<nome_do_arquivo>			
			File file = new File(storage + foto.getSubmittedFileName());
			FileOutputStream out = new FileOutputStream(file);
			out.write(arquivo);
			out.close();
			
			return storage + foto.getSubmittedFileName();
		} 
		return null;
	}

	public String excluir(Produto produto) {
		produtoService.excluir(produto);
		produtos = produtoService.listar();
		return "";
	}

	
	public String carregar(Produto produto) {
		this.id = produto.getId();
		this.preco = produto.getPreco();
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();		
		
		return "";
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	
	public String getPreco() {
		return preco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Part getFoto() {
		return foto;
	}

	public void setFoto(Part foto) {
		this.foto = foto;
	}

}
