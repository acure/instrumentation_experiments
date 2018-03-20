package xxx.yyy.zzz;

import javassist.ClassPool;
import javassist.util.HotSwapAgent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ant on 2018-03-20.
 */
public class IAgent extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>> IAgent.init() <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        HotSwapAgent agent = new HotSwapAgent();
        Class[] classes = agent.instrumentation().getAllLoadedClasses();
        for(Class clazz : classes) {
            pw.print(clazz.getName() + " :: " + clazz.getClassLoader());
            if (clazz.getSimpleName().equals("SuperCommonValueGenerator")) {
                System.out.println(" >>>>>>> FOUND : " + clazz.getName() + " :: class loader = " + clazz.getClassLoader());
            }
        }
        pw.print("DONE");
    }
}
