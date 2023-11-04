package Reflectors;

public enum ReflectorTypes {
    B(1, new int[]{24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19}),
    C(2, new int[]{5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11});

    private int reflectorNum;
    private int[] reflectorOrder;

    private ReflectorTypes(int reflectorNum, int[] reflectorOrder) {
        this.reflectorNum = reflectorNum;
        this.reflectorOrder = reflectorOrder;
    }

    public int getReflectorNum() {
        return reflectorNum;
    }

    public int[] getReflectorOrder() {
        return reflectorOrder;
    }

    
}
