package org.armon.test.service;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.armon.test.service.thrift.MytestService;
import org.armon.test.service.thrift.THelloWorldRequest;
import org.armon.test.service.thrift.THelloWorldResponse;

public class MytestClient {
	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 8090;
	public static final int TIMEOUT = 30000;

	/**
	 *
	 * @param userName
	 */
	public void startClient() {
		TTransport transport = null;
		try {
			transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
			transport.open();
			System.out.println("connect to server");

			// 协议要和服务端一致
			TProtocol protocol = new TBinaryProtocol(transport);
			// TProtocol protocol = new TCompactProtocol(transport);
			// TProtocol protocol = new TJSONProtocol(transport);
			MytestService.Client client = new MytestService.Client(protocol);

			String[] dataList = { "hello", "hello1", "HELLO", "hello2" };
			for (String data : dataList) {
				System.out.println("==========");
				System.out.println("say: " + data);
				THelloWorldRequest request = new THelloWorldRequest();
				request.setData(data);
				THelloWorldResponse response = client.sayHello(request);
				System.out.println("response: "
						+ response.getStatus().getValue() + ", "
						+ response.getStatus().getMessage());
				System.out.println("==========");
				System.out.println();
				System.out.println();
			}
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
			System.out.println("close connection.");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MytestClient client = new MytestClient();
		client.startClient();
	}
}
