package com.ronrytest.web.servlert.performance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloPerformanceServlet extends HttpServlet {

    private static final long serialVersionUID = -2026851410114466145L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String t = req.getParameter("t");
        try {
            Thread.sleep(t == null ? 1000 : Integer.parseInt(t));
            resp.getWriter().write("hello performance");
            resp.getWriter().flush();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
