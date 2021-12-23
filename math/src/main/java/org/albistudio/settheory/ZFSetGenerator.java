package org.albistudio.settheory;

import java.util.*;

/**
 * Система Цермело — Френкеля
 * https://en.wikipedia.org/wiki/Set-theoretic_definition_of_natural_numbers
 * 0 = { } = ∅ , - empty set
 * 1 = { 0 } = { ∅ } ,
 * 2 = { 0 , 1 } = { ∅ , { ∅ } } ,
 * 3 = { 0 , 1 , 2 } = { ∅ , { ∅ } , { ∅ , { ∅ } } }
 */

/*
 * TIP - use power set https://www.baeldung.com/java-power-set-of-a-set
 *
 * generate(Set, n)
 * */
public final class ZFSetGenerator {
    public static CustomSet generate(CustomSet parentSet, int n) {
        System.out.println("generate " + n);
        if (n == 0) return parentSet;
        CustomSet childSet = new CustomSet();
        parentSet.addNode(childSet);
        return generate(childSet, n - 1);
    }

    private final static class CustomSet implements CustomType {
        public static String rep = "∅"; // ??
        private final Set<CustomType> values;
        private CustomSet childSet;

        public CustomSet(CustomSet... childSet) {
            System.out.println(this);
            System.out.println(childSet);
            this.values = new HashSet<>(Arrays.asList(childSet));
        }

        public Set<CustomType> getValues() {
            return values;
        }

        public void addNode(CustomSet childSet) {
            this.childSet = childSet;
        }

        public CustomSet getChildSet() {
            return childSet;
        }

        /*@Override
        public String toString() {
            return "CustomSet{" +
                    "values=" + values +
                    ", childSet=" + childSet +
                    '}';
        }*/
    }

    private interface CustomType {
    }

    public static void main(String[] args) {

        CustomSet customSet = generate(new CustomSet(), 1);
//        customSet.getValues().forEach(System.out::println);
        System.out.println(customSet);

        /*WORKS DONT TOUCH*/
       /* InnerSet innerSet1 = new InnerSet(1);

        InnerSet innerSet3 = new InnerSet(3);
        InnerSet innerSet2 = new InnerSet(2, innerSet3);
        innerSet3.setParentInstance(innerSet2);

        InnerSet innerSet5 = new InnerSet(5);

        InnerSet innerSet7 = new InnerSet(7);
        InnerSet innerSet6 = new InnerSet(6, innerSet7);
        innerSet7.setParentInstance(innerSet6);

        InnerSet innerSet4 = new InnerSet(4, innerSet5, innerSet6);
        innerSet6.setParentInstance(innerSet4);
        innerSet5.setParentInstance(innerSet4);

        InnerSet rootSet = new InnerSet(0, innerSet1, innerSet2, innerSet4);
        innerSet4.setParentInstance(rootSet);
        innerSet2.setParentInstance(rootSet);
        innerSet1.setParentInstance(rootSet);*/

        /*var sets = new InnerSet(0,
                new InnerSet(1),
                new InnerSet(2, new InnerSet(3)),
                new InnerSet(4, new InnerSet(5), new InnerSet(6, new InnerSet(7))));*/
    }

    /*WORKS DONT TOUCH*/
   /* private static class InnerSet {
        //        private final Object parentRoot;
        private final int num;
        private InnerSet parentInstance;
        private Set<InnerSet> values1;

        public InnerSet(int num, InnerSet... values) {
            this.num = num;
            System.out.println("New InnerSet number " + getNum());
            values1 = new HashSet<>(Arrays.asList(values));
        }

        public Set<InnerSet> getValues() {
            return values1;
        }

        public void printValues() {
            System.out.println("Current: " + getNum());
            System.out.println("Parent root: " + parentInstance.getNum());
        }

        public int getNum() {
            return num;
        }

        public void setParentInstance(InnerSet innerSet) {
            parentInstance = innerSet;
            printValues();
        }
    }*/
}
