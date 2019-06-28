package controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import entity.User;
import service.UserService;

@SuppressWarnings("deprecation")
@ManagedBean(name = "MbeanCadastroUser")
@RequestScoped
public class MbeanCadastroUser {

	@EJB
	UserService userService;
	
	private Integer id;
	private String password;	
	private String login;

	public void salvar() {
		try {
			
			 User user = new User();
			 user.setId(id);
			 user.setLogin(login);
			 user.setPassword(password);

			 userService.salvar(user);
			 
			 id = null;
			 login = null;
			 password = null;
			 
			 ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); context.redirect(context.getRequestContextPath() + "/login.jsf");
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String nome) {
		this.password = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
