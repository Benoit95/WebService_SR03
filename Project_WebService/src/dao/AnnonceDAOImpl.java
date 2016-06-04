package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Annonce;

public class AnnonceDAOImpl implements AnnonceDAO {

	private static final String SQL_SELECT = "SELECT * FROM annonce";
	private static final String SQL_SELECT_CAT = "SELECT * FROM annonce WHERE id_categorie = ?";
    private static final String SQL_SELECT_PAR_ID = "SELECT * FROM annonce WHERE id = ?";
    private static final String SQL_SELECT_PAR_NOM = "SELECT * FROM annonce WHERE nom = ?";
    private static final String SQL_INSERT           = "INSERT INTO annonce (nom,tel,ad_rue,ad_ville,ad_cp,id_categorie) VALUES (?,?,?,?,?,?)";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM annonce WHERE id = ?";
    private static final String SQL_MODIF_PAR_ID = "UPDATE annonce SET nom = ?, tel = ?, ad_rue = ?, ad_ville = ?, ad_cp = ?, id_categorie = ? WHERE id = ?";

    private DAOFactory          daoFactory;

    AnnonceDAOImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
	@Override
	public Annonce trouver_ByID(int id) throws DAOException {
		return trouver( SQL_SELECT_PAR_ID, id );
	}

	@Override
	public Annonce trouver_ByNom(String nom) throws DAOException {
		return trouver( SQL_SELECT_PAR_NOM, nom );
	}

	@Override
	public List<Annonce> lister() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Annonce> q = new ArrayList<Annonce>();

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
	public List<Annonce> lister(int idcat) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Annonce> q = new ArrayList<Annonce>();

        try {
        	connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_CAT, true, idcat);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                q.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return q;
	}

	@Override
	public void creer(Annonce Annonce) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, Annonce.getNom(), Annonce.getTel(), Annonce.getAd_rue(), Annonce.getAd_ville(), Annonce.getAd_cp(), Annonce.getId_categorie());
            System.out.println(preparedStatement);
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du Annonce, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
            	Annonce.setId( valeursAutoGenerees.getInt( 1 ) );
            } else {
                throw new DAOException( "Échec de la création du Annonce en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
		
	}

	@Override
	public void modifier(Annonce Annonce) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_MODIF_PAR_ID, true,  Annonce.getNom(), Annonce.getTel(), Annonce.getAd_rue(), Annonce.getAd_ville(), Annonce.getAd_cp(), Annonce.getId_categorie(), Annonce.getId() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la modification du Annonce, aucune ligne modifiée de la table." );
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
                throw new DAOException( "Échec de la suppression du Annonce, aucune ligne supprimée de la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
		
	}

    //------------------------------------------------------------//
    
    private Annonce trouver( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Annonce Annonce = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            if ( resultSet.next() ) {
            	Annonce = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return Annonce;
    }	

   private static Annonce map( ResultSet resultSet ) throws SQLException {
	   Annonce q = new Annonce();
       q.setId( resultSet.getInt( "id" ) );
       q.setNom(resultSet.getString("nom"));
       q.setTel(resultSet.getString("tel"));
       q.setAd_rue(resultSet.getString("ad_rue"));
       q.setAd_ville(resultSet.getString("ad_ville"));
       q.setAd_cp(resultSet.getString("ad_cp"));
       q.setId_categorie(resultSet.getInt("id_categorie"));
       return q;
   } 
	
}
