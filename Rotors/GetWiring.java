package Rotors;

public class GetWiring {
    static String letterOrder = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String rotorOrder = "FVPJIAOYEDRZXWGCTKUQSBNMHL";

    public static int getCharIndex(char c) {
        return letterOrder.indexOf(c);
    }

    public static char getChar(int index) {
        return ((char)('A' + index));
    }

    public static void main(String[] args) {
        int[] indexOrder = new int[letterOrder.length()];
        for (int i = 0; i < letterOrder.length(); i++) {
            indexOrder[i] = letterOrder.indexOf(rotorOrder.charAt(i));
        }

        for (int num: indexOrder) {
            System.out.print(num + ", ");
        }
        System.out.println("");
    }
}
