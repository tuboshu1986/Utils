package com.test.util;

import java.util.BitSet;

public class BitSetTest {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet(5);
        bitSet.set(2, true);
        System.out.println(bitSet.cardinality());
        

        BitSet bitSet1 = new BitSet(3);
        bitSet1.set(0, true);
        bitSet1.set(1, false);
        bitSet1.set(2, true);
        BitSet bitSet2 = new BitSet(3);
        bitSet2.set(2, true);
        System.out.println(bitSet1);
        System.out.println(bitSet1.equals(bitSet2));
        
        bitSet1.flip(0, 4);
        System.out.println(bitSet1);
    }
}
