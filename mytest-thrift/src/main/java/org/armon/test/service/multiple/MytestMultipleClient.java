package org.armon.test.service.multiple;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.armon.test.service.thrift.Topic;
import org.armon.test.service.thrift.TopicService;
import org.armon.test.service.thrift.User;
import org.armon.test.service.thrift.UserService;

public class MytestMultipleClient {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8091;
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

            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            TMultiplexedProtocol topicProtocol = new TMultiplexedProtocol(protocol, "TopicService");
            TopicService.Client topicClient = new TopicService.Client(topicProtocol);
            TMultiplexedProtocol userProtocol = new TMultiplexedProtocol(protocol, "UserService");
            UserService.Client userClient = new UserService.Client(userProtocol);

            topicClient.store(new Topic(1, "topic1", "helloworld"));
            userClient.store(new User(1, "Jack"));

            System.out.println(topicClient.retrieve(1));
            System.out.println(userClient.retrieve(1));
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
        MytestMultipleClient client = new MytestMultipleClient();
        client.startClient();
    }
}
