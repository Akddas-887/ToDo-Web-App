package com.servlet;

import com.entities.Note;
import com.helper.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "SaveNoteServlet")
public class SaveNoteServlet extends HttpServlet {
    private static final long longserialVersioUID=1L;

    public SaveNoteServlet() {
        super();
    }

   /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("Served at: ").append(req.getContextPath());
    }
*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String title=req.getParameter("title");
            String content=req.getParameter("content");

            //System.out.println(title+"  "+content);
            Note note=new Note(title,content,new Date());
            //hibernate save

            Session session= FactoryProvider.getFactory().openSession();
            Transaction tx=session.beginTransaction();
            session.save(note);
            tx.commit();
            session.close();

            resp.setContentType("text/html");
            PrintWriter out=resp.getWriter();
            out.println("<h1 style='text-align:center;'>Note is added successfully</h1>");
            out.println("<h1 style='text-align:center;'><a href='all_notes.jsp'>View all notes</a></h1>");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
