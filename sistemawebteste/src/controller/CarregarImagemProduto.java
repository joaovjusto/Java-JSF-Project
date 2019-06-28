package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Produto;

@WebServlet("/CarregarImagemProduto")
public class CarregarImagemProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "sistemaweb")
	EntityManager em;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if (request != null && request.getParameter("idProduto") != null) {
		
			Integer idProduto = Integer.valueOf(request.getParameter("idProduto"));
			Produto produto = em.find(Produto.class, idProduto);
			String caminhoDaImagem = produto.getCaminhoDaImagem();
			
			File file = new File(caminhoDaImagem);
			
			byte [] conteudoArquivo = new byte[(int) file.length()];
			
			FileInputStream fis = new FileInputStream(file);
			fis.read(conteudoArquivo);
			
			response.getOutputStream().write(conteudoArquivo);
			
			fis.close();
			
		} 
		
	}	

}
