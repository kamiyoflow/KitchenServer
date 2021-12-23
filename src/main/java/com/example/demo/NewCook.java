package com.example.demo;
import lombok.SneakyThrows;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.demo.KitchenController.getOrderArrayList;
import static com.example.demo.KitchenController.getList1;
import static com.example.demo.KitchenController.getList2;
import static com.example.demo.KitchenController.getList3;
import static com.example.demo.KitchenController.getList4;
import static com.example.demo.KitchenController.getList5;
import static com.example.demo.Kitchen.getCooks;

public class NewCook implements Runnable {

    private Integer rank;
    private Integer proficiency;


    private static ArrayList<Order> orderArrayList = getOrderArrayList();
    private static ArrayList<Order> list1 = getList1();
    private static ArrayList<Order> list2 = getList2();
    private static ArrayList<Order> list3 = getList3();
    private static ArrayList<Order> list4 = getList4();
    private static ArrayList<Order> list5 = getList5();

    private ArrayList<Integer> impossibleCooking5 = new ArrayList<>();
    private ArrayList<Integer> impossibleCookingList43 = new ArrayList<>();
    private ArrayList<Integer> getImpossibleCookingList21 = new ArrayList<>();

    public ArrayList<Integer> getImpossibleCooking5() {
        return impossibleCooking5;
    }

    public void setImpossibleCooking5(ArrayList<Integer> impossibleCooking5) {
        this.impossibleCooking5 = impossibleCooking5;
    }

    public ArrayList<Integer> getImpossibleCookingList43() {
        return impossibleCookingList43;
    }

    public void setImpossibleCookingList43(ArrayList<Integer> impossibleCookingList43) {
        this.impossibleCookingList43 = impossibleCookingList43;
    }

    public ArrayList<Integer> getGetImpossibleCookingList21() {
        return getImpossibleCookingList21;
    }

    public void setGetImpossibleCookingList21(ArrayList<Integer> getImpossibleCookingList21) {
        this.getImpossibleCookingList21 = getImpossibleCookingList21;
    }

    public NewCook(Integer rank, Integer proficiency) {
        this.rank = rank;
        this.proficiency = proficiency;


    }

    @SneakyThrows
    @Override
    public void run() {


        KitchenController.structureByPriority(orderArrayList);
        System.out.println("Aceasta este lista mea1 " + list1);
        System.out.println("Aceasta este lista mea2 " + list2);
        System.out.println("Aceasta este lista mea3 " + list3);
        System.out.println("Aceasta este lista mea4 " + list4);
        System.out.println("Aceasta este lista mea5 " + list5);
        System.out.println("---------------------------------------------------------------------------------------");
        prepareOrder();

    }


    public synchronized void prepareOrder() {


        ArrayList<Menu> menu = Menu.getMenu();

        ArrayList<Order> mergedList1 = (ArrayList<Order>) Stream.of(list1, list2)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());

