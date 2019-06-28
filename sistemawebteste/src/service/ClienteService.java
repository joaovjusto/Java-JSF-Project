package service;

import java.util.List;

import javax.ejb.Local;

import entity.Cliente;

@Local
public interface ClienteService {

	public void salvar(Cliente cliente);
	public List<Cliente> listar();
	public void excluir(Cliente cliente);
	
}
