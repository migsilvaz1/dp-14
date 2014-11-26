<%--
 * list.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="actors" id="row" pagesize="5" class="displaytag" 
				requestURI="${requestURI}" >
	
	<spring:message code="actor.name" var="name" />
		<display:column property="actor.name" title="${name}" sortable="true" />
		
	<spring:message code="actor.surname" var="surname" />
		<display:column property="actor.surname" title="${surname}" sortable="true" />
	
	<display:column>
			<a href="profile/consumer/curriculum/edit.do?userAccountId=${row.id}">
				<spring:message	code="curriculum.edit" />
			</a>
	</display:column>
	<jstl:if test="${actor.userAccount.authorities[0]=='SUPPLIER'}">
	<security:authorize access="hasRole('CONSUMER')">
		<display:column>
			<a href="item/list.do?supplierId=${row.id}">
				<spring:message	code="item.list" />
			</a>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('SUPPLIER')">
		<display:column>
			<a href="item/list.do?supplierId=${row.id}">
				<spring:message	code="item.list" />
			</a>
		</display:column>
	</security:authorize>
	</jstl:if>
</display:table>
