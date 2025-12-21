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
		// Check if extraction-only mode is requested
		boolean extractOnly = java.util.Arrays.asList(args).contains(AppArgument.EXTRACT_ONLY.getValue());

		// Run extraction
		IDFMNetworkExtractor.extract();
		IDFMNetworkExtractor.extractShapes();

		// Exit if extraction-only mode
		if (extractOnly) {
			System.out.println("Data extraction completed. Exiting.");
			System.exit(0);
		}

		Network.getInstance();

		SpringApplication.run(IdftransportpathfinderApplication.class, args);
	}

}
