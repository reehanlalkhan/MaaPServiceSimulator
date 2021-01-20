package com.mavenir.mbmp.abcservicesimulator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import com.mavenir.mbmp.abcservicesimulator.utils.constants.ABCRequestHandlerConstants;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.http.client.HttpClient;
import io.netty.channel.ChannelOption;

@Configuration
public class NbWebClientConfiguration implements ABCRequestHandlerConstants{

    @Bean
    public WebClient nbWebClient() {
        return WebClient.builder()
            .baseUrl(BM_APP_URL)
            .clientConnector(httpConnector())
            .build();
    }

    private ClientHttpConnector httpConnector() {
        return new ReactorClientHttpConnector(
            HttpClient.create(ConnectionProvider.create("maap", 500))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .doOnConnected(conn ->
                    conn.addHandlerLast(new ReadTimeoutHandler(10))
                        .addHandlerLast(new WriteTimeoutHandler(10))
                )
        );
    }
}

