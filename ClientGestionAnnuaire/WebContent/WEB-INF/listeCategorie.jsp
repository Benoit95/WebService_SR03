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
       <h2>Liste des Categories présentes dans l'annuaire</h2>
       <c:choose>
           <%-- Si Aucune catégorie enregistrée--%>
           <c:when test="${ resultat == 'Aucune' }">
               <p class="erreur">Aucune catégorie enregistrée.</p>
           </c:when>
           <%-- Sinon--%>
           <c:otherwise>
       <%-- Analyse du document XML récupéré. --%>
		<x:parse var="doc" doc="${resultat}" />
		
		<table>
           <thead><tr>
               <th>ID</th>
               <th>Nom</th>
               <th class="action">Annonces</th>
               <th class="action">Modification</th>
               <th class="action">Suppression</th>                  
           </tr></thead>
           <%-- Parcours du document parsé pour y récupérer chaque nœud "categorie". --%>
           <tbody>
           <x:forEach var="categorie" select="$doc/categories/categorie">
         	   <x:set var="categorieID" select="string($categorie/id)"/>
         	   <x:set var="categorieNom" select="string($categorie/nom)"/>
               
           <tr>
               <%-- Affichage des propriétés de categorie --%>
               <td><c:out value="${categorieID}"/></td>
               <td><c:out value="${categorieNom}"/></td>
               <%-- Lien vers les servlets de gestions--%>

               <td class="action">
                   <a href="<c:url value="listeAnnonce"><c:param name="categorieID" value="${categorieID}"/></c:url>">lister</a>
               </td>
               <td class="action">
                   <a href="<c:url value="modifierCategorie"><c:param name="categorieID" value="${categorieID}"/></c:url>">modifier</a>
               </td>
               <td class="action">
                   <a href="<c:url value="supprimerCategorie"><c:param name="categorieID" value="${categorieID}" /></c:url>">X</a>
               </td>
           </tr>
           </x:forEach>
           </tbody>
         </table><br><br>
		 
		 </c:otherwise>
		</c:choose>
		 
		 Rajouter une catégorie : <a href="<c:url value="creerCategorie"></c:url>">creerCategorie</a>

	</body>
	
</html>