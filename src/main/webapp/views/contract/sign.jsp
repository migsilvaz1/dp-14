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

<form:form action="contract/sign.do" >

	<form:label path="creationMoment">
					<spring:message code="contract.creationMoment">:</spring:message>
	</form:label>
	<form:input path="creationMoment" readonly/>
	<br />
	
	<form:label path="description">
					<spring:message code="contract.description">:</spring:message>
	</form:label>
	<form:textarea path="description" rows="5" readonly/>
	<br />
	
	<form:label path="startDate">
					<spring:message code="contract.startDate">:</spring:message>
	</form:label>
	<form:input path="startDate" readonly/>
	<br />
	
	<form:label path="endDate">
					<spring:message code="contract.endDate">:</spring:message>
	</form:label>
	<form:input path="endDate" readonly/>
	<br />
	
	<form:label path="contractor">
					<spring:message code="contract.contractor">:</spring:message>
	</form:label>
	<form:input path="contractor" readonly />
	<br />
	
	<input type="submit"
					name="sign" value="<spring:message code="contract.sign" />" />
	
	
</form:form>
