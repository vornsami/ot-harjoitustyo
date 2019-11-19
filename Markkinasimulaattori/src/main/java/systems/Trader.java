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
public class Trader {
    
    Person buyer;
    Person seller;
    
    public Trader(Person b, Person s){
        buyer = b;
        seller = s;
    }
    
    public void trade(double startPrize){
        
        if(seller.sellTest(startPrize)){
            if(buyer.tryBuy(startPrize)){
                
                
                seller.trySell(startPrize);
                buyer.mulBuyLimit(0.95);
                seller.mulSellLimit(1.05);
            } else {
                
                buyer.mulBuyLimit(1.05);
                seller.mulSellLimit(0.95);
            }
        }
    }
}
