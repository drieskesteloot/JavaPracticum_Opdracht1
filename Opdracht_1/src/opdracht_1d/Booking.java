package opdracht_1d;
import datum.Datum;
import java.util.Date;

public class Booking 
{
	private String firstName;
	private String lastName;
	private int huisNummer;
	private Datum beginDatum;
	private Datum eindDatum;
	private int aantalNachten;
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public void setHuisNummer(int huisNummer){
		this.huisNummer = huisNummer;
	}
	
	public int getHuisNummer(){
		return this.huisNummer;
	}
	
	public void setBeginDatum(Datum beginDatum){
		this.beginDatum = beginDatum;
	}
	
	public Datum getBeginDatum(){
		return this.beginDatum;
	}
	
	public void setEindDatum(Datum eindDatum){
		this.eindDatum = eindDatum;
	}
	
	public Datum getEindDatum(){
		return this.eindDatum;
	}
	
	public void setAantelNachten(Date beginDatum, Date einDatum){
		long verschil = einDatum.getDate() - beginDatum.getDate();
		long verschilDagen = verschil / (24 * 60 * 60 * 1000);
		this.aantalNachten = (int)verschilDagen;
	}
	
	public int aantalNachten()
	{
		return this.aantalNachten;
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
