/*
 * Program Name: Casting.java
 *
 * Version :  1
 *
 * @author: Bikash Roy
 * @author: Tanay Bhardwaj
 */
interface B {
    int a = 0;

    void methodB();
}

interface BB {
    int a = 1;

    void methodBB();
}

class A {
    int a = 2;

    void parentMethod() {
        System.out.println("This :" + this);
        System.out.println("parentMethod");
    }
}

class AA extends A implements B, BB {
    int a = 3;

    void childMethod() {
        System.out.println("childMethod");
    }

    public void methodB() {
        System.out.println("In methodB");
    }

    public void methodBB() {
        System.out.println("In methodBB");
    }
}

public class Casting {

    public static void main(String[] args) {
        // here we are creating an instance of class A
        A a = new A();

        // why is this the only method we can call?
        // Class A has only one method defined in it, and also it doesn't inherit or implements any other class.
        a.parentMethod();

        // why doesn’t this work?
        // A.a - since we are trying to access the variable without referring to its object.
        // Also variable a is a non-static variable, which cannot be used directly in a static environment.
        //System.out.println(A.a);

        // here we are creating an instance of class AA
        AA aa = new AA();

        // class AA doesn’t define a parentMethod, how can we call one?
        // class AA inherits class A, so by default the access modifier of parentMethod is protected, so the base class
        // hold a reference of the parent methods, and hence we can call it.
        aa.parentMethod();

        // how could we override this method?
        // By making another class which extends class AA
        aa.childMethod();

        // Which class does this variable refer to?
        // It refers to the base class (Class AA)
        System.out.println(aa.a);

        // Which class does this variable refer to?
        // It refers to class A.  Typecasting gives direct access to public members of its class.
        System.out.println(((A) aa).a);

        // What forces us to define these methods in the AA class?
        // As class AA implements interfaces B and BB. So the methods has to be implemented in this class.
        aa.methodB();
        aa.methodBB();

        // here we are creating an instance of class AA but what is different about this reference?
        // 'a' is now an object of Class AA, it doesn't hold any reference to Class A anymore.
        a = new AA();

        // why do we need to cast this?
        // as childMethod is not available in Parent class, so as for the parent to access it, typecasting is needed.
        ((AA) a).childMethod();

        // Which class does this variable refer to?
        // class A
        System.out.println(a.a);

        // Which class does this variable refer to?
        // class AA - typecasting class A to AA, which gives direct access to public members
        System.out.println(((AA) a).a);

        // call methodB and methodBB using the variable a


        // how can we access these variables from the interfaces?
        // since fields defined in an interface are public, static and final by default.
        // just as we can access static variables without instantiating the class, interface also behaves like a class
        // and hence we can access those values.
        System.out.println(B.a);
        System.out.println(BB.a);

    }
}