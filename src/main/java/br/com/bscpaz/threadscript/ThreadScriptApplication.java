package br.com.bscpaz.threadscript;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import br.com.bscpaz.threadscript.dtos.PlaintiffDto;
import br.com.bscpaz.threadscript.entities.Plaintiff;
import br.com.bscpaz.threadscript.runnables.PlaintiffRunnable;
import br.com.bscpaz.threadscript.services.PlaintiffService;

/**
 * @author Bruno Paz - (bscpaz)
 */

@SpringBootApplication
public class ThreadScriptApplication {

	private boolean isAllProcessedSucessfully = true;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private PlaintiffService plaintiffService;

	public static void main(String[] args) {
		SpringApplication.run(ThreadScriptApplication.class, args);
	}

	@PostConstruct
	private void generateLawsuitScriptsByPlaintiff() {
		List<Plaintiff> plaintiffs = plaintiffService.findAll();
		List<PlaintiffDto> plaintiffsDtos = PlaintiffDto.convert(plaintiffs);
		
		System.out.println("Total found = " + plaintiffsDtos.size());
		
		CountDownLatch countDownLatch = new CountDownLatch(plaintiffsDtos.size());
		ExecutorService executor = Executors.newFixedThreadPool(3);

		try {
			for (PlaintiffDto plaintiffDto : plaintiffsDtos) {
				PlaintiffRunnable runnable = context.getBean(PlaintiffRunnable.class);
				runnable.setPlaintiffDto(plaintiffDto);
				runnable.setCountDownLatch(countDownLatch);
				executor.execute(runnable);
			}

			//Wait for all runnable has been completed.
			countDownLatch.await();

			for (PlaintiffDto plaintiffDto : plaintiffsDtos) {
				if (!plaintiffDto.isSuccess()) {
					isAllProcessedSucessfully = false;
				}
			}

			System.out.println(String.format("\n\nAll scripts generated? [%s]\n\n", isAllProcessedSucessfully));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!executor.isShutdown()) {
				executor.shutdown();
			}
		}
	}
}
