package warehouse;

public class PurchaseProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        Warehouse w = new Warehouse();
        int n = StdIn.readInt();
        for(int i=0; i<n; i++)
        {
            String s= StdIn.readString();
            if(s.compareTo("add")==0)
            {
                int day = StdIn.readInt();
                int PID= StdIn.readInt();
                String PName = StdIn.readString();
                int stock =StdIn.readInt();
                int demand = StdIn.readInt();
                w.addProduct(PID, PName, stock, day, demand);
                //System.out.println("add "+ w);
            }
            else
            {
                int day = StdIn.readInt();
                int id = StdIn.readInt();
                int amount = StdIn.readInt();
                w.purchaseProduct(id, day, amount);
                //System.out.println("purchase " + w);
            }   
        }
        StdOut.println(w);
	// Use this file to test purchaseProduct
    }
}
