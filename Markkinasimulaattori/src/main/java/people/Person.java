/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;

/**
 *
 * @author Sami
 */
public class Person {
    double sellLimit;
    double buyLimit;
    double money;
    
    public Person(double m){
        sellLimit = -1;
        buyLimit = -1;
        
        money = m;
    }
    
    public Person(double m, double b, double s){
        sellLimit = s;
        buyLimit = b;
        
        money = m;
    }
    
    public double getMoney(){
        return money;
    }
    
    public double getBuyLimit(){
        return buyLimit;
    }
    
    public double getSellLimit(){
        return sellLimit;
    }
    
    public void setBuyLimit(double b){
        buyLimit = b;
    }
    
    public void setSellLimit(double s){
        sellLimit = s;
    }
    
    public void setMoney(double m){
        money = m;
    }
    
    public boolean addMoney(double m){
        if(-m > money)return false;
        money += m;
        
        return true;
    }
    
    public boolean tryBuy(double m){
        if(m<=buyLimit && m<=money){
            addMoney(-m);
            return true;
        }
        return false;
    }
    
    public boolean trySell(double m){
        if(m>=sellLimit){
            addMoney(m);
            return true;
        }
        return false;
    }
    public boolean buyTest(double m){
        return m>=buyLimit && m<=money;
    }
    public boolean sellTest(double m){
        return m<=sellLimit;
    }
    
    public void mulSellLimit(double mul){
        sellLimit *= mul;
    }
    public void mulBuyLimit(double mul){
        buyLimit *= mul;
    }
    
}