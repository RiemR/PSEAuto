package autopackage;

import com.mysql.jdbc.Blob;

/**
 * 
 * @author Autogruppe Diese Klasse ist für die Erzeugung eines neuen Autos
 *         zuständig
 *
 */
public class Auto {

	private int preis = 0;
	private int autoid = 0;
	private String marke = "Marke";
	private int erstzulassung = 0;
	private String modell = "Modell";
	private String stadt = "Stadt";
	private String kraftstoff = "Kraftstoff";
	private String getriebe = "Getriebe";
	private String karosserieform = "Karosserieform";
	private int kilometerstand = 0;
	private int ps = 0;
	private java.sql.Blob foto;

	/**
	 * Konstruktor Auto erstellt ein neues Objekt eines Autos
	 * 
	 * Ferhat hat ein Kommentar hinzugefügt
	 * @param autoid
	 * @param preis
	 * @param marke
	 * @param erstzulassung
	 * @param modell
	 * @param stadt
	 * @param kraftstoff
	 * @param getriebe
	 * @param karosserieform
	 * @param kilometerstand
	 * @param ps
	 */
	public Auto(int autoid, int preis, String marke, int erstzulassung, String modell, String stadt, String kraftstoff,
			String getriebe, String karosserieform, int kilometerstand, int ps) {
		super();
		this.autoid = autoid;
		this.preis = preis;
		this.marke = marke;
		this.erstzulassung = erstzulassung;
		this.modell = modell;
		this.stadt = stadt;
		this.kraftstoff = kraftstoff;
		this.getriebe = getriebe;
		this.karosserieform = karosserieform;
		this.kilometerstand = kilometerstand;
		this.ps = ps;
	}

	public Auto() {
	}

	/**
	 * 
	 * @return foto
	 */
	public java.sql.Blob getFoto() {
		return foto;
	}

	/**
	 * 
	 * @param foto : fuegt das Foto fuer das Auto ein
	 */
	public void setFoto(Blob foto) {
		this.foto = foto;
	}

	/**
	 * 
	 * @return preis
	 */
	public int getPreis() {
		return preis;
	}

	/**
	 * 
	 * @param preis : fuegt den Preis fuer das Auto ein
	 */
	public void setPreis(int preis) {
		this.preis = preis;
	}

	/**
	 * 
	 * @return autoid
	 */
	public int getAutoid() {
		return autoid;
	}

	/**
	 * 
	 * @param autoid : fuegt die AutoID fuer das Auto ein
	 */
	public void setAutoid(int autoid) {
		this.autoid = autoid;
	}

	/**
	 * 
	 * @return marke
	 */
	public String getMarke() {
		return marke;
	}

	/**
	 * 
	 * @param marke : fuegt die Marke fuer das Auto ein
	 */
	public void setMarke(String marke) {
		this.marke = marke;
	}

	/**
	 * 
	 * @return erstzulassung
	 */
	public int getErstzulassung() {
		return erstzulassung;
	}

	/**
	 * 
	 * @param erstzulassung : fuegt die Erstzulassung fuer das Auto ein
	 */
	public void setErstezulassung(int erstzulassung) {
		this.erstzulassung = erstzulassung;
	}

	/**
	 * 
	 * @return modell
	 */
	public String getModell() {
		return modell;
	}

	/**
	 * 
	 * @param model : fuegt das Modell fuer das Auto ein
	 */
	public void setModell(String model) {
		this.modell = model;
	}

	/**
	 * 
	 * @return stadt
	 */
	public String getStadt() {
		return stadt;
	}

	/**
	 * 
	 * @param stadt : fuegt die Stadt fuer das Auto ein
	 */
	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	/**
	 * 
	 * @return kraftstoff
	 */
	public String getKraftstoff() {
		return kraftstoff;
	}

	/**
	 * 
	 * @param kraftstoff : fuegt die Krafstoffart fuer das Auto ein
	 */
	public void setKraftstoff(String kraftstoff) {
		this.kraftstoff = kraftstoff;
	}

	/**
	 * 
	 * @return getriebe
	 */
	public String getGetriebe() {
		return getriebe;
	}

	/**
	 * 
	 * @param getriebe : fuegt die Getriebeart fuer das Auto ein
	 */
	public void setGetriebe(String getriebe) {
		this.getriebe = getriebe;
	}

	/**
	 * 
	 * @return karosserieform
	 */
	public String getKarosserieform() {
		return karosserieform;
	}

	/**
	 * 
	 * @param karosserieform : fuegt die Karosserieform fuer das Auto ein
	 */
	public void setKarosserieform(String karosserieform) {
		this.karosserieform = karosserieform;
	}

	/**
	 * 
	 * @return kilometerstand
	 */
	public int getKilometerstand() {
		return kilometerstand;
	}

	/**
	 * 
	 * @param kilometerstand : fuegt den Kilometerstand fuer das Auto ein
	 */
	public void setKilometerstand(int kilometerstand) {
		this.kilometerstand = kilometerstand;
	}

	/**
	 * 
	 * @return ps
	 */
	public int getPs() {
		return ps;
	}

	/**
	 * 
	 * @param ps : fuegt die PS fuer das Auto ein 
	 */
	public void setPs(int ps) {
		this.ps = ps;
	}
}
