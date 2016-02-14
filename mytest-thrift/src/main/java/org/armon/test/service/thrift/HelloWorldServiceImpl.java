package org.armon.test.service.thrift;

import org.apache.thrift.TException;

public class HelloWorldServiceImpl implements HelloWorldService.Iface {

	@Override
	public THelloWorldResponse sayHello(THelloWorldRequest request)
			throws TException {
		THelloWorldResponse response = new THelloWorldResponse();
		TMytestStatus status = new TMytestStatus();
		String data = request.getData();
		if (data.equalsIgnoreCase("hello")) {
			status.setValue(0);
			status.setMessage("world");
		} else {
			status.setValue(1);
			status.setMessage("cant't response for " + data);
		}
		response.setStatus(status);
		return response;
	}

}
