package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import WS.MyWSProxy;

public class creerCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String AFFICHAGE_GET    = "/WEB-INF/creerCategorie.jsp";
	public static final String AFFICHAGE_POST    = "listeCategorie";

	public static final String CHAMP_NOM	= "nom";
	
	public static final String ATT_RESULTAT_FROM_WS = "resultatFromWS";
	
	private MyWSProxy ws = new MyWSProxy();
	
    public creerCategorie() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page */
		this.getServletContext().getRequestDispatcher( AFFICHAGE_GET ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultat;
		// On récupère le nom de la catégorie a créer
		String nom = request.getParameter( CHAMP_NOM );
		
		// On appelle le webservice permettant de créer une catégorie
		resultat = ws.creerCategorie(nom);

		/* On renvoie sur la page ou sont affichés toutes les catégories */
		response.sendRedirect(AFFICHAGE_POST);
		
	}

}
