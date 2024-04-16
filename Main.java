import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    private static boolean fsa(
            final HashMap<String, HashMap<Character, String[]>> transitions,
            final String startState,
            final String[] finalStates,
            final String str,
            final int idx) {
        // States array and alphabet array are implicit in the transitions
        if (idx == str.length())
            return Arrays.asList(finalStates).contains(startState);
        final char currentTransition = str.charAt(idx);
        if (!transitions.containsKey(startState) || !transitions.get(startState).containsKey(currentTransition))
            return false;
        boolean found = false;
        for (String state : transitions.get(startState).get(currentTransition))
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
                }
            }
        }

        writer.close();
        reader.close();
    }
}
