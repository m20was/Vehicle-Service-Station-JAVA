package vehicleServiceStationGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JDialog
{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JLabel lblusername,lblpassword;
	private JTextField txtusername,txtpassword;
	private JButton btnLogin,btnReset;
	Login()
	{
		lblusername=new JLabel("Username");
		lblusername.setBounds(80,10,100,25);
		this.add(lblusername);
		
		txtusername=new JTextField();
		txtusername.setBounds(180, 10, 150, 25);
		this.add(txtusername);
		
		lblpassword=new JLabel("Password");
		lblpassword.setBounds(80,55,100,25);
		this.add(lblpassword);
		
		txtpassword=new JPasswordField();
		txtpassword.setBounds(180, 55, 150, 25);
		this.add(txtpassword);
		
		btnLogin=new JButton("Login");
		btnLogin.setBounds(90,100,100,25);
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnLoginActionPerformed();
			}
		});
		this.add(btnLogin);
		
		btnReset=new JButton("Reset");
		btnReset.setBounds(200, 100, 100, 25);
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtusername.setText("");
				txtpassword.setText("");
			}
		});
		this.add(btnReset);
		
		
		
		this.setLayout(null);
		this.setSize(425,180);
		this.setLocation(450, 270);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		
		
	}
	private void btnLoginActionPerformed()
	{
		if(txtusername.getText().equals("sam")&&txtpassword.getText().equals("sam"))
		{
			new ServiceStationFrame();
			this.dispose();
		}
		else
			JOptionPane.showMessageDialog(this, "Enter Valid Username & Password");
	}
}
