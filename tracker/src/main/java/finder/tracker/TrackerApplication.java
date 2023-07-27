package finder.tracker;

import finder.tracker.service.DockerTestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class TrackerApplication {
	@Scheduled(cron = "0 * * * * *") // 스케줄링 주기 설정 (매 분)
	public void printHelloWorld() {
		System.out.println("Hello, World!");
	}

	public static void main(String[] args) {
		// SpringApplication.run(TrackerApplication.class, args);
		ApplicationContext context = SpringApplication.run(TrackerApplication.class, args);
		DockerTestService dockerTestService = context.getBean(DockerTestService.class);

		System.out.println(dockerTestService.findOne("test"));
	}
}