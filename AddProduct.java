package warehouse;

/*
 * Use this class to test to addProduct method.
 */
public class AddProduct {
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
        w.addProduct(pID, PNAme, stock, day, demand);
      //  String s = "PQ After Entering "+ i + " th product: "+ w.toString();
        //StdOut.println(s);
        }
        StdOut.println(w);
	// Use this file to test addProduct
    }
}
