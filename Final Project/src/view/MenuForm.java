package view;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import util.Connect;

public class MenuForm extends JFrame implements MenuListener{
	JMenuBar menuBar;
	JMenu menuInsert, menuView, menuUpdate, menuDelete;
	Connect con = Connect.getInstance();
	
	public MenuForm() {
		setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("PT Pudding");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		init();
		
		setVisible(true);
	}

	private void init() {
		menuBar = new JMenuBar();
		menuInsert = new JMenu("Insert Menu");
		menuView = new JMenu("View Menu");
		menuUpdate = new JMenu("Update Menu");
		menuDelete = new JMenu("Delete Menu");
		
		menuBar.add(menuInsert);
		menuBar.add(menuView);
		menuBar.add(menuUpdate);
		menuBar.add(menuDelete);
		
		setJMenuBar(menuBar);
		
		menuInsert.addMenuListener(this);
		menuView.addMenuListener(this);
		menuUpdate.addMenuListener(this);
		menuDelete.addMenuListener(this);
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuSelected(MenuEvent e) {
		if(e.getSource().equals(menuInsert)) {
			add(new Insert());
		}
		else if (e.getSource().equals(menuView)) {
			add(new View());
		}
		else if (e.getSource().equals(menuUpdate)) {
			add(new Update());
		}
		else if (e.getSource().equals(menuDelete)) {
			add(new Delete());
		}
	}

}
