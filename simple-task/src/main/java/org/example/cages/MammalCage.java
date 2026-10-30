package org.example.cages;

import org.example.animals.Mammal;

public abstract class MammalCage<T extends Mammal> extends Cage<T> {

    protected MammalCage(int maxCapacity) {
        super(maxCapacity);
    }
}