import java.io.PrintWriter;
import java.io.StringWriter;

public class PrintStackTrace {
    public static void f() throws Exception{
        throw new Exception("出问题啦！");
    }
    public static void g() throws Exception{
        f();
    }
    public static void main(String[] args) {
        try {
            g();
        } catch(Exception e) {
            e.printStackTrace();
			String stackTrace = getStackTraceString(e);
			System.out.println("Print Stack Trace\n" + stackTrace);
			System.out.println("Message: " + e.getMessage());
			System.out.println("Exception to String: " + e.toString());
        }
    }

    private static String getStackTraceString(Exception e) {
		String stackTrace = "";
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter( writer );
		e.printStackTrace( printWriter );
		printWriter.flush();
		stackTrace = writer.toString();
		return stackTrace;
    }
}
