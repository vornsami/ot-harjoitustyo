/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import actors.*;
import objects.Item;
import static objects.ItemType.*;

/**
 *
 * @author Sami
 */
public class Trader {
    
    List<Person> buyers;
    List<MarketActor> sellers;
    Item[] items;
    
    public Trader(List<Person> b, List<MarketActor> s, Item[] it) {
        buyers = b;
        sellers = s;
        items = it;
    }
    
    public void trade(int i) {
        
        List<Person> tempBuyers = new ArrayList<>();
        tempBuyers.addAll(buyers);
        List<MarketActor> tempSellers = new ArrayList<>();
        tempSellers.addAll(sellers);
        
        Item tradedItem = items[i];
        
        switch (tradedItem.getType()) {
            case GENERAL:
                this.generalTrade(tempBuyers, tempSellers, i);
            case NECESSITY:
                this.necessityTrade(tempBuyers, tempSellers, i);
            case LUXURY:
                this.luxuryTrade(tempBuyers, tempSellers, i);
                
        }
    }
    
    
    private boolean transaction(Person buyer, MarketActor seller, int i, double price) {
        if (seller.sellTest(i, price)) {
            if (buyer.tryBuy(i, price)) {
                seller.trySell(i, price);
                buyer.mulBuyLimit(i, 0.95);
                seller.mulSellLimit(i, 1.05);
                return true;
            }
        }
        return false;
    }
    
    private void generalTrade(List<Person> buy, List<MarketActor> sell, int i) {
        
        Collections.shuffle(buy);
        Collections.shuffle(sell);
        
        this.trading(buy, sell, i);

        buy.forEach(a -> a.mulBuyLimit(i, 1.05));
        sell.forEach(a -> a.mulSellLimit(i, 0.95));
    }
    
    private void necessityTrade(List<Person> buy, List<MarketActor> sell, int i) {
        
        Collections.shuffle(buy);
        Collections.shuffle(sell);
        
        this.trading(buy, sell, i);
        
        List<Person> buys = new ArrayList<>();
        buy.forEach(a -> {
            if (a.getBuyLimit(i) < a.getMoney()) {
                a.mulBuyLimit(i, 1.05);
                
                buys.add(a);
            }
        });
        
        if (!buys.isEmpty()) {
            this.necessityTrade(buys, sell, i);
        }
        
        sell.forEach(a -> a.mulSellLimit(i, 0.95));
    }
    
    private void luxuryTrade(List<Person> buy, List<MarketActor> sell, int i) {
        
        Collections.shuffle(buy);
        Collections.shuffle(sell);
        
        for (int b = 0; b < buy.size(); b++) {
            
            double sum = 0;

            for (int p = 0; p < items.length; p++) {
                if (items[p].getType() == NECESSITY) {
                    sum += buy.get(b).getAllBuyLimits()[p];
                }
            }

            if (buy.get(b).getMoney() > 30 * sum) {
                buy.remove(b);
            }
        }
        
        this.trading(buy, sell, i);

        buy.forEach(a -> a.mulBuyLimit(i, 1.05));
        sell.forEach(a -> a.mulSellLimit(i, 0.95));
    }
    private void trading(List<Person> buy, List<MarketActor> sell, int i) {
        for (int b = 0; b < buy.size(); b++) {
            for (int s = 0; s < sell.size(); s++) {
                if (this.transaction(buy.get(b), sell.get(s), i, sell.get(s).getSellLimit(i))) {
                    buy.remove(b);
                    if (sell.get(s).getProdCount(i) <= 0){
                        sell.remove(s);
                    }
                }
            }
        }
    }
}
