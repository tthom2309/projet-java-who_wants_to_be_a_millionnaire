package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import exceptions.UserBirthDateException;
import exceptions.UserEmptyFieldException;
import exceptions.UserMailException;
import exceptions.UserNicknameException;
import utility.Serialisation;
import model.CurrentUser;
import model.Deck;
import model.GestionUser;
import model.NewPlayerBuilder;
import model.SaveManage;
import model.User;
/**
 * This class allows a user to connect with his pseudo and password, or sign in
 * @author Mayeur Pierre, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class PanelConnection extends JPanel{

	private PanelLogIn panelLogin;
	private PanelSignIn panelSignIn;
	
	public PanelConnection(){
		 this.setBackground(Color.WHITE);
		 JTabbedPane jtp = new JTabbedPane();
		 Dimension dim = new Dimension(450, 450);
		 jtp.setPreferredSize(dim);
		 jtp.addTab("Log in", getPanelLogin());
		 jtp.addTab("Sign in", getPanelSignIn());
		 this.add(jtp);
	}

	/**
	 * 
	 * @return the panel where you can log in
	 */
	public PanelLogIn getPanelLogin() {
		if(panelLogin==null){
			panelLogin = new PanelLogIn();
		}
		return panelLogin;
	}

	/**
	 * 
	 * @return the panel where you can sign in
	 */
	public PanelSignIn getPanelSignIn() {
		if(panelSignIn==null){
			panelSignIn = new PanelSignIn();
		}
		return panelSignIn;
	}

	
	
}

/**
 * this class allows to log in
 * @author Mayeur Pierre, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelLogIn extends JPanel{
	private JLabel jlLogin;
	private JTextField jtLogin;
	private JLabel jlPassword;
	private JPasswordField jpPassword;
	private JButton jbLogin;
	private JButton jbExit;
	
	public PanelLogIn(){
		
		
		//temporaire
		GridLayout gl = new GridLayout(10, 2,15,5);
		this.setLayout(gl);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(getJlLogin());
		this.add(getJtLogin());
		this.add(getJlPassword());
		this.add(getJpPassword());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(getJbLogin());
		this.add(getJbExit());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		
	}

	/**
	 * 
	 * @return the jlabel of the login
	 */
	public JLabel getJlLogin() {
		if(jlLogin==null){
			jlLogin = new JLabel("Login: ");
		}
		return jlLogin;
	}

	/**
	 * 
	 * @return the jtextefield with the information of the login
	 */
	public JTextField getJtLogin() {
		if(jtLogin==null){
			jtLogin = new JTextField();
		}
		return jtLogin;
	}

	/**
	 * 
	 * @return the jlabel of the password
	 */
	public JLabel getJlPassword() {
		if(jlPassword==null){
			jlPassword = new JLabel("Password: ");
		}
		return jlPassword;
	}

	/**
	 * 
	 * @return the jtextefield with the information of the password
	 */
	public JPasswordField getJpPassword() {
		if(jpPassword==null){
			jpPassword = new JPasswordField();
		}
		return jpPassword;
	}

	/**
	 * Allows to log in
	 * @return a jbutton with an actionperformed
	 */
	public JButton getJbLogin() {
		if(jbLogin==null){
			jbLogin = new JButton("Login");
			jbLogin.setSize(new Dimension(150, 25));
			jbLogin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(GestionUser.getInstance().containsUser(getJtLogin().getText(), getJpPassword().getText())){
						CurrentUser.getInstance().setUser(GestionUser.getInstance().getUser(getJtLogin().getText(), getJpPassword().getText()));
						CurrentUser.getInstance().setAdmin(GestionUser.getInstance().getUser(getJtLogin().getText(), getJpPassword().getText()).isAdmin());
						JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelLogIn.this);
						fen.setPreferredSize(getMaximumSize());
						fen.pack();
						fen.setLocationRelativeTo(null);
						getJtLogin().setText("");
						getJpPassword().setText("");
						fen.setExtendedState(JFrame.MAXIMIZED_BOTH);
						fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Menu");
						
					}
					else
					{
						JOptionPane.showMessageDialog(PanelLogIn.this,"This user does not exist!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return jbLogin;
	}

	/**
	 * Allows to exit the application
	 * @return a jbutton with an actionperformed
	 */
	public JButton getJbExit() {
		if(jbExit==null){
			jbExit = new JButton("Exit");
			jbExit.setSize(new Dimension(150, 25));
			jbExit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelLogIn.this);
					int reponse = JOptionPane.showConfirmDialog(fen,"Do you want to exit?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (reponse==JOptionPane.YES_OPTION){
						Deck.getInstance().removeO();
						GestionUser.getInstance().removeO();
						SaveManage.getInstance().removeO();
						Serialisation.toJsonSave("save.json", SaveManage.getInstance());
						Serialisation.toJsonUsers("users.json", GestionUser.getInstance());
						Serialisation.toJsonDeck("testDeck.json", Deck.getInstance());
						System.exit(0);
					}
					
				}
			});
		}
		return jbExit;
	}
	
	
}

