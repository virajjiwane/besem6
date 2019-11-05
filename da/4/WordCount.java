import java.util.*;
import java.io.*;
import org.apache.hadoop.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

public class WordCount {
	
	public static class Map extends Mapper<Object, Text, Text, IntWritable>{

		@Override
		protected void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			StringTokenizer values = new StringTokenizer(value.toString());
			while(values.hasMoreTokens()) {
				context.write(new Text(values.nextToken()), new IntWritable(1));
			}
		}
		
		
	}
	
	public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable>{

		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			int sum = 0;
			for(IntWritable value : values) {
				sum+=value.get();
			}
			context.write(key, new IntWritable(sum));
		}
		
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		Job job = new Job(conf,"Count word");
		job.setJarByClass(WordCount.class);
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.waitForCompletion(true);		

	}

}
