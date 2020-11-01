import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;


public class Main {
	
	String flag;
	
	public static void main(String[] args) {
		
	
		
		ZipRange ranges[] = new ZipRange[4];
		
		// Sample inputs
		ranges[0] = new ZipRange(94133,94133);
		ranges[1] = new ZipRange(94200,94239);
		ranges[2] = new ZipRange(94230,94399);
		ranges[3] = new ZipRange(94600,94699);
		
		
		
		// Retrieve new ranges
		ArrayList<ZipRange> filteredRanges = filterRanges(ranges);
		
		for(ZipRange range : filteredRanges) {
			System.out.println("["+range.startingZipCode+","+range.endZipCode+"]");
		}
		
	}
	
	/**
	 * Function to merge the overlapping ranges
	 * @param ranges
	 * @return ArrayList<ZipRange>
	 */
	public static ArrayList<ZipRange> filterRanges(ZipRange[] ranges) {
		  
		// Return if nothing is there  
        if (ranges.length <= 0)  
            return null;  
    
        // Create an empty stack 
        Stack<ZipRange> stack=new Stack<>(); 
    
        // sort the ranges based on starting zipcode
        Arrays.sort(ranges,new Comparator<ZipRange>(){ 
            public int compare(ZipRange i1,ZipRange i2) 
            { 
                return i1.startingZipCode-i2.startingZipCode; 
            } 
        }); 
    
        // push the first range
        stack.push(ranges[0]);  
    
 
        for (int i = 1 ; i < ranges.length; i++)  
        {  
            // get interval from stack top  
        	ZipRange top = stack.peek();  
    
            // if end of range doesn't overlap with starting current starting zip push to stack
            if (top.endZipCode < ranges[i].startingZipCode)  
                stack.push(ranges[i]);  
    
            // update top end with current end zipcode. 
            else if (top.endZipCode < ranges[i].endZipCode)  
            {  
                top.endZipCode = ranges[i].endZipCode;  
                stack.pop();  
                stack.push(top);  
            }  
        }  
        
        // pop all items from stack and add to ArrayList.
        ArrayList<ZipRange> results = new ArrayList<>();
        while (!stack.isEmpty())  
        {  
        	results.add(stack.pop());  
            
        } 

	     return results;
	}
	


}

/**
 * 
 * @author Arjun
 * Class to store starting and ending zipcode
 *
 */

class ZipRange {
	
	public int startingZipCode;
	public int endZipCode;
	
	public ZipRange(int start , int end) {
		this.startingZipCode = start;
		this.endZipCode = end;
	}
	}

	