package com.example.springbatch;

import com.example.springbatch.domain.Person;
import com.example.springbatch.domain.PersonRepository;
import com.example.springbatch.job.JobCompletionNotificationListener;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
class BatchConfig {
    private final PersonRepository repository;

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final Map<String, Sort.Direction> map = new HashMap<>();

    @Bean
    Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    Step step1() {
        return stepBuilderFactory.get("step1")
                .<Person, Person>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    ItemReader<Person> reader() {
        map.put("id", Sort.Direction.DESC);
        RepositoryItemReader<Person> personRepositoryItemReader = new RepositoryItemReader<>();
        personRepositoryItemReader.setRepository(repository);
        personRepositoryItemReader.setMethodName("findAll");
        personRepositoryItemReader.setSort(map);
        return personRepositoryItemReader;
    }

    /**
     * run a transformer instead of thinking run an update
     */
    @Bean
    ItemProcessor<Person, Person> processor() {
        return person -> new Person(person.getId(), person.getLastName(), person.getFirstName(), 10);
    }

    @Bean
    ItemWriter<Person> writer() {
        RepositoryItemWriter<Person> personRepositoryItemWriter = new RepositoryItemWriter<>();
        personRepositoryItemWriter.setRepository(repository);
        personRepositoryItemWriter.setMethodName("save");
        return personRepositoryItemWriter;
    }
}
