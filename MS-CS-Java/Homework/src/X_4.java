public class X_4 extends X_3 {

    public int n = 4;

    public X_4()	{
        n = 44;
    }
    public void m()    {
        n = 444;
    }

    protected int go()	{
        X_3 aX_3 = new X_3();		// 1
        X_4 aX_4 = new X_4();		// 2

        System.out.println(aX_3.n);
        System.out.println(aX_4.n);
        aX_3.m();			//
        aX_4.m();			//
        System.out.println(aX_3.n);	// 3
        System.out.println(aX_4.n);	// 4

        aX_3 = (X_3)aX_4;		// 5a
        aX_4 = (X_4)aX_3;		// 5b

        aX_3.m();			//
        aX_4.m();			//
        System.out.println(aX_3.n);	// 6
        System.out.println(aX_4.n);	// 7
        System.out.println(super.n);	// 8
        return 0;

    }
    public static void main(String args[]) {
        new X_4().go();
    }
}
