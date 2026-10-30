package org.example.zoo;

import org.example.animals.Animal;
import org.example.cages.Cage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Zoo {

    private final List<Cage<? extends Animal>> cages = new ArrayList<>();

    public void addCage(Cage<? extends Animal> cage) {
        Objects.requireNonNull(cage, "cage");
        cages.add(cage);
    }

    public int getCountOfAnimals() {
        int total = 0;
        for (Cage<? extends Animal> cage : cages) {
            total += cage.getOccupied();
        }
        return total;
    }

    public List<Animal> getAllAnimals() {
        List<Animal> result = new ArrayList<>();
        for (Cage<? extends Animal> cage : cages) {
            result.addAll(cage.getAnimals());
        }
        return result;
    }
}