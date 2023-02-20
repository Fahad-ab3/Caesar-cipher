package Project;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;



public class Caesar {

	
	private int key;

	
	public Caesar(int key) {
	this.key = key;
	}
	
	
	
	public String encrypt(File textF) throws Exception{
		Scanner myReader = new Scanner(textF);
		File cipherF = new File("Cipher.txt");
		FileWriter myWriter = new FileWriter(cipherF);
		
		cipherF.createNewFile();
		while(myReader.hasNextLine()) {
		
			String cipher = "";
			String text=myReader.nextLine();
			
//			text = text.toLowerCase();
			text = text.replaceAll("\\s", "");
			
			char c;

			for(int i=0; i < text.length(); i++) {
				
					c = text.charAt(i);
					if(c >= 65 && c <= 90) 
						c = (char)(Math.floorMod(c - 65 + key, 26) + 65);
					else if(c >= 97 && c <= 122)
						c = (char)(Math.floorMod(c - 97 + key, 26) + 97);
					else
						c = '*';
					
					cipher+= c;
			}
			myWriter.append(cipher);
		}
		myWriter.close();
		myReader.close();
		return cipherF.getAbsolutePath();
	}
	
	
	
	public String decrypt(File cipherF) throws Exception{
		Scanner myReader = new Scanner(cipherF);
		File textF = new File("text_after_deciphering.txt");
		FileWriter myWriter = new FileWriter(textF);
	
		textF.createNewFile();
		while(myReader.hasNextLine()) {
		
		
		String text = "";
		String cipher = myReader.nextLine();
		
		char c;

		for(int i=0; i < cipher.length(); i++) {
			
			c = cipher.charAt(i);
				
			if(c >= 65 && c <= 90) 
				c = (char)(Math.floorMod(c - 65 - key, 26) +65);
			else if(c >= 97 && c <= 122) 
				c = (char)(Math.floorMod(c - 97 - key, 26) +97);	
			else
				c = '*';
			
			text+= c;
		}
		myWriter.append(text);
		}
		myReader.close();
		myWriter.close();
		return textF.getAbsolutePath();
	}
	
	

	
	public static void main(String[] args) {
		
		try {
			Scanner in = new Scanner(System.in);
			System.out.println("Enter the key: (Positive key)");
			int key = in.nextInt();
			
			if(key > 0) {
			Caesar caesar = new Caesar(key);
			
			File textF = new File("text.txt");		
			textF.createNewFile();
			
			System.out.println("The absolute path for cipher file: " + caesar.encrypt(textF));
			
			File cipherF = new File("Cipher.txt");
			cipherF.createNewFile();
			
			System.out.println("The absolute path for text file after deciphering: " + caesar.decrypt(cipherF));
			}
			else
				System.out.println("You entered an invalid key value!");
			in.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		

	}

}
