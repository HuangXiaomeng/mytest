package org.armon.test.service.multiple;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.armon.test.service.thrift.TopicService;
import org.armon.test.service.thrift.TopicServiceImpl;
import org.armon.test.service.thrift.UserService;
import org.armon.test.service.thrift.UserServiceImpl;

public class MytestMultipleServer {
    public static final int SERVER_PORT = 8091;

    public void startServer() {
        try {
            System.out.println("start server ....");

            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            processor.registerProcessor("TopicService", new TopicService.Processor<TopicService.Iface>(new TopicServiceImpl()));
            processor.registerProcessor("UserService", new UserService.Processor<UserService.Iface>(new UserServiceImpl()));

            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);

            // 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
            TServer server = new TThreadPoolServer(
                    new Args(serverTransport)
                    .processor(processor)
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
        MytestMultipleServer server = new MytestMultipleServer();
        server.startServer();
    }
}
