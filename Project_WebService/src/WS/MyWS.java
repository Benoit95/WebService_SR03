package WS;

import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import beans.Annonce;
import beans.Annonces;
import beans.Categorie;
import beans.Categories;
import dao.AnnonceDAO;
import dao.CategorieDAO;
import dao.DAOFactory;

public class MyWS {
	
	// DAO
	DAOFactory daoFactory = DAOFactory.getInstance();
	public CategorieDAO catDAO = daoFactory.getCategorieDAO();
	public AnnonceDAO anoDAO = daoFactory.getAnnonceDAO();
	
	// -------------------------------------------------- //
	// Méthodes du webService servant pour les catégories //
	// -------------------------------------------------- //

	public String creerCategorie(String nom){
		catDAO.creer(nom);
		return "La catégorie "+ nom + " vient d'être rajoutée";
	}
	
	public String modifierCategorie( int id, String nom ){
		catDAO.modifier(id,nom);
		return "Le nom de la catégorie "+ id + " vient d'être modifiée par"+nom;
	}
	
	public String supprimerCategorie( int id){
		if (catDAO.trouver_ByID(id) == null)
			return "La catégorie "+ id + " n'existe pas";
		else{
			catDAO.supprimer(id);
			return "La catégorie "+ id + " vient d'être supprimée";
		}

	}
	
	public String listerCategorie(){
		List<Categorie> Lcat = catDAO.lister();
		Categories categories = new Categories();
		categories.setCategories(Lcat);

		if (Lcat.size() != 0){
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Categories.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(categories, System.out);//debug
				java.io.StringWriter sw = new StringWriter();
				jaxbMarshaller.marshal(categories, sw);
				return sw.toString();
				
			} catch (JAXBException e) {
				e.printStackTrace();
			}			
		}else
			return "Aucune";

		return "Error";
	}
	
	public String rechercheParNomCategorie( String nom){
		Categorie cat =catDAO.trouver_ByNom(nom);
		if (cat != null){
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Categorie.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(cat, System.out); //debug
				java.io.StringWriter sw = new StringWriter();
				jaxbMarshaller.marshal(cat, sw);
				return sw.toString();
				
			} catch (JAXBException e) {
				e.printStackTrace();
			}			
		}else
			return "Aucune";

		
		return "Error";
		
	}
	
	// -------------------------------------------------- //
	// Méthodes du webService servant pour les annonces	  //
	// -------------------------------------------------- //

	public String creerAnnonce(String nom, String tel, String ad_rue, String ad_ville, String ad_cp, String nomCategorie){
		
		// On créé un objet annonce contenant tous les champs passés en paramètres.
		Annonce annonce = new Annonce();
		Categorie cat = catDAO.trouver_ByNom(nomCategorie);
		
		annonce.setNom(nom);
		annonce.setTel(tel);
		annonce.setAd_rue(ad_rue);
		annonce.setAd_ville(ad_ville);
		annonce.setAd_cp(ad_cp);
		if (cat!= null){
			annonce.setId_categorie(cat.getId());
		}else{
			return "Error"; //"La catégorie spécifiée n'existe pas.";
		}
		
		// On créé l'annonce a l'aide du DAO
		anoDAO.creer(annonce);
		
		return "L'annonce"+ nom + " vient d'être rajoutée à la catégorie"+nomCategorie+".";
	}
	
	public String modifierAnnonce(String nom, String tel, String ad_rue, String ad_ville, String ad_cp, int idCategorie){
		// On créé un objet annonce contenant tous les champs passés en paramètres.
		Annonce annonce = new Annonce();
		Categorie cat = catDAO.trouver_ByID(idCategorie);
		
		// On vérifie que l'annonce a modifier existe.
		if (anoDAO.trouver_ByNom(nom) == null)
			return "L'annonce "+nom+" n'existe pas.";
		else
			annonce.setId(anoDAO.trouver_ByNom(nom).getId());

		annonce.setNom(nom);
		annonce.setTel(tel);
		annonce.setAd_rue(ad_rue);
		annonce.setAd_ville(ad_ville);
		annonce.setAd_cp(ad_cp);
		if (cat!= null){
			annonce.setId_categorie(cat.getId());
		}else{
			return "Error"; //"La catégorie spécifiée n'existe pas.";
		}
		
		// On modifie l'annonce a l'aide du DAO
		anoDAO.modifier(annonce);
		
		return "L'annonce"+ nom + " vient d'être modifiée (catégorie: "+idCategorie+").";
	}
	
	public String supprimerAnnonce( int id){
		// On vérifie que l'annonce a modifier existe.
		if (anoDAO.trouver_ByID(id) == null)
			return "L'annonce "+id+" n'existe pas.";
		else{
			anoDAO.supprimer(id);
			return "L'annonce "+ id +" vient d'être supprimée";	
		}
	}
	
	public String listerAnnonceOfCat(int idcat){
		List<Annonce> LAnn = anoDAO.lister(idcat);
		Annonces annonces = new Annonces();
		annonces.setAnnonces(LAnn);

		if (LAnn.size() != 0){
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Annonces.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(annonces, System.out);//debug
				java.io.StringWriter sw = new StringWriter();
				jaxbMarshaller.marshal(annonces, sw);
				return sw.toString();
				
			} catch (JAXBException e) {
				e.printStackTrace();
			}			
		}else
			return "Aucune";

		
		return "Error";
	}

	public String rechercheParNomAnnonce( String nom){
		Annonce ano =anoDAO.trouver_ByNom(nom);
		if (ano != null){
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Annonce.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(ano, System.out); //debug
				java.io.StringWriter sw = new StringWriter();
				jaxbMarshaller.marshal(ano, sw);
				return sw.toString();
				
			} catch (JAXBException e) {
				e.printStackTrace();
			}			
		}else
			return "Aucune"; //"Aucune annonce ne correspond au nom : "+ nom;

		
		return "Error";
		
	}

}
