import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * Created by Ant on 2018-03-17.
 */
public class HotSwapJarGenerator {
    public static void main(String[] args) {
        try {
            javassist.util.HotSwapAgent.createAgentJarFile("hotswap.jar");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
