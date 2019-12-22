/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sami
 */

import org.junit.Test;
import static org.junit.Assert.*;
import actors.*;
import java.util.ArrayList;
import objects.*;
import static objects.ItemType.*;
import systems.Simulation;
import systems.Trader;

public class SimulationTest {
    public SimulationTest() {
        
    }    
        
    @Test
    public void testPersonMoney() {
        
        Person person = new Person(10.0);
        
        assertEquals(10.0,person.getMoney(),0);
        person.addMoney(5.0);
        assertEquals(15.0,person.getMoney(),0);
        person.addMoney(-15.0);
        assertEquals(0.0,person.getMoney(),0);
        assertEquals(true,person.addMoney(5.0));
        assertEquals(false,person.addMoney(-15.0));
        assertEquals(5.0,person.getMoney(),0);
    }    
    @Test
    public void testPersonBuying() {
        Person person = new Person(5.0);
        person.setAllBuyLimits(new double[]{7.0});
        
        assertEquals(true,person.tryBuy(0,5.0));
        assertEquals(0.0,person.getMoney(),0);
        assertEquals(false,person.tryBuy(0,5.0));
        assertEquals(0.0,person.getMoney(),0);
        
        person.addMoney(12.0);
        assertEquals(true,person.buyTest(0,7.0));
        assertEquals(true,person.tryBuy(0,7.0));
        assertEquals(false,person.buyTest(0,7.0));
        assertEquals(false,person.tryBuy(0,7.0));
        assertEquals(5.0,person.getMoney(),0);
        person.addMoney(2.0);
        assertEquals(true,person.buyTest(0,7.0));
        assertEquals(true,person.tryBuy(0,7.0));
        
        person.addMoney(20.0);
        
        person.setBuyLimit(0, 10);
        person.mulBuyLimit(0, 2);
        assertEquals(true,person.tryBuy(0,15.0));
    }
    @Test
    public void testPersonSellingAndProdCounts() {    
        Person person = new Person(0.0);
        
        person.setAllProdCounts(new int[]{2});
        person.setAllSellLimits(new double[]{5.0});
        
        assertEquals(true,person.sellTest(0,7.0));
        assertEquals(true,person.trySell(0,7.0));
        assertEquals(false,person.sellTest(0,4.0));
        assertEquals(false,person.trySell(0,4.0));
        assertEquals(true,person.trySell(0,5.0));
        assertEquals(false,person.trySell(0,10.0));
        person.addProdCount(0, 1);
        assertEquals(true,person.trySell(0,10.0));
        assertEquals(22.0,person.getMoney(),0);
        assertEquals(0,person.getProdCount(0),0);
        assertEquals(0,person.getAllProdCounts()[0],0);
        
        person.setSellLimit(0, 7);
        person.mulSellLimit(0, 2);
        assertEquals(false,person.trySell(0,10.0));
    }
    @Test
    public void testPersonGeneral() {
        
        Person person = new Person(0.0, new double[]{7.0} , new double[]{5.0});
        
        assertEquals(7.0,person.getBuyLimit(0),0);
        assertEquals(5.0,person.getSellLimit(0),0);
        
        person.setAllBuyLimits(new double[]{8.0});
        person.setAllSellLimits(new double[]{10.0});
        
        assertEquals(8.0,person.getAllBuyLimits()[0],0);
        assertEquals(10.0,person.getAllSellLimits()[0],0);
        
        assertEquals(0.0,person.getMoney(),0);
        person.setMoney(12.0);
        assertEquals(12.0,person.getMoney(),0);
    }
    
    @Test
    public void testCompanyConstructors() {
        Company comp1 = new Company("", 0, 1, 100);
        Company comp2 = new Company("", 0, 1, 100, new double[]{}, new double[]{});
        Company comp3 = new Company("", 0, 1, 100, new double[]{}, new double[]{}, new ArrayList<>());
        Company comp4 = new Company("", 0, 1, 100, new double[]{}, new double[]{}, new int[]{0}, new ArrayList<>(), 50.0);
    }    
    
