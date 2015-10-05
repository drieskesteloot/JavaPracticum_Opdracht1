package testing;

import static org.junit.Assert.*;
import datum.Datum;

public class Test {
	
	private Datum datumX, datumY, datumZ, datumA;
	
	@org.junit.Before
	public void setUp() throws Exception {
		datumX = new Datum(12,02,1983);
		datumY = new Datum(14,12,2023);
		datumZ = new Datum(31,05,1986);
		datumA = new Datum(18,12,2015);
	}
	

	@org.junit.Test
	public void test() {
		fail("Not yet implemented");
	}

}
