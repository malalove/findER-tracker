package finder.tracker;

import finder.tracker.service.DockerTestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TrackerApplication {
	public static void main(String[] args) {
		// SpringApplication.run(TrackerApplication.class, args);
		ApplicationContext context = SpringApplication.run(TrackerApplication.class, args);
		DockerTestService dockerTestService = context.getBean(DockerTestService.class);

		System.out.println(dockerTestService.findOne("test"));
	}
}