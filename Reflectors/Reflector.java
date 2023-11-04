package Reflectors;

public class Reflector {
    int[] reflectorOrder;

    public Reflector(ReflectorTypes type) {
        this.reflectorOrder = type.getReflectorOrder();
    }

    public int getMappedIndex(int input) {
        return reflectorOrder[input];
    }
}
