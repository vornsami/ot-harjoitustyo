/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import people.Person;

/**
 *
 * @author Sami
 */
public class Run {
    public static void main(String[] args) {
        
        Person buyer = new Person(100.0);
        Person seller = new Person(100.0);
        
        buyer.setBuyLimit(10.0);
        seller.setSellLimit(4.0);
        
        Trader trader = new Trader(buyer,seller);
        
        
        
        for(int i=0;i<10;i++){
            trader.trade(seller.getSellLimit());
            System.out.println("Buyer: " + buyer.getBuyLimit() + ", Seller: " + seller.getSellLimit());
        }
        
        
        
        
    }
}
