/**
 * Converts a String to an int - this is one way out of many
 *
 */

class  StringToInt
{
    public static void main(String args[])
    {
        int i;
        Integer aInt = new Integer("4");
        i = aInt.intValue();
        System.out.println(i);
        i = Integer.parseInt("4");
        System.out.println(i);

    }
}
