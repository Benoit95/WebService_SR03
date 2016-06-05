<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Modifier une annonce</title>
	</head>
	
	<body>
		<form method="post" action="modifierAnnonce">
		    <fieldset>
		        <legend>Modifier une annonce</legend>
		        
		        <input type="hidden" id=annonceID name="annonceID" value="${annonceID}" /><br>
		        
		        <label for="nom">nom</label>
		        <input type="text" id=nom name="nom" size="30" maxlength="60" /><br>
		        
		        <label for="nom">numéro de téléphone</label>
		        <input type="text" id=tel name="tel" size="30" maxlength="10" /><br>
		        
		        <label for="nom">rue</label>
		        <input type="text" id=ad_rue name="ad_rue" size="30" maxlength="60" /><br>
		        
		        <label for="nom">code postal</label>	        
		        <input type="text" id=ad_cp name="ad_cp" size="30" maxlength="60" /><br>
		        
		        <label for="nom">ville</label>
		        <input type="text" id=ad_ville name="ad_ville" size="30" maxlength="60" /><br>
		        
		        <!-- TODO: ajouter une liste avec les categories -->
		        
		        <input type="submit" value="Modifier"  />

		    </fieldset>
		            
		    <br><br>
		    
		    ${resultat}
		            
		</form>
	</body>
	
</html>