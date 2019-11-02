import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {
	
	public static class MapForWordCount extends Mapper<LongWritable, Text, Text, IntWritable>{
		
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();
			String[] words = line.split(",");
			for(int i = 0; i < words.length; i++) {
				Text outputKey = new Text(words[i].toUpperCase().trim());
				IntWritable outputValue = new IntWritable(1);
				context.write(outputKey, outputValue);
			}
		}
	}
	
	public static class ReduceForWordCount extends Reducer<Text, IntWritable, Text, IntWritable>{
		
		public void reduce(Text word, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int sum = 0;
			for(IntWritable value : values) {
				sum = sum + value.get();
			}
			context.write(word, new IntWritable(sum));
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		String[] files = new GenericOptionsParser(configuration, args).getRemainingArgs();
		Path input = new Path(files[0]);
		Path output = new Path(files[1]);
		Job job = new Job(configuration, "wordcount");
		job.setJarByClass(WordCount.class);
		job.setMapperClass(MapForWordCount.class);
		job.setReducerClass(ReduceForWordCount.class);
		job.setOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, input);
		FileOutputFormat.setOutputPath(job, output);
		System.exit(job.waitForCompletion(true)? 0 : 1);
	}
}
