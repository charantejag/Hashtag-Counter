

import java.util.*;

import java.io.*;

/*This represents the main Fibonacci heap where an Fibheapnode object is created along with a Hashtable */

public class FibonacciHeap {
	
	
	
	public Fibheapnode root;
	HashMap<String, Fibheapnode> hm = new HashMap<>();
    
    /** Constructor **/
    public void FibonacciHeap()
    {
        root = null;
        HashMap hm = new HashMap();
    }
 
  
 
    
    
    /*Insert Method
     * Creates an object and inserts the Hashtag name and its frequency into its attributes*/ 
	
	public void insert(String name , int element)
	{
		
	 Fibheapnode node = new Fibheapnode(element,name);
	  boolean dere = hm.containsKey(name);
	  
	  
	  if(dere==false)
	  {
	  
	    node.element = element;
	    node.name = name;
	    

	    if (root != null) 
	    {
	    	
	        if(root.right==root)
	        {   
	        	
	        	node.right=root;
	        	
	        	root.right=node;
	        	
	        	node.left=root;
	        	
	        	root.left=node;
	        	
	        }
	        else
	        {	
	        
	        node.left = root;
	        node.right = root.right;
	        root.right = node;
	        node.right.left = node;
	        }
	        if (node.element > root.element) 
	        {
	            root = node;
	        }   
	    
	    }
	    else 
	    {
	        root = node;
	        
	    }   
	    hm.put(name, node);
	    
	    
	    
	  }  
	  else
	  {
		  this.Increasekey(name,element);
		  
	  }
	   
	
		
	}	
	
	
	/*Increase Key Method 
     * Whenever a Hashtag name is already present in the hash table , it increases the frequency accordingly 
     *  after which cascading cut is performed*/ 
	
	
	public void Increasekey(String name,int element)
	{   
		
		Fibheapnode tempnode = new Fibheapnode(element,name);
		tempnode.childcut=true;
		Fibheapnode tnode = null;
		tnode = hm.get(name);
		
		     int temp = tnode.element;
		     tnode.element = temp + element;
		 if(tnode.parent==null)
		 {
			 
			if (tnode.element>root.element)
			 {
				 
				 root = tnode;
				 
			 }
		 }
		 else
		 {
			 
			 if(tnode.element>tnode.parent.element)
		     {
			 while(tempnode.childcut==true)
			 {			
		     
		    	 tnode.parent.degree=tnode.parent.degree-1;   
		    	 tempnode = tnode.parent;
		    	 if(tnode.left==tnode && tnode.right==tnode)
		    	 {
		    		 
		    		 tnode.parent.child = null;
		    	 }
		    	 else
		    	 {	 
		    	 tnode.left.right = tnode.right;
		    	 tnode.right.left = tnode.left;
		    	 if(tnode.parent.child == tnode){
		    		 tnode.parent.child = tnode.right;
		    	 }
		    	 }
		    	 tnode.parent=null;
		    	 if(root.right==root)
			        {
			        	tnode.right=root;
			        	root.right=tnode;
			        	tnode.left=root;
			        	root.left=tnode;
			        }
			        else
			        {	
			        
			        	tnode.left = root;
				        tnode.right = root.right;
				        root.right = tnode;
				        tnode.right.left = tnode;
			        
			        
			        }
		    	 if(tempnode.parent==null)
		    	 {
		    		 break;
		    	 }
		    	   
		    	   if(tempnode.childcut==true){
		    		   tnode=tempnode;
		    	   }
		     }  
		    
			 tempnode.childcut = true;
		 }
		 }
	   
	    
	    
	} 
	
	/*Removemax Method 
     * Removes the max element from the heap and its Hashtag is stored  in a string
     * After the removal of max element , the other children are melded with the rest of the tree
     * The newly formed is paiwise combined to form a Fibonacci heap
     * after extracting all the required remove max elements , Max elements are reinserted into the Heap*/ 
	
