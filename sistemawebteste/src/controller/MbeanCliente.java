package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

import entity.Cliente;
import service.ClienteService;

@SuppressWarnings("deprecation")
@ManagedBean(name = "mbeanCliente")
@RequestScoped
public class MbeanCliente {

	@EJB
	ClienteService clienteService;

	private List<Cliente> clientes;
	private Integer id;
	private String nome;	
	private String login;

	private Part foto;

	@PostConstruct
	public void listar() {
		clientes = clienteService.listar();
	}

	public void salvar() {
		try {
			String caminhoDaImagem = salvarFoto(foto);
			
			 Cliente cliente = new Cliente();
			 cliente.setId(id);
			 cliente.setNome(nome);
			 cliente.setLogin(login);
			 cliente.setCaminhoDaImagem(caminhoDaImagem);

			 clienteService.salvar(cliente);
			 
			 id = null;
			 nome = null;
			 login = null;					
			 
			 listar();
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}

	private String salvarFoto(Part foto) throws Exception {
		if (foto != null && foto.getSubmittedFileName() != null) {
			byte [] arquivo = new byte [(int) foto.getSize()];
			foto.getInputStream().read(arquivo);
			
			String storage = "/home/joao-justo/Desktop/storage/";
			////home/joao-justo/Desktop/storage/<nome_do_arquivo>			
			File file = new File(storage + foto.getSubmittedFileName());
			FileOutputStream out = new FileOutputStream(file);
			out.write(arquivo);
			out.close();
			
			return storage + foto.getSubmittedFileName();
		} 
		return null;
	}

	public String excluir(Cliente cliente) {
		clienteService.excluir(cliente);
		clientes = clienteService.listar();
		return "";
	}

	
	public String carregar(Cliente cliente) {
		this.id = cliente.getId();
		this.login = cliente.getLogin();
		this.nome = cliente.getNome();		
		
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Part getFoto() {
		return foto;
	}

	public void setFoto(Part foto) {
		this.foto = foto;
	}

}
