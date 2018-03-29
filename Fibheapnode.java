
import java.util.*;

/* This represents the class which creates the Fibonacci nodes as objects */

public class Fibheapnode {
	
	Fibheapnode child, fibheapnode, left ,right, parent;    
    int element;
    int degree;
    public String name;
    
    boolean childcut;
 
    /** Constructor **/
    public Fibheapnode(int element ,String name)
    {
        this.right = this;
        this.left = this;
        this.element = element;
        this.degree=0;
        this.childcut = false;
        this.name = name;
        this.child = null;
        this.parent = null;
    } 
}
