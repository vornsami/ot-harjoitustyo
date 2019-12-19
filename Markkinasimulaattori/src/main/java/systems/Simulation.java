/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems;

import actors.*;
import java.util.List;
import objects.Item;

/**
 *
 * @author Sami
 */
public class Simulation {
    List<Person> people;
    List<Company> companies;
    Item[] itemList;
    Trader trader;
            
    public Simulation(List<Person> p, List<Company> c, Item[] i) {
        this.people = p;
        this.companies = c;
        this.itemList = i;
        trader = new Trader(people, companies, itemList);
    }
    
    public void run(int rounds) {
        
        int day = 1;
        while (day <= rounds) {
            System.out.println("\n ---------------- \n");
            
            System.out.println("Avarage prices for day " + day + ":\n");
            trade();
            System.out.println();
            
            companyLogic();
            personLogic();
            
            System.out.println("\nNumber of people unemployed: " + people.stream().filter(a -> !a.isEmployed()).count());
            System.out.println();
            
            companies.forEach(a -> {
                System.out.println(a.getName() + " has " + a.getProdCount(a.getProd()) + " of " + itemList[a.getProd()].getName() + " in storage.");
            });
            
            companies.forEach(a -> {
                if (!a.payWages()) {
                    System.out.println(a.getName() + " couldn't afford to pay wages.");
                }
            });
            
            day++;
        }
    }
    
    private void trade() {
        for (int i = 0; i < itemList.length; i++) {
            trader.trade(i);
            int p = i;
            double average = companies.stream()
                    .filter(a -> a.getProd() == p)
                    .mapToDouble(a -> a.getSellLimit(p))
                    .average()
                    .getAsDouble();
            System.out.println(itemList[i].getName() + ": " + average);
        }
    }
    
    private void companyLogic() {
        companies.forEach(a -> {
            if (a.seeksMoreEmployees()) {
                a.mulWages(1.05);
                System.out.println(a.getName() + " raised wages!");
            }

            int p = a.getProdCount(a.getProd());
            if (p > a.getEmployees().size() && a.getEmployees().size() > 0) {
                a.addTargetEmployees(-1);
                a.removeRandomEmployee();
                System.out.println(a.getName() + " removed employee");
            } else if (p <= 0 && !a.seeksMoreEmployees()) {
                a.addTargetEmployees(1);
                System.out.println(a.getName() + " needs more employees");
            }
            if (a.operatesAtLoss()) {
                a.mulWages(0.95);
                System.out.println(a.getName() + " operates at a loss, so they lowered wages!!");
            }
            a.moneyBenchMark();
        });
    }
    
    private void personLogic() {
        people.forEach(a -> {
            companies.forEach(b -> { 
                if (b.seeksMoreEmployees()) {
                    if (!a.isEmployed()) {
                        b.addEmployee(a);
                        System.out.println(b.getName() + " hired an employee");
                    } else if (b.getWages() > a.getWages() && a.getCompany() != null) {
                        System.out.println(b.getName() + " hired an employee from " + a.getCompany().getName() + "!");
                        b.addEmployee(a);
                        a.getCompany().removeEmployee(a);
                    }
                }
            });
        });
    }
}
