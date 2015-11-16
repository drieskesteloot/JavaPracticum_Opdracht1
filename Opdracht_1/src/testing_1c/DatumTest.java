package testing_1c;


import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import datum.Datum;//gebruik model_1a.Datum voor eigen Datum klasse of model_1b.Datum voor Gregorian wrapper


public class DatumTest {
	private Datum datumX;
	private Datum datumY;
	private Datum datumZ;
	private Datum datumVroeger;
	private Datum datumLater;


	@Before
	public void setUp() throws Exception {
		datumX = new Datum(10,3,1982);
		datumY = new Datum(10,3,1982);
		datumZ = new Datum(9,5,1982);
			datumVroeger = new Datum(8,3,1982);
			datumLater = new Datum (1,5,2000);
	}
	

	
	//testen constructor Datum()

	@Test
	public void test_HuidigeDatum_object_wordt_aangemaakt() throws Exception{
		
		Date huidigeTijd = new Date();
		Datum nu = new Datum();
	
		assertEquals(huidigeTijd.getDate(),nu.getDag());
		assertEquals(huidigeTijd.getMonth()+1,nu.getMaand());
		assertEquals(huidigeTijd.getYear()+1900,nu.getJaar());
		assertEquals(nu.getDag(), huidigeTijd.getDate());
		assertEquals(nu.getMaand(), huidigeTijd.getMonth()+1);
		assertEquals(nu.getJaar(), huidigeTijd.getYear()+1900);
	}

	//testen constructor Datum (int, int, int)

