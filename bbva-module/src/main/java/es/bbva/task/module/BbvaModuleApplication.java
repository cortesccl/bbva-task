package es.bbva.task.module;

import es.bbva.task.module.cron.MarketUpdateScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ExecutionException;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class BbvaModuleApplication {


	public static void main(String[] args) {
		SpringApplication.run(BbvaModuleApplication.class, args);
	}
}
