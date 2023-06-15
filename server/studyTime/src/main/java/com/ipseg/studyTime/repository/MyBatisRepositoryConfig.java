package com.ipseg.studyTime.repository;

import com.ipseg.studyTime.api.comment.mapper.CommentMapper;
import com.ipseg.studyTime.api.timer.mapper.TimerMapper;
import com.ipseg.studyTime.repository.comment.CommentRepository;
import com.ipseg.studyTime.repository.comment.mybatis.MyBatisCommentRepositoryImpl;
import com.ipseg.studyTime.repository.timer.TimerRepository;
import com.ipseg.studyTime.repository.timer.mybatis.MyBatisTimerRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링빈을 수동으로 등록한다. 왜냐고? 나중에 바꿀수있으니까.
@Configuration
@RequiredArgsConstructor
public class MyBatisRepositoryConfig {

    private final CommentMapper commentMapper;

    private final TimerMapper timerMapper;

    @Bean
    public CommentRepository commentRepository() {
        return new MyBatisCommentRepositoryImpl(commentMapper);
    }

    @Bean
    public TimerRepository timerRepository() {
        return new MyBatisTimerRepositoryImpl(timerMapper);
    }





}
