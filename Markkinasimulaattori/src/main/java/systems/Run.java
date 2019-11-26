/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import java.util.ArrayList;
import people.Person;

/**
 *
 * @author Sami
 */
public class Run {

    public static void main(String[] args) {
        
        Person buyer1 = new Person(100.0);
        Person buyer2 = new Person(200.0);
        Person seller = new Person(100.0);
        
        buyer1.setAllBuyLimits(new double[]{10.0});
        buyer2.setAllBuyLimits(new double[]{20.0});
        seller.setAllSellLimits(new double[]{4.0});
        
        ArrayList<Person> buyers =  new ArrayList<>();
        buyers.add(buyer1);
        buyers.add(buyer2);
        ArrayList<Person> sellers =  new ArrayList<>();
        sellers.add(seller);
        Trader trader = new Trader(buyers, sellers);
        
        
        
        for (int i = 0; i < 100; i++) {
            trader.trade(0);
            System.out.println(i + ": Buyer1: " + buyer1.getBuyLimit(0) + " m: " + buyer1.getMoney() + ",  Buyer2: " + buyer2.getBuyLimit(0) + " m: " + buyer2.getMoney() + ", Seller: " + seller.getSellLimit(0));
        }
        
        
        
        
    }
}
