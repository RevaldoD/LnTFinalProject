package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Menu;
import util.Connect;

public class View extends JInternalFrame {
	JLabel lblTitle;
	JTable tblMenu;
	JScrollPane jspMenu;
	DefaultTableModel dtmMenu;
	
	
	Vector<Menu> menus = new Vector<>(); 
	String columnTableMenu[] = { "KodeMenu", "NameMenu", "HargaMenu", "StokMenu"};
	
	Connect con = Connect.getInstance();
	
	public View() {
		setSize(500, 250);
		setTitle("PT Pudding");
		setResizable(false);
		setClosable(true);
		setLayout(new BorderLayout());


		initSouth();
		initCenter();

		setVisible(true);
	}


	private void initSouth() {
		JPanel panelNorth, panel1;
		panelNorth = new JPanel(new BorderLayout());
		panel1 = new JPanel();
		
		lblTitle = new JLabel("View Menu");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
		
		panel1.add(lblTitle);
		panelNorth.add(panel1, BorderLayout.NORTH);
		add(panelNorth, BorderLayout.NORTH);
	}


	private void initCenter() {
		JPanel panelCenter;
		panelCenter = new JPanel();

		dtmMenu = new DefaultTableModel(columnTableMenu, 0);
		tblMenu = new JTable(dtmMenu);
		jspMenu = new JScrollPane(tblMenu);

		panelCenter.add(jspMenu);

		fillTableProduct();

		add(panelCenter, BorderLayout.CENTER);

		}
	private void fillTableProduct() {
		menus = new Vector<>();
		dtmMenu = new DefaultTableModel(columnTableMenu, 0);

		menus = con.getMenu();

		Vector<Object> rows;
		for (Menu p : menus) {
			rows = new Vector<>();
			rows.add(p.getKodeMenu());
			rows.add(p.getNamaMenu());
			rows.add(p.getHargaMenu());
			rows.add(p.getStokMenu());

			dtmMenu.addRow(rows);
		}
		tblMenu.setModel(dtmMenu);
	}	

}
