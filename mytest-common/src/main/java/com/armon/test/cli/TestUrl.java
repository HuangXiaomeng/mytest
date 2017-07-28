package com.armon.test.cli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TestUrl {
    public static final String fileASplitPrefix = "/tmp/a_split_";
    public static final String fileBSplitPrefix = "/tmp/b_split_";
    public static final int splitNumber = 1000;
    public static final String outFileName = "/tmp/out.txt";

    public static void main(String[] args) throws IOException {
        String fileAName = args[0];
        String fileBName = args[1];
        //1. 首先通过hash split到1000个小文件中
        splitFile(fileAName, fileASplitPrefix);
        splitFile(fileBName, fileBSplitPrefix);
        System.out.println("step 1 finished.");

        //2. 针对每个a[i]和b[i]进行比较，写到out文件中
        for (int i = 0; i < splitNumber; i++) {
            collectCommonUrl(fileASplitPrefix + i, fileBSplitPrefix + i, outFileName);
        }

        System.out.println("finished.");
    }

    public static void splitFile(String fileName, String splitFileNamePrefix) throws IOException {
        FileReader reader = null;
        BufferedReader br = null;
        FileWriter[] writers = new FileWriter[splitNumber];
        BufferedWriter[] bws = new BufferedWriter[splitNumber];
        try {
            reader = new FileReader(fileName);
            br = new BufferedReader(reader);
            for (int i = 0; i < splitNumber; i++) {
                writers[i] = new FileWriter(splitFileNamePrefix + i, true);
                bws[i] = new BufferedWriter(writers[i]);
            }
            String str = null;
            while ((str = br.readLine()) != null) {
                int index = str.hashCode() % splitNumber;
                bws[index].write(str);
                bws[index].write("\n");
            }
        } finally {
            if (br != null) {
                br.close();
            }
            if (reader != null) {
                reader.close();
            }
            for (int i = 0; i< splitNumber; i++) {
                bws[i].close();
                writers[i].close();
            }
        }
    }

    public static void collectCommonUrl(String fileA, String fileB, String outFile) throws IOException {
        //1. 先把A文件放到hashset中
        Set<String> urlSet = new HashSet<>();
        FileReader reader = new FileReader(fileA);
        BufferedReader br = new BufferedReader(reader);
        String str = null;
        while ((str = br.readLine()) != null) {
            urlSet.add(str);
        }
        br.close();
        reader.close();

        //2. 从B中读url，判断是否在hashset中，是则写到out文件中
        reader = new FileReader(fileB);
        br = new BufferedReader(reader);
        FileWriter writer = new FileWriter(outFile, true);
        BufferedWriter bw = new BufferedWriter(writer);
        while ((str = br.readLine()) != null) {
            if (urlSet.contains(str)) {
                bw.write(str);
                bw.write("\n");
            }
        }
        br.close();
        reader.close();
        bw.close();
        writer.close();
    }

}
