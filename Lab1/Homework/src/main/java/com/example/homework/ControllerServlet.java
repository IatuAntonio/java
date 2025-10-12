package com.example.homework;

import java.io.*;

import jakarta.servlet.http.*;
import java.util.logging.Logger;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

@WebServlet(name = "controllerServlet", value = "/welcome")
public class ControllerServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ControllerServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("HTTP Method: " + request.getMethod());
        logger.info("IP Address: " + request.getRemoteAddr());
        logger.info("User Agent: " + request.getHeader("User-Agent"));
        logger.info("Client Language: " + request.getHeader("Accept-Language"));
        logger.info("Parameters: " + request.getParameter("page"));

        System.out.println("HTTP Method: " + request.getMethod());
        System.out.println("IP Address: " + request.getRemoteAddr());
        System.out.println("User Agent: " + request.getHeader("User-Agent"));
        System.out.println("Client Language: " + request.getHeader("Accept-Language"));
        System.out.println("Parameters: " + request.getParameter("page"));

        System.out.println();

        String pageParam = request.getParameter("page");
        String source =  request.getParameter("source");

        if ("desktop".equals(source)) {
            response.setContentType("text/plain");
            response.getWriter().println(pageParam);
        } else {
            if ("1".equals(pageParam)) {
//                request.getRequestDispatcher("page1.html").forward(request, response);
                response.sendRedirect("page1.html");
            } else if ("2".equals(pageParam)) {
//                request.getRequestDispatcher("page2.html").forward(request, response);
                response.sendRedirect("page2.html");
            } else {
                response.setContentType("text/html");
                response.getWriter().println("Invalid parameter: " + pageParam);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
