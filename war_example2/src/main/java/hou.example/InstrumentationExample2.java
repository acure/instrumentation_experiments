package hou.example;

import xxx.yyy.zzz.CommonClass;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class InstrumentationExample2 extends HttpServlet{


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head><title>Example2 : test CC</title></title>");
        pw.println("<body>");
        new CommonClass().do_IOCC_CommonClass();
        pw.println("<h1>[Example2] CommonClass.do_IOCC_CommonClass() </h1>");
        pw.println("</body></html>");
        System.out.println("------------------------------------ EXAMPLE 1 - doGet() ---------------------------------");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("------------------------------------ EXAMPLE 1 - init() ---------------------------------");
    }
}
