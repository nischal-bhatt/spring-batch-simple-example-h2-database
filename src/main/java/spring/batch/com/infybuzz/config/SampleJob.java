package spring.batch.com.infybuzz.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.batch.com.infybuzz.service.SecondTasklet;

@Configuration
public class SampleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SecondTasklet secondTasklet;

	@Bean
	public Job firstJob() {
		return this.jobBuilderFactory.get("First Job").incrementer(new RunIdIncrementer()).start(firstStep()).next(secondStep()).next(thirdStep()).build();
	}

	private Step firstStep() {
		return this.stepBuilderFactory.get("First step").tasklet(firstTask()).build();
	}

	private Step secondStep() {
		return this.stepBuilderFactory.get("Second step").tasklet(secondTask()).build();
	}

	private Step thirdStep() {
		return this.stepBuilderFactory.get("Third step").tasklet(this.secondTasklet).build();
	}

	private Tasklet firstTask() {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("this is first tasklet step");
				return RepeatStatus.FINISHED;
			}

		};
	}

	private Tasklet secondTask() {
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("this is second tasklet step");
				return RepeatStatus.FINISHED;
			}

		};
	}

}
