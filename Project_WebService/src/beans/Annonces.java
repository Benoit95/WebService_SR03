package beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "annonces")
public class Annonces {

	List<Annonce> L_Annonces = null;

	public List<Annonce> getAnnonces() {
		return L_Annonces;
	}

	@XmlElement (name = "annonce")
	public void setAnnonces(List<Annonce> cat) {
		this.L_Annonces = cat;
	}
	
}
