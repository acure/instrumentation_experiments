package hou.example;

import xxx.yyy.zzz.AbstractClass;
import xxx.yyy.zzz.CommonClass;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InstrumentationExample1 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String test = request.getParameter("test");
        switch (test) {
            case "do_IOCC_CommonClass":
                new CommonClass().do_IOCC_CommonClass();
                break;
            case "do_IOAC_CommonClass":
                new CommonClass().do_IOAC_CommonClass();
                break;
            case "do_IOAC_Abstract":
                new CommonClass().do_IOAC_Abstract();
                break;
            case "do_IOAC_AbstractClass":
                new CommonClass().do_IOAC_AbstractClass();
            case "do_IOPAC_AbstractClass":
                new CommonClass().do_IOPAC_AbstractClass();
                break;
            case "do_IOPAC_CommonClass":
                new CommonClass().do_IOPAC_CommonClass();
                break;
            case "do_IOAC_OVERRIDEDinCommonClass":
                new CommonClass().do_IOAC_OVERRIDEDinCommonClass();
                break;
            case "do_IOAC_Static":
                AbstractClass.do_IOAC_Static();
                break;
            case "do_PAC_AbstractClass":
                new CommonClass().do_PAC_AbstractClass();
                break;
            case "do_IOPAC_ParentAbstractClass":
                new CommonClass().do_IOPAC_ParentAbstractClass();
                break;
            case "exception_CC":
                try {
                    new CommonClass().exception_CC();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default :
                break;
        }


        pw.println("<html>");
        pw.println("<head><title>TESTS</title></title>");
        pw.println("<body>");
        pw.println("<H1>" + test + "</H1>");
        pw.println("<a href=\"test?test=do_IOCC_CommonClass\">do_IOCC_CommonClass</a><br>");
        pw.println("<a href=\"test?test=do_IOAC_CommonClass\">do_IOAC_CommonClass</a><br>");
        pw.println("<a href=\"test?test=do_IOAC_Abstract\">do_IOAC_Abstract</a><br>");
        pw.println("<a href=\"test?test=do_IOAC_AbstractClass\">do_IOAC_AbstractClass</a><br>");
        pw.println("<a href=\"test?test=do_IOPAC_AbstractClass\">do_IOPAC_AbstractClass</a><br>");
        pw.println("<a href=\"test?test=do_IOPAC_CommonClass\">do_IOPAC_CommonClass</a><br>");
        pw.println("<a href=\"test?test=do_IOAC_OVERRIDEDinCommonClass\">do_IOAC_OVERRIDEDinCommonClass</a><br>");
        pw.println("<a href=\"test?test=do_IOAC_Static\">do_IOAC_Static</a><br>");
        pw.println("<a href=\"test?test=do_PAC_AbstractClass\">do_PAC_AbstractClass</a><br>");
        pw.println("<a href=\"test?test=do_IOPAC_ParentAbstractClass\">do_IOPAC_ParentAbstractClass</a><br>");
        pw.println("<a href=\"test?test=exception_CC\">exception_CC</a><br>");
        pw.println("</body></html>");
        System.out.println("------------------------------------ EXAMPLE 1 - doGet() ---------------------------------");
    }

}
