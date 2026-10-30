package org.example.cages;

import org.example.animals.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Cage<T extends Animal> {

    private final int maxCapacity;
    private final List<T> animals = new ArrayList<>();

    protected Cage(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Місткість має бути додатною");
        }
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getOccupied() {
        return animals.size();
    }

    public List<T> getAnimals() {
        return List.copyOf(animals);
    }

    public void add(T animal) {
        Objects.requireNonNull(animal, "animal");
        if (animals.size() >= maxCapacity) {
            throw new CageFullException("Вольєр заповнений: " + animal + " не вмістився");
        }
        animals.add(animal);
    }

    public void remove(T animal) {
        Objects.requireNonNull(animal, "animal");
        if (!animals.remove(animal)) {
            throw new AnimalNotFoundException("У вольєрі немає тварини: " + animal);
        }
    }
}