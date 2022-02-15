import java.io.*;
import java.math.*;
public class Lab {
	private final static int BIG_PRECISION = 16;
	public static void main(String[] args) throws IOException {
		Execute();
	}
	public static void Execute() throws IOException {
    PrintWriter out =  new PrintWriter(System.out, true);
	BufferedReader in =new BufferedReader(new InputStreamReader(System.in)); 
   	out.println("������� x.");
   	String s;
    s = in.readLine();
    double x = Double.parseDouble(s);
    BigDecimal bigX = new BigDecimal(s);
    BigDecimal minInterval = new BigDecimal(-1);
    BigDecimal maxInterval = new BigDecimal(1);
    if (bigX.compareTo(minInterval) != 1 || bigX.compareTo(maxInterval) != -1) {
    	out.println("������. x ������ ������������ ���������� (-1; 1).");
    	return;
    }
    out.println("������� k.");
    s = in.readLine();
    BigInteger k = new BigInteger(s);
    if (k.compareTo(BigInteger.ZERO) != 1) {
    	out.println("������. k ������ ���� �����������.");
    	return;
    }
    BigDecimal y = bigX.add(new BigDecimal(1));
    MathContext mc = new MathContext(BIG_PRECISION);
    y = y.sqrt(mc);
    y = (new BigDecimal(1)).divide(y, BIG_PRECISION, BigDecimal.ROUND_HALF_UP);
    out.println("�������� ������������ ��������� ����� BigDecimal  = " + y);
    out.println("�������� ������������ ��������� ����� Math  = " + 1/Math.sqrt(x+1));
	Series a = new Series(bigX, k);
    out.println("����� ���� =" + a.value());
	}
}
