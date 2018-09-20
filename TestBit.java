public class TestBit {
    public static void main(String[] args) {
        int x = 3;
        System.out.println(Integer.toBinaryString(x));
        int y = ~ x;
        System.out.println(Integer.toBinaryString(y));
        int z = x > y? x : y ;
        System.out.println(z);
    }
}
