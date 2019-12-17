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
public class Person extends MarketActor {
    boolean isEmployed;
    double wages;
    Company company;
    
    public Person(double m) {
        super(m);
        isEmployed = true;
    }
    public Person(double m, double[] b, double[] s) {
        super(m, b, s);
        isEmployed = true;
    }
    
    public boolean isEmployed() {
        return isEmployed;
    }
    
    public void toggleEmployed() {
        isEmployed = !isEmployed;
    }
    
    public double getWages() {
        return wages;
    }
    
    public Company getCompany() {
        return company;
    }
    
}