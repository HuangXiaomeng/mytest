package com.armon.test.mybatis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.armon.test.mybatis.dao.domain.Client;
import com.armon.test.mybatis.service.ClientService;

public class Main {

  public static void main(String[] args) {
    ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");
//    MyServer server = ac.getBean(MyServer.class);
//    ClientService service = server.getClientService();
    ClientService service = ac.getBean(ClientService.class);
    Client client = service.queryClient("10.11.128.60", 10001);
    printClient(client);
  }

  private static void printClient(Client client) {
    System.out.println("id: " + client.getId());
    System.out.println("clientkey: " + client.getClientKey());
  }
}
