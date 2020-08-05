package com.myretail.util;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Component
public class WebClientUtil {
//    public WebClient getWebClient()
//    {
//        HttpClient httpClient = HttpClient.create()
//                .tcpConfiguration(client ->
//                        client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
//                                .doOnConnected(conn -> conn
//                                        .addHandlerLast(new ChannelHandler(10))
//                                        .addHandlerLast(new WriteTimeoutHandler(10))));
//
//        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
//
//        return WebClient.builder()
//                .baseUrl("http://localhost:3000").
//                .clientConnector(connector)
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .build();
//    }
}
