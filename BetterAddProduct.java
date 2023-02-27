package warehouse;

/*
 * Use this class to test the betterAddProduct method.
 */ 
public class BetterAddProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        Warehouse w = new Warehouse();
        int line = StdIn.readInt();
        for(int i=0; i<line; i++)
        {
        int day = StdIn.readInt();
        int pID= StdIn.readInt();
        String PNAme = StdIn.readString();
        int stock =StdIn.readInt();
        int demand = StdIn.readInt();
        w.betterAddProduct(pID, PNAme, stock, day, demand);
        }
        StdOut.println(w);
        
        
        // Use this file to test betterAddProduct
    }
}
