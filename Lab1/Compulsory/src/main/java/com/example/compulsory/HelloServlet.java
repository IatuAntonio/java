package com.example.compulsory;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/welcome")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hellou and Welcome to this page";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h3>Acesta este compulsory-ul de la java</h3>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}