package com.ronrytest.hadoop.simple;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

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

public class WordCountJob extends Configured implements Tool {

    public static class MyMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

        private final static IntWritable one  = new IntWritable(1);
        private Text                     word = new Text();

        @Override
        public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> outputCollector,
                        Reporter reporter) throws IOException {
            if (value != null) {
                StringTokenizer st = new StringTokenizer(value.toString());
                while (st.hasMoreTokens()) {
                    word.set(st.nextToken());
                    outputCollector.collect(word, one);
                }
            }
        }

        @Override
        public void configure(JobConf job) {
            System.out.println(job.get("xxx"));
            System.out.println(job.get("yyy"));
        }
    }

    public static class MyReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        public void reduce(Text key, Iterator<IntWritable> value, OutputCollector<Text, IntWritable> outputCollector,
                           Reporter reporter) throws IOException {

            int count = 0;
            while (value.hasNext()) {
                count += value.next().get();
            }

            outputCollector.collect(key, new IntWritable(count));

        }

    }

    @Override
    public int run(String[] args) throws Exception {

        Configuration conf = getConf();

        System.out.println(conf.get("fs.default.name"));
        System.out.println(conf.get("xxx"));
        System.out.println(conf.get("dfs.data.dir"));
        System.out.println(conf.get("mapred.job.tracker"));

        JobConf jobConf = new JobConf(conf, WordCountJob.class);
        jobConf.setJobName("wordCountJob");

        Path in = new Path("hdfs://localhost:9000/user/ronry/in");
        Path out = new Path("hdfs://localhost:9000/user/ronry/out");

        FileInputFormat.setInputPaths(jobConf, in);
        FileOutputFormat.setOutputPath(jobConf, out);

        jobConf.setInputFormat(TextInputFormat.class);
        jobConf.setOutputFormat(TextOutputFormat.class);

        jobConf.setMapperClass(MyMapper.class);
        // jobConf.setReducerClass(MyReducer.class);

        jobConf.setOutputKeyClass(Text.class);
        jobConf.setOutputValueClass(IntWritable.class);

        JobClient.runJob(jobConf);

        return 0;
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new Configuration(), new WordCountJob(), args);
    }

}
