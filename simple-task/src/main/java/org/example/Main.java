package org.example;

import org.example.animals.Giraffe;
import org.example.animals.Lion;
import org.example.animals.Zebra;
import org.example.cages.AnimalNotFoundException;
import org.example.cages.CageFullException;
import org.example.cages.LionCage;
import org.example.cages.UngulateCage;

public class Main {
    public static void main(String[] args) {
        LionCage lions = new LionCage(2);
        lions.add(new Lion("Simba"));
        lions.add(new Lion("Nala"));
        System.out.println("Леви: " + lions.getOccupied() + "/" + lions.getMaxCapacity());

        // lions.add(new Zebra("Marty"));   // не скомпілюється: у вольєр для левів тільки леви

        try {
            lions.add(new Lion("Mufasa"));
        } catch (CageFullException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        UngulateCage ungulates = new UngulateCage(3);
        ungulates.add(new Zebra("Marty"));
        ungulates.add(new Giraffe("Melman"));
        System.out.println("Копитні: " + ungulates.getAnimals());

        try {
            ungulates.remove(new Zebra("Gloria"));
        } catch (AnimalNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}