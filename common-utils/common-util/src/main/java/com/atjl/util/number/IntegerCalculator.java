package com.atjl.util.number;


public class IntegerCalculator implements Calculator<Integer> {

    private Integer filterNull(Integer n) {
        if (n == null) {
            n = 0;
        }
        return n;
    }

    @Override
    public Integer add(Integer n1, Integer n2) {
        n1 = filterNull(n1);
        n2 = filterNull(n2);
        return n1 + n2;
    }

    @Override
    public Integer minus(Integer n1, Integer n2) {
        n1 = filterNull(n1);
        n2 = filterNull(n2);
        return n1 - n2;
    }

    @Override
    public Integer multipli(Integer n1, Integer n2) {
        n1 = filterNull(n1);
        n2 = filterNull(n2);
        return n1 * n2;
    }

    @Override
    public Integer divide(Integer n1, Integer n2) {
        n1 = filterNull(n1);
        n2 = filterNull(n2);
        return n1 / n2;
    }
}
