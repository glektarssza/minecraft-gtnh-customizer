package com.glektarssza.gtnh_customizer.utils;

public class ImmutableTuple<A, B> {
    protected A first;
    protected B second;

    public ImmutableTuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return this.first;
    }

    public B getSecond() {
        return this.second;
    }
}
