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
    
    public Company(String n, int p, double eff, double m) {
        super(m);
        employees = new ArrayList<>();
        targetEmployees = 0;
        wages = 0;
        name = n;
        produce = p;
        efficiency = eff;
    }
    
    public Company(String n, int p, double eff, double m, double[] b, double[] s) {
        super(m, b, s);
        employees = new ArrayList<>();
        targetEmployees = 0;
        wages = 0;
        name = n;
        produce = p;
        efficiency = eff;
    }
    
    public Company(String n, int p, double eff, double m, double[] b, double[] s, List<Person> e) {
        super(m, b, s);
        employees = e;
        targetEmployees = employees.size();
        wages = 0;
        name = n;
        produce = p;
        efficiency = eff;
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
    }
    
    
    public boolean payWages() {
        if (this.addMoney(-employees.size() * wages)) {
            employees.forEach(a -> a.addMoney(wages));
            this.prodCounts[produce] += employees.size() * efficiency;
            return true;
        }
        return false;
    }
    
    public void setWages(double w) {
        wages = w;
    }
    
    public double getWages() {
        return wages;
    }
    
    public void mulWages(double m) {
        wages *= m;
    }
    
    public void addEmployee(Person e) {
        employees.add(e);
    }
    public List<Person> getEmployees() {
        return employees;
    }
    
    public void removeEmployee(int i) {
        if (employees.size() > i) {
            employees.remove(i);
        }
    }
    
    public void setTargetEmployees(int i){
        targetEmployees = i;
    }
    public void addTargetEmployees(int i){
        if (-i <= targetEmployees) {
            targetEmployees += i;
        }
    }
    
    public void removeRandomEmployee() {
        this.removeEmployee((new Random()).nextInt(employees.size()));
    }
    
    public boolean seeksMoreEmployees() {
        return employees.size() < targetEmployees;
    }
    
    public boolean seeksReduceEmployees() {
        return employees.size() > targetEmployees;
    }
    
    public String getName() {
        return name;
    }
    
    public int getProd(){
        return produce;
    }
}
