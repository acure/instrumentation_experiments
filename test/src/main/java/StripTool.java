import java.util.Arrays;

public class StripTool {


    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------------------------------------------");
        String className = "XXXclassnameXXX";
        String methodName = "YYYmethodNameYYYY";

        String src =
                "if (_LOG.isTraceEnabled()) { \n" +
                        " _LOG.trace(\"onExit: START \" \n" +
                        "+ \"[origin=...\" \n" +                            // FIXME there should be ORIGIN - whatever it is.
                        "+ \", informationPointClassName=" + className + "\" \n" +
                        "+ \", informationPointMethodName=" + methodName + "\" \n" +
                        "+ \", script=" + (true) + "\" \n" +
                        "+ \", sampleRate=" + 100 + "\" \n" +
                        "+ \", instance= \" + $0  \n" + // FIXME should it be changed $0 to this - check the difference $0 vs this?
                        "+ \", arguments=\" + java.util.Arrays.asList($args) \n" +
                        "+ \", returned=\" \n" +  // no returning value because of exception
                        "+ \", thrown=\" + $e + \n" +
                        "+ \"]\"); " +
                        "}"
        ;

        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        Arrays.stream(src.split("\n")).forEach(System.out::println);


        System.out.println("-------------------------------------------------------------------------------------------");
    }
}
