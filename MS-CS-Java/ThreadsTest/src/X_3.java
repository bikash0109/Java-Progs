public class X_3 extends Thread    {
    private String info;
    private static String s_1 = new String();;
    private static String s_2 = new String();;

    public X_3(String info)	{
        this.info = info;
    }
    private void inProtected_2() {
        try {
            synchronized ( s_2 )       {
                synchronized ( s_1 )       {
                    s_2.notify();
                    s_1.wait();
                }
                synchronized ( s_1 )       {
                }
            }
        } catch (Exception e )	{}
    }
    private void inProtected_1() {
        try {
            synchronized ( s_1 )       {
                synchronized ( s_2 )       {
                    s_1.notify();
                    s_2.wait();
                }
                synchronized ( s_2 )       {
                }

            }
        } catch (Exception e )	{}
    }
    public void run () {
        if ( info.equals("1") )
            inProtected_1();
        else
            inProtected_2();
    }
    public static void main (String args []) {
        new X_3("1").start();
        new X_3("2").start();
    }
}
