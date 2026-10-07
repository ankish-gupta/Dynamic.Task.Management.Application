<%@ include file="common/header.jsp"%>
<div class="container">
<h1> Login </h1>
<pre>
${errorMessage}
</pre>

<form:form METHOD="POST">
    <fieldset class="mb-1">
        NAME:  <INPUT TYPE="TEXT" name="name"><BR>
    </fieldset>
    <fieldset class="mb-3">
        PASSWORD: <INPUT TYPE="PASSWORD" NAME="password"><BR>
    </fieldset>
    <INPUT TYPE="SUBMIT" class="btn btn-success">
</form:form>

</div>
<hr/>

<%@ include file="common/footer.jsp"%>