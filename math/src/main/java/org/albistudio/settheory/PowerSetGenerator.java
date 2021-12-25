package org.albistudio.settheory;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import javax.annotation.Nonnull;
import java.util.*;

public class PowerSetGenerator {


    public static <T> Set<Set<T>> generate(Set<T> set) {
        if (set.isEmpty()) return ImmutableSet.of(set);

        T primeItem = set.iterator().next();
        Set<T> restOfSet = Sets.difference(set, ImmutableSet.of(primeItem));
        Set<Set<T>> subPowerSet = generate(restOfSet);
        Set<Set<T>> powerSetOfSubSetWithExcludedItem = addPrimeItemToSubPowerSet(subPowerSet, primeItem);

        Set<Set<T>> finalPowerSet = new HashSet<>();
        finalPowerSet.addAll(subPowerSet);
        finalPowerSet.addAll(powerSetOfSubSetWithExcludedItem);
        return finalPowerSet;
    }

    private static <T> PowerSet<T> addPrimeItemToSubPowerSet(Set<Set<T>> powerSetSubSetWithoutElement, T primeItem) {
        PowerSet<T> powerSet = new PowerSet<>(powerSetSubSetWithoutElement);
        for (Set<T> restOfSet : powerSetSubSetWithoutElement) {
            Set<T> subsetWithElement = new HashSet<>(restOfSet);
            subsetWithElement.add(primeItem);
            powerSet.add(subsetWithElement);
        }
        return powerSet;
    }

    private static class PowerSet<T> extends AbstractSet<Set<T>> {
        private transient HashMap<Set<T>, Object> map;
        private static final Object PRESENT = new Object();

        public PowerSet(Set<Set<T>> set) {
            map = new HashMap<>(set.size());
            this.addAll(set);
        }

        @Override
        @Nonnull
        public Iterator<Set<T>> iterator() {
            return map.keySet().iterator();
        }

        @Override
        public int size() {
            return map.size();
        }

        public boolean add(Set<T> e) {
            return map.put(e, PRESENT) == null;
        }

    }
}
