package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import WS.MyWSProxy;

public class modifierCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String AFFICHAGE_GET    = "/WEB-INF/modifierCategorie.jsp";
	public static final String AFFICHAGE_POST   = "listeCategorie";
	
	public static final String CATEGORIE_ID    ="categorieID";
	public static final String CHAMP_NOM	= "nom";
	
	private MyWSProxy ws = new MyWSProxy();
	
    public modifierCategorie() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter(CATEGORIE_ID);
		request.setAttribute(CATEGORIE_ID, id);
		
		/* Affichage de la page */
		this.getServletContext().getRequestDispatcher( AFFICHAGE_GET ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultat;
		// On récupère le nom de la catégorie a modifier
		String nom = request.getParameter( CHAMP_NOM );
		String id = request.getParameter(CATEGORIE_ID);
		
		// On appelle le webservice permettant de modifier une catégorie
		resultat = ws.modifierCategorie(Integer.parseInt(id), nom);

		/* On renvoie sur la page ou sont affichés toutes les catégories */
		response.sendRedirect(AFFICHAGE_POST);
	}

}
