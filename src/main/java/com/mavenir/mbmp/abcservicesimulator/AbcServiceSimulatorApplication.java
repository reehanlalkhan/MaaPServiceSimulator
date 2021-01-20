package com.mavenir.mbmp.abcservicesimulator;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.mavenir.mbmp.abcservicesimulator.utils.ApplicationExitUtil;

@SpringBootApplication
public class AbcServiceSimulatorApplication {
	
	public static void main(String[] args) throws IOException {
		// SpringApplication.run(AbcServiceSimulatorApplication.class, args);
		ConfigurableApplicationContext ctx = SpringApplication.run(AbcServiceSimulatorApplication.class, args);
		ApplicationExitUtil.waitForKeyPressToCleanlyExit(ctx);
	}

}
