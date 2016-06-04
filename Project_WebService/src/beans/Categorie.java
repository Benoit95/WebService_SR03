package beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "categorie")
public class Categorie {
	int id;
	String nom;
	
	
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
	
	
}
