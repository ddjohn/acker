package se.avelon.edge.formulas;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import se.avelon.edge.omx.datafeed.CandleFeedData;


public class FormulasTestsuite {

	private static Vector<CandleFeedData> data = new Vector<CandleFeedData>();
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static {
		//                              Date,                    op,  cp,  hp,  lp,  tv,      id,   name
		try {
			data.add(new CandleFeedData(sdf.parse("2012-01-01"), 1.0, 2.0, 2.5, 0.8, 100000, "ID", "NAME"));
			data.add(new CandleFeedData(sdf.parse("2012-01-02"), 1.2, 2.1, 2.8, 0.8, 100000, "ID", "NAME"));
			data.add(new CandleFeedData(sdf.parse("2012-01-03"), 1.5, 2.2, 2.9, 0.8, 100000, "ID", "NAME"));
			data.add(new CandleFeedData(sdf.parse("2012-01-04"), 1.8, 2.6, 2.7, 0.8, 100000, "ID", "NAME"));
			data.add(new CandleFeedData(sdf.parse("2012-01-05"), 1.6, 2.0, 2.2, 0.8, 100000, "ID", "NAME"));
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ExpectedException exception = ExpectedException.none();


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

	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testMean() {
		assertEquals(2.18, Formulas.mean(data,  5), 0.01);
		// Exception ArrayIndexOutOfBoundsException
		Formulas.mean(data, 10);
	}
	
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testDonchianHigh() {
		assertEquals(2.9, Formulas.donchianHigh(data,  5), 0.01);
		// Exception ArrayIndexOutOfBoundsException
		Formulas.donchianHigh(data, 10);
	}

	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testDonchianLow() {
		assertEquals(0.8, Formulas.donchianLow(data,  5), 0.01);
		// Exception ArrayIndexOutOfBoundsException
		Formulas.donchianLow(data, 10);
	}

	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testFibonacci() {
		Formulas.fibonacci(data, 10);
	}

	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testRichochelette() {
		try {
			Assert.assertFalse(Formulas.richochette(data,  5));
			// Exception ArrayIndexOutOfBoundsException
			Formulas.richochette(data, 10);
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
