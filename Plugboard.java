import java.util.HashMap;
import java.util.Map;

import Rotors.GetWiring;

public class Plugboard {
    private Map<Integer, Integer> connections;

    public Plugboard() {
        connections = new HashMap<>();
    }

    public int getKeyByValue(char key) {
        int value = GetWiring.getCharIndex(key);
        for (Map.Entry<Integer, Integer> entry : connections.entrySet()) {
            if (value == entry.getValue()) {
                return entry.getKey();
            }
        }
        return -1; // Return null if the value is not found in the map
    }

    public void setNewConnection(char start, char target) {
        int startIndex = GetWiring.getCharIndex(start);
        int targetIndex = GetWiring.getCharIndex(target);
        if (connections.containsKey(startIndex) || getKeyByValue(start) != -1 || 
                connections.containsKey(targetIndex) || getKeyByValue(target) != -1) {
            return;
        }

        connections.put(startIndex, targetIndex);
    }

    public int getMappedIndex(char key) {
        return connections.get(GetWiring.getCharIndex(key)) == null ? GetWiring.getCharIndex(key) 
                : connections.get(GetWiring.getCharIndex(key));
    }
}
