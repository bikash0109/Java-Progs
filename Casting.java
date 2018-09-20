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
        // Class A has only one method defined, and also it doesn't inherit or implements any other class.
        a.parentMethod();

        // why doesn’t this work?
        // A.a - since we are trying to access the variable without referring to its object.
        // Also variable a is a non-static variable, which cannot be used directly in a static environment.
        //System.out.println(A.a);

        // here we are creating an instance of class AA
        AA aa = new AA();

        // class AA doesn’t define a parentMethod, how can we call one?
        // class AA inherits class A, so by default the access modifier of parentMethod is protected, so the base class
        // hold a copy of the parent method, and hence we can call it.
        aa.parentMethod();

        // how could we override this method?
        aa.childMethod();

        // Which class does this variable refer to?
        // It refers to the base class (Child Class)
        System.out.println(aa.a);

        // Which class does this variable refer to?
        System.out.println(((A) aa).a);

        // What forces us to define these methods in the AA class?
        aa.methodB();
        aa.methodBB();

        // here we are creating an instance of class AA but what is different about this reference?
        a = new AA();

        // why do we need to cast this?
        ((AA) a).childMethod();

        // Which class does this variable refer to?
        System.out.println(a.a);

        // Which class does this variable refer to?
        System.out.println(((AA) a).a);

        // call methodB and methodBB using the variable a


        // how can we access these variables from the interfaces?
        System.out.println(B.a);
        System.out.println(BB.a);

    }
}