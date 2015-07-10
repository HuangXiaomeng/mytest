package com.armon.test.mybatis.service;

import com.armon.test.mybatis.dao.domain.Client;


public interface ClientService {
  Client queryClient(String ip, int port);

  int updateClientStatus(int id, int status);
}
