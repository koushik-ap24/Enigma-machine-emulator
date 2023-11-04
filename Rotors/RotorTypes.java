package Rotors;

public enum RotorTypes {
    FIRST(1, 17, new int[]{4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9}),
    SECOND(2, 5, new int[]{0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4}),
    THIRD(3, 22, new int[]{1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14});

    private int rotorNum;
    private int[] rotorOrder;
    private int notchIndex;

    private RotorTypes(int rotorNum, int notchIndex, int[] rotorOrder) {
        this.rotorNum = rotorNum;
        this.notchIndex = notchIndex;
        this.rotorOrder = rotorOrder;
    }

    public int getRotorNum() {
        return rotorNum;
    }


    public int getNotchIndex() {
        return notchIndex;
    }

    public int[] getRotorOrder() {
        return rotorOrder;
    }
}
