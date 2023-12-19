package com.github.anopensaucedev.libmcdevfabric.inspector;

public class Inspectable <T extends Object>{

    public T value;

    public Inspectable(T value){
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
