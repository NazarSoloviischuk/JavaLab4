import org.example.animals.Eagle;
import org.example.animals.Giraffe;
import org.example.animals.Lion;
import org.example.animals.Zebra;
import org.example.cages.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CageTest {

    @Test
    void newCageIsEmpty() {
        LionCage cage = new LionCage(3);
        assertEquals(3, cage.getMaxCapacity());
        assertEquals(0, cage.getOccupied());
    }

    @Test
    void addIncreasesOccupiedCount() {
        LionCage cage = new LionCage(3);
        cage.add(new Lion("Simba"));
        cage.add(new Lion("Nala"));
        assertEquals(2, cage.getOccupied());
    }

    @Test
    void addToFullCageThrows() {
        LionCage cage = new LionCage(1);
        cage.add(new Lion("Simba"));
        assertThrows(CageFullException.class, () -> cage.add(new Lion("Nala")));
        assertEquals(1, cage.getOccupied());
    }

    @Test
    void removeExistingAnimal() {
        LionCage cage = new LionCage(2);
        Lion simba = new Lion("Simba");
        cage.add(simba);
        cage.remove(simba);
        assertEquals(0, cage.getOccupied());
    }

    @Test
    void removeMissingAnimalThrows() {
        LionCage cage = new LionCage(2);
        cage.add(new Lion("Simba"));
        assertThrows(AnimalNotFoundException.class, () -> cage.remove(new Lion("Nala")));
        assertEquals(1, cage.getOccupied());
    }

    @Test
    void ungulateCageAcceptsZebraAndGiraffe() {
        UngulateCage cage = new UngulateCage(3);
        cage.add(new Zebra("Marty"));
        cage.add(new Giraffe("Melman"));
        assertEquals(2, cage.getOccupied());
    }

    @Test
    void birdCageAcceptsEagle() {
        BirdCage cage = new BirdCage(1);
        cage.add(new Eagle("Sam"));
        assertEquals(1, cage.getOccupied());
    }

    @Test
    void nullAnimalThrows() {
        LionCage cage = new LionCage(2);
        assertThrows(NullPointerException.class, () -> cage.add(null));
        assertThrows(NullPointerException.class, () -> cage.remove(null));
    }

    @Test
    void invalidCapacityThrows() {
        assertThrows(IllegalArgumentException.class, () -> new LionCage(0));
    }

    @Test
    void getAnimalsReturnsUnmodifiableCopy() {
        LionCage cage = new LionCage(2);
        cage.add(new Lion("Simba"));
        List<Lion> copy = cage.getAnimals();
        assertThrows(UnsupportedOperationException.class, () -> copy.add(new Lion("Nala")));
        assertEquals(1, cage.getOccupied());
    }
}