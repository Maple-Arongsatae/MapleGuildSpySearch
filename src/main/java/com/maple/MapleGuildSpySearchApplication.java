package com.maple;

import com.maple.global.util.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
public class MapleGuildSpySearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(MapleGuildSpySearchApplication.class, args);
	}
}
