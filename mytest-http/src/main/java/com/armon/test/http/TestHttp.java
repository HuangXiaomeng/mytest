package com.armon.test.http;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class TestHttp {
    public static void main(String[] args) throws IllegalArgumentException, IOException {
        String ip = Inet4Address.getLocalHost().getHostAddress();
        System.out.println(ip);
        URI baseUri = UriBuilder.fromUri("http://" + ip + "/").port(8085).build();
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(new RestfulResource());

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, resourceConfig);
        server.start();
    }
}
