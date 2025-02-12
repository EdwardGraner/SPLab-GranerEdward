package ro.uvt.info.dessignpatternslab2024.app;


import ro.uvt.info.dessignpatternslab2024.models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ro.uvt.info.dessignpatternslab2024.difexample.ClientComponent;
import ro.uvt.info.dessignpatternslab2024.difexample.SingletonComponent;
import ro.uvt.info.dessignpatternslab2024.difexample.TransientComponent;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);

        System.out.println("\n=== Testing TransientComponent ===");
        // Obținem o instanță de TransientComponent (care trebuie să fie diferită de fiecare dată)
        TransientComponent transientBean1 = context.getBean(TransientComponent.class);
        transientBean1.operation();

        TransientComponent transientBean2 = context.getBean(TransientComponent.class);
        transientBean2.operation();

        System.out.println("\n=== Testing SingletonComponent ===");
        // Obținem o instanță de SingletonComponent (care trebuie să fie aceeași de fiecare dată)
        SingletonComponent singletonBean1 = context.getBean(SingletonComponent.class);
        singletonBean1.operation();

        SingletonComponent singletonBean2 = context.getBean(SingletonComponent.class);
        singletonBean2.operation();

        System.out.println("\n=== Testing ClientComponent ===");
        // Obținem o instanță de ClientComponent și verificăm cum sunt injectate dependințele
        ClientComponent client = context.getBean(ClientComponent.class);
        client.operation();

        // Obținem ClientComponent prin nume
        ClientComponent clientByName = (ClientComponent) context.getBean("clientComponent");
        clientByName.operation();
    }
}
