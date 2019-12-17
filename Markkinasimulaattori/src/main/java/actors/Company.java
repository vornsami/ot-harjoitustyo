/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Sami
 */
public class Company extends MarketActor {
    
    List<Person> employees;
    int targetEmployees;
    double wages;
    String name;
    int produce;
    double efficiency;
    double moneyBefore;
    
    public Company(String n, int p, double eff, double m) {
        super(m);
        employees = new ArrayList<>();
        targetEmployees = 0;
        wages = 0;
        name = n;
        produce = p;
        efficiency = eff;
        moneyBefore = m;
    }
    
    public Company(String n, int p, double eff, double m, double[] b, double[] s) {
        super(m, b, s);
        employees = new ArrayList<>();
        targetEmployees = 0;
        wages = 0;
        name = n;
        produce = p;
        efficiency = eff;
        moneyBefore = m;
    }
    
    public Company(String n, int p, double eff, double m, double[] b, double[] s, List<Person> e) {
        super(m, b, s);
        employees = e;
        targetEmployees = employees.size();
        wages = 0;
        name = n;
        produce = p;
        efficiency = eff;
        moneyBefore = m;
        
        employees.forEach(a -> {
            a.wages = wages;
            a.company = this;
        });
    }
    public Company(String n, int p, double eff, double m, double[] b, double[] s, int[] prd, List<Person> e, double w) {
        super(m, b, s);
        employees = e;
        targetEmployees = employees.size();
        wages = w;
        name = n;
        produce = p;
        prodCounts = prd;
        efficiency = eff;
        moneyBefore = m;
        
        employees.forEach(a -> {
            a.wages = wages;
            a.company = this;
        });
    }
    
    /**
     * Company pays money to its employees, and recieves products according to amount of workers and efficiency
     * @return
     */
    public boolean payWages() {
        if (this.addMoney(-employees.size() * wages)) {
            employees.forEach(a -> a.addMoney(wages));
            this.prodCounts[produce] += employees.size() * efficiency;
            return true;
        }
        return false;
    }
    
    /**
     * Sets company wages to given value
     * @param w Wage amount
     */
    public void setWages(double w) {
        wages = w;
        employees.forEach(a -> a.wages = wages);
    }
    
    /**
     * Return company wages
     * @return
     */
    public double getWages() {
        return wages;
    }
    
    /**
     * Multiplies wages by amount given
     * @param m
     */
    public void mulWages(double m) {
        wages *= m;
        employees.forEach(a -> a.wages = wages);
    }
    
    /**
     * Adds given Person to the list of employees
     * @param e Person to be added
     */
    public void addEmployee(Person e) {
        employees.add(e);
        e.wages = wages;
        e.company = this;
    }

    /**
     * Returns a list of employees
     * @return
     */
    public List<Person> getEmployees() {
        return employees;
    }
    
    /**
     * Removes employee from the list of employees
     * @param i Emloyee position in list
     */
    public void removeEmployee(int i) {
        if (employees.size() > i) {
            employees.get(i).toggleEmployed();
            employees.get(i).company = null;
            employees.remove(i);
        }
    }
    
    public void removeEmployee(Person person) {
        this.removeEmployee(employees.indexOf(person));
    }
    /**
     * Removes a random employee from the list of employees
     */
    public void removeRandomEmployee() {
        if (employees.size() > 0) {
            this.removeEmployee((new Random()).nextInt(employees.size()));
        }
    }
    /**
     * Sets the amount of employees the company targets to have
     * @param i amount to be setted
     */
    public void setTargetEmployees(int i){
        targetEmployees = i;
    }

    /**
     * Adds given number to the amount of employees
     * @param i amount to be added
     */
    public void addTargetEmployees(int i){
        
        targetEmployees += i;
        if (targetEmployees < 0) {
            targetEmployees = 0;
        }
    }
    
    
    
    /**
     * Returns boolean value representing if there are less employees than the target
     * @return
     */
    public boolean seeksMoreEmployees() {
        return employees.size() < targetEmployees;
    }
    
    /**
     * Returns boolean value representing if there are more employees than the target
     * @return
     */
    public boolean seeksReduceEmployees() {
        return employees.size() > targetEmployees;
    }
    
    /**
     * returns the name of the company
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * return wich product the company produces
     * @return
     */
    public int getProd(){
        return produce;
    }
    @Override
    public void mulSellLimit(int i, double mul) {
        sellLimit[i] *= mul;
    }
    
    public boolean operatesAtLoss() {
        return moneyBefore > money;
    }
    
    public double getPrevMoney() {
        return this.moneyBefore;
    }
    public void moneyBenchMark() {
        moneyBefore = money;
    }
}
