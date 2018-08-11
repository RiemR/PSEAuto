package autopackage;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.sql.DataSource;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 * 
 * @author Autogruppe In der Klasse Liste werden alle wichtigen Interaktionen
 *         mit der Datenbank ausgefuehrt, also Autos suchen, einfuegen,
 *         loeschen, Nutzer registrieren und einloggen, etc.
 */
@Named
@SessionScoped
public class Liste implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private int autoid;
	private String marke;
	private int erstzulassung;
	private String modell;
	private String stadt;
	private String kraftstoff;
	private String getriebe;
	private String karosserieform;
	private int kilometerstand;
	private int ps;
	private UploadedFile file;

	private String firstName;
	private String lastName;
	private String email;
	private String password;

	private String dbPassword;
	private String dbName;
	DataSource ds;

	public Liste() {
		as = new ArrayList<Auto>();
		idm = new DataModel(as);
	}

	/*--------------------------------------------------------------------------*/

	Auto aktuellesAuto = new Auto(0, 0, "Marke", 0, "Modell", "Stadt", "Kraftstoff", "Getriebe", "Karosserieform", 0,
			0);

	/**
	 * 
	 * @param aktuellesAuto
	 */
	public void setAktuellesAuto(Auto aktuellesAuto) {
		this.aktuellesAuto = aktuellesAuto;
	}

	/**
	 * Die Methode gibt das aktuelle Auto zurück, welches dann auf der Foto.xhtml
	 * separat angezeigt wird mit den einzelnen Daten zum Auto.
	 * 
	 * @return aktuellesAuto
	 */
	public Auto getAktuellesAuto() {

		for (int i = 0; i < as.size(); i++) {
			System.out.println(as.get(i).toString());
			if (autoid == as.get(i).getAutoid()) {
				aktuellesAuto = as.get(i);

				break;
			}

		}
		return aktuellesAuto;
	}

	ArrayList<Auto> as = new ArrayList<Auto>();
	private DataModel idm = null;

	/*--------------------------------------------------------------------------*/
	/**
	 * Die Methode suchen gibt je nach Angabe der Informationen, das passende Auto
	 * zurück aus der Datenbank.
	 * 
	 * @param ae
	 */
	public void suchen(ActionEvent ae) {
		as.clear();

		final String sql = "Select * from AutoDatenbank where Preis <=? and Marke=?  and Erstzulassung>=? and Modell=? and Stadt=? and Kraftstoff=? and Getriebe=? and Karosserieform=? and Kilometerstand<=? and PS>=?";
		Util util = new Util();
		Connection con = util.getCon();
		try {

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, preis);
			pst.setString(2, marke);
			pst.setInt(3, erstzulassung);
			pst.setString(4, modell);
			pst.setString(5, stadt);
			pst.setString(6, kraftstoff);
			pst.setString(7, getriebe);
			pst.setString(8, karosserieform);
			pst.setInt(9, kilometerstand);
			pst.setInt(10, ps);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Auto i = new Auto(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10),
						rs.getInt(11));
				as.add(i);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * einfuegen ist dafuer verantwortlich, dass ein neues Auto in die Datenbank
	 * eingefuegt wird
	 * 
	 * @param ae
	 */
	public void einf�gen(ActionEvent ae) {
		as.clear();

		final String sql = "Insert into AutoDatenbank (Preis, Marke,Erstzulassung, Modell, Stadt, Kraftstoff, Getriebe, Karosserieform, Kilometerstand,PS) VALUES (?,?,?,?,?,?,?,?,?,?)";

		Util util = new Util();
		Connection con = util.getCon();
		try {

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, preis);
			pst.setString(2, marke);
			pst.setInt(3, erstzulassung);
			pst.setString(4, modell);
			pst.setString(5, stadt);
			pst.setString(6, kraftstoff);
			pst.setString(7, getriebe);
			pst.setString(8, karosserieform);
			pst.setInt(9, kilometerstand);
			pst.setInt(10, ps);

			setPic();

			pst.executeUpdate();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * loeschen loescht ein Auto aus der Datenbank mithilfe der AutoId als
	 * Primaerschluessel
	 * 
	 * @param ae
	 */
	public void loeschen(ActionEvent ae) {
		as.clear();
		final String sql = "DELETE FROM AutoDatenbank WHERE AutoId=?";
		Util util = new Util();

		Connection con = util.getCon();

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			System.out.println("kommt ");
			pst.setInt(1, autoid);
			pst.executeUpdate();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * add fügt einen neuen Nutzer in die Datenbank ein
	 * 
	 * @param ae
	 */
	public void add(ActionEvent ae) {
		final String sql = "INSERT INTO user(firstname, password, lastname, email) VALUES(?,?,?,?)";
		Util util = new Util();
		Connection con = util.getCon();

		try {
			PreparedStatement pst = con.prepareStatement(sql);

			pst = con.prepareStatement(sql);
			pst.setString(1, firstName);
			pst.setString(2, password);
			pst.setString(3, lastName);
			pst.setString(4, email);
			pst.executeUpdate();
			System.out.println("Data Added Successfully");
			pst.close();

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param ae
	 *            dbData ist dafür verantwortlich die Nutzerdaten mit den
	 *            Nutzerdaten aus der Datenbanktabell user zu vergleichen
	 */
	public void dbData(ActionEvent ae) {
		final String sql = "Select firstname,password from user where firstname = ? and password=?";
		Util util = new Util();
		Connection con = util.getCon();
		ResultSet rs = null;
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst = con.prepareStatement(sql);
			pst.setString(1, firstName);
			pst.setString(2, password);
			rs = pst.executeQuery();
			rs.next();
			dbName = rs.getString("firstname");
			dbPassword = rs.getString("password");
			pst.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	/**
	 * login überprüft die eingegebenen Daten, wenn die Nutzerdaten stimmen, kommt
	 * man zur Homepage, wenn nicht, dann zur unsuccess-Seite
	 * 
	 * @return xhtml
	 */
	public String login() {
		ActionEvent ae = null;

		dbData(ae);
		if (firstName.equals(dbName) && password.equals(dbPassword)) {
			return "Homepage.xhtml";
		} else
			return "unsuccess.xhtml";

	}

	/*--------------------------------------------------------------------------*/
	Util util = new Util();
	private StreamedContent streamedPic = null;
	final String PIC_TEXT_JA = "Das Bild";
	final String PIC_TEXT_NEIN = "";
	private String picText = PIC_TEXT_NEIN;
	final String CLASSNAME = getClass().getName();

	/**
	 * 
	 * @return picText
	 */
	public String getPicText() {
		return picText;
	}

	/**
	 * Die Methode getStreamedPic() holt das Bild aus der Datenbank und gibt es
	 * zurück.
	 * 
	 * @return das Bild
	 */
	public StreamedContent getStreamedPic() {
		getPic();
		return streamedPic;
	}

	/**
	 * getPic holt mithilfe der AutoId das Bild zu dem Auto aus der Datenbank
	 * 
	 */
	public void getPic() {
		System.out.println(CLASSNAME + ".getPic()...");
		String typ = "?";

		Connection con = util.getCon();
		if (con != null) {

			try {
				PreparedStatement pst = con.prepareStatement("SELECT Foto FROM AutoDatenbank WHERE AutoId=?");
				pst.setInt(1, autoid);
				ResultSet rs = pst.executeQuery();

				if (rs.next()) {
					InputStream is = rs.getBinaryStream(1);
					streamedPic = new DefaultStreamedContent(is, typ);
					is.close();
					picText = PIC_TEXT_JA;
				} else
					System.err.println("Leeres ResultSet");

				rs.close();
				pst.close();
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else
			System.err.println("Connection null");
	}

	/**
	 * setPic soll ein Bild des neuen Autos in die Datenbank einfuegen
	 */
	public void setPic() {
		System.out.println(CLASSNAME + ".setPic()...");

		// String typ = "?";
		Connection con = util.getCon();
		if (con != null) {

			try {
				PreparedStatement pst = con
						.prepareStatement("Insert into AutoDatenbank(Foto) values (?) WHERE AutoId=?");
				pst.setBinaryStream(1, file.getInputstream());
				pst.setInt(2, autoid);
				pst.executeUpdate();

				pst.close();
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else
			System.err.println("Connection null");
	}

	/**
	 * 
	 * @return foto.xhtml
	 */
	public String foto() {
		return "foto.xhtml";
	}

	/**
	 * 
	 * @return idm
	 */
	public DataModel getAutos() {
		if (as != null) {
			System.out.println(as.size());
		} else
			System.out.println("as ist null");

		return idm;
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
	public void setErstzulassung(int erstzulassung) {
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
	 * @param modell : fuegt das Modell fuer das Auto ein
	 */
	public void setModell(String modell) {
		this.modell = modell;
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
	 * @param kraftstoff : fuegt die Kraftstoffart fuer das Auto ein
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

	/**
	 * 
	 * @return dbPassword
	 */
	public String getDbPassword() {
		return dbPassword;
	}

	/**
	 * 
	 * @return dbName
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param name : fuegt den Nutzernamen fuer den Nutzer ein
	 */
	public void setFirstName(String name) {
		this.firstName = name;
	}

	/**
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName : fuegt den Namen des Nutzers ein
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email : fuegt die Email-Adresse des Nutzers ein
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password : fuegt das Passwort des Nutzers ein
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}