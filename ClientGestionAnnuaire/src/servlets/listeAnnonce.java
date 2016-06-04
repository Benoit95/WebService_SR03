package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WS.MyWSProxy;


public class listeAnnonce extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	public static final String AFFICHAGE_GET    = "/WEB-INF/listeAnnonce.jsp";
	public static final String AFFICHAGE_LIST_Categorie    = "listeCategorie";

	public static final String CATEGORIE_ID    ="categorieID";
	public static final String ATT_RESULTAT = "resultat";
	
	private MyWSProxy ws = new MyWSProxy();

    public listeAnnonce() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On récupère l'id de la catégorie a explorer.
		String id = (String) request.getParameter(CATEGORIE_ID);
		if (id == null)
			response.sendRedirect(AFFICHAGE_LIST_Categorie); // si null -> renvoie sur la liste des catégories
		else{
			// On appelle le webservice permettant de lister les annonces
			String resultat = ws.listerAnnonceOfCat(Integer.parseInt(id));

			// On passe en attribut à la requete le résultat donné par le webService.
			request.setAttribute( CATEGORIE_ID, id );
			request.setAttribute( ATT_RESULTAT, resultat );
			
			/* Affichage de la page */
			this.getServletContext().getRequestDispatcher( AFFICHAGE_GET ).forward( request, response );			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
