
<%@ page import="java.util.List"%>
<%@ page import="org.hibernate.Query"%>
<%@ page import="com.helper.FactoryProvider"%>
<%@ page import="org.hibernate.Transaction"%>
<%@ page import="org.hibernate.Session"%>
<%@ page import="com.entities.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All notes: Note Taker</title>
<%@include file="all_js_css.jsp"%>
</head>
<body>

	<div class="container">
		<%@include file="navbar.jsp"%>
		<br>
		<h1 class="text-uppercase">All Notes:</h1>

        <div class="row">
            <div class="col-12">
                <%
                            Session s = null;
                            Transaction tx=null;
                                        try {
                                                s = FactoryProvider.getFactory().openSession();
                                                tx=s.beginTransaction();
                                                Query q = s.createQuery("from Note");

                                                List<Note> list = q.list();

                                                for(Note notes : list) {
                                                %>
                                                    <div class="card mt-3">
                                                      <img class="card-img-top m-4 mx-auto" style="max-width:100px" src="image/note.png" alt="Card image cap">
                                                      <div class="card-body px-5">
                                                        <h5 class="card-title"><%= notes.getTitle() %></h5>
                                                        <p class="card-text"><%= notes.getContent() %></p>
                                                        <p class="card-text"><b class="text-primary"><%= notes.getDate() %></b></p>
                                                        <div class="container text-center m-2">
                                                             <a href="DeleteServlet?note_id=<%= notes.getId() %>" class="btn btn-danger">Delete</a>
                                                            <a href="edit.jsp?note_id=<%= notes.getId() %>" class="btn btn-primary">Update</a>
                                                        </div>
                                                      </div>
                                                    </div>
                                                <%
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace(); // This will print the stack trace to the JSP page for debugging
                                            } finally {
                                                if (s != null) {
                                                    tx.commit();
                                                    s.close();
                                                }
                                            }
                        %>
            </div>
        </div>

	</div>
</body>
</html>