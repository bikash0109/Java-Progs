public class XX {
    int instanceV = 1;

    static XX bbbbb;
    static XX aaaaaa;

    public XX() {
    }

    public void method(int i) {
        instanceV = i;
    }

    public String toString() {
        return "instanceV = " + instanceV;
    }

    public void m2(int i) {
        aaaaaa.method(i);
        method(2 * i);
        System.out.println("-----------------------------------------");
        System.out.println("print itself : " + this);
        System.out.println("print aaaaaa: " + aaaaaa);
        System.out.println("=========================================");
    }

    public static void main(String args[]) {
        //bbbbb = new XX();
        //aaaaaa = new XX();

        System.out.println("Well, 3 + 4 = " + 7);
        System.out.println("Well, 3 + 4 = " + 3 + 4);
        System.out.println("Well, 3 + 4 = " + (3 + 4));
        System.out.println(3 + 7);
        System.out.println(3 + 7 + "abc");
        System.out.println(3 + 7 + "abc" + 3 + 7);
        System.out.println(3 + 7 + "abc" + 1 + 2);
        System.out.println("" + 3 + 7 + "abc" + 1 + 2);
        System.out.println("" + (3 + 7) + "abc" + (1 + 2));
        //bbbbb.m2(3);
        //aaaaaa.m2(24);
    }
}