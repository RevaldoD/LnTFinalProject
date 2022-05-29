package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Menu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.Connect;


public class Insert extends JInternalFrame implements ActionListener{
	JLabel lblTitle, lblKodeMenu, lblNamaMenu, lblHargaMenu, lblStokMenu;
	JTextField txtNamaMenu, txtKodeMenu ;
	JSpinner spinHargaMenu, spinStokMenu;
	JButton btnInsertMenu;
	
	Connect con = Connect.getInstance();
	
	
	public Insert() {
		setSize(500, 250);
		setTitle("PT Pudding");
		setResizable(false);
		setClosable(true);
		setLayout(new BorderLayout());


		initSouth();
		initCenter();
		initSouth();

		setVisible(true);
	}

	private void initSouth() {
		JPanel panelNorth, panel1;
		panelNorth = new JPanel(new BorderLayout());
		panel1 = new JPanel();
		
		lblTitle = new JLabel("Menu Form");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
		
		panel1.add(lblTitle);
		panelNorth.add(panel1, BorderLayout.NORTH);
		add(panelNorth, BorderLayout.NORTH);
		
	}

	private void initCenter() {
		JPanel panelSouth, panel1, panel2, panel3;
		panelSouth = new JPanel(new BorderLayout());
		panel1 = new JPanel(new GridLayout(4, 2));
		panel2 = new JPanel();
		panel3 = new JPanel(new GridLayout(1, 1));

		lblKodeMenu = new JLabel("Kode Menu");
		lblNamaMenu = new JLabel("Nama Menu");
		lblHargaMenu = new JLabel("Harga Menu");
		lblStokMenu = new JLabel("Stok Menu");
		txtKodeMenu = new JTextField();
		txtNamaMenu = new JTextField();
		spinHargaMenu = new JSpinner();
		spinStokMenu= new JSpinner();
		btnInsertMenu = new JButton("Insert");

		txtKodeMenu.setEnabled(false);
		
		Random rand = new Random();
		int number = rand.nextInt(999);
		
		String kode = String.format("PD-%03d",number);
		
		txtKodeMenu.setText(kode);
		
		panel1.add(lblKodeMenu);
		panel1.add(txtKodeMenu);
		panel1.add(lblNamaMenu);
		panel1.add(txtNamaMenu);
		panel1.add(lblHargaMenu);
		panel1.add(spinHargaMenu);
		panel1.add(lblStokMenu);
		panel1.add(spinStokMenu);

		panel2.add(btnInsertMenu);
		
		panelSouth.add(panel1, BorderLayout.CENTER);
		panelSouth.add(panel2, BorderLayout.SOUTH);

		add(panelSouth, BorderLayout.CENTER);
		
		btnInsertMenu.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnInsertMenu)) {
			
			Menu menu = new Menu(txtKodeMenu.getText(), txtNamaMenu.getText()
					, Integer.parseInt(spinHargaMenu.getValue().toString()), Integer.parseInt(spinStokMenu.getValue().toString()) );
			con.addMenu(menu);
			JOptionPane.showMessageDialog(null, "Menu Added", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

}
