package by.zvezdina.xmltask.builder;

import by.zvezdina.xmltask.entity.Flower;
import by.zvezdina.xmltask.exception.FlowerXmlException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFlowerBuilder {

    protected Set<Flower> flowers;

    public AbstractFlowerBuilder() {
        flowers = new HashSet<>();
    }

    public AbstractFlowerBuilder(Set<Flower> flowers) {
        this.flowers = flowers;
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public abstract void buildSetFlowers(String filename) throws FlowerXmlException;
}
