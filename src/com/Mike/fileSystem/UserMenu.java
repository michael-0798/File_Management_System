package com.Mike.fileSystem;

import java.io.File;
import java.util.Scanner;

/**
 * the class is used to interacts with the user. It will take the usuer's command
 * and call the methods in FileUtility class accordingly.
 * @author mikew
 * @version 1.0
 */
public class UserMenu {
	
	// the menu list which displays the options and take the choice from user
	public static void menuList() {
		
		// instantiate the FileUtility
		FileUtility fu = new FileUtility();

		// declare the input as the user's choice
		String input= new String();
		// the loop will end as long as the user picks 'Q' or 'q'
		while(!"q".equalsIgnoreCase(input)) {
			System.out.println("welcome to the file manage system, please pick 1 of the options bellow:" + "\n"
					+ "1: create a new folder;"+"\n"
					+ "2: add a new file;"+"\n"
					+ "3: delete a file" + "\n"
					+ "4: empty the folder" + "\n"
					+ "5, calculate the size of the folder" + "\n"
					+ "6, copy and paste the file"+"\n"
					+ "Q, exit the project" +"\n");
			Scanner keyboard = new Scanner(System.in);
			input = keyboard.next();
			
			// switch the user's choice
			switch(input) {
			// create folder
			case "1":
				System.out.println("you are going create a folder, pleas insert the absolute path of the folder");
				Scanner finput = new Scanner(System.in);
				String fname = finput.next();
				fu.createFolder(new File(fname));
				System.out.println();
				break;
			// create new file
			case "2":
				System.out.println("you are going add a file, pleas insert the absolute path of the file");
				Scanner finput2 = new Scanner(System.in);
				String fname2 = finput2.next();
				fu.createFile(new File(fname2));
				System.out.println();
				break;
			// delete a file
			case "3":
				System.out.println("you are going to delete a file, pleas insert the absolute path of the file");
				Scanner finput3 = new Scanner(System.in);
				String fname3 = finput3.next();
				fu.deleteFile(new File(fname3));
				System.out.println();
				break;
			// empty the folder
			case "4":
				System.out.println("you are going to empty the folder, pleas insert the absolute path of the folder");
				Scanner finput4 = new Scanner(System.in);
				String fname4 = finput4.next();
				fu.clearFolder(new File(fname4));
				System.out.println();
				break;
			
			// calculate the folder size
			case "5":
				System.out.println("you are going to calculate the size of the folder, pleas insert the absolute path of the folder");
				Scanner finput5 = new Scanner(System.in);
				String fname5 = finput5.next();
				int size = fu.calSize(new File(fname5));
				System.out.println("the size of the folder is "+size +"b");
				break;
			// copy the file
			case "6":
				System.out.println("you are going to copy a file, please insert the original file path you want to copy");
				Scanner finput6 = new Scanner(System.in);
				String fname6 = finput6.next();
				File srcFile = new File(fname6);
				System.out.println("you are going to copy a file, please insert the new file path you want to save");
				String fname7 = finput6.next();
				File desFile = new File(fname7);
				fu.copyFile(srcFile, desFile);
				System.out.println();
				break;
			case "q":
				System.out.println("the program will end");
				break;
			case "Q":
				System.out.println("the program will end");
				break;
			// invalid input
			default:
				System.out.println("invalid input, please insert your choice again");
			}
			
		}
		
	}

}
