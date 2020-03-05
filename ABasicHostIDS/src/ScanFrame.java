import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.FlowLayout;

public class ScanFrame extends JFrame{
	  String maindirpath ;
	 JTextField enter;
	 JFrame f;
	 JButton ok;
	 static File files[];

	 public ScanFrame()  {
			 f= new JFrame("Scanning and storing data about the files");  
            JLabel scanpath;  
            scanpath=new JLabel("Enter folder path for scanning"); 
            JLabel example;  
            example=new JLabel("EX -> C:\\Users\\Buse\\Desktop\\DenemeSecurity"); 
            enter=new JTextField();
           
            scanpath.setBounds(50,25, 400,20);
            example.setBounds(50,80, 400,20);    
            enter.setBounds(50,50, 400,20); 
            ok=new JButton("OK");  
            ok.setBounds(50,100,95,30); 
            f.add(ok);
            f.add(scanpath);
            f.add(example);
            f.add(enter);
            ButtonHandler buttonHandler = new ButtonHandler();
    		    
            
            ok.addActionListener((ActionListener) buttonHandler);
         
            f.setSize(500,200);  
            f.setLayout(null);  
            f.setLocationRelativeTo(null);
            f.setVisible(true);  
            
		}
		private class ButtonHandler implements ActionListener {  
			public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();
			if (source == ok) {
		        try{  
		        	
		        	 maindirpath =enter.getText();
		        	 BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Buse\\eclipse-workspace\\ABasicHostIDS\\maindirpath.txt"));//write path to detect later the same path
		        	 writer.write(maindirpath);
		        	 writer.close();
		        	 
		        	 File maindir = new File(maindirpath); 
			           
		    	        if(maindir.exists() && maindir.isDirectory()) 
		    	        { 
		    	            // array for files and sub-directories  
		    	            // of directory pointed by maindir 
		    	            File files[] = maindir.listFiles();
		    	              
		    	            System.out.println("**********************************************"); 
		    	            System.out.println("Files from main directory : " + maindir); 
		    	            System.out.println("**********************************************"); 
		    	              
		    	            // Calling recursive method 
		    	            ScanFiles scan=new ScanFiles();
		    	            
		    	            
		    	            try {
								scan.ScanFiles(files,0,0);			//scan all files in the path and hash,encrypt save to data structure		
					        	 
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(f, "An error occurred. while scanning");
							} 
		    	            
		    	            
		    	            try
		    	            {
		    	                   FileOutputStream fos =
		    	                   new FileOutputStream("initial_hashmap.ser"); //Save HashMap at the first run (scan) so that when program shuts downs and re-run can detect modification
		    	                   ObjectOutputStream oos = new ObjectOutputStream(fos);
		    	                   oos.writeObject(scan.HashMap);
		    	                   oos.close();
		    	                   fos.close();
		    	                   System.out.printf("Serialized HashMap data is saved in hashmap.ser");
		    	            }catch(IOException ioe)
		    	             {
		    	                   ioe.printStackTrace();
		    	                   JOptionPane.showMessageDialog(f, "An error occurred while writing initial_hashmap.ser.");
		    	             }
		    	        }
		    	        
		    	        else
		    	        {
		    	        	JOptionPane.showMessageDialog(f, "Try a valid path");
		    	        }
		    	        
		        	f.dispose();
		       }
		        
		       catch(Exception ex){
		    	   System.out.println(ex);
		    	   JOptionPane.showMessageDialog(f, "maindirpath.txt has been deleted scan again.");
		    	   }  
		    }  
		}
}
}
