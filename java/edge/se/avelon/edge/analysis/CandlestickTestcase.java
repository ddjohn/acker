package se.avelon.edge.analysis;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.avelon.edge.analysis.Candlestick.CandlestickEnum;

public class CandlestickTestcase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		System.out.println("=> " + Candlestick.lookup(CandlestickEnum.DragonflyDoji));
		System.out.println("=> " + Candlestick.lookup(Candlestick.analyze(2.0, 1.0, 3.0, 2.0)));
		
		for(double i = 1.0; i <= 2.0; i += +.01) {
			for(double j = 1.0; j <= 2.0; j += +.01) {
				System.out.println("=> op=" + i + ":cp=" + j + " = " + Candlestick.lookup(Candlestick.analyze(i, 1.0, 2.0, j)));
			}
		}
	}
}
