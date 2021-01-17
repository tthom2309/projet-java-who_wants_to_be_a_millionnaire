package model;

import java.util.ArrayList;

import java.util.List;

/**
 * this class allows to create a user
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class User implements Comparable {


		private String nom, prenom, mail, pseudo, password,birthDate;
		private boolean admin;
		private int gamePlayed, nbCorrectAnswers, maxGain, totalGain, nbGiveUp, nbWin, nbLoose, nbJokers, nbJokerSwitch, nbJoker5050, nbJokerCall;

		/**
		 * Create a new use for the application
		 * @param nom = the name of the user
		 * @param prenom = the first name
		 * @param mail
		 * @param pseudo = the login of the user, the name he choosed
		 * @param password
		 * @param birthDate
		 */
		public User(String nom, String prenom,boolean admin, String mail, String pseudo, String password, String date) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.admin=admin;
			this.mail = mail;
			this.pseudo = pseudo;
			this.password = password;
			this.birthDate=date;
			
			this.gamePlayed=0;
			this.nbCorrectAnswers=0;
			this.maxGain=0;
			this.totalGain=0;
			this.nbGiveUp=0;
			this.nbWin=0;
			this.nbLoose=0;
			this.nbJokers=0;
			this.nbJokerSwitch=0;
			this.nbJoker5050=0;
			this.nbJokerCall=0;
		}

		/**
		 * allows to create a user whatever the name and so one
		 * @param nom 
		 * @param prenom
		 * @param mail
		 * @param pseudo
		 * @param password
		 * @param birthDate
		 * @param admin = a boolean to know if the user is an admin or not
		 */
		public User(String nom, String prenom, String mail, String pseudo,
				String password, String birthDate, boolean admin,
				int gamePlayed, int nbCorrectAnswers, int maxGain,
				int totalGain, int nbGiveUp, int nbWin, int nbLoose,
				int nbJokers, int nbJokerSwitch, int nbJoker5050,
				int nbJokerCall, String gamePlayedString, String maxGString,String totalgainString) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.mail = mail;
			this.pseudo = pseudo;
			this.password = password;
			this.birthDate = birthDate;
			this.admin = admin;
			this.gamePlayed = gamePlayed;
			this.nbCorrectAnswers = nbCorrectAnswers;
			this.maxGain = maxGain;
			this.totalGain = totalGain;
			this.nbGiveUp = nbGiveUp;
			this.nbWin = nbWin;
			this.nbLoose = nbLoose;
			this.nbJokers = nbJokers;
			this.nbJokerSwitch = nbJokerSwitch;
			this.nbJoker5050 = nbJoker5050;
			this.nbJokerCall = nbJokerCall;
		}




		/**
		 * 
		 * @return the name of the user
		 */
		public String getNom() {
			return nom;
		}

		/**
		 * 
		 * @return the first name of the user
		 */
		public String getPrenom() {
			return prenom;
		}

		/**
		 * 
		 * @return the mail of the user
		 */
		public String getMail() {
			return mail;
		}

		/**
		 * 
		 * @return the pseudo of the user
		 */
		public String getPseudo() {
			return pseudo;
		}

		/**
		 * 
		 * @return the password of the user
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * 
		 * @return the birth date of the user
		 */

		public String getBirthDate() {
			return birthDate;
		}

		
		
		public boolean isAdmin() {
			return admin;
		}

		/**
		 * Displays all the informations of the user
		 */
		public String toString(){
			StringBuilder result = new StringBuilder();
			result.append(": ").append(nom).append(", ").append(prenom).append(", ").append(birthDate).append(", ").append(mail);
			return result.toString();
			
		}
		
		/**
		 * Allows to clone a user with the same informations
		 */
		public User clone(){
			return new User(nom,prenom,admin,mail, pseudo, password, birthDate);
		}

		

		/**
		 * Compare two users
		 * Allows a sorting of the list later
		 */
		@Override
		public int compareTo(Object o) {
			User u = (User) o;
			String nom = this.nom;
			String uNom = u.nom;
			return nom.compareTo(uNom);
		}

		/**
		 * 
		 * @return the total number of game played by a user
		 */
		public int getGamePlayed() {
			return gamePlayed;
		}

		/**
		 * allows to change the value of the game played
		 * @param gamePlayed
		 */
		public void setGamePlayed(int gamePlayed) {
			this.gamePlayed += gamePlayed;
		}

		/**
		 * 
		 * @return the number of correct answers
		 */
		public int getNbCorrectAnswers() {
			return nbCorrectAnswers;
		}

		/**
		 * allows to change the value of the nbcorrectanswers
		 * @param nbCorrectAnswers
		 */
		public void setNbCorrectAnswers(int nbCorrectAnswers) {
			this.nbCorrectAnswers += nbCorrectAnswers;
		}

		/**
		 * 
		 * @return the maximum gain that a user won in game
		 */
		public int getMaxGain() {
			return maxGain;
		}

		/**
		 * allows to change the value of the maximum gain
		 * @param maxGain
		 */
		public void setMaxGain(int maxGain) {
			this.maxGain = maxGain;
		}

		/**
		 * 
		 * @return the total of gains that a user could have probably won from all of his games
		 */
		public int getTotalGain() {
			return totalGain;
		}

		/**
		 * allows to change to change the value of the total gains
		 * @param totalGain
		 */
		public void setTotalGain(int totalGain) {
			this.totalGain += totalGain;
		}

		/**
		 * 
		 * @return the number of times that the user decided to give up a game
		 */
		public int getNbGiveUp() {
			return nbGiveUp;
		}
		
		/**
		 * allows to change the value of the number of give up
		 * @param nbGiveUp
		 */
		public void setNbGiveUp(int nbGiveUp) {
			this.nbGiveUp += nbGiveUp;
		}

		/**
		 * 
		 * @return the number of games won
		 */
		public int getNbWin() {
			return nbWin;
		}

		/**
		 * allows to change the value of the numbers of win
		 * @param nbWin
		 */
		public void setNbWin(int nbWin) {
			this.nbWin += nbWin;
		}

		/**
		 * 
		 * @return the number of games lost
		 */
		public int getNbLoose() {
			return nbLoose;
		}

		/**
		 * allows to change the value of the number of lost
		 * @param nbLoose
		 */
		public void setNbLoose(int nbLoose) {
			this.nbLoose += nbLoose;
		}

		/**
		 * 
		 * @return the number of jokers used
		 */
		public int getNbJokers() {
			return nbJokers;
		}

		public void setNbJokers(int nbJokers) {
			this.nbJokers += nbJokers;
		}

		/**
		 * 
		 * @return the number of jokers switch used
		 */
		public int getNbJokerSwitch() {
			return nbJokerSwitch;
		}
		
		/**
		 * allows to change the value of the number of joker switch
		 * @param nbJokerSwitch
		 */
		public void setNbJokerSwitch(int nbJokerSwitch) {
			this.nbJokerSwitch += nbJokerSwitch;
		}

		/**
		 * 
		 * @return the number of jokers 50 50 used
		 */
		public int getNbJoker5050() {
			return nbJoker5050;
		}
		
		/**
		 * allows to change the value of the number of the joker 50 50
		 * @param nbJoker5050
		 */
		public void setNbJoker5050(int nbJoker5050) {
			this.nbJoker5050 += nbJoker5050;
		}

		/**
		 * 
		 * @return the number of jokers Call a friend used
		 */
		public int getNbJokerCall() {
			return nbJokerCall;
		}

		/**
		 * allows to change the value of the number of joker Call a friend
		 * @param nbJokerCall
		 */
		public void setNbJokerCall(int nbJokerCall) {
			this.nbJokerCall = nbJokerCall;
		}

		/**
		 * allows to change the name of a user
		 * @param nom
		 */
		public void setNom(String nom) {
			this.nom = nom;
		}

		/**
		 * allows to change the first name
		 * @param prenom
		 */
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		/**
		 * allows to change the mail
		 * @param mail
		 */
		public void setMail(String mail) {
			this.mail = mail;
		}

		/**
		 * allows to change the pseudo
		 * @param pseudo
		 */
		public void setPseudo(String pseudo) {
			this.pseudo = pseudo;
		}

		/**
		 * allow to change the password
		 * @param password
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		
		/**
		 * allows to change the birthdate
		 * @param birthDate
		 */
		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
		}

		/**
		 * allows to change a normal user in an admin and an admin in a normal one
		 * @param admin
		 */
		public void setAdmin(boolean admin) {
			this.admin = admin;
			GestionUser.getInstance().notifyO();
		}
		
	}

