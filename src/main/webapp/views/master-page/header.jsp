<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Broker Co., Inc." />
</div>

<div>
	<ul id="jMenu">
			<li><a class="fNiv" href=""><spring:message code="master.page.list" /></a></li>
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.admin" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href=""><spring:message code="master.page.admin.create.contract" /></a></li>
					<li><a href=""><spring:message code="master.page.admin.list.canceled.contracts" /></a></li>
					<li><a href=""><spring:message code="master.page.admin.dashboard" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('AUDITOR')">
			<li><a class="fNiv"><spring:message	code="master.page.auditor" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href=""><spring:message code="master.page.auditor.auditedby" /></a></li>
					<li><a href=""><spring:message code="master.page.auditor.toaudit" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CONSUMER')">
			<li><a class="fNiv"><spring:message	code="master.page.consumer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href=""><spring:message code="master.page.consumer.requests" /></a></li>
					<li><a href=""><spring:message code="master.page.consumer.contracts" /></a></li>
					<li><a href=""><spring:message code="master.page.consumer.notsigned" /></a></li>
					<li><a href=""><spring:message code="master.page.consumer.invoices" /></a></li>
					<li><a href=""><spring:message code="master.page.consumer.invoicesnotpayed" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('SUPPLIER')">
			<li><a class="fNiv"><spring:message	code="master.page.supplier" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href=""><spring:message code="master.page.supplier.items" /></a></li>
					<li><a href=""><spring:message code="master.page.supplier.contracts.offered" /></a></li>
					<li><a href=""><spring:message code="master.page.supplier.contracts.notsigned" /></a></li>
					<li><a href=""><spring:message code="master.page.supplier.invoices" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href=""><spring:message code="master.page.profile.messages" /></a></li>
					<li><a href=""><spring:message code="master.page.profile.search.item.keyword" /></a></li>
				<security:authorize access="hasRole('CONSUMER', 'SUPPLIER')">
					<li><a href=""><spring:message code="master.page.consumer.assessments" /></a></li>
					<li><a href=""><spring:message code="master.page.consumer.curriculum" /></a></li>
				</security:authorize>
				<security:authorize access="hasRole('ADMIN')">
					<li><a href=""><spring:message code="master.page.broadcast.create.curriculum" /></a></li>
					<li><a href=""><spring:message code="master.page.broadcast.update.curriculum" /></a></li>
				</security:authorize>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

