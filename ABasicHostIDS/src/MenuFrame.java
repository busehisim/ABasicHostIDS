import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;


public class MenuFrame extends JFrame implements ActionListener {
	
		String initialhashmapPath = "C:\\Users\\Buse\\eclipse-workspace\\ABasicHostIDS\\initial_hashmap.ser"; //NEED TO CHANGE ACCORDING TO PC
		File initialhashmapdir = new File(initialhashmapPath);
		
		JButton detectButton;
		JButton scanButton ;
		public File[] files;
		ScanFrame scanframe ;
		String maindirpath;
	   
	    
		public MenuFrame() {

			super("A Basic Host IDS");
			
			setLayout(new FlowLayout());
			
			JLabel background = new JLabel(new ImageIcon());
			add(background);
			background.setLayout(new FlowLayout());
			
			scanButton = new JButton("Scanning and storing data about the files");
			scanButton.setBounds(50, 80, 80, 30);
			detectButton = new JButton("Detecting a modification");
			detectButton.setBounds(80, 80, 80, 30);
			 
			scanButton.addActionListener(this);
	        detectButton.addActionListener(this);
	 
	        //add buttons to the frame
	        add(scanButton);
	        add(detectButton);
	 		 
			
		}
		

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String action = e.getActionCommand();
			   	            
			HashMap<String, String> initial_hashMap = new HashMap<String, String>();
	            
	       
	        if (action.equals("Scanning and storing data about the files"))
	        {
	        	 scanframe = new ScanFrame();
	  	        	
	        }
	        
	       
	        if (action.equals("Detecting a modification")) {
	        	 if(initialhashmapdir.exists() && initialhashmapdir.isFile()) 	//Scan completed
	 	        { 
	 	        	System.out.println("Once scanned");
	 	        	try {
	 	        	      File myObj = new File("maindirpath.txt");//remember the scan path
	 	        	      Scanner myReader = new Scanner(myObj);
	 	        	      while (myReader.hasNextLine()) {
	 	        	    	  maindirpath = myReader.nextLine();
	 	        	    	  
	 	        	      }
	 	        	      myReader.close();
	 	        	    } catch (FileNotFoundException e2) {
	 	        	      
	 	        	    	JOptionPane.showMessageDialog(this, "An error occurred. maindirpath.txt have been deleted scan again");
	 	        	    	e2.printStackTrace();
	 	        	    }
	 	        	DetectFrame detectframe = new DetectFrame(maindirpath);
	 	        
	 	        }
	 	        else
	 	        {
	 	        	JOptionPane.showMessageDialog(this, "NEED TO SCAN FIRST!");
	 	        }
	            
	        }
			
		}
}