/**
 * this class allows to sign in
 * @author Mayeur Pierre, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelSignIn extends JPanel{
	
	private JLabel jlUsername;
	private JLabel jlPassword;
	private JLabel jlName;
	private JLabel jlFirstName;
	private JLabel jlMail;
	private JLabel jlBirthDate;
	private JLabel jlBirthDateNote;
	private JTextField jtUsername;
	private JPasswordField jpfPassword;
	private JTextField jtName;
	private JTextField jtFirstName;
	private JTextField jtMail;
	private JTextField jtBirthDate;
	private JButton jbSignIn;
	
	public PanelSignIn(){
		GroupLayout gl = new GroupLayout(this);
		this.setLayout(gl);
		
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addGap(50)
				.addGroup(gl.createParallelGroup()
						.addComponent(getJlUsername())
						.addComponent(getJtUsername(),25,25,25)
						)
				.addGroup(gl.createParallelGroup()
						.addComponent(getJlPassword())
						.addComponent(getJpfPassword(),25,25,25)
						)
				.addGroup(gl.createParallelGroup()
						.addComponent(getJlName())
						.addComponent(getJtName(),25,25,25)
						)
				.addGroup(gl.createParallelGroup()
						.addComponent(getJlFirstName())
						.addComponent(getJtFirstName(),25,25,25)
						)
				.addGroup(gl.createParallelGroup()
						.addComponent(getJlBirthDate())
						.addComponent(getJtBirthDate(),25,25,25)
						)
				.addComponent(getJlBirthDateNote())
				.addGroup(gl.createParallelGroup()
						.addComponent(getJlMail())
						.addComponent(getJtMail(),25,25,25)
						)
						.addGap(100)
				.addComponent(getJbSignIn(),25,25,25)
				);
		
		gl.setHorizontalGroup(gl.createParallelGroup()
				.addGroup(gl.createSequentialGroup()
						.addComponent(getJlUsername())
						.addComponent(getJtUsername())
						)
				.addGroup(gl.createSequentialGroup()
						.addComponent(getJlPassword())
						.addComponent(getJpfPassword())
						)
				.addGroup(gl.createSequentialGroup()
						.addComponent(getJlName())
						.addComponent(getJtName())
						)
				.addGroup(gl.createSequentialGroup()
						.addComponent(getJlFirstName())
						.addComponent(getJtFirstName())
						)
				.addGroup(gl.createSequentialGroup()
						.addComponent(getJlBirthDate())
						.addComponent(getJtBirthDate())
						)
				.addComponent(getJlBirthDateNote())
				.addGroup(gl.createSequentialGroup()
						.addComponent(getJlMail())
						.addComponent(getJtMail())
						)
				.addComponent(getJbSignIn(),150,150,150)
				);
	
		
	}

	/**
	 * 
	 * @return the jlabel username
	 */
	public JLabel getJlUsername() {
		if(jlUsername==null){
			jlUsername = new JLabel("Username: ");
		}
		return jlUsername;
	}

	/**
	 * 
	 * @return the jlabel password
	 */
	public JLabel getJlPassword() {
		if(jlPassword==null){
			jlPassword = new JLabel("Password: ");
		}
		return jlPassword;
	}

	/**
	 * 
	 * @return the jlabel name
	 */
	public JLabel getJlName() {
		if(jlName==null){
			jlName =new JLabel("Name: ");
		}
		return jlName;
	}

	/**
	 * 
	 * @return the jlabel first name
	 */
	public JLabel getJlFirstName() {
		if(jlFirstName==null){
			jlFirstName = new JLabel("First Name: ");
		}
		return jlFirstName;
	}

	/**
	 * 
	 * @return the jlabel mail
	 */
	public JLabel getJlMail() {
		if(jlMail==null){
			jlMail = new JLabel("E-mail: ");
		}
		return jlMail;
	}

	/**
	 * 
	 * @return the jlabel birthdate
	 */
	public JLabel getJlBirthDate() {
		if(jlBirthDate==null){
			jlBirthDate = new JLabel("Birth date: ");
		}
		return jlBirthDate;
	}

	/**
	 * 
	 * @return the information of the jtextfield
	 */
	public JTextField getJtUsername() {
		if(jtUsername==null){
			jtUsername = new JTextField();
		}
		return jtUsername;
	}

	/**
	 * 
	 * @return the information of the jtextfield
	 */
	public JPasswordField getJpfPassword() {
		if(jpfPassword==null){
			jpfPassword = new JPasswordField();
		}
		return jpfPassword;
	}

	/**
	 * 
	 * @return the information of the jtextfield
	 */
	public JTextField getJtName() {
		if(jtName==null){
			jtName = new JTextField();
		}
		return jtName;
	}

	/**
	 * 
	 * @return the information of the jtextfield
	 */
	public JTextField getJtFirstName() {
		if(jtFirstName==null){
			jtFirstName = new JTextField();
		}
		return jtFirstName;
	}

	/**
	 * 
	 * @return the information of the jtextfield
	 */
	public JTextField getJtMail() {
		if(jtMail==null){
			jtMail = new JTextField();
		}
		return jtMail;
	}

	/**
	 * 
	 * @return the information of the jtextfield
	 */
	public JTextField getJtBirthDate() {
		if(jtBirthDate==null){
			jtBirthDate = new JTextField();
		}
		return jtBirthDate;
	}
	
	/**
	 * if you enter a wrong date format, it prevents you
	 * @return a jlabel
	 */
	public JLabel getJlBirthDateNote() {
		if(jlBirthDateNote==null){
			jlBirthDateNote = new JLabel("(The birth date must be in the format dd/mm/yyyy)");
		}
		return jlBirthDateNote;
	}

	/**
	 * Allows to sign in
	 * @return a jbutton with an actionperformed
	 */
	public JButton getJbSignIn() {
		if(jbSignIn==null){
			jbSignIn = new JButton("Sign in");
			jbSignIn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try{
						//Design pattern builder
						NewPlayerBuilder constructor = new NewPlayerBuilder();
						constructor.builderNickname(getJtUsername().getText());
						constructor.builderPassword(getJpfPassword().getText());
						constructor.builderName(getJtName().getText());
						constructor.builderFirstName(getJtFirstName().getText());
						constructor.builderBirthDate(getJtBirthDate().getText());
						constructor.builderMail(getJtMail().getText());
						
						User user = constructor.builderUser();
						if(GestionUser.getInstance().addUser(user)){
							JOptionPane.showMessageDialog(PanelSignIn.this,"You have been registered succesfully.","Confirmation",JOptionPane.INFORMATION_MESSAGE);
							CurrentUser.getInstance().setUser(GestionUser.getInstance().getUser(getJtUsername().getText(), getJpfPassword().getText()));
							CurrentUser.getInstance().setAdmin(GestionUser.getInstance().getUser(getJtUsername().getText(), getJpfPassword().getText()).isAdmin());
							JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelSignIn.this);
							fen.setPreferredSize(getMaximumSize());
							fen.pack();
							fen.setLocationRelativeTo(null);
							getJtUsername().setText("");
							getJpfPassword().setText("");
							getJtName().setText("");
							getJtFirstName().setText("");
							getJtBirthDate().setText("");
							getJtMail().setText("");
							fen.setExtendedState(JFrame.MAXIMIZED_BOTH);
							fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Menu");
						}
					}
					catch (UserBirthDateException | UserEmptyFieldException | UserMailException | UserNicknameException e1){
						JOptionPane.showMessageDialog(PanelSignIn.this, e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return jbSignIn;
	}
	
	
	
}

