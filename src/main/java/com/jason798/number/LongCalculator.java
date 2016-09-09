package com.jason798.number;


public class LongCalculator implements Calculator<Long>{

    private Long filterNull(Long n){
        if(n==null){
            n = 0L;
        }
        return n;
    }
    @Override
    public Long add(Long n1, Long n2) {
        n1 = filterNull(n1);
        n2 = filterNull(n2);
        return n1+n2;
    }

    @Override
    public Long minus(Long n1, Long n2) {
        n1 = filterNull(n1);
        n2 = filterNull(n2);
        return n1-n2;
    }

    @Override
    public Long multipli(Long n1, Long n2) {
        n1 = filterNull(n1);
        n2 = filterNull(n2);
        return n1*n2;
    }

    @Override
    public Long divide(Long n1, Long n2) {
        n1 = filterNull(n1);
        n2 = filterNull(n2);
        return n1/n2;
    }
}