	@Test
	public void test_Datum_met_3_parameters_wordt_aangemaakt() throws Exception {
		Datum datumMet3 = new Datum (27,5,1995);
		
		assertEquals(27, datumMet3.getDag());
		assertEquals(5, datumMet3.getMaand());
		assertEquals(1995, datumMet3.getJaar());
		assertEquals(datumMet3.getDag(), 27);
		assertEquals(datumMet3.getMaand(), 5);
		assertEquals(datumMet3.getJaar(), 1995);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Datum_met_3_parameters_aanmaken_dag_onmogelijk_te_groot() throws Exception {
		Datum datumMet3 = new Datum (35,5,1995);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Datum_met_3_parameters_aanmaken_dag_onmogelijk_negatief() throws Exception {
		Datum datumMet3 = new Datum (-5,5,1995);
	}
	@Test (expected = IllegalArgumentException.class)
	public void test_Datum_met_3_parameters_aanmaken_dag_onmogelijk_nul() throws Exception {
		Datum datumMet3 = new Datum (0,5,1995);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Datum_met_3_parameters_aanmaken_maand_onmogelijk() throws Exception {
		Datum datumMet3 = new Datum (5,15,1995);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Datum_met_3_parameters_aanmaken_maand_onmogelijk_negatief() throws Exception {
		Datum datumMet3 = new Datum (5,-15,1995);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Datum_met_3_parameters_aanmaken_maand_onmogelijk_nul() throws Exception {
		Datum datumMet3 = new Datum (5,0,1995);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Datum_met_3_parameters_aanmaken_jaar_onmogelijk_nul() throws Exception {
		Datum datumMet3 = new Datum (5,15,0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Datum_met_3_parameters_aanmaken_geen_schrikkeljaar() throws Exception {
		Datum datumMet3 = new Datum (29,2,2015);
	}
	
	@Test 
	public void test_Datum_met_3_parameters_aanmaken_wel_schrikkeljaar() throws Exception {
		Datum datumMet3 = new Datum (29,2,2020);
		assertEquals(29, datumMet3.getDag());
		assertEquals(2, datumMet3.getMaand());
		assertEquals(2020, datumMet3.getJaar());
		assertEquals(datumMet3.getDag(), 29);
		assertEquals(datumMet3.getMaand(), 2);
		assertEquals(datumMet3.getJaar(), 2020);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_Datum_met_3_parameters_aanmaken_geen_schrikkeljaar_eeuw() throws Exception {
		Datum datumMet3 = new Datum (29,2,1900);
	}
	
	@Test 
	public void test_Datum_met_3_parameters_aanmaken_wel_schrikkeljaar_eeuw() throws Exception {
		Datum datumMet3 = new Datum (29,2,2000);
		assertEquals(29, datumMet3.getDag());
		assertEquals(2, datumMet3.getMaand());
		assertEquals(2000, datumMet3.getJaar());
		assertEquals(datumMet3.getDag(), 29);
		assertEquals(datumMet3.getMaand(), 2);
		assertEquals(datumMet3.getJaar(), 2000);
	}
	
	//Testen constructoren Datum (String)
	
	@Test
	public void test_DatumString_met_3_parameters_wordt_aangemaakt() throws Exception {
		Datum datumMet3 = new Datum ("27/05/1995");
		
		assertEquals(27, datumMet3.getDag());
		assertEquals(5, datumMet3.getMaand());
		assertEquals(1995, datumMet3.getJaar());
		assertEquals(datumMet3.getDag(), 27);
		assertEquals(datumMet3.getMaand(), 5);
		assertEquals(datumMet3.getJaar(), 1995);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_DatumString_met_3_parameters_aanmaken_dag_onmogelijk_te_groot() throws Exception {
		Datum datumMet3 = new Datum ("35/05/1995");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_DatumString_met_3_parameters_aanmaken_dag_onmogelijk_negatief() throws Exception {
		Datum datumMet3 = new Datum ("-5/05/1995");
	}
	@Test (expected = IllegalArgumentException.class)
	public void test_DatumString_met_3_parameters_aanmaken_dag_onmogelijk_nul() throws Exception {
		Datum datumMet3 = new Datum ("00/05/1995");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_DatumString_met_3_parameters_aanmaken_maand_onmogelijk() throws Exception {
		Datum datumMet3 = new Datum ("05/15/1995");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_DatumString_met_3_parameters_aanmaken_maand_onmogelijk_negatief() throws Exception {
		Datum datumMet3 = new Datum ("05/-15/1995");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_DatumString_met_3_parameters_aanmaken_maand_onmogelijk_nul() throws Exception {
		Datum datumMet3 = new Datum ("05/00/1995");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_DatumString_met_3_parameters_aanmaken_jaar_onmogelijk_nul() throws Exception {
		Datum datumMet3 = new Datum ("05/15/00");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_DatumString_met_3_parameters_aanmaken_geen_schrikkeljaar() throws Exception {
		Datum datumMet3 = new Datum ("29/02/2015");
	}
	
	@Test 
	public void test_DatumString_met_3_parameters_aanmaken_wel_schrikkeljaar() throws Exception {
		Datum datumMet3 = new Datum ("29/02/2020");
		assertEquals(29, datumMet3.getDag());
		assertEquals(2, datumMet3.getMaand());
		assertEquals(2020, datumMet3.getJaar());
		assertEquals(datumMet3.getDag(), 29);
		assertEquals(datumMet3.getMaand(), 2);
		assertEquals(datumMet3.getJaar(), 2020);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_DatumString_met_3_parameters_aanmaken_geen_schrikkeljaar_eeuw() throws Exception {
		Datum datumMet3 = new Datum ("29/02/1900");
	}
	
	@Test 
	public void test_DatumString_met_3_parameters_aanmaken_wel_schrikkeljaar_eeuw() throws Exception {
		Datum datumMet3 = new Datum ("29/02/2000");
		assertEquals(29, datumMet3.getDag());
		assertEquals(2, datumMet3.getMaand());
		assertEquals(2000, datumMet3.getJaar());
		assertEquals(datumMet3.getDag(), 29);
		assertEquals(datumMet3.getMaand(), 2);
		assertEquals(datumMet3.getJaar(), 2000);
	}
	

	
	@Test
	public void test_Datum_CompareTo() {
		
		assertEquals(-1, datumX.compareTo(datumLater));
		assertEquals(1, datumX.compareTo(datumVroeger));
		assertEquals(0, datumX.compareTo(datumY));
	}
	
	@Test
	public void test_DatumInAmerikaansFormaat()
	{
		assertEquals("1982/3/10", datumX.getDatumInAmerikaansFormaat(datumX));
		assertEquals(datumX.getDatumInAmerikaansFormaat(datumX), "1982/3/10");
	}
	
	@Test
	public void test_DatumInEuropeesFormaat()
	{
		assertEquals("10/3/1982", datumX.getDatumInEuropeesFormaat(datumX));
		assertEquals(datumX.getDatumInEuropeesFormaat(datumX), "10/3/1982");
	}
	
	@Test
    public void test_ToString()
    {
        
        String expected = "10 maart 1982"; 
        assertEquals(expected, datumX.toString());
        assertEquals(datumX.toString(), expected);
    }
	
	@Test
    public void test_VerschilInDagen_binnenMaand()
    {
        int expected = 2; 
        assertEquals(expected, datumVroeger.verschilInDagen(datumX));
        assertEquals(datumVroeger.verschilInDagen(datumX), expected);
    }
	
	@Test
    public void test_VerschilInDagen_buitenMaand()
    {
        int expected = 60; 
        assertEquals(expected, datumZ.verschilInDagen(datumX));
        assertEquals(datumZ.verschilInDagen(datumX), expected);
    }
	
	

	
	
	

}
