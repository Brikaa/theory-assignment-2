import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    private static final char EPSILON = 'ε';

    private static boolean fsa(
            final HashMap<String, HashMap<Character, String[]>> transitions,
            final String startState,
            final String[] finalStates,
            final String str,
            final int idx) {
        // States array and alphabet array are implicit in the transitions
        final HashMap<Character, String[]> possibleNextStates = transitions.get(startState);

        if (idx == str.length())
            return Arrays.asList(finalStates).contains(startState);

        final char currentTransition = str.charAt(idx);
        if (!transitions.containsKey(startState) || (!possibleNextStates.containsKey(currentTransition)
                && !possibleNextStates.containsKey(EPSILON)))
            return false;

        boolean found = false;

        if (possibleNextStates.containsKey(EPSILON))
            for (String state : possibleNextStates.get(EPSILON))
                found |= fsa(transitions, state, finalStates, str, idx);

        if (possibleNextStates.containsKey(currentTransition))
            for (String state : possibleNextStates.get(currentTransition))
                found |= fsa(transitions, state, finalStates, str, idx + 1);

        return found;
    }

    private static boolean fsa(
            final HashMap<String, HashMap<Character, String[]>> transitions,
            final String startState,
            final String[] finalStates,
            final String str) {
        return fsa(transitions, startState, finalStates, str, 0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")));
        FileWriter writer = new FileWriter("output.txt");

        boolean expectingProblemNumber = true;
        String line;
        String problemNumber = "";
        while ((line = reader.readLine()) != null) {
            if (line.equals("")) {
                continue;
            } else if (expectingProblemNumber) {
                expectingProblemNumber = false;
                problemNumber = line;
                writer.write(problemNumber + "\n");
            } else if (line.toLowerCase().equals("end")) {
                writer.write("x\n\n");
                expectingProblemNumber = true;
            } else {
                switch (problemNumber) {
                    case "1": {
                        HashMap<String, HashMap<Character, String[]>> transitions = new HashMap<>() {
                            {
                                put("A", new HashMap<>() {
                                    {
                                        put('a', new String[] { "A" });
                                        put('b', new String[] { "B" });
                                    }
                                });
                                put("B", new HashMap<>() {
                                    {
                                        put('a', new String[] { "C" });
                                        put('b', new String[] { "B" });
                                    }
                                });
                                put("C", new HashMap<>() {
                                    {
                                        put('a', new String[] { "C" });
                                        put('b', new String[] { "C" });
                                    }
                                });
                            }
                        };
                        if (fsa(transitions, "A", new String[] { "A", "B" }, line)) {
                            writer.write("True\n");
                        } else {
                            writer.write("False\n");
                        }
                        break;
                    }
                    case "2": {
                        HashMap<String, HashMap<Character, String[]>> transitions = new HashMap<>() {
                            {
                                put("A", new HashMap<>() {
                                    {
                                        put('0', new String[] { "B" });
                                        put('1', new String[] { "D" });
                                    }
                                });
                                put("B", new HashMap<>() {
                                    {
                                        put('0', new String[] { "C" });
                                        put('1', new String[] { "E" });
                                    }
                                });
                                put("C", new HashMap<>() {
                                    {
                                        put('0', new String[] { "B" });
                                        put('1', new String[] { "D" });
                                    }
                                });
                                put("D", new HashMap<>() {
                                    {
                                        put('0', new String[] { "E" });
                                        put('1', new String[] { "E" });
                                    }
                                });
                                put("E", new HashMap<>() {
                                    {
                                        put('0', new String[] { "E" });
                                        put('1', new String[] { "E" });
                                    }
                                });
                            }
                        };
                        if (fsa(transitions, "A", new String[] { "D" }, line)) {
                            writer.write("True\n");
                        } else {
                            writer.write("False\n");
                        }
                        break;
                    }
                    case "3": {
                        HashMap<String, HashMap<Character, String[]>> transitions = new HashMap<>() {
                            {
                                put("A", new HashMap<>() {
                                    {
                                        put('x', new String[] { "B" });
                                        put('y', new String[] { "A" });
                                    }
                                });
                                put("B", new HashMap<>() {
                                    {
                                        put('x', new String[] { "A" });
                                        put('y', new String[] { "B" });
                                    }
                                });
                            }
                        };
                        if (fsa(transitions, "A", new String[] { "B" }, line)) {
                            writer.write("True\n");
                        } else {
                            writer.write("False\n");
                        }
                        break;
                    }
                    case "4": {
                        HashMap<String, HashMap<Character, String[]>> transitions = new HashMap<>() {
                            {
                                put("A", new HashMap<>() {
                                    {
                                        put('a', new String[] { "B" });
                                        put('b', new String[] { "D" });
                                    }
                                });
                                put("B", new HashMap<>() {
                                    {
                                        put('a', new String[] { "C" });
                                        put('b', new String[] { "B" });
                                    }
                                });
                                put("C", new HashMap<>() {
                                    {
                                        put('a', new String[] { "C" });
                                        put('b', new String[] { "B" });
                                    }
                                });
                                put("D", new HashMap<>() {
                                    {
                                        put('a', new String[] { "D" });
                                        put('b', new String[] { "E" });
                                    }
                                });
                                put("E", new HashMap<>() {
                                    {
                                        put('a', new String[] { "D" });
                                        put('b', new String[] { "E" });
                                    }
                                });
                            }
                        };
                        if (fsa(transitions, "A", new String[] { "C", "E" }, line)) {
                            writer.write("True\n");
                        } else {
                            writer.write("False\n");
                        }
                        break;
                    }
                    case "5": {
                        HashMap<String, HashMap<Character, String[]>> transitions = new HashMap<>() {
                            {
                                put("A", new HashMap<>() {
                                    {
                                        put('0', new String[] { "B" });
                                        put('1', new String[] { "C" });
                                    }
                                });
                                put("B", new HashMap<>() {
                                    {
                                        put('0', new String[] { "D" });
                                        put('1', new String[] { "C" });
                                    }
                                });
                                put("C", new HashMap<>() {
                                    {
                                        put('0', new String[] { "D" });
                                        put('1', new String[] { "C" });
                                    }
                                });
                                put("D", new HashMap<>() {
                                    {
                                        put('0', new String[] { "E" });
                                        put('1', new String[] { "C" });
                                    }
                                });
                                put("E", new HashMap<>() {
                                    {
                                        put('0', new String[] { "E" });
                                        put('1', new String[] { "C" });
                                    }
                                });
                            }
                        };
                        if (fsa(transitions, "A", new String[] { "B", "E" }, line)) {
                            writer.write("True\n");
                        } else {
                            writer.write("False\n");
                        }
                        break;
                    }
                    case "6": {
                        HashMap<String, HashMap<Character, String[]>> transitions = new HashMap<>() {
                            {
                                put("A", new HashMap<>() {
                                    {
                                        put('0', new String[] { "E" });
                                        put('1', new String[] { "B" });
                                    }
                                });
                                put("B", new HashMap<>() {
                                    {
                                        put('0', new String[] { "E" });
                                        put('1', new String[] { "C" });
                                    }
                                });
                                put("C", new HashMap<>() {
                                    {
                                        put('0', new String[] { "E" });
                                        put('1', new String[] { "D" });
                                    }
                                });
                                put("D", new HashMap<>() {
                                    {
                                        put('0', new String[] { "E" });
                                        put('1', new String[] { "E" });
                                    }
                                });
                                put("E", new HashMap<>() {
                                    {
                                        put('0', new String[] { "E" });
                                        put('1', new String[] { "E" });
                                    }
                                });
                            }
                        };
                        if (fsa(transitions, "A", new String[] { "A", "B", "E" }, line)) {
                            writer.write("True\n");
                        } else {
                            writer.write("False\n");
                        }
                        break;
                    }
                    case "7": {
                        HashMap<String, HashMap<Character, String[]>> transitions = new HashMap<>() {
                            {
                                put("E", new HashMap<>() {
                                    {
                                        put(EPSILON, new String[] { "A", "F" });
                                    }
                                });
                                put("A", new HashMap<>() {
                                    {
                                        put('0', new String[] { "A", "B" });
                                    }
                                });
                                put("B", new HashMap<>() {
                                    {
                                        put('1', new String[] { "C" });
                                    }
                                });
                                put("C", new HashMap<>() {
                                    {
                                        put('0', new String[] { "D" });
                                        put('1', new String[] { "C" });
                                    }
                                });
                                put("D", new HashMap<>() {
                                    {
                                        put(EPSILON, new String[] { "C" });
                                    }
                                });
                                put("F", new HashMap<>() {
                                    {
                                        put('1', new String[] { "F", "G" });
                                    }
                                });
                                put("G", new HashMap<>() {
                                    {
                                        put('0', new String[] { "H" });
                                    }
                                });
                                put("H", new HashMap<>() {
                                    {
                                        put('1', new String[] { "I" });
                                        put('0', new String[] { "H" });
                                    }
                                });
                                put("I", new HashMap<>() {
                                    {
                                        put(EPSILON, new String[] { "H" });
                                    }
                                });
                            }
                        };
                        if (fsa(transitions, "E", new String[] { "D", "I" }, line)) {
                            writer.write("True\n");
                        } else {
                            writer.write("False\n");
                        }
                        break;
                    }
                    case "8": {
                        HashMap<String, HashMap<Character, String[]>> transitions = new HashMap<>() {
                            {
                                put("E", new HashMap<>() {
                                    {
                                        put(EPSILON, new String[] { "A", "F" });
                                    }
                                });
                                put("A", new HashMap<>() {
                                    {
                                        put('0', new String[] { "A" });
                                        put('1', new String[] { "A", "B" });
                                    }
                                });
                                put("B", new HashMap<>() {
                                    {
                                        put('0', new String[] { "C" });
                                    }
                                });
                                put("C", new HashMap<>() {
                                    {
                                        put('1', new String[] { "D" });
                                    }
                                });
                                put("D", new HashMap<>() {
                                    {
                                        put('0', new String[] { "D" });
                                        put('1', new String[] { "D" });
                                    }
                                });
                                put("F", new HashMap<>() {
                                    {
                                        put('1', new String[] { "F" });
                                        put('0', new String[] { "F", "G" });
                                    }
                                });
                                put("G", new HashMap<>() {
                                    {
                                        put('1', new String[] { "H" });
                                    }
                                });
                                put("H", new HashMap<>() {
                                    {
                                        put('0', new String[] { "I" });
                                    }
                                });
                                put("I", new HashMap<>() {
                                    {
                                        put('0', new String[] { "I" });
                                        put('1', new String[] { "I" });
                                    }
                                });
                            }
                        };
                        if (fsa(transitions, "E", new String[] { "D", "I" }, line)) {
                            writer.write("True\n");
                        } else {
                            writer.write("False\n");
                        }
                        break;
                    }
                    case "9": {
                        HashMap<String, HashMap<Character, String[]>> transitions = new HashMap<>() {
                            {
                                put("A", new HashMap<>() {
                                    {
                                        put(EPSILON, new String[] { "B", "E" });
                                    }
                                });
                                put("B", new HashMap<>() {
                                    {
                                        put('0', new String[] { "C" });
                                    }
                                });
                                put("C", new HashMap<>() {
                                    {
                                        put('1', new String[] { "D" });
                                    }
                                });
                                put("D", new HashMap<>() {
                                    {
                                        put(EPSILON, new String[] { "B" });
                                    }
                                });
                                put("E", new HashMap<>() {
                                    {
                                        put('1', new String[] { "F" });
                                    }
                                });
                                put("F", new HashMap<>() {
                                    {
                                        put('0', new String[] { "G" });
                                    }
                                });
                                put("G", new HashMap<>() {
                                    {
                                        put(EPSILON, new String[] { "E" });
                                    }
                                });
                            }
                        };
                        if (fsa(transitions, "A", new String[] { "A", "B", "C", "D", "E", "F", "G" }, line)) {
                            writer.write("True\n");
                        } else {
                            writer.write("False\n");
                        }
                        break;
                    }
                    case "10": {
                        HashMap<String, HashMap<Character, String[]>> transitions = new HashMap<>() {
                            {
                                put("A", new HashMap<>() {
                                    {
                                        put('1', new String[] { "A" });
                                        put('0', new String[] { "B" });
                                    }
                                });
                                put("B", new HashMap<>() {
                                    {
                                        put('1', new String[] { "A" });
                                    }
                                });
                            }
                        };
                        if (fsa(transitions, "A", new String[] { "A" }, line)) {
                            writer.write("True\n");
                        } else {
                            writer.write("False\n");
                        }
                        break;
                    }
                }
            }
        }

        writer.close();
        reader.close();
    }
}
