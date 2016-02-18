package com.armon.test.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newCachedThreadPool();
		int bufferSize = 1024;
		Disruptor<RecordEvent> disruptor = new Disruptor<RecordEvent>(RecordEvent.FACTORY, bufferSize, es, 
				ProducerType.MULTI, new BlockingWaitStrategy());
		final ConsumerWithWorkerHandler[] consumers = new ConsumerWithWorkerHandler[5];
		for (int i = 0; i < consumers.length; i++) {
			consumers[i] = new ConsumerWithWorkerHandler();
		}
		disruptor.handleEventsWithWorkerPool(consumers);
		disruptor.start();
		es.shutdown();
		
		RingBuffer<RecordEvent> ringBuffer = disruptor.getRingBuffer();
		ProducerWithTranslator producer = new ProducerWithTranslator(ringBuffer);
		for (int i = 0; i < 10; i++) {
			producer.put(new Record("data:" + i));
			Thread.sleep(1000);
		}
		
		disruptor.shutdown();
	}
}
