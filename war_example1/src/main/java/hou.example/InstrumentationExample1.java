package hou.example;

import xxx.yyy.zzz.SuperCommonValueGenerator;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class InstrumentationExample1 extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        int value = new SuperCommonValueGenerator().getValue();
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head><title>Example1 : value = "+value+"</title></title>");
        pw.println("<body>");
        pw.println("<h1>[Example1] VALUE = "+value+" and default value = 111</h1>");
        pw.println("</body></html>");
        System.out.println("------------------------------------ EXAMPLE 1 - doGet() ---------------------------------");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("------------------------------------ EXAMPLE 1 - init() ---------------------------------");
    }
}
