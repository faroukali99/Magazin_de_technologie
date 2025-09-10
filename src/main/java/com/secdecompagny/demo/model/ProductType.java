package com.secdecompagny.demo.model;

public enum ProductType {
    SMARTPHONE("Smartphone"),
    TABLETTE("Tablette"),
    PC("Ordinateur"),
    TV("Télévision"),
    CAMERA("Appareil photo"),
    HEADPHONE("Casque audio"),
    SPEAKER("Enceinte");

    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
