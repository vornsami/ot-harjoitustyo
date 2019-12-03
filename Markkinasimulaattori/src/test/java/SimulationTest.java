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
import systems.Trader;

public class SimulationTest {
    public SimulationTest() {
        
    }    
        
    @Test
    public void testPerson() {
        
        Person person = new Person(10.0);
        
        assertEquals(10.0,person.getMoney(),0);
        person.addMoney(5.0);
        assertEquals(15.0,person.getMoney(),0);
        person.addMoney(-15.0);
        assertEquals(0.0,person.getMoney(),0);
        assertEquals(true,person.addMoney(5.0));
        assertEquals(false,person.addMoney(-15.0));
        assertEquals(5.0,person.getMoney(),0);
        
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
        
        person.setBuyLimit(0, 10);
        person.mulBuyLimit(0, 2);
        assertEquals(true,person.tryBuy(0,15.0));
        
        Person person2 = new Person(0.0, new double[]{7.0} , new double[]{5.0});
        
        assertEquals(7.0,person2.getBuyLimit(0),0);
        assertEquals(5.0,person2.getSellLimit(0),0);
        
        person2.setAllBuyLimits(new double[]{8.0});
        person2.setAllSellLimits(new double[]{10.0});
        
        assertEquals(8.0,person2.getAllBuyLimits()[0],0);
        assertEquals(10.0,person2.getAllSellLimits()[0],0);
        
        assertEquals(0.0,person2.getMoney(),0);
        person2.setMoney(12.0);
        assertEquals(12.0,person2.getMoney(),0);
    }
    @Test
    public void testCompany() {
        Company comp1 = new Company(100);
        Company comp2 = new Company(100, new double[]{}, new double[]{});
        Company comp3 = new Company(100, new double[]{}, new double[]{}, new ArrayList<>());
        Company comp4 = new Company(100, new double[]{}, new double[]{}, new ArrayList<>(), 50.0);
        
        Person person = new Person(50);
        comp4.addEmployee(person);
        
        assertTrue(comp4.payWages());
        assertEquals(100, person.getMoney(), 0);
        assertEquals(50, comp4.getMoney(), 0);
        
        comp4.mulWages(0.5);
        assertEquals(25, comp4.getWages(), 0);
        
        assertTrue(comp4.payWages());
        
        assertEquals(125, person.getMoney(), 0);
        assertEquals(25, comp4.getMoney(), 0);
        
        comp4.setMoney(0);
        assertTrue(!comp4.payWages());
        
        comp4.setWages(50);
        comp4.setTargetEmployees(1);
        
        assertTrue(!comp4.seeksMoreEmployees());
        assertTrue(!comp4.seeksReduceEmployees());
        
        comp4.addTargetEmployees(1);
        assertTrue(comp4.seeksMoreEmployees());
        assertTrue(!comp4.seeksReduceEmployees());
        
        comp4.addEmployee(new Person(1));
        comp4.addEmployee(new Person(1));
        
        assertTrue(!comp4.seeksMoreEmployees());
        assertTrue(comp4.seeksReduceEmployees());
        
        comp4.removeEmployee(0);
        comp4.removeRandomEmployee();
        
        assertTrue(comp4.seeksMoreEmployees());
        assertTrue(!comp4.seeksReduceEmployees());
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
        ArrayList<MarketActor> s = new ArrayList<>();
        Item[] items = new Item[1];
        
        Trader trader = new Trader(b, s, items);
    }
    
}
