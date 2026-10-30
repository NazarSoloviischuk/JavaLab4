import org.example.animals.Animal;
import org.example.animals.Eagle;
import org.example.animals.Lion;
import org.example.animals.Zebra;
import org.example.cages.BirdCage;
import org.example.cages.LionCage;
import org.example.cages.UngulateCage;
import org.example.zoo.Zoo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {

    @Test
    void emptyZooHasNoAnimals() {
        assertEquals(0, new Zoo().getCountOfAnimals());
    }

    @Test
    void countsAnimalsInAllCages() {
        LionCage lions = new LionCage(3);
        lions.add(new Lion("Simba"));
        lions.add(new Lion("Nala"));
        UngulateCage ungulates = new UngulateCage(3);
        ungulates.add(new Zebra("Marty"));
        BirdCage birds = new BirdCage(2);
        birds.add(new Eagle("Sam"));

        Zoo zoo = new Zoo();
        zoo.addCage(lions);
        zoo.addCage(ungulates);
        zoo.addCage(birds);

        assertEquals(4, zoo.getCountOfAnimals());
    }

    @Test
    void getAllAnimalsReturnsEveryAnimal() {
        Lion simba = new Lion("Simba");
        Zebra marty = new Zebra("Marty");
        LionCage lions = new LionCage(2);
        lions.add(simba);
        UngulateCage ungulates = new UngulateCage(2);
        ungulates.add(marty);

        Zoo zoo = new Zoo();
        zoo.addCage(lions);
        zoo.addCage(ungulates);

        List<Animal> all = zoo.getAllAnimals();
        assertEquals(2, all.size());
        assertTrue(all.contains(simba));
        assertTrue(all.contains(marty));
    }

    @Test
    void addNullCageThrows() {
        Zoo zoo = new Zoo();
        assertThrows(NullPointerException.class, () -> zoo.addCage(null));
    }
}