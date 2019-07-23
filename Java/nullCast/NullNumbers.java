import java.math.BigInteger;
import java.util.List;

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

		try {
			List<String> lst = null;
			for (String s : lst) {
				System.out.println("here?");
			}
			System.out.println("do not throw nullpointer");
		} catch(Exception e) {
			System.out.println("nullpointer ?");
			e.printStackTrace();
		}

		
	}
}
