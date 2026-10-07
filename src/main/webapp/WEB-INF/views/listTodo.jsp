<%@ include file="common/header.jsp"%>
<%@ include file="common/navigation.jsp" %>

<div class="container">
<h1>TODOS:</h1><hr/>
<table class="table">
    <thead class="thead-dark">
      <tr>
        <th scope="col">ID</th>
        <th scope="col">USERNAME</th>
        <th scope="col">DESCRIPTION</th>
        <th scope="col">TARGET DATE</th>
        <th scope="col">STATUS</th>
        <th scope="col"></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${todos}" var="todos">
         <tr>
           <td> <c:out value = "${todos.id}"/><p> </td>
           <td> <c:out value = "${todos.username}"/><p> </td>
           <td> <c:out value = "${todos.description}"/><p> </td>
           <td> <c:out value = "${todos.localDate}"/><p> </td>
           <td> <c:out value = "${todos.done}"/><p> </td>
           <td> <a href="update-todo?id=${todos.id}" class="btn btn-warning">Update</a> </td>
           <td> <a href="delete-todo?id=${todos.id}" class="btn btn-warning">Delete</a> </td>
          </tr>
      </c:forEach>
    </tbody>
</table>
<br/>
<a href="add-todo" class="btn btn-success">Add todos</a>
<hr/>
</div>
<%@ include file="common/footer.jsp"%>