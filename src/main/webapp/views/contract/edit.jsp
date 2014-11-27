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

<form:form action="contract/edit.do" >

	<form:hidden path="creationMoment" />
	<form:hidden path="contractor" />
	<form:hidden path="request" />
	<form:hidden path="auditionRecord" />
	<form:hidden path="invoices" />

	<form:label path="description">
					<spring:message code="contract.description">:</spring:message>
	</form:label>
	<form:textarea path="description" rows="5"/>
	<br />
	
	<form:label path="startDate">
					<spring:message code="contract.startDate">:</spring:message>
	</form:label>
	<form:input path="startDate" />
	<br />
	
	<form:label path="endDate">
					<spring:message code="contract.endDate">:</spring:message>
	</form:label>
	<form:input path="endDate" />
	<br />

	<form:label path="dateContractHolderSign">
					<spring:message code="contract.dateContractHolderSign">:</spring:message>
	</form:label>
	<form:input path="dateContractHolderSign" />
	<br />
	
	<form:label path="dateContractorSign">
					<spring:message code="contract.dateContractorSign">:</spring:message>
	</form:label>
	<form:input path="dateContractorSign" />
	<br />
	
	<form:label path="consumerAssessment">
					<spring:message code="contract.consumerAssessment">:</spring:message>
	</form:label>
	<form:input path="consumerAssessment" />
	<br />
	
	<form:label path="supplierAssessment">
					<spring:message code="contract.supplierAssessment">:</spring:message>
	</form:label>
	<form:input path="supplierAssessment" />
	<br />
	
	<input type="submit"
					name="edit" value="<spring:message code="contract.edit" />" />
	
</form:form>
