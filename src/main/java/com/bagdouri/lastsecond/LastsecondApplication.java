package com.bagdouri.lastsecond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LastsecondApplication {

	public static void main(String[] args) {
		System.out.print(123);
		SpringApplication.run(LastsecondApplication.class, args);
	}
/*server.ssl.key-store-type=PKCS12
server.ssl.key-store=classpath:keystore/baeldung.p12
server.ssl.key-store-password=javazxc
server.ssl.key-alias=baeldung
server.ssl.enabled=true
trust.store=classpath:keystore/baeldung.p12
trust.store.password=javazxc
security.basic.enabled=true                                                     1
security.basic.realm=Spring Cloud Data Flow*/
}
