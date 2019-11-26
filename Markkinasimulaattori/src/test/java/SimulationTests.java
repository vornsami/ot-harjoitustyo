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
import people.Person;

public class SimulationTests {
    public SimulationTests(){
    }    
        
    @Test
    public void testPerson(){
        
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
        assertEquals(true,person.tryBuy(0,7.0));
        assertEquals(false,person.tryBuy(0,7.0));
        assertEquals(5.0,person.getMoney(),0);
        person.addMoney(2.0);
        assertEquals(true,person.tryBuy(0,7.0));
        
        person.setSellLimit(0,5.0);
        assertEquals(true,person.trySell(0,7.0));
        assertEquals(false,person.trySell(0,4.0));
        assertEquals(true,person.trySell(0,5.0));
        assertEquals(12.0,person.getMoney(),0);
    }
}
