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

<form:form action="register/${role}/edit.do" modelAttribute="${role}">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="rating" />

	<jstl:if test="${role.equals('consumer')}">
		<jstl:set var="rol" value="CONSUMER" />
	</jstl:if>
	<jstl:if test="${role.equals('supplier')}">
		<jstl:set var="rol" value="SUPPLIER" />
	</jstl:if>

	<form:hidden path="userAccount.authorities[0].authority" value="${rol}" />

	<fieldset>
		<legend>
			<spring:message code="register.user" />
		</legend>
		<form:label path="userAccount.username">
			<spring:message code="register.userAccount.username">:</spring:message>
		</form:label>
		<form:input path="userAccount.username" />
		<form:errors cssClass="error" path="userAccount.username"></form:errors>
		<br />

		<form:label path="userAccount.password">
			<spring:message code="register.userAccount.password">:</spring:message>
		</form:label>
		<form:password path="userAccount.password" />
		<form:errors cssClass="error" path="userAccount.password"></form:errors>
		<br />
	</fieldset>

	<fieldset>
		<legend>
			<spring:message code="register.personalData" />
		</legend>
		<form:label path="name">
			<spring:message code="register.name">:</spring:message>
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name"></form:errors>
		<br />

		<form:label path="surname">
			<spring:message code="register.surname">:</spring:message>
		</form:label>
		<form:input path="surname" />
		<form:errors cssClass="error" path="surname"></form:errors>
		<br />

		<form:label path="email">
			<spring:message code="register.email">:</spring:message>
		</form:label>
		<form:input path="email" />
		<form:errors cssClass="error" path="email"></form:errors>

		<form:label path="ticker">
			<spring:message code="register.ticker">:</spring:message>
		</form:label>
		<form:input path="ticker" />
		<form:errors cssClass="error" path="ticker"></form:errors>
		<br />

		<fieldset>
			<legend>
				<spring:message code="register.creditCard" />
			</legend>
			<form:label path="creditCard.holderName">
				<spring:message code="register.creditCard.holderName">:</spring:message>
			</form:label>
			<form:input path="creditCard.holderName" />
			<form:errors cssClass="error" path="creditCard.holderName"></form:errors>
			<br />

			<form:label path="creditCard.brandName">
				<spring:message code="register.creditCard.brandName">:</spring:message>
			</form:label>
			<form:input path="creditCard.brandName" placeholder="Ej. Euro6000" />
			<form:errors cssClass="error" path="creditCard.brandName"></form:errors>
			<br />

			<form:label path="creditCard.number">
				<spring:message code="register.creditCard.number">:</spring:message>
			</form:label>
			<form:input path="creditCard.number" placeholder="0000000000000000"
				maxlength="16" />
			<form:errors cssClass="error" path="creditCard.number"></form:errors>
			<br />

			<form:label path="creditCard.expirationMonth">
				<spring:message code="register.creditCard.expirationMonth">:</spring:message>
			</form:label>
			<form:input path="creditCard.expirationMonth" maxlength="2" size="2" />
			<form:errors cssClass="error" path="creditCard.expirationMonth"></form:errors>
			<br />

			<form:label path="creditCard.expirationYear">
				<spring:message code="register.creditCard.expirationYear">:</spring:message>
			</form:label>
			<form:input path="creditCard.expirationYear" maxlength="4" size="3" />
			<form:errors cssClass="error" path="creditCard.expirationYear"></form:errors>
			<br />

			<form:label path="creditCard.cVV">
				<spring:message code="register.creditCard.cVV">:</spring:message>
			</form:label>
			<form:input path="creditCard.cVV" maxlength="3" size="3" />
			<form:errors cssClass="error" path="creditCard.cVV"></form:errors>
			<form:errors cssClass="error" path="creditCard.CVV"></form:errors>
		</fieldset>
	</fieldset>


	<input type="submit" name="save"
		value="<spring:message code="register.save" />" />
	<input type="button" name="cancel"
		value="<spring:message code="register.cancel" />"
		onclick="location='./welcome/index.do'" />


</form:form>