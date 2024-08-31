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
import java.util.Date;


@WebServlet(name = "UpdateServlet")
public class UpdateServlet extends HttpServlet{
    private static final long longserialVersioUID=1L;

    public UpdateServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String title=req.getParameter("title");
            String content=req.getParameter("content");
            Session s=FactoryProvider.getFactory().openSession();
            Transaction tx=s.beginTransaction();
            int noteId=Integer.parseInt(req.getParameter("noteId").trim());
            Note note=s.get(Note.class,noteId);
            note.setTitle(title);
            note.setContent(content);
            note.setDate(new Date());

            tx.commit();
            s.close();
            resp.sendRedirect("all_notes.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
