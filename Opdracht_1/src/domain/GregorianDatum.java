package domain;

import java.util.Calendar;

import java.util.GregorianCalendar;


public class GregorianDatum {

	private GregorianCalendar gC;

	/* De calendar wordt lenient 'false' gezet in de constructors.
	 * Dit betekent dat hij zelf strikt schrikkeljaren en aantal dagen in maand berekent.
	 * Als er een datum wordt aangemaakt met jaar nul bv. wordt wel het object in eerste
	 * instantie aangemaakt, maar dan zonder jaar. Vandaar de extra check bij constructors
	 * om een IllegalArgumentException te gooien (zo is gedrag == gedrag van de andere Datum klasse.)
	 */


	private static final String[] Maanden = new String[] { "januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december"};

	public GregorianDatum() {
		GregorianCalendar Vandaag = new GregorianCalendar();
		gC = (GregorianCalendar) Vandaag.clone();
		gC.setLenient(false);
	}

	public GregorianDatum(int d, int m, int j) {
			gC = new GregorianCalendar();
		gC.setLenient(false);
		gC.set(j,m-1,d);
		
		//volgende check om zeker dezelfde soort fout te hebben als andere Datum klasse
		if ((gC.get(Calendar.DAY_OF_MONTH)) <= 0 || (gC.get(Calendar.MONTH)) <= 0 || (gC.getWeekYear()) <= 0 )
		{
			throw new IllegalArgumentException("illegale input");
		}
		
	}

	public GregorianDatum(GregorianDatum d){
		gC = new GregorianCalendar();
		gC.setLenient(false);
		gC.set(d.getJaar(), d.getMaand()-1, d.getDag());
	}

	public GregorianDatum(String Dat){
		String DatumString[] = Dat.split("/");
		String sDag = DatumString[0];
		if (sDag.substring(0, 1) == "0") { sDag = sDag.substring(2,1); }	
		String sMaand = DatumString[1];
		if (sMaand.substring(0, 1) == "0") { sMaand = sMaand.substring(2,1); }	
		String sJaar = DatumString[2];

		int Dag = Integer.parseInt(sDag);
		int Maand = Integer.parseInt(sMaand);
		int Jaar = Integer.parseInt(sJaar);
		gC = new GregorianCalendar();
		gC.setLenient(false);
		gC.set(Jaar,Maand-1,Dag);
		
		//volgende check om zeker dezelfde soort fout te hebben als andere Datum klasse
		if ((gC.get(Calendar.DAY_OF_MONTH)) <= 0 || (gC.get(Calendar.MONTH)) <= 0 || (gC.getWeekYear()) <= 0 )
		{
			throw new IllegalArgumentException("illegale input");
		}
	}

	/*
	 * Getters en setters
	 */

	// Dag
	public int getDag() {
		return (gC.get(Calendar.DAY_OF_MONTH));
	}

	// Maand
	public int getMaand() {
		return (gC.get(Calendar.MONTH))+1;
	}

	//Jaar
	public int getJaar() {
		return gC.getWeekYear();

	}

	// Getters formaten
	public String getDatumInAmerikaansFormaat(GregorianDatum x) {
		String AmerikaanseDatum = Integer.toString(x.getJaar()) + "/" + Integer.toString(x.getMaand()) + "/" + Integer.toString(x.getDag());
		return AmerikaanseDatum;
	}
	public String getDatumInEuropeesFormaat(GregorianDatum x) {
		String EuropeseDatum = Integer.toString(x.getDag()) + "/" + Integer.toString(x.getMaand()) + "/" + Integer.toString(x.getJaar());
		return EuropeseDatum;
	}

	// Controle functie

	//Deze is als hulpfunctie eigenlijk niet nodig omdat de Gregorian zelf de schrikkeljaren weet.
	private boolean isSchrikkeljaar(int jaar) {
		boolean Schrikkeljaar = gC.isLeapYear(jaar);
		return Schrikkeljaar;
	}

	// functies

	public boolean kleinerDan(GregorianDatum d) { 
		if (d.getJaar() > this.getJaar()) {
			return false;
		} 
		else if (d.getJaar() < this.getJaar()) {
			return true;
		}
		else if (d.getMaand() > this.getMaand()) {
			return false;
		}
		else if (d.getMaand() < this.getMaand()) {
			return true;
		}
		else if (d.getDag() > this.getDag()) {
			return false;
		}
		else if (d.getDag() == this.getDag()) {
			return false;
		}
		else {
			return true;
		}
	}

