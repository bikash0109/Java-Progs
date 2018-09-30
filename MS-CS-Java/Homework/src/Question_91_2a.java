class SuperClass {
    static  String  staticString  = "Super";
    private String  m = "SuperClass";

    protected String getM()     {
        return m;
    }
}
public class Question_91_2a extends  SuperClass {
    static  String  staticString  = "NotSuper";
    private String  m = "Question_91_2a";

    protected String getM()     {
        return m;
    }
    public static void main(String args[])        {
        SuperClass aSuperClass 		= new SuperClass();
        Question_91_2a aQuestion_91_2a 	= new Question_91_2a();
        Question_91_2a supercast = (Question_91_2a)aSuperClass;
        System.out.println(supercast.m);
        System.out.println("1: " + aSuperClass.getM() );               			// 1
        System.out.println("2: " + aQuestion_91_2a.getM() );               		// 2
        System.out.println("3: " + ((SuperClass)(aQuestion_91_2a)).staticString );      // 3
        System.out.println("4: " + ((SuperClass)(aQuestion_91_2a)).getM() );      	// 4
    }
}