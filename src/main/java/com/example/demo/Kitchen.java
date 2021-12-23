package com.example.demo;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;


@Data
@Getter
@Builder
@NoArgsConstructor
@SpringBootApplication
public class Kitchen {
    private static ArrayList<Order> orderArrayList = getOrderArrayList();
    private static ArrayList<NewCook> cooks = new ArrayList<>();



    public static ArrayList<Order> getOrderArrayList() {
        return orderArrayList;
    }

    public static ArrayList<NewCook> getCooks() {
        return cooks;
    }

    public synchronized static void main(String[] args) {

        SpringApplication.run(Kitchen.class, args);


        NewCook newCook = new NewCook(3, 4);
        NewCook newCook1 = new NewCook(2, 4);
        cooks.add(newCook);
        cooks.add(newCook1);

        Thread thread = new Thread(newCook);
        Thread thread1 = new Thread(newCook1);
        thread1.start();



    }
}
