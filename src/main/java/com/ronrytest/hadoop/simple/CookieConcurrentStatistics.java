package com.ronrytest.hadoop.simple;

import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CookieConcurrentStatistics extends Configured implements Tool {

    public static class MyMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

        private static final IntWritable oneCount = new IntWritable(1);

        @Override
        public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
                                                                                                                   throws IOException {
            String valueStr = StringUtils.substring(value.toString(), value.toString().indexOf("[") + 1,
                                                    value.toString().indexOf("]"));
            String[] valueSplits = valueStr.split(" ");
            output.collect(new Text(valueSplits[0].substring(valueSplits[0].lastIndexOf("/") + 1)), oneCount);
        }

    }

    public static class MyReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output,
                           Reporter reporter) throws IOException {
            int totalValue = 0;
            while (values.hasNext()) {
                totalValue += values.next().get();
            }
            output.collect(key, new IntWritable(totalValue));
        }

    }

    @Override
    public int run(String[] args) throws Exception {

        if (args == null || args.length != 2) {
            throw new RuntimeException("error args");
        }

        Configuration conf = this.getConf();

        JobConf jobconf = new JobConf(conf, CookieConcurrentStatistics.class);
        jobconf.setJobName("CookieConcurrentStatistics");

        Path inPath = new Path(args[0]);
        Path outPath = new Path(args[1]);

        FileInputFormat.setInputPaths(jobconf, inPath);
        FileOutputFormat.setOutputPath(jobconf, outPath);

        jobconf.setMapperClass(MyMapper.class);
        jobconf.setReducerClass(MyReducer.class);
        jobconf.setCombinerClass(MyReducer.class);

        jobconf.setInputFormat(TextInputFormat.class); // 设置k1,v1
        jobconf.setOutputFormat(TextOutputFormat.class);

        jobconf.setOutputValueClass(IntWritable.class); // 设置v2
        jobconf.setOutputKeyClass(Text.class); // 设置k2

        JobClient.runJob(jobconf);

        return 0;
    }

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new CookieConcurrentStatistics(), args);
    }
}
