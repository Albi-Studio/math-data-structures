package org.albistudio.settheory;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import javax.annotation.Nonnull;
import java.util.*;

public class PowerSetGenerator {


    public static <T> PowerSet<T> generate(Set<T> set) {
        if (set.isEmpty()) {
            PowerSet<T> ret = new PowerSet<T>();
            ret.add(set);
            return ret;
        }

        T primeItem = set.iterator().next();
        Set<T> restOfSet = Sets.difference(set, ImmutableSet.of(primeItem));
        PowerSet<T> subPowerSet = generate(restOfSet);
        Set<Set<T>> powerSetOfSubSetWithExcludedItem = addPrimeItemToSubPowerSet(subPowerSet, primeItem);

        PowerSet<T> finalPowerSet = generate(new HashSet<>());
        finalPowerSet.addAll(subPowerSet);
        finalPowerSet.addAll(powerSetOfSubSetWithExcludedItem);
        return finalPowerSet;
    }

    private static <T> PowerSet<T> addPrimeItemToSubPowerSet(PowerSet<T> subPowerSet, T primeItem) {
        PowerSet<T> powerSet = new PowerSet<>(subPowerSet);
        for (Set<T> restOfSet : subPowerSet) {
            Set<T> subsetWithElement = new HashSet<>(restOfSet);
            subsetWithElement.add(primeItem);
            powerSet.add(subsetWithElement);
        }
        return powerSet;
    }

    private final static class PowerSet<T> extends AbstractSet<Set<T>> {
        private transient HashMap<Set<T>, Object> map;
        private static final Object PRESENT = new Object();

        private PowerSet(Set<Set<T>> set) {
            map = new HashMap<>(set.size());
            this.addAll(set);
        }

        public PowerSet() {
            map = new HashMap<>(1);
            map.put(new HashSet<>(), PRESENT);
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
