public class ClassXX {
    static int instanceV = 1;

    static ClassXX bbbbb;
    static ClassXX aaaaaa;

    public ClassXX() {
    }
    public void method(int i){
        instanceV = i;
    }

    public String toString() {
        return "instanceV = " + instanceV;
    }

    public void m2(int i){
        aaaaaa.method(-9);
        method(12);
        System.out.println("-----------------------------------------");
        System.out.println("print itself : " + this);
        System.out.println("print aaaaaa: " + aaaaaa);
        System.out.println("=========================================");
    }

    public static void main(String args[] ) {
        bbbbb = new ClassXX();
        aaaaaa = new ClassXX();

        bbbbb.m2(3);
        aaaaaa.m2(24);
    }
}