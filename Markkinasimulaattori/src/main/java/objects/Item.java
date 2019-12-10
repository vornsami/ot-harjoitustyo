/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author Sami
 */
public class Item {
    ItemType type;
    String name;
    
    public Item(String n, ItemType t) {
        type = t;
        name = n;
    }
    
    public ItemType getType() {
        return type;
    }
    
    public String getName() {
        return name;
    }
    
}
