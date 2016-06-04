package dao;

import java.util.List;

import beans.Annonce;

public interface AnnonceDAO {
	Annonce trouver_ByID( int id ) throws DAOException;
	Annonce trouver_ByNom( String nom) throws DAOException;
	//Annonce trouver_ByCategorie( String categorie_nom) throws DAOException;

	List<Annonce> lister() throws DAOException;
	List<Annonce> lister( int idcat) throws DAOException;
	
    void creer( Annonce Annonce ) throws DAOException;
    void modifier( Annonce Annonce ) throws DAOException;
	void supprimer(int id) throws DAOException;
}
