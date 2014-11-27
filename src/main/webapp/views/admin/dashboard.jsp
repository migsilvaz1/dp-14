<%--
 * edit.jsp
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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<form:form action="admin/dashboard.do" >


	<form:label path="seccion1">
					<spring:message code="admin.seccion1">:</spring:message>
	</form:label>
	<br />

	<display:table name="auditors" id="row" pagesize="5" class="displaytag" requestURI="${requestURI}">
		<spring:message code="admin.auditor" var="name"/>
		<display:column class="auditor" property="auditor" title="${row.key}"/>
		
		<spring:message code="admin.auditor.min" var="min"/>
		<display:column class="Double" property="min" title="${row.getValue(row.key)[0]}"/> 
		
		<spring:message code="admin.auditor.max" var="max"/>
		<display:column class="Double" property="max" title="${row.getValue(row.key)[1]}"/> 
		
		<spring:message code="admin.auditor.ave" var="ave"/>
		<display:column class="Double" property="ave" title="${row.getValue(row.key)[2]}"/> 
	</display:table>	
	<br />


	<form:label path="seccion2">
					<spring:message code="admin.seccion2">:</spring:message>
	</form:label>
	<br />
	
	<display:table name="auditionrecords" id="row" pagesize="5" class="displaytag" requestURI="${requestURI}">
		<spring:message code="admin.auditonrecord" var="name"/>
		<display:column class="auditor" property="auditor" title="${row.key}"/>
		
		<spring:message code="admin.auditonrecord.min" var="min"/>
		<display:column class="Double" property="min" title="${row.getValue(row.key)[0]}"/> 
		
		<spring:message code="admin.auditonrecord.max" var="max"/>
		<display:column class="Double" property="max" title="${row.getValue(row.key)[1]}"/> 
		
		<spring:message code="admin.auditonrecord.ave" var="ave"/>
		<display:column class="Double" property="ave" title="${row.getValue(row.key)[2]}"/> 
	</display:table>	
	<br />
	
	<form:label path="seccion3">
					<spring:message code="admin.seccion3">:</spring:message>
	</form:label>
	<form:input path="auditormorecontracts" readonly/>
	<br />
	
	<form:label path="seccion4">
					<spring:message code="admin.seccion4">:</spring:message>
	</form:label>
	<form:input path="auditorlesscontracts" readonly/>
	<br />
	
	<form:label path="seccion5">
					<spring:message code="admin.seccion5">:</spring:message>
	</form:label>
	<form:input path="customerregisteredcurriculum" readonly/>
	<br />
	
	<form:label path="seccion6">
					<spring:message code="admin.seccion6">:</spring:message>
	</form:label>
	<form:input path="customernotregisteredcurricula" readonly/>
	<br />
	
	<form:label path="seccion7">
					<spring:message code="admin.seccion7">:</spring:message>
	</form:label>
	<form:input path="itemsnottagged" readonly/>
	<br />
	
	<form:label path="seccion8">
					<spring:message code="admin.seccion8">:</spring:message>
	</form:label>
	<br />
	<display:table name="consumernotregisteredcurricula" id="row" pagesize="5" class="displaytag" requestURI="${requestURI}">
		<spring:message code="admin.customer" var="name"/>
		<display:column class="String" property="customer" title="${useraccount.name}"/>
		
		<spring:message code="admin.name" var="name"/>
		<display:column class="String" property="consumer" title="${actor.name}"/> 
		
	</display:table>	
	<br />
	
	<form:label path="seccion9">
					<spring:message code="admin.seccion9">:</spring:message>
	</form:label>
	<br />
		<display:table name="consumernotnotupdatedcurricula" id="row" pagesize="5" class="displaytag" requestURI="${requestURI}">
		<spring:message code="admin.customer" var="name"/>
		<display:column class="String" property="customer" title="${useraccount.name}"/>
		
		<spring:message code="admin.name" var="name"/>
		<display:column class="String" property="consumer" title="${actor.name}"/> 
		
	</display:table>	
	<br />
	
	<form:label path="seccion10">
					<spring:message code="admin.seccion9">:</spring:message>
	</form:label>
	<form:input path="consumersignedmorecontracts" readonly/>
	<br />
	
	<form:label path="seccion11">
					<spring:message code="admin.seccion9">:</spring:message>
	</form:label>
	<form:input path="suppliersignedmorecontracts" readonly/>
	<br />
	
	<form:label path="seccion12">
					<spring:message code="admin.seccion9">:</spring:message>
	</form:label>
	<form:input path="consumermoreunpaidinvoices" readonly/>
	<br />
	
	<form:label path="seccion13">
					<spring:message code="admin.seccion9">:</spring:message>
	</form:label>
	<form:input path="suppliermoreunpaidinvoices" readonly/>
	<br />
	
	<form:label path="seccion14">
					<spring:message code="admin.seccion9">:</spring:message>
	</form:label>
	<form:input path="consuerpaidmoremoney" readonly/>
	<br />
	
	<form:label path="seccion15">
					<spring:message code="admin.seccion9">:</spring:message>
	</form:label>
	<form:input path="supplierearnedmoremoney" readonly/>
	<br />
	
</form:form>
