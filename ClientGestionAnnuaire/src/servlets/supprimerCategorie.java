package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import WS.MyWSProxy;

public class supprimerCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String AFFICHAGE_GET    = "listeCategorie";
	
	public static final String CATEGORIE_ID    ="categorieID";
	
	private MyWSProxy ws = new MyWSProxy();
	
    public supprimerCategorie() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter(CATEGORIE_ID);
		String resultat = ws.supprimerCategorie(Integer.parseInt(id));
		
		/* Affichage de la page */
		response.sendRedirect(AFFICHAGE_GET);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
