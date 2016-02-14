package org.mytest.storm.blots;

import java.util.Map;

import org.apache.storm.shade.com.google.common.collect.Maps;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class WordCounter extends BaseBasicBolt {
    private Integer id;
    private String name;
    Map<String, Integer> counters;

    @Override
    public void cleanup() {
        System.out.println("-- Word Counter ["+name+"-"+id+"] --");
        for (Map.Entry<String, Integer> entry : counters.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        this.counters = Maps.newHashMap();
        this.name = context.getThisComponentId();
        this.id = context.getThisTaskId();
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String str = input.getString(0);

        if (!counters.containsKey(str)) {
            counters.put(str, 1);
        } else {
            Integer c = counters.get(str) + 1;
            counters.put(str, c);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer arg0) {
    }

}
