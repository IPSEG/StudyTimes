package com.ipseg.studyTime;

import com.ipseg.studyTime.repository.MyBatisRepositoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MyBatisRepositoryConfig.class)
public class StudyTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyTimeApplication.class, args);
	}

}