    @Test
    public void testCompanyMoneyAndWages() {
        
        Company comp = new Company("", 0, 1, 100, new double[]{}, new double[]{}, new int[]{0}, new ArrayList<>(), 50.0);
        
        Person person = new Person(50);
        comp.addEmployee(person);
        
        assertTrue(comp.payWages());
        assertEquals(100, person.getMoney(), 0);
        assertEquals(50, comp.getMoney(), 0);
        
        comp.mulWages(0.5);
        assertEquals(25, comp.getWages(), 0);
        
        assertTrue(comp.payWages());
        
        assertEquals(125, person.getMoney(), 0);
        assertEquals(25, comp.getMoney(), 0);
        
        comp.setMoney(0);
        assertTrue(!comp.payWages());
    }
    
    @Test
    public void testCompanyEmployees() { 
        
        Company comp = new Company("", 0, 1, 100, new double[]{}, new double[]{}, new int[]{0}, new ArrayList<>(), 50.0);
        
        Person person = new Person(50);
        comp.addEmployee(person);
        
        comp.setWages(50);
        comp.setTargetEmployees(1);
        
        assertTrue(!comp.seeksMoreEmployees());
        assertTrue(!comp.seeksReduceEmployees());
        
        comp.addTargetEmployees(1);
        assertTrue(comp.seeksMoreEmployees());
        assertTrue(!comp.seeksReduceEmployees());
        
        comp.addEmployee(new Person(1));
        comp.addEmployee(new Person(1));
        
        assertTrue(!comp.seeksMoreEmployees());
        assertTrue(comp.seeksReduceEmployees());
        
        comp.removeEmployee(0);
        comp.removeRandomEmployee();
        
        assertTrue(comp.seeksMoreEmployees());
        assertTrue(!comp.seeksReduceEmployees());
    }
    
    @Test
    public void testItem() {
        Item item = new Item("product",GENERAL);
        
        assertEquals("product", item.getName());
        assertEquals(GENERAL, item.getType());
    }
    
    @Test
    public void testTrader() {
        
        ArrayList<Person> b = new ArrayList<>();
        ArrayList<Company> s = new ArrayList<>();
        Item[] items = new Item[]{new Item("", NECESSITY), new Item("", GENERAL), new Item("", LUXURY)};
        
        b.add(new Person(50.0, new double[]{10, 10, 10}, new double[]{}));
        s.add(new Company("", 0, 1, 100, new double[]{}, new double[]{5.0, -1, -1}, new int[]{1, 0, 0}, new ArrayList<>(), 50.0));
        s.add(new Company("", 1, 1, 100, new double[]{}, new double[]{-1, 5.0, -1}, new int[]{0, 1, 0}, new ArrayList<>(), 50.0));
        s.add(new Company("", 2, 1, 100, new double[]{}, new double[]{-1, -1, 5.0}, new int[]{0, 0, 1}, new ArrayList<>(), 50.0));
        
        Trader trader = new Trader(b, s, items);
        
        trader.trade(0);
        trader.trade(1);
        trader.trade(2);
        
    }
    @Test
    public void testSimulation() {
        
        ArrayList<Person> b = new ArrayList<>();
        ArrayList<Company> s = new ArrayList<>();
        Item[] items = new Item[]{new Item("", NECESSITY), new Item("", GENERAL), new Item("", LUXURY)};
        
        b.add(new Person(50.0, new double[]{10, 10, 10}, new double[]{}));
        s.add(new Company("", 0, 1, 100, new double[]{}, new double[]{5.0, -1, -1}, new int[]{1, 0, 0}, new ArrayList<>(), 50.0));
        s.add(new Company("", 1, 1, 100, new double[]{}, new double[]{-1, 5.0, -1}, new int[]{0, 1, 0}, new ArrayList<>(), 50.0));
        s.add(new Company("", 2, 1, 100, new double[]{}, new double[]{-1, -1, 5.0}, new int[]{0, 0, 1}, new ArrayList<>(), 50.0));
        
        Simulation simulation = new Simulation(b, s, items);
        
        simulation.run(5);
        
    }
}
