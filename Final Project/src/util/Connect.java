package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import model.Menu;

public class Connect {
	Connection con;
	Statement st;
	ResultSet result;

	private static Connect instance;

	public Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbName = "ptpudding";
			String dbUser = "root";
			String dbPass = "";
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, dbUser, dbPass);
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Connected");
	}

	public static Connect getInstance() {
		if (instance == null) {
			instance = new Connect();
		}

		return instance;
	}

	public Vector<Menu> getMenu() {
		Vector<Menu> m = new Vector<>();
		String query = "SELECT * FROM Menu;";

		try {
			result = st.executeQuery(query);

			while (result.next()) {
				m.add(new Menu(result.getString("KodeMenu"), result.getString("NamaMenu"), result.getInt("HargaMenu"),
						result.getInt("StokMenu")));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;

	}

	public void addMenu(Menu menu) {
		String query = String.format(
				"INSERT INTO Menu (KodeMenu, NamaMenu, HargaMenu, StokMenu)" + "VALUES (\"%s\", \"%s\", %d, %d)",
				menu.getKodeMenu(), menu.getNamaMenu(), menu.getHargaMenu(), menu.getStokMenu());
		System.out.println(query);
		try {
			st.execute(query);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void updateMenu(String kode, String name, int price, int quantity) {
		String query = String.format("UPDATE `Menu` SET NamaMenu = '%s', HargaMenu = %d , StokMenu = %d WHERE KodeMenu = '%s' ", name, price, quantity, kode);
		try {
			st.executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void deleteProduct(String kode) {
		String query = String.format("DELETE FROM `Menu` WHERE KodeMenu = '%s' ",kode);
		try {
			st.executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
