package demo.com;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyGroupingGroup extends WritableComparator {  
	protected MyGroupingGroup() {  
        super(TextPair.class, true);  
    }  
	 
     public int compare(WritableComparable a, WritableComparable b) {  
    	 TextPair mip1 = (TextPair)a;  
         TextPair mip2 = (TextPair)b;
        return mip1.getFirst().compareTo(mip2.getFirst());  
     }  
}  
