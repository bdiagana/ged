package com.sdz.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JOptionPane;
/***
 * 
 * @author quent
 *
 */
public class Word{

	private String word = "", secretWord = "";
	private char[] tabChar;
	private int error = 0;
	private int nbreCoup = 0;
	/***
	* Constructor of an Word objet
	*/
	public Word(){
		initWord();
	}
	/***
	* Selects a random word in the file word.txt
	*/
	public void initWord(){
		
		int i = (int)(Math.random() * 100000);
		while(i > 336529){
			i /= 2;
		}
		
		try {
			LineNumberReader fnr = new LineNumberReader(new FileReader(new File("file/word.txt")));
			int carac;
			this.word = "";
			this.secretWord= "";
			while((carac = fnr.read()) != -1){
				if(fnr.getLineNumber() == (i+1))
					break;
				
				else{
					if(fnr.getLineNumber() == i){
						this.word += (char)carac;						
					}
				}
			}
			
			this.word = this.word.trim().toUpperCase();
			
			for(int j = 0; j < this.word.length(); j++)
			{
				this.secretWord += (this.word.charAt(j) != '\'' && this.word.charAt(j) != '-') ? "*" : this.word.charAt(j);
			}
			
			fnr.close();
			this.nbreCoup = 0;
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier de mots !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur de chargement depuis le fichier de mots !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		
		this.tabChar = this.secretWord.toCharArray();
		this.error = 0;
	}
	/***
	* Check if the char is in secret word
	* @param c
	* 		character to check
	* @return -1 if it doesn't exist in the word, 1 if it does 
	*/
	public int verifyWord(char c){
		
		boolean bok = false;
		
		for(int i = 0; i < this.word.length(); i++){
			
			if(this.word.toUpperCase().charAt(i) == c){
				this.tabChar[i] = c;
				bok = true;
			}
		}
		++this.nbreCoup;
		this.secretWord = new String(this.tabChar);
		System.out.println("Mot secret = " + this.word);
		return (bok == true) ? 1 : -1;
	}
	/***
	* Check if a list of character is in secret word
	* @param tc
	* 			array of characters
	* @return -1 if it doesn't exist, 1 if it does 
	*/
	public int verifyWord(char[] tc){
		
		boolean bok = false;
		
		for(char c : tc)
		{
			for(int i = 0; i < this.word.length(); i++){
				
				if(this.word.toUpperCase().charAt(i) == c){
					this.tabChar[i] = c;
					System.out.println("OK !");
					bok = true;
				}
			}
		}	
		++this.nbreCoup;	
		this.secretWord = new String(this.tabChar);
		System.out.println("Mot secret = " + this.word);
		
		return (bok == true) ? 1 : -1;
	}
	/***
	* Check if the player found all the letters of the word
	* @return true if the player found all the letters, false if not
	*/
	public boolean isFinished(){
		
		for(char c : this.tabChar){
			if(c == '*')
				return false;
		}
		return true;
	}
	/***
	* return the word
	* @return the word
	*/
	public String getWord() {
		return word;
	}

	/***
	* return the secret word
	* @return the secret word
	*/
	public String getSecretWord() {
		return secretWord;
	}
	/***
	* return the number of error
	* @return the number of error
	*/
	public int getNombreErreur(){
		return this.error;
	}
	/***
	* set the number of error
	*/
	public void setNombreErreur(int nbre){
		this.error = nbre;
	}
	/***
	* return the number of attempt
	* @return the number of attempt
	*/
	public int getNombreCoup() {
		return nbreCoup;
	}
}

	
	
	
