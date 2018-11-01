public class X_2 extends Thread    {
    private int id;
    static String aObject;

    public X_2 (int id) {
        this.id = id;
        aObject = new String("" + id);
    }

    public void run () {
        synchronized ( aObject )     {
            System.out.println(id + ": -->");
            System.out.println(id + ": <--");
        }
    }
    public static void main (String args []) {
        new X_2(1).start();
        new X_2(2).start();
        new X_2(3).start();
    }
}