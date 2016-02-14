package org.armon.test.service.thrift;

import java.util.Map;

import org.apache.thrift.TException;

import com.google.common.collect.Maps;

public class UserServiceImpl implements UserService.Iface {
    private Map<Integer, User> userMap = Maps.newConcurrentMap();

    @Override
    public void store(User user) throws TException {
        int uid = user.getUid();
        userMap.put(uid, user);
    }

    @Override
    public User retrieve(int uid) throws TException {
        return userMap.get(uid);
    }

}
