package utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import model.Deck;
import model.GestionUser;
import model.SaveManage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serialisation {


	
	/**
	 * This method converts a deck instance to a json file
	 * @param String
	 * @param Deck
	 */
	public static void toJsonDeck(String nomFichier,Deck deck){
		Gson gson = new GsonBuilder().create();
		PrintWriter pw = null;
		
		try{
			pw = new PrintWriter(nomFichier);
			pw.write(gson.toJson(deck));
		}catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			
			if(pw!=null)
			{
				pw.close();
			}
		}
	}
	

	
	/**
	 * This method converts a json file to a deck instance
	 * @param String
	 * @return Deck
	 */
	public static Deck fromJsonDeck(String nomFichier){
		Gson gson = new GsonBuilder().create();
		Deck deck=null;
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader(nomFichier));
			 deck = gson.fromJson(br, Deck.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		finally{
			
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return deck;
	}
	
	
	/**
	 * This method converts a GestionUser instance to a json file
	 * @param String
	 * @param GestionUser
	 */
	public static void toJsonUsers(String nomF,GestionUser users){
		Gson gson = new GsonBuilder().create();
		PrintWriter pw = null;
		
		try{
			pw = new PrintWriter(nomF);
			pw.write(gson.toJson(users));
		}catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			
			if(pw!=null)
			{
				pw.close();
			}
		}
	
	}
	
	/**
	 * This method converts a json file to a GestionUser instance
	 * @param String
	 * @return GestionUser
	 */
	public static GestionUser fromJsonUsers(String nomF){
		Gson gson = new GsonBuilder().create();
		GestionUser users=null;
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader(nomF));
			 users = gson.fromJson(br, GestionUser.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		finally{
			
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return users;
	}
	
	
	/**
	 * This method converts a Deck instance to a json file
	 * @param String
	 * @param SaveManage
	 */
	public static void toJsonSave(String nomF,SaveManage save){
		Gson gson = new GsonBuilder().create();
		PrintWriter pw = null;
		
		try{
			pw = new PrintWriter(nomF);
			pw.write(gson.toJson(save));
		}catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			
			if(pw!=null)
			{
				pw.close();
			}
		}
	
	}
	
	/**
	 * This method converts a json file to a SaveManage instance
	 * @param String
	 * @return SaveManage
	 */
	public static SaveManage fromJsonSave(String nomF){
		Gson gson = new GsonBuilder().create();
		SaveManage save=null;
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader(nomF));
			 save = gson.fromJson(br, SaveManage.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		finally{
			
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return save;
	}
}