        ArrayList<Order> mergedList2 = (ArrayList<Order>) Stream.of(list3, list4)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());

        ArrayList<Integer> impossibleCooking5 = new ArrayList<>();
        ArrayList<Integer> possibleCooking5 = new ArrayList<>();


        ArrayList<Integer> impossibleCooking43 = new ArrayList<>();

        ArrayList<Integer> possibleCooking43 = new ArrayList<>();

        ArrayList<Integer> impossibleCooking21 = new ArrayList<>();

        ArrayList<Integer> possibleCooking21 = new ArrayList<>();


        switch (rank) {
            case 3:
                if (!list5.isEmpty()) {
                    for (int i = 0; i < list5.size(); i++) {
                        Integer[] foodItems = list5.get(i).getItems();

                        for (int k = 0; k < menu.size(); k++) {
                            for (int j = 0; j < foodItems.length; j++) {
                                if (foodItems[j] == menu.get(k).getId()) {
                                    if (menu.get(k).getComplexity() == rank || menu.get(k).getComplexity() == rank - 1) {
                                        possibleCooking5.add(foodItems[j]);
                                        foodItems[j] = 0;


                                    } else {
                                        impossibleCooking5.add(foodItems[j]);
                                        foodItems[j] = 0;
                                        setImpossibleCooking5(impossibleCooking5);
                                        list5.get(i).setCooking_time(cookImpossibleFoodForRank3(impossibleCooking5));
                                    }
                                }
                            }
                        }

                        if (impossibleCooking5.isEmpty()) {
                            list5.get(i).setCooking_time(cookPossibleFood(possibleCooking5));
                        } else {
                            list5.get(i).setCooking_time(cookImpossibleFoodForRank3(impossibleCooking5) + cookPossibleFood(possibleCooking5));
                        }

                    }

                }
                break;
            case 2:
                if (!mergedList2.isEmpty()) {
                    for (int i = 0; i < mergedList2.size(); i++) {
                        Integer[] foodItems = mergedList2.get(i).getItems();
                        for (int k = 0; k < menu.size(); k++) {
                            for (int j = 0; j < foodItems.length; j++) {
                                if (foodItems[j] == menu.get(k).getId()) {
                                    if (menu.get(k).getComplexity() == rank || menu.get(k).getComplexity() == rank - 1) {
                                        possibleCooking43.add(foodItems[j]);
                                        foodItems[j] = 0;
                                        mergedList2.get(i).setCooking_time(cookPossibleFood(possibleCooking43));
                                    } else {
                                        impossibleCooking43.add(foodItems[j]);
                                        foodItems[j] = 0;
                                        setImpossibleCookingList43(impossibleCooking43);
                                    }
                                }
                            }
                        }
                        if (impossibleCooking43.isEmpty()) {
                            mergedList2.get(i).setCooking_time(cookPossibleFood(possibleCooking43));
                        } else {
                            mergedList2.get(i).setCooking_time(cookImpossibleFoodForRank21(impossibleCooking43) + cookPossibleFood(possibleCooking43));
                        }

                    }
                }
                break;
            case 1:
                if (!mergedList1.isEmpty()) {
                    for (int i = 0; i < mergedList1.size(); i++) {
                        Integer[] foodItems = mergedList1.get(i).getItems();
                        for (int k = 0; k < menu.size(); k++) {
                            for (int j = 0; j < foodItems.length; j++) {
                                if (foodItems[j] == menu.get(k).getId()) {
                                    if (menu.get(k).getComplexity() == rank) {
                                        possibleCooking21.add(foodItems[j]);
                                        foodItems[j] = 0;
                                        mergedList1.get(i).setCooking_time(cookPossibleFood(possibleCooking21));
                                    } else {
                                        impossibleCooking21.add(foodItems[j]);
                                        foodItems[j] = 0;
                                        setGetImpossibleCookingList21(impossibleCooking21);
                                    }
                                }
                            }
                        }
                        if (impossibleCooking21.isEmpty()) {
                            list5.get(i).setCooking_time(cookPossibleFood(possibleCooking21));
                        } else {
                            list5.get(i).setCooking_time(cookImpossibleFoodForRank21(impossibleCooking21) + cookPossibleFood(possibleCooking21));
                        }
                    }
                }

        }

        System.out.println("Orders for another cuc5: " + impossibleCooking5);
        System.out.println("Prepared orders5: " + possibleCooking5);
        System.out.println("Orders for another cuc4-3 : " + impossibleCooking43);
        System.out.println("Prepared orders4-3 : " + possibleCooking43);


    }

    public synchronized int cookPossibleFood(ArrayList<Integer> list) {


        Integer[] array = list.toArray(new Integer[0]);

        ArrayList<Menu> menu = Menu.getMenu();
        ArrayList<Integer> foodPreparingTimes1 = new ArrayList<>();
        ArrayList<Integer> foodPreparingTimes2 = new ArrayList<>();
        int firstMaxTime;
        int secondMaxTime;
        int totalTime = 0;

        if (array.length > proficiency) {

            System.out.println("am intrat in conditie");

            Integer[] firstArray = new Integer[proficiency];
            Integer[] secondArray = new Integer[array.length - proficiency];
            System.arraycopy(array, 0, firstArray, 0, firstArray.length);
            System.arraycopy(array, firstArray.length, secondArray, 0, secondArray.length);

            System.out.println("Array1 " + Arrays.toString(firstArray));
            System.out.println("Array 2 " + Arrays.toString(secondArray));


            for (int i = 0; i < firstArray.length; i++) {
                for (int k = 0; k < menu.size(); k++) {
                    if (firstArray[i] == menu.get(k).getId()) {
                        foodPreparingTimes1.add(menu.get(k).getPreparationTime());
                    }
                }
            }

            firstMaxTime = Collections.max(foodPreparingTimes1);

            for (int i = 0; i < secondArray.length; i++) {
                for (int k = 0; k < menu.size(); k++) {
                    if (secondArray[i] == menu.get(k).getId()) {
                        foodPreparingTimes2.add(menu.get(k).getPreparationTime());
                    }

                }
            }

            secondMaxTime = Collections.max(foodPreparingTimes2);
            totalTime = firstMaxTime + secondMaxTime;
            System.out.println("Timpul total de preparare: " + totalTime);
        } else if (array.length == proficiency) {
            System.out.println("cazul 2");
            Integer[] firstArray = new Integer[proficiency];
            System.arraycopy(array, 0, firstArray, 0, firstArray.length);


            for (int i = 0; i < firstArray.length; i++) {
                for (int k = 0; k < menu.size(); k++) {
                    if (firstArray[i] == menu.get(k).getId()) {
                        foodPreparingTimes1.add(menu.get(k).getPreparationTime());
                    }
                }
            }

            firstMaxTime = Collections.max(foodPreparingTimes1);
            totalTime = firstMaxTime;
            System.out.println("Timpul total de preparare: " + totalTime);


        } else if (array.length < proficiency) {
            System.out.println("cazul 3");
            for (int i = 0; i < array.length; i++) {
                for (int k = 0; k < menu.size(); k++) {
                    if (array[i] == menu.get(k).getId()) {
                        foodPreparingTimes1.add(menu.get(k).getPreparationTime());

                    }
                }
            }

            totalTime = Collections.max(foodPreparingTimes1);
            System.out.println("Timpul total de preparare: " + totalTime);
        }
        return totalTime;
    }

    public int cookImpossibleFoodForRank3(ArrayList<Integer> list1) {

        Integer[] array = list1.toArray(new Integer[0]);
        ArrayList<Menu> menu = Menu.getMenu();
        ArrayList<Integer> foodPreparingTimes1 = new ArrayList<>();
        ArrayList<Integer> foodPreparingTimes2 = new ArrayList<>();
        int firstMaxTime;
        int secondMaxTime;
        int totalTime = 0;


        for (NewCook cook : getCooks()) {
            if (cook.rank == 2 || cook.rank == 1) {
                ArrayList<Integer> list = getImpossibleCooking5();

                if (list.size() > cook.proficiency) {
                    System.out.println("IMPOSIBLE  am intrat in conditie");

                    Integer[] firstArray = new Integer[proficiency];
                    Integer[] secondArray = new Integer[array.length - proficiency];
                    System.arraycopy(array, 0, firstArray, 0, firstArray.length);
                    System.arraycopy(array, firstArray.length, secondArray, 0, secondArray.length);

                    System.out.println("Array1 " + Arrays.toString(firstArray));
                    System.out.println("Array 2 " + Arrays.toString(secondArray));


                    for (int i = 0; i < firstArray.length; i++) {
                        for (int k = 0; k < menu.size(); k++) {
                            if (firstArray[i] == menu.get(k).getId()) {
                                foodPreparingTimes1.add(menu.get(k).getPreparationTime());
                            }
                        }
                    }

                    firstMaxTime = Collections.max(foodPreparingTimes1);

                    for (int i = 0; i < secondArray.length; i++) {
                        for (int k = 0; k < menu.size(); k++) {
                            if (secondArray[i] == menu.get(k).getId()) {
                                foodPreparingTimes2.add(menu.get(k).getPreparationTime());
                            }

                        }
                    }

                    secondMaxTime = Collections.max(foodPreparingTimes2);
                    totalTime = firstMaxTime + secondMaxTime;
                    System.out.println(" IMPOSIBLE Timpul total de preparare: " + totalTime);
                } else if (list.size() == proficiency) {
                    System.out.println(" IMPOSIBLE cazul 2");
                    Integer[] firstArray = new Integer[proficiency];
                    System.arraycopy(array, 0, firstArray, 0, firstArray.length);


                    for (int i = 0; i < firstArray.length; i++) {
                        for (int k = 0; k < menu.size(); k++) {
                            if (firstArray[i] == menu.get(k).getId()) {
                                foodPreparingTimes1.add(menu.get(k).getPreparationTime());
                            }
                        }
                    }

                    firstMaxTime = Collections.max(foodPreparingTimes1);
                    totalTime = firstMaxTime;
                    System.out.println("IMPOSIBLE Timpul total de preparare: " + totalTime);
                } else {
                    System.out.println(" IMPOSIBLE cazul 3");
                    for (int i = 0; i < array.length; i++) {
                        for (int k = 0; k < menu.size(); k++) {
                            if (array[i] == menu.get(k).getId()) {
                                foodPreparingTimes1.add(menu.get(k).getPreparationTime());

                            }
                        }
                    }

                    totalTime = Collections.max(foodPreparingTimes1);
                    System.out.println(" IMPOSIBLE Timpul total de preparare: " + totalTime);
                }

            }
        }

        return totalTime;

    }

    public int cookImpossibleFoodForRank21(ArrayList<Integer> list1) {

        Integer[] array = list1.toArray(new Integer[0]);
        ArrayList<Menu> menu = Menu.getMenu();
        ArrayList<Integer> foodPreparingTimes1 = new ArrayList<>();
        ArrayList<Integer> foodPreparingTimes2 = new ArrayList<>();
        int firstMaxTime;
        int secondMaxTime;
        int totalTime = 0;


        for (NewCook cook : getCooks()) {
            if (cook.rank == 3) {
                ArrayList<Integer> mergedList = (ArrayList<Integer>) Stream.of(getImpossibleCookingList21, getImpossibleCookingList43())
                        .flatMap(x -> x.stream())
                        .collect(Collectors.toList());

                ArrayList<Integer> list = mergedList;

                if (array.length > cook.proficiency) {
                    System.out.println("IMPOSIBLE  am intrat in conditie");

                    Integer[] firstArray = new Integer[proficiency];
                    Integer[] secondArray = new Integer[array.length - proficiency];
                    System.arraycopy(array, 0, firstArray, 0, firstArray.length);
                    System.arraycopy(array, firstArray.length, secondArray, 0, secondArray.length);

                    System.out.println("Array1 " + Arrays.toString(firstArray));
                    System.out.println("Array 2 " + Arrays.toString(secondArray));


                    for (int i = 0; i < firstArray.length; i++) {
                        for (int k = 0; k < menu.size(); k++) {
                            if (firstArray[i] == menu.get(k).getId()) {
                                foodPreparingTimes1.add(menu.get(k).getPreparationTime());
                            }
                        }
                    }

                    firstMaxTime = Collections.max(foodPreparingTimes1);

                    for (int i = 0; i < secondArray.length; i++) {
                        for (int k = 0; k < menu.size(); k++) {
                            if (secondArray[i] == menu.get(k).getId()) {
                                foodPreparingTimes2.add(menu.get(k).getPreparationTime());
                            }

                        }
                    }

                    secondMaxTime = Collections.max(foodPreparingTimes2);
                    totalTime = firstMaxTime + secondMaxTime;
                    System.out.println(" IMPOSIBLE Timpul total de preparare: " + totalTime);
                } else if (array.length == proficiency) {
                    System.out.println(" IMPOSIBLE cazul 2");
                    Integer[] firstArray = new Integer[proficiency];
                    System.arraycopy(array, 0, firstArray, 0, firstArray.length);


                    for (int i = 0; i < firstArray.length; i++) {
                        for (int k = 0; k < menu.size(); k++) {
                            if (firstArray[i] == menu.get(k).getId()) {
                                foodPreparingTimes1.add(menu.get(k).getPreparationTime());
                            }
                        }
                    }

                    firstMaxTime = Collections.max(foodPreparingTimes1);
                    totalTime = firstMaxTime;
                    System.out.println("IMPOSIBLE Timpul total de preparare: " + totalTime);
                } else {
                    System.out.println(" IMPOSIBLE cazul 3");
                    for (int i = 0; i < array.length; i++) {
                        for (int k = 0; k < menu.size(); k++) {
                            if (array[i] == menu.get(k).getId()) {
                                foodPreparingTimes1.add(menu.get(k).getPreparationTime());

                            }
                        }
                    }

                    totalTime = Collections.max(foodPreparingTimes1);
                    System.out.println("IMPOSIBLE Timpul total de preparare: " + totalTime);
                }

            }
        }
        return totalTime;

    }


    public static void cookingApparatus(ArrayList<Integer> list) {
        ArrayList<Menu> menu = Menu.getMenu();

        int numberOfStoves=0;
        int numberOfOvens=0;

        int maximumNumOfStoves = 2;
        int maximumNumOfOvens = 2;

        for(int i=0; i<menu.size(); i++){
            for(int k=0; k<list.size(); k++){
                if(menu.get(i).getId()==list.get(k)){
                    if(menu.get(i).getCookingApparatus().equals("oven")){
                        numberOfOvens++;
                    }
                    else if(menu.get(i).getCookingApparatus().equals("stove")){
                        numberOfStoves++;
                    }
                    else{
                        System.out.println("No apparatus needed");
                    }
                }
            }
        }
        if(numberOfOvens > maximumNumOfOvens || numberOfStoves > maximumNumOfStoves){

        }

    }

    public static Integer calculateTime(Integer[] array) {
        ArrayList<Menu> menu = Menu.getMenu();
        ArrayList<Integer> time = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            for (Menu item : menu) {
                if (item.getId().equals(array[i])) {
                    time.add(item.getPreparationTime());
                }
            }
        }
        return Collections.max(time);

    }
}