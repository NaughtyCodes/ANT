package com.ant.app;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
public class AntApplication {

	private static final Logger logger = LoggerFactory.getLogger(AntApplication.class);
	
	@Autowired
    private JobLauncher jobLauncher;
     
    @Autowired
    private Job job;
	
	public static void main(String[] args) {
		ApplicationContext ctx  = SpringApplication.run(AntApplication.class, args);
		String[] beanNameList = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNameList);
		for(String beanName : beanNameList) {
			 // logger.info("Bean Definition => "+beanName);
		}
	}
	
	 
//	@Scheduled(cron = "*/10 * * * * *")
//    public void perform() throws Exception
//    {
//        JobParameters params = new JobParametersBuilder()
//                .addString("JobID", String.valueOf(System.currentTimeMillis()))
//                .toJobParameters();
//        jobLauncher.run(job, params);
//    }

}

