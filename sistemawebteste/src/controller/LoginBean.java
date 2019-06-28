package controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entity.User;
import service.UserService;

@SuppressWarnings("deprecation")
@ManagedBean @SessionScoped
public class LoginBean {
	
	@EJB
	UserService userService;
	
	private List<User> users;
	private String userName;
	private String password;

	//Será desenvolvido a seguir com validação do banco de dados
	
	//@PostConstruct
	//public void listar() {
	//	setUsers(userService.listar());
	//}
	

	public String validateUserLogin() {
		String navResult = "";
		System.out.println("Entered Username is= " + userName + ", password is= " + password);
		if (userName.equalsIgnoreCase("joao") && password.equals("access123")) {
			navResult = "index";
		} else {
			navResult = "failure";
		}
		return navResult;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}
}