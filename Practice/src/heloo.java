import javax.swing.*;

class Test{
    static void display(){
        System.out.println("Buggy Bread");
    }
}

class Demo{

    public static String reverse(String str){
        if(str.length() <= 1){
            return str;
        }
        return reverse(str.substring(1)) + str.charAt(0);
    }

    static int a = 1111;

    static {
        a = a-- - --a;
    }
    {a = a++ + ++a;}
    public static void main(String... args){
//        Test t = null;
//        t.display();
          //\u000d System.out.println(reverse("bikash"));
          //\nSystem.out.println(reverse("bikash"));
        System.out.println(a);
        String s = "One" + (1 + 2) + "Two";
        System.out.println(s);
        int i = 10 + + 11 - - 12 + + 13 - - 14 + + 15;
        System.out.println(i);

//        final class Constants {public static String name = "PI";}

        int i1 = 400;
        int i2 = 400;
        System.out.println(i1==i2);

        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4);
        System.out.println(Math.min(Double.MIN_VALUE, 0.0d));
    }
}
