//package com.example.demo;
//import lombok.SneakyThrows;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.web.client.RestTemplate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//
//
//import static com.example.demo.Kitchen.getCooks;
//import static com.example.demo.KitchenController.getOrderArrayList;
//
//public class Cook implements Runnable{
//
//    private Integer rank;
//    private Integer proficiency;
//    private RestTemplate restTemplate = new RestTemplate();
//    private static ArrayList<Order> orderArrayList = getOrderArrayList();
//
//
//    public Cook(Integer rank, Integer proficiency) {
//        this.rank = rank;
//        this.proficiency = proficiency;
//    }
//
//
//    @SneakyThrows
//    @Override
//    public void run() {
//
//        while (orderArrayList.isEmpty()){}
//
//        switch (rank){
//            case 3:
//                for (int i = 0; i < orderArrayList.size(); i++){
//                        if (orderArrayList.get(i).getPriority() == 5) {
//                            Order preparedOrder = prepareOrder(this.rank, this.proficiency,orderArrayList.get(i));
//                            sendOrder(preparedOrder);
//                            orderArrayList.set(orderArrayList.indexOf(orderArrayList.get(i)), new Order());
//                        }
//
//
//                }
//                break;
//            case 2:
//                for (int i = 0; i < orderArrayList.size(); i++){
//                        if (orderArrayList.get(i).getPriority() == 4 || orderArrayList.get(i).getPriority() == 3) {
//                            Order preparedOrder = prepareOrder(this.rank, this.proficiency,orderArrayList.get(i));
//                            sendOrder(preparedOrder);
//                            orderArrayList.set(orderArrayList.indexOf(orderArrayList.get(i)), new Order());
//
//                        }
//                }
//
//                break;
//            case 1:
//                for (int i = 0; i < orderArrayList.size(); i++){
//                        if (orderArrayList.get(i).getPriority() == 2 || orderArrayList.get(i).getPriority() == 1) {
//                            Order preparedOrder = prepareOrder(this.rank, this.proficiency,orderArrayList.get(i));
//                            sendOrder(preparedOrder);
//                            orderArrayList.set(orderArrayList.indexOf(orderArrayList.get(i)), new Order());
//                        }
//
//                }
//
//        }
//    }
//    public Order prepareOrder(Integer rank, Integer proficiency, Order order) throws InterruptedException {
//        ArrayList<Menu> menu = Menu.getMenu();
//        Integer[] items = order.getItems();
//        ArrayList<Integer> foods = new ArrayList<>();
//        ArrayList<Integer> foods1 = new ArrayList<>();
//
//        for(int i = 0; i< items.length; i++){
//            for (Menu item: menu) {
//                if (items[i].equals(item.getId())){
//                    if(item.getComplexity() == rank || item.getComplexity() == rank - 1){
//                        foods.add(items[i]);
//                    }
//                }else{
//                    foods1.add(items[i]);
//                }
//            }
//        }
//        Integer[] possibleItems = foods.toArray(foods.toArray(new Integer[0]));
//        Integer[] impossibleItems = foods1.toArray(foods1.toArray(new Integer[0]));
//        Integer numberOfImpossibleItems = items.length - foods.size();
//        int possibleItemsNum = possibleItems.length;
//
//        if (possibleItemsNum <= proficiency && numberOfImpossibleItems == 0){
//            System.out.println("Entered in first cond");
//            manageTechnology(order,possibleItems);
//
//
//        }else if (possibleItemsNum > proficiency && numberOfImpossibleItems == 0){
//            System.out.println("Entered in second condition");
//            Integer[] array = new Integer[proficiency];
//            Integer[] array1 = new Integer[items.length-array.length];
//            System.arraycopy(items,0,array,0,array.length);
//            System.arraycopy(items,array.length,array1,0,array1.length);
//            int count = 0;
//            for (Thread thread:getCooks()) {
//                if(!thread.isAlive()){
//                    count++;
//                    System.out.println("Accessed another thread for help");
//                }
//            }
//            switch (rank){
//                case 3:   manageTechnology(order,array);
//                break;
//                case 2:
//                case 1:
//                    if(count > 1) {manageTechnology(order,array);}
//                break;
//
//            }
//
//        } else if (possibleItemsNum == 0){
//            System.out.println("Entered in third condition");
//            int count = 0;
//            for (Thread thread:getCooks()) {
//                if(!thread.isAlive()){
//                    count++;
//                }
//            }
//            switch (rank){
//                case 3:
//                    if(count > 1) {manageTechnology(order,impossibleItems);}
//                    break;
//                case 2:
//                case 1:
//                    if(count >= 2){manageTechnology(order,impossibleItems);}
//                    break;
//
//            }
//
//        }else {
//            System.out.println("Entered in fourth condition");
//            int count = 0;
//            for (Thread thread:getCooks()) {
//                if(!thread.isAlive()){
//                    count++;
//                }
//            }
//            switch (rank){
//                case 3:
//                case 2:
//                    if(count > 1) {manageTechnology(order,possibleItems);}
//                    break;
//                case 1:
//                    if(count >= 2){manageTechnology(order,possibleItems);}
//                    break;
//
//            }
//        }
//        return order;
//    }
//
//    public static  Integer calculateTime(Integer[] array){
//        ArrayList<Menu> menu = Menu.getMenu();
//        ArrayList<Integer> time = new ArrayList<>();
//
//        for (int i = 0; i< array.length; i++){
//            for (Menu item: menu) {
//                if (item.getId().equals(array[i])){
//                    time.add(item.getPreparationTime());
//                }
//            }
//        }
//        return Collections.max(time);
//    }
//
//    public static void manageTechnology(Order order, Integer[] items) {
//
//        ArrayList<Menu> menu = Menu.getMenu();
//        int numOfStoves = 0;
//        int numOfOvens = 0;
//
//        int requiredStovesNum = 2;
//        int requiredOvenNum = 2;
//
//        for (Menu obj:Collections.synchronizedList(menu)) {
//            for (Integer item:items) {
//                if(obj.getId().equals(item)){
//                    if(obj.getCookingApparatus() == null){
//                        System.out.println("NO COOKING APPARATUS NEEDED");
//                    }else if(obj.getCookingApparatus().equals("stove")){
//                        numOfStoves++;
//                    }else if(obj.getCookingApparatus().equals("oven")){
//                        numOfOvens++;
//                    }
//                }
//            }
//        }
//        if(numOfStoves <= requiredStovesNum && numOfOvens <= requiredOvenNum){
//            //TimeUnit.SECONDS.sleep( calculateTime(items));
//            order.setCooking_time(calculateTime(items));
//
//
//        }else if(numOfStoves > requiredStovesNum || numOfOvens > requiredOvenNum){
//           //TimeUnit.SECONDS.sleep(calculateTime(items)+(calculateTime(items)/4));
//            order.setCooking_time(calculateTime(items)+(calculateTime(items)/4));
//
//
//        }else {
//            //TimeUnit.SECONDS.sleep( calculateTime(items));
//            order.setCooking_time(calculateTime(items));
//
//        }
//
//    }
//    public synchronized void sendOrder(Order order) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<Order> entity = new HttpEntity<>(order, headers);
//        restTemplate.exchange(
//                "http://localhost:8085/distribution", HttpMethod.POST, entity, Order.class).getBody();
//    }
//
//
//}
