package com.prac.springbatch.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j //로깅시스템 사용위한 어노테이션
@RequiredArgsConstructor //생성자 DI를 위한 어노테이션
@Configuration //SpringBatch의 모든 Job은 @Configuration으로 등록해서 사용
public class SimpleJobConfig {
    private final JobBuilderFactory jobBuilderFactory; //생성자 DI
    private final StepBuilderFactory stepBuilderFactory; //생성자 DI

    @Bean
    public Job simpleJob(){ //Job 은 하나의 배치 작업 단위를 얘기함. //Job안에는 하나 이상의 Step이 존재할 수 있음.
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep1())
                .build();
    }

    @Bean
    public Step simpleStep1() { //Step 안에는 Tasklet 혹은 Reader & Processeor & Writer 묶음이 존재함.
        return stepBuilderFactory.get("simpleStep1")
                //tasklet은 Step안에서 수행될 기능들을 명시함.
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>This is Step1");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
