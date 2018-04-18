package epi.recursion;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTestHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class PowerSet {
    @EpiTest(testfile = "power_set.tsv")

    public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int num : inputSet) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> list = res.get(i);
                List<Integer> listWithNum = new ArrayList<>(list);
                listWithNum.add(num);
                res.add(listWithNum);
            }
        }
        return res;
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>,
            List<List<Integer>>> comp = (expected, result) -> {
        if (result == null) {
            return false;
        }
        for (List<Integer> l : expected) {
            Collections.sort(l);
        }
        expected.sort(new LexicographicalListComparator<>());
        for (List<Integer> l : result) {
            Collections.sort(l);
        }
        result.sort(new LexicographicalListComparator<>());
        return expected.equals(result);
    };

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}
