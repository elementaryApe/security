package com.herman.security.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * @author hsh
 * @create 2018-11-16 16:14
 **/
public class MockServer {

    public static void main(String[] args) throws Exception {
        WireMock.configureFor("127.0.0.1",8090);
        WireMock.removeAllMappings();
        mock("/order/1","01");
    }

    private static void mock(String url, String file) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("mock/response/"+file+".txt");
        String content = StringUtils.join(FileUtils.readLines(classPathResource.getFile(), "UTF-8"), "\n");
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url)).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}
