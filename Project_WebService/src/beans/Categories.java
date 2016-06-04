package beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "categories")
public class Categories {
	
	List<Categorie> L_categories = null;

	public List<Categorie> getCategories() {
		return L_categories;
	}

	@XmlElement (name = "categorie")
	public void setCategories(List<Categorie> cat) {
		this.L_categories = cat;
	}
}
