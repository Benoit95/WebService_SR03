package dao;

import java.util.List;

import beans.Categorie;

public interface CategorieDAO {
	Categorie trouver_ByID( int id ) throws DAOException;
	Categorie trouver_ByNom( String nom) throws DAOException;

	List<Categorie> lister() throws DAOException;
	
    void creer( String nom ) throws DAOException;
    void modifier( int id, String nom ) throws DAOException;
	void supprimer(int id) throws DAOException;
}
