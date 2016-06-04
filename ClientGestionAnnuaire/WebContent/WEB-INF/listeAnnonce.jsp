<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
       <h2>Liste des Annonces de la catégorie n° ${categorieID }</h2>
       
       <c:choose>
            <%-- Si Aucune Annonce enregistrée--%>
            <c:when test="${ resultat == 'Aucune' }">
                <p class="erreur">Aucune Annonce enregistrée.</p>
            </c:when>
            <%-- Sinon--%>
            <c:otherwise>
       
       <%-- Analyse du document XML récupéré. --%>
		<x:parse var="doc" doc="${resultat}" />
		
		<table>
           <thead><tr>
               <th>ID</th>
               <th>Nom</th>
               <th>Telephone</th>
               <th>Rue</th>
               <th>Ville</th>
               <th>Code Postal</th>
               <th>Id Categorie</th>
               <th class="action">Modification</th>
               <th class="action">Suppression</th>                  
           </tr></thead>
           <%-- Parcours du document parsé pour y récupérer chaque nœud "annonce". --%>
           <tbody>
           <x:forEach var="annonce" select="$doc/annonces/annonce">
         	   <x:set var="ID" select="string($annonce/id)"/>
         	   <x:set var="Nom" select="string($annonce/nom)"/>
         	   <x:set var="Telephone" select="string($annonce/tel)"/>
         	   <x:set var="Rue" select="string($annonce/ad_rue)"/>
         	   <x:set var="Ville" select="string($annonce/ad_ville)"/>
         	   <x:set var="CodePostal" select="string($annonce/ad_cp)"/>
         	   <x:set var="Id_Categorie" select="string($annonce/id_categorie)"/>
               
           <tr>
               <%-- Affichage des propriétés de annonce --%>
               <td><c:out value="${ID}"/></td>
               <td><c:out value="${Nom}"/></td>
               <td><c:out value="${Telephone}"/></td>
               <td><c:out value="${Rue}"/></td>
               <td><c:out value="${Ville}"/></td>
               <td><c:out value="${CodePostal}"/></td>
               <td><c:out value="${Id_Categorie}"/></td>
               
               <%-- Lien vers les servlets de gestions--%>
               <td class="action">
                   <a href="<c:url value="modifierAnnonce"><c:param name="annonceID" value="${ID}"/></c:url>">modifier</a>
               </td>
               <td class="action">
                   <a href="<c:url value="supprimerAnnonce"><c:param name="annonceID" value="${ID}" /></c:url>">X</a>
               </td>
           </tr>
           </x:forEach>
           </tbody>
         </table><br><br>
		 
		 </c:otherwise>
		</c:choose>
		 
		 Rajouter une annonce : <a href="<c:url value="creerAnnonce"></c:url>">creerAnnonce</a>
		
				
	</body>
	
</html>