<%--
 * edit.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="item/edit.do" >

	<form:hidden path="tags" />

	<form:label path="name">
					<spring:message code="item.name">:</spring:message>
	</form:label>
	<form:input path="name" />
	<!--
	<form:errors cssClass="error" path="title"></form:errors>
	-->
	<br />


	<form:label path="code">
					<spring:message code="item.code">:</spring:message>
	</form:label>
	<form:input path="code" />
	<br />
	
	<form:label path="description">
					<spring:message code="item.description">:</spring:message>
	</form:label>
	<form:textarea path="description" rows="5"/>
	<br />
	
	<form:label path="price">
					<spring:message code="item.price">:</spring:message>
	</form:label>
	<form:input path="price" />
	<br />
	
	

	<input type="submit"
					name="edit" value="<spring:message code="item.edit" />" />

	
	
</form:form>
