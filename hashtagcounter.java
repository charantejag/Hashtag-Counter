
import java.io.*;

public class hashtagcounter {

	
	/* This represents main class where input is read from the input file and methods are called according to the input */
	
	public static void main(String[] args) {
		
		FibonacciHeap Heap = new FibonacciHeap();
		
	      String fileName= args[0]; 
	       try{
	          
	          FileReader inputFile = new FileReader(fileName);
              BufferedReader bufferReader = new BufferedReader(inputFile);
	          String line;
	          while ((line = bufferReader.readLine()) != null)  
	          {
	            System.out.println(line);
	            char achar = line.charAt(0);
	            if(achar=='#')
	            {
	            	
	            	String[] parts = line.split(" ");
	            	
	            	String hashname = parts[0];
	            	String name = hashname.substring(1);
	            	String number = parts[1];
	                int count = Integer.parseInt(number);
	                Heap.insert(name,count);   	
	            	}
	            	
	            
	            else if(achar=='S')
	            {
	            	
	                System.exit(0);
	            }
	            else
	            {
	            	
	            	int numreq = Integer.parseInt(line);
	            	Heap.Removemax(numreq);
	            }
	          }
	          
	          bufferReader.close();
	       }catch(Exception e){
	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
	       }
	       
	
	}
	}
