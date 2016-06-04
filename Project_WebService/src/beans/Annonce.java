package beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "annonce")
public class Annonce {
	int id;
	String nom;
	String tel;
	String ad_rue;
	String ad_ville;
	String ad_cp;
	int id_categorie;
	
	
	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	@XmlElement
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getTel() {
		return tel;
	}
	@XmlElement
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAd_rue() {
		return ad_rue;
	}
	@XmlElement
	public void setAd_rue(String ad_rue) {
		this.ad_rue = ad_rue;
	}
	public String getAd_ville() {
		return ad_ville;
	}
	@XmlElement
	public void setAd_ville(String ad_ville) {
		this.ad_ville = ad_ville;
	}
	public String getAd_cp() {
		return ad_cp;
	}
	@XmlElement
	public void setAd_cp(String ad_cp) {
		this.ad_cp = ad_cp;
	}
	public int getId_categorie() {
		return id_categorie;
	}
	@XmlElement
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}
}
