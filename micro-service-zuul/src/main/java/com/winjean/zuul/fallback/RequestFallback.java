package com.winjean.zuul.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Slf4j
@Component
public class RequestFallback implements FallbackProvider {

//	@Override
//	public ClientHttpResponse fallbackResponse() {
//		return new ClientHttpResponse() {
//
//
//			@Override
//			public InputStream getBody() throws IOException {
////				String body = new Gson().toJson(new BusinessException("-10000", "服务异常，请稍后重试"));
////				return new ByteArrayInputStream(body.getBytes());
//
//				return null;
//			}
//
//			@Override
//			public HttpHeaders getHeaders() {
//				HttpHeaders headers = new HttpHeaders();
//				MediaType mt = new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8"));
//				headers.setContentType(mt);
//				return headers;
//			}
//		};
//	}

	/**
	 * api服务id，如果需要所有调用都支持回退，则return "*"或return null
	 */
	@Override
	public String getRoute() {
		return null;
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {

		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return this.getStatusCode().value();
			}

			@Override
			public String getStatusText() throws IOException {
				return this.getStatusCode().getReasonPhrase();
			}

			@Override
			public void close() {

			}

			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("fallback".getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8"));
				headers.setContentType(mediaType);
				return headers;
			}
		};
	}
}
