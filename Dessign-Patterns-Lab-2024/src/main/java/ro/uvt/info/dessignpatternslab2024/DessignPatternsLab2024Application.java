package ro.uvt.info.dessignpatternslab2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ro.uvt.info.dessignpatternslab2024.difexample.ClientComponent;
import ro.uvt.info.dessignpatternslab2024.difexample.SingletonComponent;
import ro.uvt.info.dessignpatternslab2024.difexample.TransientComponent;

@SpringBootApplication
public class DessignPatternsLab2024Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DessignPatternsLab2024Application.class, args);
        // Gets an instance of TransientComponent from the DI context
        TransientComponent transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();

        transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();

        SingletonComponent singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();

        singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();

        ClientComponent c = context.getBean(ClientComponent.class);
        c.operation();

        c = (ClientComponent)context.getBean("clientComponent");
        c.operation();
    }
}
