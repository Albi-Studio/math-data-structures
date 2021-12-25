package org.albistudio.settheory;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerSetGeneratorTest {

    @Test
    @DisplayName("Should return exact quantity of sets")
    public void testCardinality() {
        ImmutableSet<Integer> set = ImmutableSet.of(1, 2, 3);
        var cardinality = set.size();

//        Set<Set<Integer>> powerSet = Sets.powerSet(set);
        Set<Set<Integer>> powerSet = PowerSetGenerator.generate(set);
        assertThat(powerSet).hasSize(1 << cardinality);


    }

    @Test
    @DisplayName("Should return singleton power set whose only element is the empty set")
    public void testGenerateSingletonEmptySet() {
//        PowerSet powerSet = PowerSetGenerator.generate(new HashSet<>());
//        assertThat(powerSet).hasSize(1);
    }

    @Test
    @DisplayName("String presentation of power set should be equal to set string")
    public void testStringsEquality() {

        Set<Set<Integer>> set = new HashSet<>();
        set.add(new HashSet<>());
        set.add(ImmutableSet.of(1));
        set.add(ImmutableSet.of(2));
        set.add(ImmutableSet.of(1, 2));
        System.out.println(set.size());
        StringJoiner joiner = new StringJoiner(",", "{", "}");
        joiner.add("{}")
                .add("{1}")
                .add("{2}")
                .add("{1,2}");

        //        PowerSet powerSet = PowerSetGenerator.generate(set);
        assertEquals("{{},{1},{1},{1,2}}", joiner.toString());

    }

    @Test
//    @Disabled
    @DisplayName("Each element of power set must be paired with tested set (sets are bijective)")
    public void testBijection() {

        Set<Set<Integer>> set = new HashSet<>();
        set.add(new HashSet<>());
        set.add(ImmutableSet.of(1));
        set.add(ImmutableSet.of(2));
        set.add(ImmutableSet.of(1, 2));

        /*todo to be replaced by PS*/
        Set<Set<Integer>> set2 = new HashSet<>();
        set2.add(new HashSet<>());
        set2.add(ImmutableSet.of(1));
        set2.add(ImmutableSet.of(2));
        set2.add(ImmutableSet.of(1, 2));

        assertThat(set).hasSize(set2.size());
        assertThat(set).containsAll(set2);
        assertThat(set2).containsAll(set);
    }
}
