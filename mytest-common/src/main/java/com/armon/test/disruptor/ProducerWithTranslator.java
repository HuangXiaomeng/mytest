package com.armon.test.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;

public class ProducerWithTranslator {
	private final Disruptor<RecordEvent> disruptor;

	public ProducerWithTranslator(Disruptor<RecordEvent> disruptor) {
		this.disruptor = disruptor;
	}

	private static final EventTranslatorOneArg<RecordEvent, Record> TRANSLATOR = new EventTranslatorOneArg<RecordEvent, Record>() {

		@Override
		public void translateTo(RecordEvent event, long sequence, Record record) {
			event.setRecord(record);
		}
	};

	public void put(Record record) {
	    disruptor.publishEvent(TRANSLATOR, record);
	}
}
