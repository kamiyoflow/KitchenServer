package com.example.demo;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;


@RestController
public class  KitchenController {

	private Order order;
	private Order order1;
	private static ArrayList<Order> orderArrayList = new ArrayList<>();

	private static ArrayList<Order>  list1 = new ArrayList<>();
	private static ArrayList<Order>  list2 = new ArrayList<>();
	private static ArrayList<Order>  list3 = new ArrayList<>();
	private  static ArrayList<Order> list4 = new ArrayList<>();
	private  static ArrayList<Order> list5 = new ArrayList<>();

	public static ArrayList<Order> getList1() {
		return list1;
	}

	public static ArrayList<Order> getList2() {
		return list2;
	}

	public static ArrayList<Order> getList3() {
		return list3;
	}

	public static ArrayList<Order> getList4() {
		return list4;
	}

	public static ArrayList<Order> getList5() {
		return list5;
	}

	public static ArrayList<Order> getOrderArrayList() {
		return orderArrayList;
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


	@RequestMapping(value = "/order", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public synchronized void postOrder(@RequestBody Order order){
		this.order = order;
		orderArrayList.add(this.order);

	}

	@RequestMapping(value = "/order", produces = "application/json", method = RequestMethod.GET)
	public static ArrayList<Order> getOrder(){
		return orderArrayList;
	}

	public static void structureByPriority(ArrayList<Order> orderArrayList) {


		while (orderArrayList.isEmpty()) {
		}

		while (!orderArrayList.isEmpty()) {
			for (int i = 0; i < orderArrayList.size(); i++) {
				if (orderArrayList.get(i) == null){
					System.out.println("null object ");
				}
				if (orderArrayList.get(i).getLock().tryLock()){

					if (orderArrayList.get(i).getPriority() == 5) {
						list5.add(orderArrayList.get(i));
						orderArrayList.remove(orderArrayList.get(i));

					} else if (orderArrayList.get(i).getPriority() == 4) {
						list4.add(orderArrayList.get(i));
						orderArrayList.remove(orderArrayList.get(i));

					} else if (orderArrayList.get(i).getPriority() == 3) {
						list3.add(orderArrayList.get(i));
						orderArrayList.remove(orderArrayList.get(i));

					} else if (orderArrayList.get(i).getPriority() == 2) {
						list2.add(orderArrayList.get(i));
						orderArrayList.remove(orderArrayList.get(i));

					} else if (orderArrayList.get(i).getPriority() == 1) {
						list1.add(orderArrayList.get(i));
						orderArrayList.remove(orderArrayList.get(i));

					}

				}

			}


			System.out.println("List 5" + list5);
			System.out.println();
			System.out.println("List 4" + list4);
			System.out.println();
			System.out.println("List 3" + list3);
			System.out.println();
			System.out.println("List 2" + list2);
			System.out.println();
			System.out.println("List 1" + list1);
		}
	}
}
