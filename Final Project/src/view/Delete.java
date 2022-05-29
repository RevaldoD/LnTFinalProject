package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Menu;
import util.Connect;

public class Delete extends JInternalFrame implements MouseListener, ActionListener {
	int ID = -1;
	String kode;
	JLabel lblTitle;
	JButton btnDeleteMenu;
	JTable tblMenu;
	JScrollPane jspMenu;
	DefaultTableModel dtmMenu;
	
	
	Vector<Menu> menus = new Vector<>(); 
	String columnTableMenu[] = { "KodeMenu", "NameMenu", "HargaMenu", "StokMenu"};
	
	Connect con = Connect.getInstance();
	
	public Delete() {
		setSize(500, 250);
		setTitle("PT Pudding");
		setResizable(false);
		setClosable(true);
		setLayout(new BorderLayout());

		initNorth();
		initSouth();
		initCenter();

		setVisible(true);
	}


	private void initNorth() {
		JPanel panelNorth, panel1;
		panelNorth = new JPanel(new BorderLayout());
		panel1 = new JPanel();
		
		lblTitle = new JLabel("Delete Menu");
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
		
		tblMenu.addMouseListener(this);

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
	
	private void initSouth() {
		JPanel panelSouth, panel1;
		panelSouth = new JPanel(new BorderLayout());
		panel1 = new JPanel();

		btnDeleteMenu = new JButton("Delete");

		panel1.add(btnDeleteMenu);

		panelSouth.add(panel1, BorderLayout.SOUTH);

		add(panelSouth, BorderLayout.SOUTH);

		btnDeleteMenu.addActionListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource().equals(tblMenu)) {
			if (tblMenu.getSelectedRow() > -1) {
				ID = tblMenu.getSelectedRow();
				kode = menus.get(ID).getKodeMenu();
			}
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnDeleteMenu)) {
			if (ID == -1) {
				JOptionPane.showMessageDialog(null, "Please Select the Data", "Message", JOptionPane.ERROR_MESSAGE);
			} 
			else {	
				con.deleteProduct(menus.get(ID).getKodeMenu());
				JOptionPane.showMessageDialog(null, "Product Deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
				fillTableProduct();
				reset();
			}
		}
	}	
	
	private void reset() {
		ID = -1;
	}

}

