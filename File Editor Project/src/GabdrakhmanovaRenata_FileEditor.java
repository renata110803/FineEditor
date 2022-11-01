import java.util.*;
import java.io.*;

public class GabdrakhmanovaRenata_FileEditor {
	/*
	 * AP Prog 2
	 *  Renata Gabdrakhmanova
	 * 
	 * File Editor-
	 * loads text from the file and saves it into an array.
	 * Depending on the choices the programm will be able to find a word from the text/ 
	 * replace a word/ and have an undo feature/ and save things to another or used file
	 */

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		int choice;
		String[] lines = { "" };
		
		do {
//user input
			System.out.println("1. Load File ");
			System.out.println("2. Display Files");
			System.out.println("3. Save Files");
			System.out.println("4. Find Word ");
			System.out.println("5. Replace Word");
			System.out.println("6. Undo Last Change ");
			System.out.println("7. Exit ");
			choice = keyboard.nextInt();
			keyboard.nextLine();

			if (choice == 1) {
				System.out.println("Enter the file name");
				String fname = keyboard.nextLine();

				lines = loadFile(fname);
			}
			if (choice == 2) {
				displayFile(lines);
			}
			if (choice == 3) {
				System.out.println("Enter the file name");
				String fname = keyboard.nextLine();

				saveFile(fname, lines);
			}
			if (choice == 4) {
				System.out.println("Enter a word");
				String word = keyboard.nextLine();

				findWord(lines, word);
			}
			if (choice == 5) {
				System.out.println("Please enter the word you want to replace: ");
				String toReplace  = keyboard.nextLine();
				System.out.println("What would you like to replace it with?: ");
				String word= keyboard.nextLine();
				
				replaceAllLines( lines,  toReplace,  word);
			}
			if (choice == 6) {
				lines=copyFile(lines);
			}
			
		} while (choice != 7);

	}
/*
 * loads text from the file to an array
 */
	public static String[] loadFile(String fname) {
		int Nlines = 0;
		
		try {
			Scanner OpenFile = new Scanner(new File(fname));
			while (OpenFile.hasNextLine()) {//counts the number of lines in file
				Nlines = Nlines + 1;
				OpenFile.nextLine();
			}
			Scanner OpenFile2 = new Scanner(new File(fname));
			
			String[]  lines = new String[Nlines];//sets an array with the size of file

			for (int i = 0; i < Nlines; i++) {//inputs each line from the file into an array
				lines[i] = OpenFile2.nextLine();
			}
			
			return lines;

		} catch (FileNotFoundException e) {//if the file was not opened
			

		}
		return null;
	}
/*
 * Displays what is in an array
 */
	public static void displayFile(String[] lines) {
		for (int i = 0; i < lines.length; i++) {//prints out each line
			System.out.println(lines[i]);
		}
	}
/*
 * Saves changed or unchanged things in an array to a file
 */
	public static void saveFile(String fname, String[] lines) {
		try {
			FileWriter outFile = new FileWriter(fname);//opens file
			
			for (int i = 0; i < lines.length; i++) {//writes out each line from array to file
				outFile.write(lines[i] + "\r\n");
			}
			outFile.close();
		} catch (IOException e) {//if not opened

		}
	}
/*
 * Looks for a word in an array
 */
	public static void findWord(String[] lines, String word) {
		int Wordlen = word.length();
		String s1 = " ";
		String s2 = " ";

		for (int i = 0; i < lines.length; i++) {//checks each line for having a word
			int location = lines[i].indexOf(word);

			if (location != -1) {//if the word was not found ignore if-statement
				int Linelen = lines[i].length();
				int Word_EndLoc = Wordlen + location;

				if (location != 0)//ingnore words that start at 0
					s1 = lines[i].substring(location - 1, location);

				if (Word_EndLoc != Linelen)//ingnore words that end the same as the length of the line
					s2 = lines[i].substring(Word_EndLoc, Word_EndLoc + 1);

				if (s1.equals(" ") && s2.equals(" ") || location == 0 && s1.equals("")|| Word_EndLoc == Linelen && s2.equals(""))
					//if the word has spaces at the end and begginging/ if start at 0 and have space at the end/ if have space at begginging and end with the line
					System.out.println((i + 1) + ". " + lines[i]);
			}
		}
	}
	/*
	 * replaces all lines that contain a specific word
	 */
	public static void replaceAllLines(String[] lines, String toReplace, String word) {
		//lines=copyFile( lines);
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			lines[i] = replaceLine(line, toReplace, word);

		}
	}
/*
 * replaces a single line
 */
	public static String replaceLine(String line, String toReplace, String word) {
		int location = 0;
		int ReplaceLen = toReplace.length();
		
		while (location != -1) {
			location = line.indexOf(toReplace);
			int tot = location + ReplaceLen;

			if (location != -1) {
				String Piece1_Line = line.substring(0, location);
				String Piece2_Line = line.substring(tot);
				line = Piece1_Line + word + Piece2_Line;
			}
		}
		return line;
	}
	public static String[] copyFile(String[] lines) {
	 String[] NewLines=new String[lines.length];
		
		for(int i=0;i<lines.length;i++) {
			NewLines[i]=lines[i];
		}
		return NewLines;
	}

}
