import java.math.*;
public class Series {
	BigDecimal x;
	BigInteger k;
Series(BigDecimal x1, BigInteger k1) {
	x = x1;
	k = k1;
}
public BigDecimal value() {
	BigDecimal c = BigDecimal.ONE;
	BigDecimal e = BigDecimal.ONE.divide(new BigDecimal(10).pow(k.intValue()));
	BigDecimal y = BigDecimal.ZERO;
	BigDecimal bigTwo = new BigDecimal(2);
	BigDecimal negOne = new BigDecimal(-1);
	BigDecimal i = BigDecimal.ONE;
	BigDecimal j = bigTwo;
	BigDecimal temp;
	for (; (c.abs()).compareTo(e) != -1; i = i.add(bigTwo), j = j.add(bigTwo))
	{
		y = y.add(c);
		//c *= ((-1)*x*i) / j;
		temp = x.multiply(i);
		temp = negOne.multiply(temp);
		temp = temp.divide(j, k.add(new BigInteger("1")).intValue(), BigDecimal.ROUND_HALF_UP);
		c = c.multiply(temp);
		//c = c.multiply((negOne.multiply(x.multiply(i))).divide(j));
	}
	y = y.setScale(k.add(new BigInteger("1")).intValue(), BigDecimal.ROUND_HALF_UP);
	return y;
}
}
