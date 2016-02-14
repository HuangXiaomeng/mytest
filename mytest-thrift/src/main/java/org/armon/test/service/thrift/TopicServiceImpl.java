package org.armon.test.service.thrift;

import java.util.Map;

import org.apache.thrift.TException;

import com.google.common.collect.Maps;

public class TopicServiceImpl implements TopicService.Iface {
    private Map<Integer, Topic> topicMap = Maps.newConcurrentMap();

	@Override
	public void store(Topic topic) throws TException {
		int uid = topic.getUid();
		topicMap.put(uid, topic);
	}

	@Override
	public Topic retrieve(int uid) throws TException {
		return topicMap.get(uid);
	}

}
