package warehouse;

public class Restock {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        Warehouse w = new Warehouse();
        int x = StdIn.readInt();
        for(int i=0; i<x; i++)
        {
            String y = StdIn.readString();
            if(y.compareTo("add")==0)
            {
              int day = StdIn.readInt();
              int PID= StdIn.readInt();
              String PName = StdIn.readString();
              int stock =StdIn.readInt();
              int demand = StdIn.readInt();
              w.addProduct(PID, PName, stock, day, demand);
            }
             else
            {
              int RPID = StdIn.readInt();
              int ramount = StdIn.readInt();
              System.out.println("RPID: "+ RPID + " amount: "+ramount);
              w.restockProduct(RPID, ramount);
            }

        }
        StdOut.println(w);
	// Uset his file to test restock
    }
}
