package spring_demo.component;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring_demo.measure.Task;
import spring_demo.support.DBTemplate;

/**
 * @DESC
 * @AUTHOR wangbing
 * @DATE 2019-04-25
 */
@Component
@Order(10000)
public class Executor {
    private static final Logger log = LoggerFactory.getLogger(Executor.class);
    private static int i = 0;

    @Autowired
    @Qualifier("t1")
    protected DBTemplate t1Db;

    @Autowired
    protected ApplicationContext context;

    @Scheduled(cron = "*/10 * * * * *")
    public void execute() {
        Task task = context.getBean(Task.class);
        task.calc();
        log.debug("Execute task: "+ (i ++));
    }

}
