package demo.com;

import java.io.DataInput;  
import java.io.DataOutput;  
import java.io.IOException;  
  
import org.apache.hadoop.io.Text;  
import org.apache.hadoop.io.WritableComparable;  
  
public class TextPair implements WritableComparable<TextPair> {  
  
    private Text first;  
    private Text second;  
  
    public TextPair() {  
        set(new Text(), new Text());  
    }  
  
    public void set(Text first, Text second) {  
        this.first = first;  
        this.second = second;  
    }  
  
    public Text getFirst() {  
        return first;  
    }  
  
    public Text getSecond() {  
        return second;  
    }  
  
    public void readFields(DataInput in) throws IOException {  
        first.readFields(in);  
        second.readFields(in);  
    }  
  
    
    public void write(DataOutput out) throws IOException {  
        first.write(out);  
        second.write(out);  
    }  
  
    public int compareTo(TextPair o) {  
        int cmp = first.compareTo(o.first);  
        if (cmp != 0) {  
            return cmp;  
        } else {  
            return second.compareTo(o.second);  
        }  
    }  
}  