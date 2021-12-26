package org.albistudio.settheory;

import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerSetGeneratorTest {

    @Test
    @DisplayName("Should return exact quantity of sets")
    public void testCardinality() {
        var set = ImmutableSet.of(1, 2, 3);
        var cardinality = set.size();

        Set<Set<Integer>> powerSet = PowerSetGenerator.generate(set);
        assertThat(powerSet).hasSize(1 << cardinality);
    }

    @Test
    @DisplayName("Should return singleton power set whose only element is the empty set")
    public void testGenerateSingletonEmptySet() {
        Set<Set<Integer>> powerSet = PowerSetGenerator.generate(new HashSet<>());
        assertThat(powerSet).hasSize(1);
    }

    @Test
    @DisplayName("String presentation of power set should be equal to set string")
    public void testStringsEquality() {

        var set = ImmutableSet.of(1, 2);
        var testJoiner = new StringJoiner(",", "{", "}");
        testJoiner.add("{}")
                .add("{1}")
                .add("{2}")
                .add("{2,1}");
        Set<Set<Integer>> powerSet = PowerSetGenerator.generate(set);
        var powerSetJoiner = new StringJoiner(",", "{", "}");

        powerSet.forEach(subSet->{
            var innerSJ = new StringJoiner(",", "{", "}");
            subSet.forEach(subSetItem->{
                innerSJ.add(subSetItem.toString());
            });
            powerSetJoiner.add(innerSJ.toString());
        });
        assertEquals(powerSetJoiner.toString(), testJoiner.toString());
    }

    @Test
    @DisplayName("Each element of power set must be paired with tested set (sets are bijective)")
    public void testBijection() {

        Set<Set<Integer>> testedSet = new LinkedHashSet<>();
        testedSet.add(new HashSet<>());
        testedSet.add(ImmutableSet.of(1));
        testedSet.add(ImmutableSet.of(2));
        testedSet.add(ImmutableSet.of(1, 2));

        var set = ImmutableSet.of(1, 2);
        Set<Set<Integer>> powerSet = PowerSetGenerator.generate(set);

        assertThat(testedSet).hasSize(powerSet.size());
        assertThat(testedSet).containsAll(powerSet);
        assertThat(powerSet).containsAll(testedSet);
    }
}