	public void Removemax(int numreq)
	{
		
	   int tempval= numreq;
	   
	   Fibheapnode nextroot = null;
	   Fibheapnode orderednode = null;
	   Fibheapnode  temp = root;
	   Fibheapnode  temp1 = root;
	   String appstring = "";
	   while(numreq>=1)
	   {
		   
		   if (root != null) 
		   {
	            
	       if (root.child != null) 
	       {
	    	   Fibheapnode child = root.child;
               do {
                   child.parent = null;
                   child = child.right;
               } while (child != root.child);
               }
	          if(root.right==root)
	          {
	        	   nextroot = null;
	          }
	          else
	          {
	        	   nextroot = root.right;
	          }	  
		   }
		
		   
		    root.left.right=root.right; 
			root.right.left=root.left;

	        root.left =  root;
	        root.right = root;
	        Fibheapnode childroot = root.child;
	        root.child = null;
	       appstring+=root.name;
	       if(numreq>1)
	       {   
	       appstring+=',';
	       }
           hm.remove(root.name);
	        

         
           root = mergelist(nextroot,childroot);
           
           pairwisecombine();
           
           
        
          numreq--;
          if(orderednode==null)
          {
        	  orderednode = temp;
          }
          else
          {
          orderednode.right.left=temp;
          temp.right=orderednode.right;
          temp.left=orderednode;
          orderednode.right=temp;
          }    
          temp=root;
        
        
        }
	
     printhashtags(appstring);
	 while(tempval>=1)
	   {
		   
		   
		   insert(orderednode.name,orderednode.element);
		   
		   orderednode=orderednode.right;
		   tempval--;
		   
	   }
	   } 
	
	
	/*Pairwise Combine   
     * after the Max element is removed and melded , the structure is sent to pairwise combine methode
     * Here every heap with equal degree combine pairwise , looking for the max root element of both
     * pairwise combine is continued until there are no heaps left with same degree*/ 
	
	public void pairwisecombine()
	{
		
		HashMap<Integer, Fibheapnode> pc = new HashMap<>();
		Fibheapnode working = root;
		Fibheapnode index = root;
		Fibheapnode max = root;
		Fibheapnode tempIndex;
		 
		boolean last = false;
		do{
			working=index;			 
			tempIndex = index.right;
			if (tempIndex==max)
			{
				 last = true;
			}
			
			while(pc.containsKey(working.degree)==true)
			{	
				
			
					Fibheapnode temp = pc.get(working.degree);
					pc.remove(working.degree);
					if(temp.element>=working.element && temp!=working)
					{
						
						if(working==max)
						{
							max=max.right;
							root = max;
						}
						working.right.left=working.left;
						working.left.right=working.right;
						working.parent=temp;
						working.childcut = false;
						
						if(temp.degree==0)
						{
							temp.child = working;
							working.right=working;
							working.left=working;
						}
						else
						{

							working.right=temp.child.right;
							temp.child.right.left=working;
							temp.child.right=working;
							working.left=temp.child;
						}
						
						
						
						
						temp.child=working;
						temp.degree++;
						working=temp;
						
					}
					else if(temp.element<working.element && temp!=working)
					{
						
						if(temp==max)
						{
							max=max.right;
							root = max;
						}
						temp.right.left=temp.left;
						temp.left.right=temp.right;
						temp.parent=working;
						temp.childcut = false;
						temp.left = null;
						temp.right = null;
						if(working.degree==0)
						{
							working.child = temp;
							temp.right=temp;
							temp.left=temp;
						}
						else
						{

							temp.right=working.child.right;
							working.child.right.left=temp;
							working.child.right=temp;
							temp.left=working.child;
						}
						working.child=temp;
						working.degree++;
					}	
			}
			pc.put(working.degree,working);
			
			if(last==true)
			{
				break;
			}
			if(index!=tempIndex)
			{	
			index = tempIndex.left;
			}
			index = index.right;
		} while(index!=max);
		Fibheapnode tempvar = max;
		Fibheapnode maxval = max;
		do
		{
		    if(tempvar.element>maxval.element)
		    {
		    	maxval = tempvar;
		    }
			tempvar=tempvar.right;
			
		}while(tempvar!=max);
		
		root = maxval;
    	}
            
    
/*mergelist  
 * after the Max element is remove , its child and next element root is passed to this method
 * then both the lists are melded and root is returned accordingly*/ 
	
    public Fibheapnode mergelist(Fibheapnode a, Fibheapnode b)
    {
        
        if (a == null && b == null) 
        {
            return null;
        }
        if (a == null) {
              return b;   
        }
        if (b == null) {
            return a;
        }
        else
        {	
         Fibheapnode t1,t2;
         t1 = a.right;
         t2 = b.right;
         
         a.right=t2;
         t2.left=a;
         b.right=t1;
         t1.left=b;
        	
        	
        return a;
        }
        
    }

    /*printhashtags   
     * This method takes the appended string and prints out the list of Max elements in an output file*/
    
	public void printhashtags(String prnt)
	{

                try
              {
                  Writer w = new BufferedWriter(new FileWriter("output_file.txt",true));
                  w.write(prnt);
                  w.write("\n");
	          w.close();
		//System.out.println(" \n " + prnt);
	    }

    catch(Exception e)
   {
   System.out.println(" Error while reading file line by line : " + e.getMessage());
    }
	} 
		
}
