package epi.string;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestTimer;

import java.util.ArrayList;
import java.util.List;

public class ReplaceAndRemove {

    public static int replaceAndRemove(int size, char[] s) {
        return replace(remove(size, s), s);
    }

    private static int replace(int size, char[] s) {
        int newSize = size;
        for (int i = 0; i < size; i++) {
            if (s[i] == 'a') {
                newSize++;
            }
        }
        int t = newSize - 1;
        for (int i = size - 1; i >= 0; i--) {
            if (s[i] == 'a') {
                s[t--] = 'd';
                s[t--] = 'd';
            } else {
                s[t--] = s[i];
            }
        }
        return newSize;
    }

    private static int remove(int size, char[] s) {
        int t = 0;
        for (int i = 0; i < size; i++) {
            if (s[i] != 'b') {
                s[t++] = s[i];
            }
        }
        return t;
    }

    @EpiTest(testfile = "replace_and_remove.tsv")
    public static List<String>
    replaceAndRemoveWrapper(TestTimer timer, Integer size, List<String> s) {
        char[] sCopy = new char[s.size()];
        for (int i = 0; i < size; ++i) {
            if (!s.get(i).isEmpty()) {
                sCopy[i] = s.get(i).charAt(0);
            }
        }
        timer.start();
        Integer resSize = replaceAndRemove(size, sCopy);
        timer.stop();

        List<String> result = new ArrayList<>();
        for (int i = 0; i < resSize; ++i) {
            result.add(Character.toString(sCopy[i]));
        }
        return result;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}
