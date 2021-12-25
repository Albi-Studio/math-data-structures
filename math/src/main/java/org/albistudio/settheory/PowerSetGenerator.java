package org.albistudio.settheory;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/* *
 * https://www.baeldung.com/java-power-set-of-a-set
 * */
public class PowerSetGenerator {


    public static <T> Set<Set<T>> generate(Set<T> set) {
        if (set.isEmpty()) return ImmutableSet.of(new HashSet<>());

        Set<Set<T>> sets = producePS(set);
        System.out.println(sets.size());
        return sets;
    }

    /*recursive returns */
    private static <T> Set<Set<T>> producePS(Set<T> set) {
        if (set.isEmpty()) return new HashSet<>();
        /*iteration over set and probably recursion*/
        /*Set<T> original = new LinkedHashSet<>(set);

        T element = original.iterator().next();
        original.remove(element);
        System.out.println("removed " + element);

        Set<T> subsetWithoutElement = new LinkedHashSet<>(original);
        subsetWithoutElement.forEach(x -> System.out.println("next element " + x));
        Set<Set<T>> powerSetSubsetWithoutElement = new HashSet<>();
        producePS(subsetWithoutElement);*/

        T element = set.iterator().next();
        Set<T> subSetWithoutElement = getSubSetWithoutElement(set, element);
        Set<Set<T>> powerSetSubSetWithoutElement = producePS(subSetWithoutElement);
        Set<Set<T>> powerSetSubSetWithElement = addElementToAll(powerSetSubSetWithoutElement, element);

        Set<Set<T>> powerSet = new HashSet<>();
        powerSet.addAll(powerSetSubSetWithoutElement);
        powerSet.addAll(powerSetSubSetWithElement);
        return powerSet;
    }

    private static <T> Set<Set<T>> addElementToAll(Set<Set<T>> powerSetSubSetWithoutElement, T element) {
        Set<Set<T>> powersetSubSetWithElement = new HashSet<>();
        for (Set<T> subsetWithoutElement : powerSetSubSetWithoutElement) {
            Set<T> subsetWithElement = new HashSet<>(subsetWithoutElement);
            subsetWithElement.add(element);
            powersetSubSetWithElement.add(subsetWithElement);
        }
        return powersetSubSetWithElement;
    }

    private static <T>Set<T> getSubSetWithoutElement(Set<T> set, T element){
        Set<T> original = new LinkedHashSet<>(set);
        original.remove(element);
        System.out.println("removed " + element);

        Set<T> subsetWithoutElement = new LinkedHashSet<>(original);
        subsetWithoutElement.forEach(x -> System.out.println("next element " + x));
        return subsetWithoutElement;
    }

    private static class PowerSet<T> extends AbstractSet<Set<T>> {

        public PowerSet() {
            Set<T> set = new LinkedHashSet<>();
        }

        @Override
        public Iterator iterator() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }
    }
}
