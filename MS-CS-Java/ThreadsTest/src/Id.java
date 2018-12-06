import java.util.*;

class MyComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        if ((o1 instanceof Id) && (o2 instanceof Id)) {
            Id n1 = (Id) o1;
            Id n2 = (Id) o2;
            return n1.a.compareTo(n2.a);
        }
        return 0;
    }
}

class Id implements Comparable {

    String a;

    Id(String a) {
        this.a = a;
    }

    public int compareTo(Object obj) {
        Id ch = (Id) obj;
        return -(this.a.compareTo(ch.a));
    }

    public String toString() {
        return "-" + this.a + "-";
    }
}

class lol {
    public static void main(String args[]) {
        List<Id> myIntList = new ArrayList<Id>();
        myIntList.add(new Id("a"));
        myIntList.add(new Id("b"));
        myIntList.add(new Id("c"));
        Collections.sort(myIntList);
        System.out.println(myIntList);
        Collections.sort(myIntList, new MyComparator());
        System.out.println(myIntList);
    }
}