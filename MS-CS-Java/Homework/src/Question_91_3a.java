class Question_91_3a	{

    static int aInt = 12;
    static String	aString = "abc";
    static String	bString = "abc";
    static String	cString = "a" + "bc";;
    static String	dString = new String() + "a" + "bc";;
    static String[]	eString = { aString, bString };

    public static void method1( String aString )	{
        aString = "zyx";
        aInt = 14;
    }
    public static void method2( String aString[] )	{
        aString[0] = "zyx";
    }
    public static void method3( String aString )	{
        aString = aString;
    }
    public static void main( String args[] ) {

        /* 1 */	if ( aString == bString )
            System.out.println("1");
        /* 2 */	if ( aString == cString )
            System.out.println("2");
        /* 3 */	if ( aString == dString )
            System.out.println("3");
        /* 4 */	if ( new String(aString) ==  new String(aString) )
            System.out.println("d");
        /* 5 */	if ( new String(aString).equals(new String(aString)) )
            System.out.println("5");

        method1(aString);
        /* 6 */		System.out.println("6. " + aString);
                    System.out.println("6. " + aInt);
        method2(eString);
        /* 7 */		System.out.println("7. " + eString[0]);
        method3(aString);
        /* 8 */		System.out.println("8. " + aString);

        String rt = "abc";

        System.out.println(rt == "ab" + "c");
        //int n = -1;
        //--n;
        //System.out.println(--n);
        //System.out.println(n--);
        //System.out.println(n++);

//        {
//            int n,a=1,b=0,c;
//            System.out.println("First 20 Pell numbers: ");
//            for(n=1; n<=20; n++)
//            {
//                c= a + 2*b;
//                System.out.print(c+" ");
//                a = b;
//                b = c;
//            }
//        }

    }
}
/*
1
2
5
6. abc
7. zyx
8. abc
*/