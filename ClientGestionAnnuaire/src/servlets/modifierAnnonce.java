package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import WS.MyWSProxy;

public class modifierAnnonce extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String AFFICHAGE_GET    = "/WEB-INF/modifierAnnonce.jsp";
	public static final String AFFICHAGE_POST   = "listeAnnonce";
	
	public static final String ANNONCE_ID    = "annonceID";
	public static final String CATEGORIE_ID    = "categorieID";
	public static final String CHAMP_NOM	= "nom";
	public static final String CHAMP_TEL	= "tel";
	public static final String CHAMP_AD_RUE	= "ad_rue";
	public static final String CHAMP_AD_CP	= "ad_cp";
	public static final String CHAMP_AD_VILLE	= "ad_ville";
	
	private MyWSProxy ws = new MyWSProxy();
	
    public modifierAnnonce() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cat = (String) request.getParameter(CATEGORIE_ID);
		request.setAttribute(CATEGORIE_ID, cat);
		System.out.println("-------doGet-------");
		System.out.println(cat);
		String id = (String) request.getParameter(ANNONCE_ID);
		request.setAttribute(ANNONCE_ID, id);
		
		/* Affichage de la page */
		this.getServletContext().getRequestDispatcher( AFFICHAGE_GET ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultat;
		String nom = request.getParameter( CHAMP_NOM );
		String tel = request.getParameter( CHAMP_TEL );
		String ad_rue = request.getParameter( CHAMP_AD_RUE );
		String ad_cp = request.getParameter( CHAMP_AD_CP );
		String ad_ville = request.getParameter( CHAMP_AD_VILLE );
		String id = request.getParameter(ANNONCE_ID);
		//int cat = Integer.parseInt(request.getParameter(CATEGORIE_ID));
		//int cat = 24;
		String cat = (String) request.getParameter(CATEGORIE_ID);
		System.out.println("--------------");
		System.out.println(cat);	//null <-- pourquoi ?
		System.out.println("--------------");
//		
//		// On appelle le webservice permettant de modifier une annonce
//		resultat = ws.modifierAnnonce(nom, tel, ad_rue, ad_ville, ad_cp, cat);
//		System.out.println(resultat);

		/* On renvoie sur la page ou sont affichés toutes les annonces */
		response.sendRedirect(AFFICHAGE_POST);
	}

}
