package com.github.anopensaucedev.libmcdevfabric.inspector;

public class Inspectable <T extends Object>{

    public String name;
    private T value;

    public Inspectable(T value, String name){
        this.name = name;
        this.value = value;
        //InspectorWindow.AddInspectable(this);
    }

    public T getValue() {
        return value;
    }

    public T setValue(T newValue){
        value = value;
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
