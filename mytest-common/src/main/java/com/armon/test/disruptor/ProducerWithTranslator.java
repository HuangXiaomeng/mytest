package com.armon.test.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class ProducerWithTranslator {
	private final RingBuffer<RecordEvent> ringBuffer;
	
	public ProducerWithTranslator(RingBuffer<RecordEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}
	
	private static final EventTranslatorOneArg<RecordEvent, Record> TRANSLATOR = new EventTranslatorOneArg<RecordEvent, Record>() {

		@Override
		public void translateTo(RecordEvent event, long sequence, Record record) {
			event.setRecord(record);
		}
	};
	
	public void put(Record record) {
		ringBuffer.publishEvent(TRANSLATOR, record);
	}
}
