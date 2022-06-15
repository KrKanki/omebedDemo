package com.oembed.sanghyunKim;

import com.oembed.sanghyunKim.Util.OmebedUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SanghyunKimApplication {

	OmebedUtil util = new OmebedUtil();
	public static void main(String[] args) {

		SpringApplication.run(SanghyunKimApplication.class, args);

	}


}
