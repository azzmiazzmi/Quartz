package com.example.demo;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
 

import org.quartz.JobBuilder;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		try {
			Scheduler scheduelr = StdSchedulerFactory.getDefaultScheduler();

			JobDetail job = JobBuilder.newJob(SayHelloJob.class)
					//.withIdentity("say-hello-job")
					.build();

			SimpleTrigger trigger = TriggerBuilder.newTrigger()
					//.withIdentity("say-hello-trigger")
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
					.build();

			scheduelr.scheduleJob(job, trigger);
			scheduelr.start();

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
