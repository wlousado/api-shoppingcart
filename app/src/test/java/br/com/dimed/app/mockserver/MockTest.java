package br.com.dimed.app.mockserver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.logging.MockServerLogger;
import org.mockserver.socket.PortFactory;
import org.mockserver.socket.tls.KeyStoreFactory;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import javax.net.ssl.HttpsURLConnection;

public class MockTest {
    private static ClientAndServer mockServer;

    @BeforeClass
    public static void startMockServer() {
        HttpsURLConnection.setDefaultSSLSocketFactory(new KeyStoreFactory(new MockServerLogger()).sslContext().getSocketFactory());
        mockServer = ClientAndServer.startClientAndServer(PortFactory.findFreePort());
    }
    
    @AfterClass
    public static void stopMockServer() {
        mockServer.stop();
    }

    @Test
    public void testUrl(){
        mockServer
        .when(
            request()
                .withPath("/v1/cart-service/create")
                .withMethod("GET")
            )
        .respond(
            response()
                .withBody("1")
        );
    }
    
}
