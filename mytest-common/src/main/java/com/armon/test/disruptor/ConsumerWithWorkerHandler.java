package com.armon.test.disruptor;

import com.lmax.disruptor.WorkHandler;

public class ConsumerWithWorkerHandler implements WorkHandler<RecordEvent> {

	@Override
	public void onEvent(RecordEvent event) throws Exception {
		System.out.printf("%s: %s\n", Thread.currentThread().getName(), event.getRecord().getData());
	}

}
