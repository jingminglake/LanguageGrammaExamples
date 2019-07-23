import java.math.BigInteger;

public class NullNumbers {
    public static void main(String[] args) {
        try {
	    String num = "123";
	    BigInteger bi = new BigInteger(num);
	    System.out.println("bi: " + bi);
	} catch(Exception e) {
	     e.printStackTrace();
	}

	try {
	    String num = null;
	    BigInteger bi = new BigInteger(num);
	    System.out.println("bi: " + bi);
	} catch(Exception e) {
	     e.printStackTrace();
	}

	try {
	     Long l = null;
	     long ll = l;
	     System.out.println("long: " + ll);
	} catch(Exception e) {
	     System.out.println("when parse long");
	     e.printStackTrace();
	}

	try {
	     Long l = null;
	     System.out.println("can print null Long " + l);
	} catch(Exception e) {
	     System.out.println("can not print null Long");
	     e.printStackTrace();
	}
    }
}
