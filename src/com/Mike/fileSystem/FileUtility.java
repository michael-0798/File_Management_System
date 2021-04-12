package com.Mike.fileSystem;

import static java.lang.System.in;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * the class includes the functions which are used for the file management system.
 * The function includes create the folder, add the file, remove the file, 
 * empty the folder, calculate the size of the folder, and copy and paste the file
 * @author mikew
 * @version 1.0
 */
public class FileUtility {
	
	/**
	 * the function is used to create the new folder if not exists 
	 * @param f1 the folder to be created
	 */
	public void createFolder(File f1) {
		// check if the folder already exists
		if(!f1.exists()) {
			// if not, create the new folder
			System.out.println(f1+ " not exists, new folder created successfully now");
			f1.mkdirs();
		}else {
			System.out.println(f1 + " already existed, you don't have to create the folder again");
		}
	}
	
	/**
	 * the function is used to create the new file, 
	 * @param file   the file want to be created
	 * It will catch IO exception in the process when the new file is created. 
	 */
	public void createFile(File file) {
		
		if(file.exists()) {
			System.out.println(file + " already exists, please check. No file is created at this step");
		}else {
			try {
				file.createNewFile(); 
				System.out.println(file + " is created successfully");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * delete the specific file
	 * @param file the file to be deleted
	 * @return if the file is deleted, return true; else return false;
	 */
	public boolean deleteFile(File file) {
		// declare the boolean flag
		boolean flag = false;

		// identify if the file exists
		if(!file.exists()) {
			System.out.println(file.getName()+ " not exists");
		}else {
			// identify if the file is a directory
			if(!file.isFile()) {
				System.out.println(file.getName()+" is a directory");
			}else {
				// delete the file and switch the boolean flag value
				file.delete();
				System.out.println(file.getName()+" is deleted successfuly");
				flag = true;
			}
		}
		return flag;
	}
	
	
	/**
	 * clear the folder and delete all the files/folders inside.
	 * @param folder  the folder needs to be deleted
	 */
	public void clearFolder(File folder) {
		// if the folder exists
		if(folder.exists()) {
			// output the warning if the parameter is not a valid folder
			if(!folder.isDirectory()) {
				System.out.println(folder.getName()+ " is not a valid folder");
			}else {
				// get all the files under the folder
				File[] fileList = folder.listFiles();
				
				// iterate through the file list
				for(File file: fileList) {
					// delete the files
					if(file.isFile()) {
						file.delete();
						// if there's 2nd level folder, recursively call claerFolder()
					}else {
						clearFolder(file);
					}
				}
				
				// when all files and folders under the directory have been deleted
				folder.delete();
				System.out.println(folder.getName() + " has been deleted");
				}
			}else {
			System.out.println(folder.getName()+" does not exist");
		}
	}

	/**
	 * to calculate the sum size of the specific folder
	 * @param folder	the folder to be calculated the sum value of size
	 * @return -1, if the parameter is not a valid folder; or any positive value 
	 * representing the total size of the folder
	 */
	public int calSize(File folder) {
		// declare and initial the size of the folder
		int size = 0;
		
		// output the warning if the parameter is not a valid folder
		if(!folder.isDirectory()) {
			System.out.println(folder.getName()+ " is not a valid folder");
			return -1;
		}
		
		// get all the files inside the folder
		File[] fileList = folder.listFiles();
		
		// if the folder does not exist, output the information
		if(!folder.exists()) {
			System.out.println(folder.getName()+" does not exist");
		}else {
			// if the folder exists, iterate through the file list
			for(File file: fileList) {
				// if file is not a directory, accumulate the size
				if(file.isFile()) {
					size+= file.length();
				}else {
					// if file is a directory, recursively call the calSize() and accumulate the size
					size += calSize(file);
				}
			}
		}
		
		// return the total value
		return size;
	}
	

	/**
	 * copy the original file and save the new file
	 * @param originFile   the original file to be copied
	 * @param destFile   the new file to be saved as
	 */
	public void copyFile(File originFile, File destFile)  {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			// create the stream of input and output
			bis = new BufferedInputStream(new FileInputStream(originFile));
			bos = new BufferedOutputStream(new FileOutputStream(destFile));
			
			// declare the byte array to take the value from the input stream
			byte [] buff = new byte [10];
			// declare the length of the array
			int len = 0;
			
			// read the input from the original file and then write the output to the target file
			while((len = (bis.read(buff)))!=-1) {
				bos.write(buff, 0, len);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// close the stream
			try {
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
