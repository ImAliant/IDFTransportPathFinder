package com.diamant.idftransportpathfinder;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.diamant.idftransportpathfinder.idfm.IDFMNetworkExtractor;
import com.diamant.idftransportpathfinder.model.Network;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class IdftransportpathfinderApplication {

	public static void main(String[] args) throws IOException {
		//IDFMNetworkExtractor.extract();

		Network.getInstance();

		SpringApplication.run(IdftransportpathfinderApplication.class, args);
	}

}
