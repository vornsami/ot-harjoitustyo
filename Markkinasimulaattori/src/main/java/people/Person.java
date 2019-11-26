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
    double[] sellLimit;
    double[] buyLimit;
    double money;
    
    public Person(double m) {
        money = m;
    }
    public Person(double m, double[] b, double[] s) {
        sellLimit = s;
        buyLimit = b;
        money = m;
    }
    public double getMoney() {
        return money;
    }
    public double[] getAllBuyLimits() {
        return buyLimit;
    }
    public double[] getAllSellLimits() {
        return sellLimit;
    }
    public double getBuyLimit(int i) {
        return buyLimit[i];
    }
    public double getSellLimit(int i) {
        return sellLimit[i];
    }

    /**
     *
     * @param b A list comprised of all limits.
     */
    public void setAllBuyLimits(double[] b) {
        buyLimit = b;
    }

    /**
     *
     * @param s - A list comprised of all limits. Use value -1 to denote seller not selling said product.
     */
    public void setAllSellLimits(double[] s) {
        sellLimit = s;
    }
    public void setBuyLimit(int i, double b) {
        buyLimit[i] = b;
    }
    public void setSellLimit(int i, double s) {
        sellLimit[i] = s;
    }
    public void setMoney(double m) {
        money = m;
    }
    
    public boolean addMoney(double m) {
        if (-m > money) {
            return false;
        }
        money += m;
        
        return true;
    }
    public boolean tryBuy(int i, double m) {
        if (m <= buyLimit[i] && m <= money) {
            addMoney(-m);
            return true;
        }
        return false;
    }
    public boolean trySell(int i, double m) {
        if (m >= sellLimit[i] && sellLimit[i] != -1) {
            addMoney(m);
            return true;
        }
        return false;
    }
    public boolean buyTest(int i, double m) {
        return m <= buyLimit[i] && m <= money;
    }
    public boolean sellTest(int i, double m) {
        return m >= sellLimit[i] && sellLimit[i] != -1;
    }
    public void mulSellLimit(int i, double mul) {
        sellLimit[i] *= mul;
    }
    public void mulBuyLimit(int i, double mul) {
        buyLimit[i] *= mul;
    }
}