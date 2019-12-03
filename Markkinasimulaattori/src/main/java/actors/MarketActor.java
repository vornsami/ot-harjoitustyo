/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

/**
 *
 * @author Sami
 */
public class MarketActor {
    int[] prodCounts;
    double[] sellLimit;
    double[] buyLimit;
    double money;
    
    public MarketActor(double m) {
        money = m;
    }
    public MarketActor(double m, double[] b, double[] s) {
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
    public int[] getAllProdCounts() {
        return prodCounts;
    }
    public double getBuyLimit(int i) {
        return buyLimit[i];
    }

    public double getSellLimit(int i) {
        return sellLimit[i];
    }
    public int getProdCount(int i) {
        return prodCounts[i];
    }

    public void setAllBuyLimits(double[] b) {
        buyLimit = b;
    }

    public void setAllSellLimits(double[] s) {
        sellLimit = s;
    }
    public void setAllProdCounts(int[] p) {
        prodCounts = p;
    }
    
    /**
     * Before calling this, please set up buy limits with setAllBuylimits, otherwise you will run into a nullPointerException.
     * @param i Position in buyLimit-list.
     * @param b The limit to be setted.
     */
    public void setBuyLimit(int i, double b) {
        buyLimit[i] = b;
    }
    
    /**
     * Before calling this, please set up buy limits with setAllSelllimits, otherwise you will run into a nullPointerException.
     * @param i Position in sellLimit-list.
     * @param s The limit to be setted.
     */
    public void setSellLimit(int i, double s) {
        sellLimit[i] = s;
    }
    /**
     * Before calling this, please set up buy limits with setAllProdCounts, otherwise you will run into a nullPointerException.
     * @param i Position in prodCounts-list.
     * @param p The count to be setted.
     */
    public void setProdCount(int i, int p) {
        prodCounts[i] = p;
    }
    
    public void setMoney(double m) {
        money = m;
    }
    
    public boolean addProdCount(int i, int p) {
        if (-p > prodCounts[i]) {
            return false;
        }
        prodCounts[i] += p;
        
        return true;
    }
    
    public boolean addMoney(double m) {
        if (-m > money) {
            return false;
        }
        money += m;
        
        return true;
    }
    
    /**
     * Tries to buy an item with given price. If succesful, transaction is done and return true. Else returns false.
     * @param i position of the item in buy- and sellLimit-lists
     * @param m amount of money to try to buy with
     * @return
     */
    public boolean tryBuy(int i, double m) {
        if (m <= buyLimit[i] && m <= money) {
            addMoney(-m);
            return true;
        }
        return false;
    }
    
    /**
     * Tries to an sell item with given price. If succesful, transaction is done and return true. Else returns false.
     * @param i position of the item in buy- and sellLimit-lists.
     * @param m amount of money to try to sell with.
     * @return
     */
    public boolean trySell(int i, double m) {
        if (m >= sellLimit[i] && sellLimit[i] != -1 && prodCounts[i] > 0) {
            addMoney(m);
            prodCounts[i]--;
            return true;
        }
        return false;
    }
    
    /**
     * Tries to an buy item with given price. If succesful, return true. Else returns false. Transaction is NOT done.
     * @param i position of the item in buy- and sellLimit-lists.
     * @param m amount of money to try to sell with.
     * @return
     */
    public boolean buyTest(int i, double m) {
        return m <= buyLimit[i] && m <= money;
    }
    
    /**
     * Tries to an sell item with given price. If succesful, return true. Else returns false. Transaction is NOT done.
     * @param i position of the item in buy- and sellLimit-lists.
     * @param m amount of money to try to sell with.
     * @return
     */
    public boolean sellTest(int i, double m) {
        return m >= sellLimit[i] && sellLimit[i] != -1 && prodCounts[i] > 0;
    }
    public void mulSellLimit(int i, double mul) {
        sellLimit[i] *= mul;
    }
    public void mulBuyLimit(int i, double mul) {
        buyLimit[i] *= mul;
    }
}

