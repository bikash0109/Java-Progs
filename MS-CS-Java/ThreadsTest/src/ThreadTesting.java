class NumberGenerator
{
    static int counter = 0;

    public synchronized int getNextNumber()
    {
        return counter++;
    }


}
class FirstThreadClass
{
    NumberGenerator num;

    FirstThreadClass(NumberGenerator num)
    {
        this.num = num;
    }

    public void run()
    {
        System.out.println("i am from 1st thread :" + num.getNextNumber());

    }


}
class SecondThreadClass
{
    NumberGenerator num;

    SecondThreadClass(NumberGenerator num)
    {
        this.num = num;
    }

    public void run()
    {
        System.out.println("i am from 2nd thread :" + num.getNextNumber());
    }


}

public class ThreadTesting
{
    public static void main(String[] args)
    {
        FirstThreadClass ftc = new FirstThreadClass(new NumberGenerator());
        SecondThreadClass stc = new SecondThreadClass(new NumberGenerator());
        for (int k = 0; k < 10; k++)
        {
            ftc.run();
            stc.run();
        }
    }


}