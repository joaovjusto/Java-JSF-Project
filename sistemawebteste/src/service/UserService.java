package service;

import java.util.List;

import javax.ejb.Local;

import entity.User;

@Local
public interface UserService {

	public void salvar(User user);
	public List<User> listar();
	public void excluir(User user);
	
}
