package com.armon.test.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.armon.test.mybatis.service.ClientService;

@Component
public class MyServer {
  @Autowired
  private ClientService clientService;

  public ClientService getClientService() {
    return clientService;
  }

  public void setClientService(ClientService clientService) {
    this.clientService = clientService;
  }
}
