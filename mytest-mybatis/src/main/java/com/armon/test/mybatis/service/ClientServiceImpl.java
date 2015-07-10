package com.armon.test.mybatis.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armon.test.mybatis.dao.ClientDao;
import com.armon.test.mybatis.dao.domain.Client;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public Client queryClient(String ip, int port) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clientIp", ip);
        map.put("clientPort", port);
        return clientDao.query(map);
    }

    @Override
    public int updateClientStatus(int id, int status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("status", status);
        return clientDao.updateClientStatus(map);
    }

}
