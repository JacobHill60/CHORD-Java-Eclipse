package chord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

public class Hillchord {
	
	 	private int ID2;
	    private int userNode;
	    private int initnode;
	    private int desired_key;
	    private int[] nodearr; 
	    private Random randomKeyInt;
	    private int circ;

	   
	    public Hillchord(int idSpace, int numberOfNodes) {
	        this.ID2 = idSpace;
	        this.circ = idSpace;
	        this.userNode = numberOfNodes;
	        nodeANDidspace();
	    }
	    
	    
	    public Hashtable<Integer, Integer> table(int node) {
	        int individualNode = node;
	        // Create finger table
	        Hashtable<Integer, Integer> chordTABLES = new Hashtable<Integer, Integer>();
	        int[] point = new int[circ];
	        for (int i = 0; i < point.length; i++) {
	            int trackk = (int) ((int) (individualNode) + Math.pow(2, i));
	            if (trackk > ID2) {
	                trackk = trackk - ID2;
	            }
	            point[i] = trackk;
	        }
	        
	        int k = 0;
	        for (int i = 1; k < point.length;) {
	            if (point[k] > nodearr[nodearr.length - 1]) {
	                chordTABLES.put(point[k], nodearr[0]);
	                i = 0;
	                k++;
	            }
	            else if (point[k] > nodearr[i]) {
	                i++;
	            }
	            else if (point[k] <= nodearr[i]) {
	                chordTABLES.put(point[k], nodearr[i]);
	                i = 0;
	                k++;
	            }
	        }
	       // FINGER TABLE CREATION (for each node)
	        System.out.println("Node N" + individualNode);
	        System.out.println("------------------------------------------");
	        
	        for (int i = 0; i < point.length; i++) {
	        	
	        	
	        	System.out.println("| N" + individualNode + " + " + ((int) Math.pow(2, i)) + " = "
	                    + (int) ((int) (individualNode) + Math.pow(2, i)) + "| current node:  N"
	                    + chordTABLES.get(point[i]));
	        	 
	  
	            double IDcurrent = individualNode + Math.pow(2, i);
	            System.out.println("---------------------------------------");
	            
	           
	   
	            if(IDcurrent == desired_key) {
	            	System.out.println(" ");
	            	System.out.println("|||||||||| We have located keyID "+ desired_key+" in the fingertable for Node "+individualNode+" ////////////////");
	            	System.out.println(" ");
	            }
	            else if(IDcurrent == desired_key+1 || IDcurrent == desired_key-1 && IDcurrent != desired_key){ 
	            	System.out.println(" ");
	            	System.out.println("|||||||||| We have located keyID "+ desired_key+" in the fingertable for Node "+individualNode+" ////////////////");
	            	System.out.println(" ");
	            }
	            
	        }
	        
	        return chordTABLES;
	    }
	    
	    
	   
		public void nodeANDidspace() {
	       
	        ID2 = (int) Math.pow(2, ID2);
	        
	        System.out.println("Number of Nodes: " + userNode);
	        System.out.println("Circular ID Space: " + ID2);
	        nodearr = new int[userNode]; // Node Array
	       
	        ArrayList<Integer> list = new ArrayList<Integer>();
	        for (int i = 1; i < ID2; i++) {
	            list.add(i);
	        }
	        Collections.shuffle(list);
	        for (int i = 0; i < nodearr.length; i++) {
	            nodearr[i] = list.get(i);
	        }
	        Arrays.sort(nodearr);

	        System.out.println("Nodes array: " + Arrays.toString(nodearr));
	        
	        randomKeyInt = new Random();
	        desired_key = randomKeyInt.nextInt(ID2 + 1) + 1;
	        System.out.println("Desired KEY ID: " + desired_key);
	        initnode = nodearr[1];
	        System.out.println("Initial Node " + initnode);
	
	        for (int i = 1; i < nodearr.length; i++) {
	            System.out.println("\n");
	            table(nodearr[i]);
	        }
	    }

	   

	    public static void main(String[] args) {
	        System.out.println("Enter a Circular ID Space (5 <= number <= 10)");
	        Scanner sc = new Scanner(System.in);
	        int userIDSpace;
		    
		    while (true){
		        if (sc.hasNextInt()){
		             userIDSpace = sc.nextInt(); 
		             if (userIDSpace  <= 10  && userIDSpace >= 5){ 
		            	 
		                   break;
		            }
		        }else{
		              sc.next();
		        }
		        System.out.println("Invalid Input. Enter a circular ID value between: 5<= Input <= 10");
		    }
	        
	        
	        
	        System.out.println("Enter number of nodes to be in Chord system (5 <= number <= 15)");
	        int userNumberOfNodes;
	        while(true) {
	        	if (sc.hasNextInt()){
		             userNumberOfNodes = sc.nextInt();
	        	if(userNumberOfNodes <=15 && userNumberOfNodes >=5) {
	        		break;
	        	}
	        	}else {
	        	sc.next();
	        }
	        
	        	System.out.println("Invalid Input. Enter number of Nodes in Chord: 5<= Input <= 15");
	        }
	        
	        sc.close();
	        new Hillchord(userIDSpace, userNumberOfNodes);
	        
	    }}
