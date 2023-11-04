import Reflectors.Reflector;
import Reflectors.ReflectorTypes;
import Rotors.GetWiring;
import Rotors.Rotor;
import Rotors.RotorTypes;

import java.util.Scanner;

public class Enigma {

    static String terminationCharacter = "quit";

    public static void initialRotation(Rotor wheel1, Rotor wheel2, Rotor wheel3) {
        wheel3.rotate();
        if (wheel3.isAtNotchPos()) {
            wheel2.rotate();
            if (wheel2.isAtNotchPos()) {
                wheel1.rotate();
            }
        }
    }

    public static void main(String[] args) {
        Rotor wheel1 = new Rotor(RotorTypes.FIRST);
        Rotor wheel2 = new Rotor(RotorTypes.SECOND);
        Rotor wheel3 = new Rotor(RotorTypes.THIRD);
        Reflector reflector = new Reflector(ReflectorTypes.B);
        Plugboard plugboard = new Plugboard();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Debug mode ON");
            System.out.println("2. Debug mode OFF");
            System.out.println("3. Plugboard Settings");
            System.out.println("4. Initial Rotor Settings");
            System.out.println("5. Exit program");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            System.out.println("-----------------------------------------------");

            if ("1".equals(choice)) {
                encryption(true, wheel1, wheel2, wheel3, reflector, plugboard, scanner);
            } else if ("2".equals(choice)) {
                encryption(false, wheel1, wheel2, wheel3, reflector, plugboard, scanner);
            } else if ("3".equals(choice)) {
                plugboardMode(plugboard, scanner);
            } else if ("4".equalsIgnoreCase(choice)) {
                changeInitialPos(wheel1, wheel2, wheel3, scanner);
            } else if ("5".equalsIgnoreCase(choice) || "quit".equalsIgnoreCase(choice)) {
                System.out.println("Program terminated!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    
    }

    public static void plugboardMode(Plugboard plugboard, Scanner scanner) {
        System.out.println("Plugboard option selected");

        while (true) {
            System.out.println("Enter the first pair letter or quit to return to main menu");
            String input1 = scanner.nextLine();
            if (input1.equals(terminationCharacter)) {
                break;
            }
            
            System.out.println("Enter the target letter or quit to return to main menu");
            String input2 = scanner.nextLine();

             if (input2.equals(terminationCharacter)) {
                break; // Exit the loop
            }

            char requestedLetter = Character.toUpperCase(input1.charAt(0));
            char targetLetter = Character.toUpperCase(input2.charAt(0));

            plugboard.setNewConnection(requestedLetter, targetLetter);

        }

        System.out.println("Returning to the main menu.");
        System.out.println("-----------------------------------------------");
    }

    public static void encryption(boolean debug, Rotor wheel1, Rotor wheel2, Rotor wheel3, Reflector reflector, 
                            Plugboard plugboard, Scanner scanner) {
        
        int output;
        int mappedOutput;
        char letter;
        char result;

        while (true) {
            System.out.println("Enter letter to be encrypted");
            String userInput = scanner.nextLine();

            if (userInput.equals(terminationCharacter)) {
                break; // Exit the loop
            }

            letter = Character.toUpperCase(userInput.charAt(0));

            int input = GetWiring.getCharIndex(letter);
            input = getPlugboardMapping(input, plugboard);
            Enigma.initialRotation(wheel1, wheel2, wheel3);
            
            if (debug) {
                System.out.println("User Input: " + letter);
                String rotorPos = "" + GetWiring.getChar(wheel1.getOffset()) + GetWiring.getChar(wheel2.getOffset()) + GetWiring.getChar(wheel3.getOffset());
                System.out.println("Rotors Position: " + rotorPos);
                System.out.println("Plugboard Encryption: " + GetWiring.getChar(input));
            }

            output = wheel3.forwardEncryption(input);
            if (debug) 
                System.out.println("Wheel 3 Encryption: " + GetWiring.getChar(output));
            output = wheel2.forwardEncryption(output);
            if (debug) 
                System.out.println("Wheel 2 Encryption: " + GetWiring.getChar(output));
            output = wheel1.forwardEncryption(output);
            if (debug) 
                System.out.println("Wheel 1 Encryption: " + GetWiring.getChar(output));
            output = reflector.getMappedIndex(output);
            if (debug) 
                System.out.println("Reflector Encryption: " + GetWiring.getChar(output));
            output = wheel1.backwardEncryption(output);
            if (debug) 
                System.out.println("Wheel 1 Encryption: " + GetWiring.getChar(output));
            output = wheel2.backwardEncryption(output);
            if (debug) 
                System.out.println("Wheel 2 Encryption: " + GetWiring.getChar(output));
            output = wheel3.backwardEncryption(output);
            if (debug) 
                System.out.println("Wheel 3 Encryption: " + GetWiring.getChar(output));

            mappedOutput = getPlugboardMapping(output, plugboard);

            if (debug) 
                System.out.println("Plugboard Encryption: " + GetWiring.getChar(mappedOutput));

            result = (char)('A' + mappedOutput);
            System.out.println(letter + " ----> " + result);
            System.out.println("-----------------------------------------------");
            
        }

        System.out.println("Returning to the main menu.");
        System.out.println("-----------------------------------------------");
    }

    public static int getPlugboardMapping(int input, Plugboard plugboard) {
        int mappedOutput;
        if ((mappedOutput = plugboard.getMappedIndex(GetWiring.getChar(input))) == input) {
            mappedOutput = plugboard.getKeyByValue(GetWiring.getChar(input));
            if (mappedOutput == -1) {
                mappedOutput = input;
            }
        }

        return mappedOutput;
    }

    public static void changeInitialPos(Rotor wheel1, Rotor wheel2, Rotor wheel3, Scanner scanner) {
        System.out.println("Change initial rotor position option selected");

            System.out.println("Enter the intial position char for rotor3 (rightmost)");
            String input1 = scanner.nextLine();
            if (input1.equals(terminationCharacter)) {
                return;
            }
            
            System.out.println("Enter the intial position char for rotor2 (middle)");
            String input2 = scanner.nextLine();

             if (input2.equals(terminationCharacter)) {
                return;
            }

            System.out.println("Enter the intial position char for rotor1 (leftmost)");
            String input3 = scanner.nextLine();

             if (input3.equals(terminationCharacter)) {
                return;
            }

            char rotor3Offset = Character.toUpperCase(input1.charAt(0));
            char rotor2Offset = Character.toUpperCase(input2.charAt(0));
            char rotor1Offset = Character.toUpperCase(input3.charAt(0));

            wheel3.changeInitialPos(rotor3Offset);
            wheel2.changeInitialPos(rotor2Offset);
            wheel1.changeInitialPos(rotor1Offset);

        System.out.println("Initial positions for rotors are: "+ GetWiring.getChar(wheel1.getOffset()) + GetWiring.getChar(wheel2.getOffset()) + GetWiring.getChar(wheel3.getOffset()));
        System.out.println("Returning to the main menu.");
        System.out.println("-----------------------------------------------");
    }
}
