package br.nagualcode.kitchenorders;

import org.springframework.boot.SpringApplication;

public class TestKitchenordersApplication {

	public static void main(String[] args) {
		SpringApplication.from(KitchenordersApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
