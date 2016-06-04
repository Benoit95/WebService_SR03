package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WS.MyWSProxy;


public class listeCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	public static final String AFFICHAGE_GET    = "/WEB-INF/listeCategorie.jsp";

	public static final String ATT_RESULTAT = "resultat";
	
	private MyWSProxy ws = new MyWSProxy();

    public listeCategorie() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// On appelle le webservice permettant de lister une catégorie
		String resultat = ws.listerCategorie();

		// On passe en attribut à la requete le résultat donné par le webService.
		request.setAttribute( ATT_RESULTAT, resultat );
		
		/* Affichage de la page */
		this.getServletContext().getRequestDispatcher( AFFICHAGE_GET ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
