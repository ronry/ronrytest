package com.ronrytest.web.servlert.performance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CpuBusyServlet extends HttpServlet {

    private static final long serialVersionUID = 5134071924555811789L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String t = req.getParameter("t");
        String c = req.getParameter("c");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < (c == null ? 1000000 : 100000 * Integer.parseInt(c)); i++) {
            sb.append("a");
        }
        try {
            Thread.sleep(t == null ? 100 : Integer.parseInt(t));
            resp.getWriter().write("cpu busy!! cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!cpu busy!!");
            resp.getWriter().flush();
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6000000; i++) {
            sb.append("a");
        }
        System.out.println(System.currentTimeMillis() - begin);
    }

}
