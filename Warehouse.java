package warehouse;



/*
 *
 * This class implements a warehouse on a Hash Table like structure, 
 * where each entry of the table stores a priority queue. 
 * Due to your limited space, you are unable to simply rehash to get more space. 
 * However, you can use your priority queue structure to delete less popular items 
 * and keep the space constant.
 * 
 * @author Ishaan Ivaturi
 */ 
public class Warehouse {
    private Sector[] sectors;
    
    // Initializes every sector to an empty sector
    public Warehouse() {
        sectors = new Sector[10];

        for (int i = 0; i < 10; i++) {
            sectors[i] = new Sector();
        }
    }
    
    /**
     * Provided method, code the parts to add their behavior
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void addProduct(int id, String name, int stock, int day, int demand) {
    //    System.out.println("id= "+id+" n="+name+" s="+stock+" day="+day+" d=" +demand);
        evictIfNeeded(id);
        addToEnd(id, name, stock, day, demand);
        fixHeap(id);
    }

    /**
     * Add a new product to the end of the correct sector
     * Requires proper use of the .add() method in the Sector class
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    private void addToEnd(int id, String name, int stock, int day, int demand) {
        
        Product product = new Product(id, name, stock, day, demand);
        Sector s = getSector(id);
        s.add(product);
        // IMPLEMENT THIS METHOD
    }
    private Sector getSector(int id)
    {
        int hashIndex = id%sectors.length;
        return sectors[hashIndex];
    }
    private boolean isPQueueFull(Sector s)
    {   
       // return (s.getSize()==5) ?true:false;
       return (s.getSize()==5);
    }

    /**
     * Fix the heap structure of the sector, assuming the item was already added
     * Requires proper use of the .swim() and .getSize() methods in the Sector class
     * @param id The id of the item which was added
     */
     private void fixHeap(int id) {
        Sector s = getSector(id);
        int NPIndex = s.getSize();
        s.swim(NPIndex);

        // IMPLEMENT THIS METHOD
    }

    /**
     * Delete the least popular item in the correct sector, only if its size is 5 while maintaining heap
     * Requires proper use of the .swap(), .deleteLast(), and .sink() methods in the Sector class
     * @param id The id of the item which is about to be added
     */
    private void evictIfNeeded(int id) {
        Sector s = getSector(id);
        if(!isPQueueFull(s))
            return;
        int pQSize = s.getSize();
        s.swap(1, pQSize);
        s.deleteLast();
        s.sink(1);        
       // IMPLEMENT THIS METHOD
    }

    /**
     * Update the stock of some item by some amount
     * Requires proper use of the .getSize() and .get() methods in the Sector class
     * Requires proper use of the .updateStock() method in the Product class
     * @param id The id of the item to restock
     * @param amount The amount by which to update the stock
     */
    public void restockProduct(int id, int amount) {
        Sector s = getSector(id);
        int x = s.getSize();
        for(int i=1; i<=x; i++)
        {
            if(id==s.get(i).getId())
            {
              s.get(i).updateStock(amount);
              break;
            }
        }  
        // IMPLEMENT THIS METHOD
    }
    
    /**
     * Delete some arbitrary product while maintaining the heap structure in O(logn)
     * Requires proper use of the .getSize(), .get(), .swap(), .deleteLast(), .sink() and/or .swim() methods
     * Requires proper use of the .getId() method from the Product class
     * @param id The id of the product to delete
     */
    public void deleteProduct(int id) {
        Sector s = getSector(id);
        int x = s.getSize();
        for(int i=1;i<=x;i++)
        {
            if(id==s.get(i).getId())
            {
                s.swap(i, x);
                s.deleteLast();
                s.sink(i);
                break;
            }
        }
        // IMPLEMENT THIS METHOD
    }
    
    /**
     * Simulate a purchase order for some product
     * Requires proper use of the getSize(), sink(), get() methods in the Sector class
     * Requires proper use of the getId(), getStock(), setLastPurchaseDay(), updateStock(), updateDemand() methods
     * @param id The id of the purchased product
     * @param day The current day
     * @param amount The amount purchased
     */
    public void purchaseProduct(int id, int day, int amount) {
        Sector s = getSector(id);
        int n=s.getSize();
        for(int i=1; i<=n; i++)
        {
           Product p =s.get(i);
           if(id==p.getId())
           {
            if(amount>p.getStock())
                return;
                //System.out.println(p);
               // System.out.println("id= "+id+" day= "+ day+ " a="+ amount);
             p.setLastPurchaseDay(day);
             p.updateStock(-amount); 
             //System.out.println(p);
             p.updateDemand(amount);
             s.sink(i);
             break;
           }
        }
        // IMPLEMENT THIS METHOD
    }
    
    /**
     * Construct a better scheme to add a product, where empty spaces are always filled
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void betterAddProduct(int id, String name, int stock, int day, int demand) {
         Sector s = getSector(id);
       //  System.out.println("ID: " + id + "Name: " + name);
         if(!isPQueueFull(s))
         {
            addProduct(id, name, stock, day, demand);
         }
         else
         {
            Product product = new Product(id, name, stock, day, demand);
            int HashIndex = id%sectors.length;
            for(int i= HashIndex+1; i<=HashIndex+sectors.length;i++)
            {
              s=getSector(i);   
              /*if(isPQueueFull(s) && i<HashIndex+sectors.length)
              {
                continue;
              }*/
              if(i==HashIndex+sectors.length)
              {
                addProduct(id, name, stock, day, demand);
              }
              else if (!isPQueueFull(s))
              {
                s.add(product);
                s.swim(s.getSize());
                break;
              }
        
            }
         }
        // IMPLEMENT THIS METHOD
    }

    /*
     * Returns the string representation of the warehouse
     */
    public String toString() {
        String warehouseString = "[\n";

        for (int i = 0; i < 10; i++) {
            warehouseString += "\t" + sectors[i].toString() + "\n";
        }
        
        return warehouseString + "]";
    }

    /*
     * Do not remove this method, it is used by Autolab
     */ 
    public Sector[] getSectors () {
        return sectors;
    }
}
