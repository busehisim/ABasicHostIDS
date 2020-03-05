import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

import javax.crypto.SecretKey;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ModifiedFilesCheck extends JFrame{
	HashMap<String, String> CheckHashMap = new HashMap<String, String>();
	String checkhash;
	FileCheckSumSHA sha = new FileCheckSumSHA();
	AES_Encryption aes = new AES_Encryption();
	
	public void CheckFiles(File[] arr,int index,int level) throws NoSuchAlgorithmException, IOException ,Exception
    { 
		 String pathname="NULL";
		 		
        // terminate condition 
        if(index == arr.length) {	
            return; 
        } 
          
        // for files 
        if(arr[index].isFile()) {
            
        	 pathname=arr[index].getAbsolutePath();
        
        	checkhash = sha.checksum(pathname);
        	CheckHashMap.put(pathname, checkhash);
        }
          
        // for sub-directories 
        else if(arr[index].isDirectory()) 
        {             
            // recursion for sub-directories 
            
       	 CheckFiles(arr[index].listFiles(), 0, level + 1); 
        } 
              		              
        // recursion for main directory 
        CheckFiles(arr,++index, level); 
   } 
	
	public void CompareFiles(HashMap<String, String> initial_hashMap) throws NoSuchAlgorithmException, IOException ,Exception
    { 
		int flag=0;
		String list="\n";
		for (String i : initial_hashMap.keySet()) {
			
			  	String cipherText=initial_hashMap.get(i);
	         	String decryptedText = aes.decrypt(cipherText, aes.secretKey);
	         	initial_hashMap.replace(i, initial_hashMap.get(i),decryptedText);
	         	
			  for (String j : CheckHashMap.keySet()) {

				  if(i.equals(j) && !initial_hashMap.get(i).equals(CheckHashMap.get(j)))
				  {
					  System.out.println("ALERT MODIFIED FILE!!!! --> "+i);
					  flag=1;
					  list= list + i + "\n";
					  break;
				  }
			  }
			 
			 /* if(CheckHashMap.containsValue(decryptedText))
			  {
				  System.out.println("böyle bir hash olduguna göre modife olmamýs dublicate file yoksa");
			  }
			  else
			  {
				  System.out.println("AlERT MODIFIED FILE !!!! --> "+i);
			  }*/
			 
		}
		
		if(flag==1) {
					
				  JOptionPane.showMessageDialog(this, list, "Error-List of modified files", JOptionPane.ERROR_MESSAGE);
				  
			  }
		if(flag==0)
		{
			JOptionPane.showMessageDialog(this, "THERE IS NO MODIFICATION");
			System.out.println("THERE IS NO MODIFICATION");
		}
		       
    }
}
