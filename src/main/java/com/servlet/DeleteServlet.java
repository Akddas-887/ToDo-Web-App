package com.servlet;
import com.entities.Note;
import com.helper.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet{
    private static final long longserialVersioUID=1L;
    public DeleteServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            int noteId=Integer.parseInt(req.getParameter("note_id").trim());
            Session s=FactoryProvider.getFactory().openSession();
            Transaction tx=s.beginTransaction();
            Note note=s.get(Note.class,noteId);
            s.delete(note);
            tx.commit();
            s.close();
            resp.sendRedirect("all_notes.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
