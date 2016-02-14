/*
 * 蘑菇街 Inc.
 * Copyright (c) 2010-2016 All Rights Reserved.
 *
 * Author: guangming
 * Create Date: 2016年1月27日 上午9:50:53
 */

package org.mytest.storm;

import org.mytest.storm.blots.WordCounter;
import org.mytest.storm.blots.WordNormalizer;
import org.mytest.storm.spouts.WordReader;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

/**
 * @author guangming
 *
 */
public class WordCountTopologyMain {
    public static void main(String[] args) throws InterruptedException {
        // Topology definition
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader", new WordReader());
        builder.setBolt("word-normalizer", new WordNormalizer())
            .shuffleGrouping("word-reader");
        builder.setBolt("word-counter", new WordCounter(), 1)
            .fieldsGrouping("word-normalizer", new Fields("word"));

        //configuration
        Config conf = new Config();
        conf.put("wordsFile", args[0]);
        conf.setDebug(false);

        //Topology run
        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("WordCount-Topology", conf, builder.createTopology());
        Thread.sleep(10000);
        cluster.shutdown();
    }
}
