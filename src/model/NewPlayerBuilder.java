package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import exceptions.UserBirthDateException;
import exceptions.UserEmptyFieldException;
import exceptions.UserMailException;
import exceptions.UserNicknameException;

/**
 * This class is based on the Builder Design Patter to create a new user.
 * 
 * @author Tratskas Thomas, Delatte Mélanie, Logeot Gautier, Mayeur Pierre
 *
 */
public class NewPlayerBuilder {

	private String name;
	private String firstName;
	private String mail;
	private String nickname;
	private String password;
	private String birthDate;
	
	
	public NewPlayerBuilder(){
		
	}
	
	/**
	 * This method checks if the String value passed as parameters is not empty.
	 * If the String is empty the method throws a UserEmptyFieldException, if not it creates the name field of the user.
	 * @param name
	 * @throws UserEmptyField
	 */
	public void builderName(String name) throws UserEmptyFieldException{
		if(name.length()==0){
			throw new UserEmptyFieldException();
		}
		else
		{
			this.name=name;
		}
	}
	
	/**
	 * This method checks if the String value passed as parameters is not empty.
	 * If the String is empty the method throws a UserEmptyFieldException, if not it creates the first name field of the user.
	 * @param firstName
	 * @throws UserEmptyFieldException
	 */
	public void builderFirstName(String firstName) throws UserEmptyFieldException{
		if(firstName.length()==0){
			throw new UserEmptyFieldException();
		}
		else
		{
			this.firstName=firstName;
		}
	}
	
	/**
	 * This method checks if the mail address passed as a parameters matches with the regulars expressions on the condition.
	 * If it matches the mail field is create, if not the method throws a UserMailException
	 * @param mail
	 * @throws UserMailException
	 */
	public void builderMail(String mail) throws UserMailException{
		if(Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", mail)){
			this.mail=mail;
		}
		else
		{
			throw new UserMailException();
		}
	}
	
	/**
	 * This method checks if the nickname passed as a parameters is not empty and is not already taken.
	 * If it is empty, the method throws a UserEmptyFieldException.
	 * If the nickname is already taken, the method throws the UserNicknameException.
	 * If no one of the both exception is thrown the method create the nickname field.
	 * @param nickname
	 * @throws UserNicknameException
	 * @throws UserEmptyFieldException
	 */
	public void builderNickname(String nickname) throws UserNicknameException, UserEmptyFieldException{
		if(nickname.length()==0){
			throw new UserEmptyFieldException();
		}
		if(GestionUser.getInstance().containsUserName(nickname)){
			throw new UserNicknameException();
		}
		else
		{
			this.nickname=nickname;
		}
	}
	
	/**
	 * This method checks if the password password passed as a parameter is not empty.
	 * If it is empty, the method throws a UserEmptyFieldException.
	 * If not the method creates the password field.
	 * @param password
	 * @throws UserEmptyFieldException
	 */
	public void builderPassword(String password) throws UserEmptyFieldException{
		if(password.length()==0){
			throw new UserEmptyFieldException();
		}
		else
		{
			this.password=password;
		}
	}
	
	/**
	 * This method checks if the birth date passed as a parameters is on the correct Date format.
	 * If the parameter is not on the correct format, the method throws a UserBirthDateException.
	 * If the parameter is on the correct format, the method create a birth date field.
	 * @param birthdate
	 * @throws UserBirthDateException
	 */
	public void builderBirthDate(String birthdate) throws UserBirthDateException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		
		try {
			d = sdf.parse(birthdate);
			String t = sdf.format(d);
			if(t.compareTo(birthdate) !=  0){
				throw new UserBirthDateException();
			}
			else
			{
				this.birthDate=birthdate;
			}
		} catch (ParseException e) {
			throw new UserBirthDateException();
		}
	}
	
	/**
	 * This method creates and returns a new user instance based on all the method of the class that create the fields.
	 * @return User
	 */
	public User builderUser(){
		return new User(name,firstName,false,mail,nickname,password,birthDate);
	}
}
