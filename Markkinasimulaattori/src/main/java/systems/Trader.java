/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import people.Person;

/**
 *
 * @author Sami
 */
public class Trader {
    
    List<Person> buyers;
    List<Person> sellers;
    double[] costs;
    
    public Trader(List<Person> b, List<Person> s) {
        buyers = b;
        sellers = s;
    }
    
    public void setProdCosts(double[] prodCosts) {
        costs = prodCosts;
    }
    
    public void trade(int i) {
        
        List<Person> tempBuyers = new ArrayList<>();
        tempBuyers.addAll(buyers);
        List<Person> tempSellers = new ArrayList<>();
        tempSellers.addAll(sellers);
        
        Collections.shuffle(tempBuyers);
        Collections.shuffle(tempSellers);
        
        for (int b = 0; b < tempBuyers.size(); b++) {
            for (int s = 0; s < tempSellers.size(); s++) {
                if (this.transaction(tempBuyers.get(b), tempSellers.get(s), i, tempSellers.get(s).getSellLimit(i))) {
                    tempBuyers.remove(b);
                    tempSellers.remove(s);
                }
            }
        }
        
        tempBuyers.forEach(a -> a.mulBuyLimit(i, 1.05));
        tempSellers.forEach(a -> a.mulSellLimit(i, 0.95));
    }
    
    private boolean transaction(Person buyer, Person seller, int i, double price) {
        if (seller.sellTest(i, price)) {
            if (buyer.tryBuy(i, price)) {
                seller.trySell(i, price);
                buyer.mulBuyLimit(i, 0.95);
                seller.mulSellLimit(i, 1.05);
                if (costs != null) {
                    seller.addMoney(-costs[i]);
                }
                return true;
            }
        }
        return false;
    }
    
}
