package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.border.LineBorder;

import controller.UpdateCustomerController;
import model.connection;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class CustomerListView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private connection conn;
	private ResultSet rs;
	public String selectedCustomerID;
	private JComboBox customerComboBox;
	private JButton selectButton;
	private JFrame frame;
	

	public CustomerListView() {

		components();
		populateComboBox();

	}

	public void components() {

		frame = new JFrame();

		frame.setVisible(true);

		frame.setTitle("Customer List");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 657, 535);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(CustomerListView.class.getResource("/img/logo3-removebg-preview.png")));
		lblNewLabel.setBounds(-139, 0, 580, 159);
		contentPane.add(lblNewLabel);

		JTextArea txtrCustomerList = new JTextArea();
		txtrCustomerList.setBorder(new LineBorder(new Color(189, 183, 107), 3, true));
		txtrCustomerList.setFont(new Font("Calibri", Font.BOLD, 22));
		txtrCustomerList.setForeground(new Color(255, 255, 255));
		txtrCustomerList.setBackground(new Color(0, 0, 0));
		txtrCustomerList.setText("  Customer List");
		txtrCustomerList.setBounds(438, 63, 150, 38);
		contentPane.add(txtrCustomerList);

		customerComboBox = new JComboBox();
		customerComboBox.setBounds(50, 172, 448, 38);
		contentPane.add(customerComboBox);

		selectButton = new JButton("Select");
		selectButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		selectButton.setBorder(new LineBorder(new Color(189, 183, 107), 3, true));
		selectButton.setBounds(510, 179, 97, 25);
		contentPane.add(selectButton);
		selectButton.addActionListener(this);
		selectButton.setActionCommand("select");

	}

	public void populateComboBox() {

		conn = new connection();

		String query = "Select * FROM customer";
		rs = conn.executeQuery(query);

		try {
			while (rs.next()) {
				try {
					customerComboBox.addItem(
							rs.getString("customerID") + " " + rs.getString("fName") + " " + rs.getString("lName"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			selectedCustomerID = customerComboBox.getSelectedItem().toString();
			selectedCustomerID = selectedCustomerID.split(" ")[0];

			String query = "Select * FROM customer WHERE customerID = '" + selectedCustomerID + "'";
			rs = conn.executeQuery(query);

			while (rs.next()) {
				rs.getString("fName");
				rs.getString("lName");
				rs.getString("email");
				rs.getString("cardNumber");
				rs.getString("plan");
				rs.getString("phoneNumber");

			}

		} catch (SQLException ex) {
			Logger.getLogger(CustomerListView.class.getName()).log(Level.SEVERE, null, ex);
		}

		String ac = e.getActionCommand();
		if (ac.equals("select")) {
			frame.dispose();
			 new UpdateCustomerController(selectedCustomerID);
		}

	}
}