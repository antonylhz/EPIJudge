package epi.hash;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;

import java.util.*;

public class LruCache {

    private LinkedHashMap<Integer, Integer> map;

    LruCache(final int capacity) {
        this.map = new LinkedHashMap<Integer, Integer>(capacity, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public Integer lookup(Integer key) {
        return map.getOrDefault(key, -1);
    }

    public void insert(Integer key, Integer value) {
        if (!map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.get(key);
        }
    }

    public Boolean erase(Object key) {
        if (map.containsKey(key)) {
            map.remove(key);
            return true;
        } else {
            return false;
        }
    }

    @EpiUserType(ctorParams = {String.class, int.class, int.class})
    public static class Op {
        String code;
        int arg1;
        int arg2;

        public Op(String code, int arg1, int arg2) {
            this.code = code;
            this.arg1 = arg1;
            this.arg2 = arg2;
        }
    }

    @EpiTest(testfile = "lru_cache.tsv")
    public static void runTest(List<Op> commands) throws TestFailureException {
        if (commands.isEmpty() || !commands.get(0).code.equals("LruCache")) {
            throw new RuntimeException("Expected LruCache as first command");
        }
        LruCache cache = new LruCache(commands.get(0).arg1);
        for (Op op : commands.subList(1, commands.size())) {
            int result;
            switch (op.code) {
                case "lookup":
                    result = cache.lookup(op.arg1);
                    if (result != op.arg2) {
                        throw new TestFailureException("Lookup: expected " +
                                String.valueOf(op.arg2) + ", got " +
                                String.valueOf(result));
                    }
                    break;
                case "insert":
                    cache.insert(op.arg1, op.arg2);
                    break;
                case "erase":
                    result = cache.erase(op.arg1) ? 1 : 0;
                    if (result != op.arg2) {
                        throw new TestFailureException("Erase: expected " +
                                String.valueOf(op.arg2) + ", got " +
                                String.valueOf(result));
                    }
                    break;
                default:
                    throw new RuntimeException("Unexpected command " + op.code);
            }
        }
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}
