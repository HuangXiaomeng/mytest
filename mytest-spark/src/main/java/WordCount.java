import java.util.Arrays;
import java.util.Iterator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class WordCount {

    public static void main(String[] args) {
        String datain = "hdfs://10.17.36.145:9000/user/guangming/test/input/a.txt";
        String dataout = "hdfs://10.17.36.145:9000/user/guangming/test/output/output1";

        SparkConf sparkConf = new SparkConf().setAppName("TestWordCount")
        // .setMaster("local")
        // .set("spark.yarn.jar",
        // "hdfs://10.17.36.145:9000/user/guangming/spark-assembly-1.6.1-hadoop2.6.0.jar")
        // .set("spark.yarn.dist.files",
        // "/Users/guangming/project/mytest/mytest-spark/src/main/resources/core-site.xml,/Users/guangming/project/mytest/mytest-spark/src/main/resources/hdfs-site.xml")
        ;
        JavaSparkContext sc = new JavaSparkContext(sparkConf);

//         JavaPairRDD<String, Integer> result =
//                 sc.textFile(datain)
//                 .flatMap(line -> Arrays.asList(line.split(" ")))
//                 .mapToPair(word -> new Tuple2<String, Integer>(word, 1))
//                 .reduceByKey((x,y) -> x+y);
//         result.saveAsTextFile(dataout);

        // List<Tuple2<String, Integer>> output = result.collect();
        // for (Tuple2<?,?> tuple : output) {
        // System.out.println(tuple._1() + ": " + tuple._2());
        // }

        JavaRDD<String> textFile = sc.textFile(datain);
        JavaRDD<String> words = textFile.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) {
                return Arrays.asList(s.split(" ")).iterator();
            }
        });
        JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) {
                return new Tuple2<String, Integer>(s, 1);
            }
        });
        JavaPairRDD<String, Integer> counts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer a, Integer b) {
                return a + b;
            }
        });
        counts.saveAsTextFile(dataout);

        sc.stop();

    }

}
