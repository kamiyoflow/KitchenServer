package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Menu {

    private Integer id;
    private String name;
    private Integer preparationTime;
    private Integer complexity;
    private String cookingApparatus;


    public static ArrayList<Menu> getMenu(){
    ArrayList<Menu> menuItmes = new ArrayList<>();

    Menu item1 = new Menu();
    Menu item2 = new Menu();
    Menu item3 = new Menu();
    Menu item4 = new Menu();
    Menu item5 = new Menu();
    Menu item6 = new Menu();
    Menu item7 = new Menu();
    Menu item8 = new Menu();
    Menu item9 = new Menu();
    Menu item10 = new Menu();

    item1.setId(1);
    item1.setName("pizza");
    item1.setPreparationTime(20);
    item1.setComplexity(2);
    item1.setCookingApparatus("oven");
    menuItmes.add(item1);

    item2.setId(2);
    item2.setName("salad");
    item2.setPreparationTime(10);
    item2.setComplexity(1);
    item2.setCookingApparatus(null);
    menuItmes.add(item2);

    item3.setId(3);
    item3.setName("zeama");
    item3.setPreparationTime(7);
    item3.setComplexity(1);
    item3.setCookingApparatus("stove");
    menuItmes.add(item3);

    item4.setId(4);
    item4.setName("Scallop Sashimi with Meyer Lemon Confit");
    item4.setPreparationTime(32);
    item4.setComplexity(3);
    item4.setCookingApparatus(null);
    menuItmes.add(item4);

    item5.setId(5);
    item5.setName("Island Duck with Mulberry Mustard");
    item5.setPreparationTime(35);
    item5.setComplexity(3);
    item5.setCookingApparatus("oven");
    menuItmes.add(item5);

    item6.setId(6);
    item6.setName("Waffles");
    item6.setPreparationTime(10);
    item6.setComplexity(1);
    item6.setCookingApparatus("stove");
    menuItmes.add(item6);

    item7.setId(7);
    item7.setName("Aubergine");
    item7.setPreparationTime(20);
    item7.setComplexity(2);
    item7.setCookingApparatus(null);
    menuItmes.add(item7);

    item8.setId(8);
    item8.setName("Lasagna");
    item8.setPreparationTime(30);
    item8.setComplexity(2);
    item8.setCookingApparatus("oven");
    menuItmes.add(item8);

    item9.setId(9);
    item9.setName("Burger");
    item9.setPreparationTime(15);
    item9.setComplexity(1);
    item9.setCookingApparatus("oven");
    menuItmes.add(item9);

    item10.setId(10);
    item10.setName("Gyros");
    item10.setPreparationTime(15);
    item10.setComplexity(1);
    item10.setCookingApparatus(null);
    menuItmes.add(item10);

    return menuItmes;
    }

}

