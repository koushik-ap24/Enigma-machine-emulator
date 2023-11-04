package Rotors;

public class Rotor {
    int offset;
    int[] rotorOrder;
    RotorTypes type;

    public Rotor(RotorTypes type) {
        this.offset = 0;
        rotorOrder = type.getRotorOrder();
        this.type = type;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isAtNotchPos() {
        return (offset == type.getNotchIndex());
    }

    public void rotate() {
        int firstNum = rotorOrder[0];
        for (int i = 0; i < rotorOrder.length - 1; i++) {
            rotorOrder[i] = rotorOrder[i+1];
        }

        rotorOrder[rotorOrder.length - 1] = firstNum;
        offset = (offset + 1) % rotorOrder.length;
    }

    public int forwardEncryption(int input) {
        int mappedChar = rotorOrder[input];
        mappedChar -= offset;
        if (mappedChar < 0) {
            return (rotorOrder.length + mappedChar);
        }

        return mappedChar;
    }

    public int backwardEncryption(int input) {
        int mappedChar = (input + offset) % rotorOrder.length;
        return indexOf(mappedChar);
    }

    private int indexOf(int target) {
        for (int i = 0; i < rotorOrder.length; i++) {
            if (rotorOrder[i] == target) {
                return i; // Return the index if the target is found
            }
        }
        return -1; // Return -1 if the target is not found in the array
    }

    public void changeInitialPos(char c) {
        int offset = GetWiring.getCharIndex(c);
        for (int i = 0; i < offset; i++) {
            rotate();
        }
    }
}