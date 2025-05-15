package com.glektarssza.gtnh_customizer.utils;

public class MutableTuple<A, B> extends ImmutableTuple<A, B> {
    public MutableTuple(A first, B second) {
        super(first, second);
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public void setSecond(B second) {
        this.second = second;
    }
}
