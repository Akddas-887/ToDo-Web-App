<%@ page import="java.util.List"%>
<%@ page import="org.hibernate.*"%>
<%@ page import="com.helper.FactoryProvider"%>
<%@ page import="com.entities.*"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


<title>Note Taker : Home page</title>
<%@include file="all_js_css.jsp"%>
    <div class="container">
    		<%@include file="navbar.jsp"%>
    		<br>
    		<h1>Edit your Note</h1>

    		<%
    		    String noteIdParam = request.getParameter("note_id").trim();
    		    int noteId=Integer.parseInt(noteIdParam);
    		    Session s=FactoryProvider.getFactory().openSession();
                Transaction tx=s.beginTransaction();
                Note note=s.get(Note.class,noteId);


    		%>

    		<form action="UpdateServlet" method="post">
                <input value="<%= note.getId() %>" name="noteId" type="hidden"/>
            			<div class="form-group">
            				<label for="title">Note title</label>
            				<input
            				name="title"
            				required
            				type="text"
            				class="form-control"
            				id="title"
            				aria-describedby="emailHelp"
            				placeholder="Enter here"
            				value="<%= note.getTitle() %>"
            				/>

            			</div>


            			<div class="form-group">
            				<label for="content">Note Content</label>
            				<textarea
            				name="content"
            				required
            				id="content"
            				placeholder="Enter your content here"
            				class="form-control"
            				style="height: 300px;"
            					>
            					<%= note.getContent() %></textarea>


            			</div>

            			<div class="container text-center">

            				<button type="submit" class="btn btn-success">Save</button>
            			</div>

            		</form>
    </br>

</head>
<body>

</body>
</html>