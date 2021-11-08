package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.dataccess.LoginDataAccess;
import model.entities.MessageException;
import model.entities.User;

import model.business.LoginBusiness; 
@SuppressWarnings("serial")
public class LoginView extends JFrame implements ActionListener {

	private JLabel lblUserName, lblPassword;
	
	private JButton buttonSubmit, buttonClean;

	private JTextField txtUserName, txtPassword;

	private JPanel panel1, panel2, panel3;
	private LoginBusiness loginBusiness;
	
	public LoginView() {

		this.initializeComponents();

		this.buildUI();
	}

	private void initializeComponents() {
		
		this.lblUserName = new JLabel("Username:   ");
		this.lblPassword = new JLabel("Password:   ");

		this.buttonSubmit = new JButton("Submit");
		this.buttonSubmit.addActionListener(this);

		this.buttonClean = new JButton("Clean");
		this.buttonClean.addActionListener(this);

		this.txtUserName = new JTextField(23);
		this.txtPassword = new JTextField(23);

		this.panel1 = new JPanel();
		this.panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.panel2 = new JPanel();
		this.panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.panel3 = new JPanel();
		this.panel3.setLayout(new FlowLayout(FlowLayout.CENTER));

	}

	private void buildUI() {

		this.panel1.add(this.lblUserName);
		this.panel1.add(this.txtUserName);
		
		this.panel2.add(this.lblPassword);
		this.panel2.add(this.txtPassword);

		this.panel3.add(this.buttonSubmit);
		this.panel3.add(this.buttonClean);

		this.getContentPane().add(panel1, BorderLayout.NORTH);
		this.getContentPane().add(panel2, BorderLayout.CENTER);
		this.getContentPane().add(panel3, BorderLayout.SOUTH);

		this.setTitle("Authentication");
		this.setBounds(350, 140, 550, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new LoginView();
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.buttonSubmit) {
			try {
				
				loginBusiness = new LoginBusiness();
				loginBusiness.setUserName(txtUserName.getText());
				loginBusiness.setPassword(txtPassword.getText());
				
				if(loginBusiness.verifyCredentials()) 
				{ 
					new LoginSuccessView(txtUserName.getText()); 
					dispose(); 
				}
		
				
				
				
			} catch (MessageException e) {
				JOptionPane.showMessageDialog (null, e.getMessage());
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog (null, e.getMessage());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog (null, e.getMessage());
			}
		} else {
			this.txtUserName.setText("");
			this.txtPassword.setText("");
		}
	}
	
	public LoginBusiness createLoginBusiness() 
	{ 
		if(this.loginBusiness == null) 
		{ 
			this.loginBusiness = new LoginBusiness(); 
			} 
		return this.loginBusiness; }
	}
	
}
