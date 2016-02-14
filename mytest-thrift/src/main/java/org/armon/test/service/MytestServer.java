package org.armon.test.service;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.armon.test.service.thrift.MytestService;
import org.armon.test.service.thrift.MytestServiceImpl;

public class MytestServer {
	public static final int SERVER_PORT = 8090;

	public void startServer() {
		try {
			System.out.println("HelloWorld TThreadPoolServer start ....");

			TProcessor tprocessor = new MytestService.Processor<MytestService.Iface>(
					new MytestServiceImpl());
			TServerSocket serverTransport = new TServerSocket(SERVER_PORT);

			// 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
			TServer server = new TThreadPoolServer(
					new Args(serverTransport)
					.processor(tprocessor)
					.protocolFactory(new TBinaryProtocol.Factory())
				);
			server.serve();

		} catch (Exception e) {
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MytestServer server = new MytestServer();
		server.startServer();
	}
}
