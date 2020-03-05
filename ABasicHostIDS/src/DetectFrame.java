import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;

import javax.swing.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.FlowLayout;
import java.security.KeyStore;
import java.security.KeyStore.SecretKeyEntry;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
public class DetectFrame extends JFrame{
	
	HashMap<String, String> initial_hashMap = new HashMap<String, String>();
	JFrame f;
	
	
	public DetectFrame(String maindirpath) {
		 f= new JFrame("Detection Modification");  
		
		File maindir = new File(maindirpath); 
        
        if(maindir.exists() && maindir.isDirectory()) 
        { 
	            File files[] = maindir.listFiles();
	            ModifiedFilesCheck checkfiles = new ModifiedFilesCheck();
	        try {
				checkfiles.CheckFiles(files,0,0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	   
	        HashMap<String, String> map = null;
	        try
	        {
	           FileInputStream fis = new FileInputStream("initial_hashmap.ser"); //get the scanned HashMap
	           ObjectInputStream ois = new ObjectInputStream(fis);
	           map = (HashMap) ois.readObject();
	           ois.close();
	           fis.close();
	        }catch(IOException ioe)
	        {
	           ioe.printStackTrace();
	           JOptionPane.showMessageDialog(f, "initial_hashmap.ser has been deleted scan again");
	           return;
	        }catch(ClassNotFoundException c)
	        {
	           System.out.println("Class not found");
	           c.printStackTrace();
	           return;
	        }
	        System.out.println("Deserialized HashMap..");
	        // Display content using Iterator
	        String key;
	        String value;
	        Set set = map.entrySet();
	        Iterator iterator = set.iterator();
	       
	        while(iterator.hasNext()) {
	        	
	           HashMap.Entry mentry = (HashMap.Entry)iterator.next();
	           key=(String) mentry.getKey();
	           value =(String) mentry.getValue();
	           initial_hashMap.put(key, value);
	          
	        }
	        
	        try {	            
				checkfiles.CompareFiles(initial_hashMap); //compares the hash of file in the ser. and the current files hashes
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        else
        {
        	JOptionPane.showMessageDialog(f, "Error occured , directory do not exists.");
        }
	}
}