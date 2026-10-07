<%@ include file="common/header.jsp"%>
<%@ include file="common/navigation.jsp"  %>

<div class="container">
<h1><u> ADD TODO</u> </h1>
<form:form METHOD="POST" modelAttribute="todos">
    <fieldset>
        <form:label path="description"> DESCRIPTION:  </form:label>
        <form:input TYPE="TEXT" required="required" path="description"/>
        <form:errors path="description" cssClass="text-warning"/>
    </fieldset>
<br/>
    <fieldset class="mb-3">
        <form:label path="localDate"> TARGET DATE:  </form:label>
        <form:input TYPE="DATE" path="localDate" />
    </fieldset>

    <form:input type="hidden" path="id"/>
	<form:input type="hidden" path="done"/>

    <INPUT TYPE="SUBMIT" class="btn btn-success">
</form:form>
</div>
<%@ include file="common/footer.jsp"%>