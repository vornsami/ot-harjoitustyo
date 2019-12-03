/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import java.util.ArrayList;
import actors.*;
import objects.Item;
import static objects.ItemType.*;

/**
 *
 * @author Sami
 */
public class Run {

    public static void main(String[] args) {
        
        Person person1 = new Person(100.0);
        Person person2 = new Person(200.0);
        Person person3 = new Person(100.0);
        
        person1.setAllBuyLimits(new double[]{10});
        person2.setAllBuyLimits(new double[]{20.0});
        person3.setAllBuyLimits(new double[]{15.0});
        
        Item[] items = new Item[]{new Item("Product", NECESSITY)};
        
        ArrayList<Person> people =  new ArrayList<>();
        people.add(person1);
        people.add(person2);
        people.add(person3);
        
        Company company = new Company(0.0, new double[]{10}, new double[]{10}, people, 5);
        company.setAllProdCounts(new int[]{10});
        
        ArrayList<MarketActor> sellers =  new ArrayList<>();
        sellers.add(company);
        Trader trader = new Trader(people, sellers, items);
        
        for (int i = 0; i < 100; i++) {
            company.addProdCount(0, 2);
            company.payWages();
            trader.trade(0);
            System.out.println("Round " + i + ": ");
            System.out.print("People ready to pay: ");
            people.forEach(a -> System.out.print(a.getBuyLimit(0) + ", "));
            System.out.print("Company sells at: " + company.getSellLimit(0));
            System.out.println();
        }
        
        
        
        
    }
}
