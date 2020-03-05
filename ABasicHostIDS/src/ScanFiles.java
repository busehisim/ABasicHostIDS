// Recursive Java program to print all files 
// in a folder(and sub-folders) 
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

import javax.crypto.SecretKey;
public class ScanFiles {
	HashMap<String, String> HashMap = new HashMap<String, String>();
	String hash;
	FileCheckSumSHA sha = new FileCheckSumSHA();
	AES_Encryption aes = new AES_Encryption();		
	  
	public void ScanFiles(File[] arr,int index,int level ) throws NoSuchAlgorithmException, IOException ,Exception
     { 
		 String pathname="NULL";
		 
		
         // terminate condition 
         if(index == arr.length) {	
        	
             return; 
         } 
           
         // for files 
         if(arr[index].isFile()) {
             
         	pathname=arr[index].getAbsolutePath();
         
         	hash = sha.checksum(pathname);		//hash the file
         	String cipherText = aes.encrypt(hash, aes.secretKey); //encrypt the hash to store
         			
         	HashMap.put(pathname,cipherText); 
         }
           
         // for sub-directories 
         else if(arr[index].isDirectory()) 
         {           
             // recursion for sub-directories         
        	 ScanFiles(arr[index].listFiles(), 0, level + 1); 
         } 
               		              
         // recursion for main directory 
         ScanFiles(arr,++index, level); 
    } 
}

