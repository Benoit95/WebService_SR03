/**
 * MyWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WS;

public interface MyWS extends java.rmi.Remote {
    public java.lang.String creerAnnonce(java.lang.String nom, java.lang.String tel, java.lang.String ad_rue, java.lang.String ad_ville, java.lang.String ad_cp, java.lang.String nomCategorie) throws java.rmi.RemoteException;
    public java.lang.String listerCategorie() throws java.rmi.RemoteException;
    public java.lang.String supprimerAnnonce(int id) throws java.rmi.RemoteException;
    public java.lang.String creerCategorie(java.lang.String nom) throws java.rmi.RemoteException;
    public java.lang.String modifierAnnonce(java.lang.String nom, java.lang.String tel, java.lang.String ad_rue, java.lang.String ad_ville, java.lang.String ad_cp, java.lang.String nomCategorie) throws java.rmi.RemoteException;
    public java.lang.String supprimerCategorie(int id) throws java.rmi.RemoteException;
    public java.lang.String modifierCategorie(int id, java.lang.String nom) throws java.rmi.RemoteException;
    public java.lang.String rechercheParNomCategorie(java.lang.String nom) throws java.rmi.RemoteException;
    public java.lang.String rechercheParCatAnnonce(java.lang.String nom) throws java.rmi.RemoteException;
    public java.lang.String rechercheParNomAnnonce(java.lang.String nom) throws java.rmi.RemoteException;
    public java.lang.String listerAnnonceOfCat(int idcat) throws java.rmi.RemoteException;
}
