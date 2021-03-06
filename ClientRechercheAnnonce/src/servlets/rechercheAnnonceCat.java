package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WS.MyWSProxy;


public class rechercheAnnonceCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	public static final String AFFICHAGE_GET    = "/WEB-INF/rechercheAnnonceCat.jsp";
	public static final String AFFICHAGE_POST    = "/WEB-INF/rechercheAnnonceCat.jsp";

	public static final String CHAMP_NOM_CAT = "nomCat";
	
	public static final String ATT_RESULTAT = "resultat";
	
	private MyWSProxy ws = new MyWSProxy();

    public rechercheAnnonceCat() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			/* Affichage de la page */
			this.getServletContext().getRequestDispatcher( AFFICHAGE_GET ).forward( request, response );			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On récupère le nom de l'annonce a rechercher
		String nom = request.getParameter( CHAMP_NOM_CAT );
		
		//Appel du ws
		String resultat = ws.rechercheParCatAnnonce(nom);
		
		// On passe en attribut à la requete le résultat donné par le webService.
		request.setAttribute( ATT_RESULTAT, resultat );
		
		/* Affichage de la page */
		this.getServletContext().getRequestDispatcher( AFFICHAGE_POST ).forward( request, response );	
	}

}