	public int verschilInJaren(GregorianDatum d) { 

		int huidigeDag = this.getDag();
		int huidigeMaand = this.getMaand();
		int huidigJaar = this.getJaar();

		int dDag = d.getDag();
		int dMaand = d.getMaand();
		int dJaar = d.getJaar();

		int aantalJaren = 0;

		if (huidigJaar == dJaar){
			return aantalJaren;
		}


		if (this.kleinerDan(d) == false) {
			do {
				huidigJaar++;
				aantalJaren++;
			} while (huidigJaar < dJaar);
			if (huidigJaar >= dJaar) {
				if (huidigeMaand > dMaand) { 
					aantalJaren--; 
				} else if (huidigeMaand == dMaand && huidigeDag > dDag) {
					aantalJaren--;
				}
			}
		} 

		else if (this.kleinerDan(d) == true) {
			do {
				dJaar++;
				aantalJaren++;
			} while (dJaar < huidigJaar);
			if (dJaar >= huidigJaar) {
				if (dMaand > huidigeMaand) 
				{ 
					aantalJaren--; 
				} 
				else if (dMaand== huidigeMaand && dDag > huidigeDag) 
				{
					aantalJaren--;
				}
			}
		}
		return aantalJaren;
	}

	public int verschilInMaanden(GregorianDatum d) {

		//verschil in jaren al ophalen
		int aantalMaanden = this.verschilInJaren(d)*12;
		int maxMaandenNogVerschil =0;

		int huidigeDag = this.getDag();
		int huidigeMaand = this.getMaand();


		int dDag = d.getDag();
		int dMaand = d.getMaand();


		if (this.kleinerDan(d) == false) {
			while (huidigeMaand != dMaand && maxMaandenNogVerschil < 11) 
			{ huidigeMaand++;
			if (huidigeMaand == 13){huidigeMaand = 1;}
			aantalMaanden++;
			maxMaandenNogVerschil++;
			} ;

			if (huidigeMaand == dMaand) {
				if (huidigeDag > dDag) { 
					aantalMaanden--; 
				} 
			}
		} 
		else if (this.kleinerDan(d) == true) {
			while (dMaand != huidigeMaand && maxMaandenNogVerschil < 11) 
			{ dMaand++;
			if (dMaand == 13){dMaand = 1;}
			aantalMaanden++;
			maxMaandenNogVerschil++;
			} 

			if (huidigeMaand == dMaand) {
				if (dDag > huidigeDag) { 
					aantalMaanden--; 
				} 
			}


		}
		return aantalMaanden;


	}

	public int verschilInDagen(GregorianDatum d) {



		int aantalDagen = 0;

		GregorianDatum datum1;
		GregorianDatum datum2;
		if (this.kleinerDan(d) == true)
		{
			datum1 = new GregorianDatum (d)	;
			datum2 = new GregorianDatum (this)	;
		}
		else
		{	
			datum1 = new GregorianDatum (this);
			datum2 = new GregorianDatum (d);
		}
		if (datum1.getDag() == datum2.getDag() && datum1.getMaand() == datum2.getMaand()
				&& datum1.getJaar() == datum2.getJaar()){
			return 0;
		}

		do
		{
			datum1.veranderDatum(1);

			aantalDagen++;
		} while (datum1.getDag() != datum2.getDag() || datum1.getMaand() != datum2.getMaand()
				|| datum1.getJaar() != datum2.getJaar());
		return aantalDagen;



	}


	public void veranderDatum(int aantalDagen) {

		gC.add(Calendar.DAY_OF_MONTH, aantalDagen);

	}

	public GregorianDatum veranderDatumObj(int aantalDagen) {
		GregorianDatum date = new GregorianDatum (this);
		date.veranderDatum(aantalDagen);
		return date;

	}

	


	@Override
	public String toString() {
		return Integer.toString(getDag()) + " " + Maanden[getMaand()-1] +" "+ Integer.toString(getJaar());
	}
	
	public int compareTo(GregorianDatum d) {
		if (this.getJaar() == d.getJaar() && this.getMaand() == d.getMaand() && this.getDag() == d.getDag()) return 0;
		if (this.kleinerDan(d)==true) return 1;
		if (this.kleinerDan(d)==false) return -1;
		return 0;
	}


}