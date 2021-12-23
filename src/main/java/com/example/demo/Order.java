package com.example.demo;
import lombok.*;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Integer orderId;
    private int tableId;
    private int waiterId;
    private Integer[] items;
    private int priority;
    private double max_wait;
    private double cooking_time;
    private long pickUpTime;
    private final ReentrantLock lock = new ReentrantLock();
    public ReentrantLock getLock() {
        return lock;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", tableId=" + tableId +
                ", waiterId=" + waiterId +
                ", items=" + Arrays.toString(items) +
                ", priority=" + priority +
                ", max_wait=" + max_wait +
                ", cooking_time=" + cooking_time +
                ", pickUpTime=" + pickUpTime +
                '}';
    }
}
