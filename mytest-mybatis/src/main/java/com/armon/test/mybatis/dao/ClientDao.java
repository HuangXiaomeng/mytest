package com.armon.test.mybatis.dao;

import java.util.Map;

import com.armon.test.mybatis.dao.domain.Client;

public interface ClientDao {
  Client query(Map<String, Object> map);

  int updateClientStatus(Map<String, Object> map);
}

