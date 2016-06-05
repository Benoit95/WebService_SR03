package WS;

public class MyWSProxy implements WS.MyWS {
  private String _endpoint = null;
  private WS.MyWS myWS = null;
  
  public MyWSProxy() {
    _initMyWSProxy();
  }
  
  public MyWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initMyWSProxy();
  }
  
  private void _initMyWSProxy() {
    try {
      myWS = (new WS.MyWSServiceLocator()).getMyWS();
      if (myWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)myWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)myWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (myWS != null)
      ((javax.xml.rpc.Stub)myWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public WS.MyWS getMyWS() {
    if (myWS == null)
      _initMyWSProxy();
    return myWS;
  }
  
  public java.lang.String listerAnnonceOfCat(int idcat) throws java.rmi.RemoteException{
    if (myWS == null)
      _initMyWSProxy();
    return myWS.listerAnnonceOfCat(idcat);
  }
  
  public java.lang.String rechercheParNomAnnonce(java.lang.String nom) throws java.rmi.RemoteException{
    if (myWS == null)
      _initMyWSProxy();
    return myWS.rechercheParNomAnnonce(nom);
  }
  
  public java.lang.String supprimerCategorie(int id) throws java.rmi.RemoteException{
    if (myWS == null)
      _initMyWSProxy();
    return myWS.supprimerCategorie(id);
  }
  
  public java.lang.String modifierCategorie(int id, java.lang.String nom) throws java.rmi.RemoteException{
    if (myWS == null)
      _initMyWSProxy();
    return myWS.modifierCategorie(id, nom);
  }
  
  public java.lang.String rechercheParNomCategorie(java.lang.String nom) throws java.rmi.RemoteException{
    if (myWS == null)
      _initMyWSProxy();
    return myWS.rechercheParNomCategorie(nom);
  }
  
  public java.lang.String creerCategorie(java.lang.String nom) throws java.rmi.RemoteException{
    if (myWS == null)
      _initMyWSProxy();
    return myWS.creerCategorie(nom);
  }
  
  public java.lang.String listerCategorie() throws java.rmi.RemoteException{
    if (myWS == null)
      _initMyWSProxy();
    return myWS.listerCategorie();
  }
  
  public java.lang.String supprimerAnnonce(int id) throws java.rmi.RemoteException{
    if (myWS == null)
      _initMyWSProxy();
    return myWS.supprimerAnnonce(id);
  }
  
  public java.lang.String modifierAnnonce(java.lang.String nom, java.lang.String tel, java.lang.String ad_rue, java.lang.String ad_ville, java.lang.String ad_cp, int idCategorie) throws java.rmi.RemoteException{
    if (myWS == null)
      _initMyWSProxy();
    return myWS.modifierAnnonce(nom, tel, ad_rue, ad_ville, ad_cp, idCategorie);
  }
  
  public java.lang.String creerAnnonce(java.lang.String nom, java.lang.String tel, java.lang.String ad_rue, java.lang.String ad_ville, java.lang.String ad_cp, java.lang.String nomCategorie) throws java.rmi.RemoteException{
    if (myWS == null)
      _initMyWSProxy();
    return myWS.creerAnnonce(nom, tel, ad_rue, ad_ville, ad_cp, nomCategorie);
  }
  
  
}