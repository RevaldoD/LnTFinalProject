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

public class Update extends JInternalFrame implements MouseListener, ActionListener {
	int ID = -1;
	String kode;
	JLabel lblTitle, lblMenuName, lblMenuPrice, lblMenuQuantity;
	JTextField txtMenu;
	JSpinner spinMenuPrice, spinMenuQuantity;
	JButton btnUpdateMenu;
	JTable tblMenu;
	JScrollPane jspMenu;
	DefaultTableModel dtmMenu;
	
	
	Vector<Menu> menus = new Vector<>(); 
	String columnTableMenu[] = { "KodeMenu", "NameMenu", "HargaMenu", "StokMenu"};
	
	Connect con = Connect.getInstance();
	
	public Update() {
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
		JPanel panelSouth, panel1, panel2;
		panelSouth = new JPanel(new BorderLayout());
		panel1 = new JPanel(new GridLayout(4, 2));
		panel2 = new JPanel();

		lblMenuName = new JLabel("Product Name");
		lblMenuPrice = new JLabel("Product Price");
		lblMenuQuantity = new JLabel("Product Quantity");
		txtMenu = new JTextField();
		spinMenuPrice = new JSpinner();
		spinMenuQuantity = new JSpinner();
		btnUpdateMenu = new JButton("Update");


		panel1.add(lblMenuName);
		panel1.add(txtMenu);
		panel1.add(lblMenuPrice);
		panel1.add(spinMenuPrice);
		panel1.add(lblMenuQuantity);
		panel1.add(spinMenuQuantity);

		panel2.add(btnUpdateMenu);

		panelSouth.add(panel1, BorderLayout.CENTER);
		panelSouth.add(panel2, BorderLayout.SOUTH);

		add(panelSouth, BorderLayout.SOUTH);

		btnUpdateMenu.addActionListener(this);

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
				txtMenu.setText(menus.get(ID).getNamaMenu());
				spinMenuPrice.setValue(menus.get(ID).getHargaMenu());
				spinMenuQuantity.setValue(menus.get(ID).getStokMenu());
			}
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnUpdateMenu)) {
			String name;
			int type, price, quantity;

			name = txtMenu.getText();
			price = (int) spinMenuPrice.getValue();
			quantity = (int) spinMenuQuantity.getValue();

			if (e.getSource().equals(btnUpdateMenu) && ID == -1) {
				JOptionPane.showMessageDialog(null, "Please Select the Data", "Message", JOptionPane.ERROR_MESSAGE);
			}
			
			else {
				if (e.getSource().equals(btnUpdateMenu)) {
					con.updateMenu(kode, name, price, quantity);
					JOptionPane.showMessageDialog(null, "Product Updated", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
				fillTableProduct();
				reset();
			}
		}
	}	
	
	private void reset() {
		txtMenu.setText("");
		spinMenuPrice.setValue(0);
		spinMenuQuantity.setValue(0);
		ID = -1;
	}

}

