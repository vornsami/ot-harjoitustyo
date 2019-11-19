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
        
        person.setBuyLimit(7.0);
        
        assertEquals(true,person.tryBuy(5.0));
        assertEquals(0.0,person.getMoney(),0);
        assertEquals(false,person.tryBuy(5.0));
        assertEquals(0.0,person.getMoney(),0);
        
        person.addMoney(12.0);
        assertEquals(true,person.tryBuy(7.0));
        assertEquals(false,person.tryBuy(7.0));
        assertEquals(5.0,person.getMoney(),0);
        person.addMoney(2.0);
        assertEquals(true,person.tryBuy(7.0));
        
        person.setSellLimit(5.0);
        assertEquals(true,person.trySell(7.0));
        assertEquals(false,person.trySell(4.0));
        assertEquals(true,person.trySell(5.0));
        assertEquals(12.0,person.getMoney(),0);
        
        
        
    }
}
