package com.whc;

import com.whc.enums.Events;
import com.whc.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class Chapter9Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Chapter9Application.class, args);
    }

    @Autowired
    private StateMachine<States, Events> stateMachine;

    public void run(String... strings) throws Exception {
        stateMachine.start();
        stateMachine.sendEvent(Events.PAY);
        stateMachine.sendEvent(Events.RECEIVE);
    }
}
