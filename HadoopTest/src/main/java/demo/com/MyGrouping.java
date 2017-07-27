package demo.com;

import org.apache.hadoop.conf.Configuration;  
import org.apache.hadoop.fs.Path;  
import org.apache.hadoop.io.LongWritable;  
import org.apache.hadoop.io.Text;  
import org.apache.hadoop.io.WritableComparator;  
import org.apache.hadoop.mapreduce.Job;  
import org.apache.hadoop.mapreduce.Mapper;  
import org.apache.hadoop.mapreduce.Partitioner;  
import org.apache.hadoop.mapreduce.Reducer;  
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;  
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;  
import org.apache.hadoop.util.GenericOptionsParser;  
  
import demo.com.TextPair;  
  
public class MyGrouping {  
  
    /** 
     * Map 
     *  
     * @author Administrator 
     */  
    public static class MyGroupingMap extends Mapper<LongWritable, Text, TextPair, Text> {  
        protected void map(LongWritable key, Text value,  
                org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, TextPair, Text>.Context context)  
                throws java.io.IOException, InterruptedException {  
            String arr[] = value.toString().split("/t");  
            if (arr.length != 2) {  
                return;  
            }  
            TextPair tp = new TextPair();  
            tp.set(new Text(arr[0]), new Text(arr[1]));  
            context.write(tp, new Text(arr[1]));  
        }  
    }  
  
    /** 
     * 按照Hashcode值来进行切分 
     *  
     * @author Administrator 
     */  
    public static class MyGroupingPartition extends Partitioner<TextPair, Text> {  
        @Override  
        public int getPartition(TextPair key, Text value, int numPartitions) {  
            return (key.hashCode() & Integer.MAX_VALUE) % numPartitions;  
        }  
    }  
  
    /** 
     * group进行排序 
     *  
     * @author Administrator 
     */  
    @SuppressWarnings("unchecked")  
    public static class MyGroupingGroup extends WritableComparator {  
        //代码变动部分  
    }  
  
    /** 
     * reduce 
     *  
     * @author Administrator 
     */  
    public static class MyGroupingReduce extends Reducer<TextPair, Text, Text, Text> {  
        protected void reduce(TextPair key, java.lang.Iterable<Text> value,  
                org.apache.hadoop.mapreduce.Reducer<TextPair, Text, Text, Text>.Context context)  
                throws java.io.IOException, InterruptedException {  
            StringBuffer sb = new StringBuffer();  
            while (value.iterator().hasNext()) {  
                sb.append(value.iterator().next().toString() + "_");  
            }  
            context.write(key.getFirst(), new Text(sb.toString().substring(0, sb.toString().length() - 1)));  
        }  
    }  
  
    public static void main(String args[]) throws Exception {  
        Configuration conf = new Configuration();  
     
        @SuppressWarnings("deprecation")
		Job job = new Job(conf, "MyGrouping");  
        // 设置运行的job  
        job.setJarByClass(MyGrouping.class);  
        // 设置Map相关内容  
        job.setMapperClass(MyGroupingMap.class);  
        job.setMapOutputKeyClass(TextPair.class);  
        job.setMapOutputValueClass(Text.class);  
        job.setPartitionerClass(MyGroupingPartition.class);  
          
        job.setGroupingComparatorClass(MyGroupingGroup.class);  
          
        // 设置reduce  
        job.setReducerClass(MyGroupingReduce.class);  
        job.setOutputKeyClass(Text.class);  
        job.setOutputValueClass(Text.class);  
        // 设置输入和输出的目录  
        FileInputFormat.addInputPath(job, new Path("C:\\Users\\Pin-Wang\\Desktop\\hadoopT.txt"));  
        FileOutputFormat.setOutputPath(job, new Path("C:\\Users\\Pin-Wang\\Desktop\\hadoopTOut.txt"));  
        // 执行，直到结束就退出  
        System.exit(job.waitForCompletion(true) ? 0 : 1);  
    }  
}  