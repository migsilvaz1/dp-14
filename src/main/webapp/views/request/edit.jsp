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

<form:form action="request/edit.do" >


	<form:hidden path="consumer" />
	<form:hidden path="item" />
	<form:hidden path="contracts" />


	<form:label path="code">
					<spring:message code="request.code">:</spring:message>
	</form:label>
	<form:input path="code" />
	<!--
	<form:errors cssClass="error" path="title"></form:errors>
	-->
	<br />
	
	<form:label path="description">
					<spring:message code="request.description">:</spring:message>
	</form:label>
	<form:textarea path="description" rows="5"/>
	<br />
	
	<form:label path="requestedStart">
					<spring:message code="request.requestedStart">:</spring:message>
	</form:label>
	<form:input path="requestedStart"/>
	<br />
	
	<form:label path="requestedEnd">
					<spring:message code="request.requestedEnd">:</spring:message>
	</form:label>
	<form:input path="requestedEnd"/>
	<br />
	
	
</form:form>
