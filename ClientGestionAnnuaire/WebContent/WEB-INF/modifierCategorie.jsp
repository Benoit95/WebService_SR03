<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<form method="post" action="modifierCategorie">	
		    <fieldset>
		        <legend>Modifier une catégorie</legend>
		        
		        <input type="hidden" id=categorieID name="categorieID" value="${categorieID}" /><br>
		        
		        <label for="nom">nom</label>
		        <input type="text" id=nom name="nom" size="30" maxlength="60" /><br>
		        
		        <input type="submit" value="Modifier"  />

		    </fieldset>
		            
		    <br><br>
		    
		    ${resultat}
		            
		</form>
	</body>
	
</html>