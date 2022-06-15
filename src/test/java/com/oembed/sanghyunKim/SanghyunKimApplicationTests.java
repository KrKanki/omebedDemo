package com.oembed.sanghyunKim;

import ac.simons.oembed.OembedEndpoint;
import ac.simons.oembed.OembedResponse;
import ac.simons.oembed.OembedService;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class SanghyunKimApplicationTests {

	@Test
	void contextLoads() {
		final List<OembedEndpoint> endpoints = new ArrayList<>();
		OembedEndpoint endpoint;

		endpoint = new OembedEndpoint();
		endpoint.setName("youtube");
		endpoint.setFormat(OembedResponse.Format.json);
		endpoint.setMaxWidth(480);
		endpoint.setEndpoint("https://www.youtube.com/watch?v=fCJYBMCnr8k");
		endpoint.setUrlSchemes(Arrays.asList("https?://(www|de)\\.youtube\\.com/watch\\?v=.*"));
		// Optional, specialised renderer, not included here
		// endpoint.setResponseRendererClass(YoutubeRenderer.class);
		endpoints.add(endpoint);

		final OembedService oembedService = new OembedService(new DefaultHttpClient(), null, endpoints, "some-app");
		System.out.println(oembedService.embedUrls("Need some action... <a href=\"https://www.youtube.com/watch?v=fCJYBMCnr8k\">The Hoff!</a>", "https://www.youtube.com/watch?v=fCJYBMCnr8k"));

	}

}
