package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Categorie;

public class CategorieDAOImpl implements CategorieDAO {
	
	private static final String SQL_SELECT = "SELECT * FROM categorie";
    private static final String SQL_SELECT_PAR_ID = "SELECT * FROM categorie WHERE id = ?";
    private static final String SQL_SELECT_PAR_NOM = "SELECT * FROM categorie WHERE nom = ?";
    private static final String SQL_INSERT           = "INSERT INTO categorie (nom) VALUES (?)";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM categorie WHERE id = ?";
    private static final String SQL_MODIF_PAR_ID = "UPDATE categorie SET nom = ? WHERE id = ?";

    private DAOFactory          daoFactory;

    CategorieDAOImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
	@Override
	public Categorie trouver_ByID(int id) throws DAOException {
		return trouver( SQL_SELECT_PAR_ID, id );
	}

	@Override
	public Categorie trouver_ByNom(String nom) throws DAOException {
		return trouver( SQL_SELECT_PAR_NOM, nom );
	}

	@Override
	public List<Categorie> lister() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Categorie> q = new ArrayList<Categorie>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                q.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return q;
	}

	@Override
	public void creer(String nom) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, nom);
//            System.out.println(preparedStatement);
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du Categorie, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
            	//Categorie.setId( valeursAutoGenerees.getInt( 1 ) );
            } else {
                throw new DAOException( "Échec de la création du Categorie en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
		
	}

	@Override
	public void modifier(int id, String nom) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_MODIF_PAR_ID, true, nom, id );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la modification du Categorie, aucune ligne modifiée de la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
		
	}

	@Override
	public void supprimer(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, id );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression du Categorie, aucune ligne supprimée de la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
		
	}

    //------------------------------------------------------------//
    
    private Categorie trouver( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Categorie Categorie = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            if ( resultSet.next() ) {
            	Categorie = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return Categorie;
    }	

   private static Categorie map( ResultSet resultSet ) throws SQLException {
	   Categorie q = new Categorie();
       q.setId( resultSet.getInt( "id" ) );
       q.setNom(resultSet.getString("nom"));
       return q;
   }  
	
}
