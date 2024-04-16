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
            final String start_state,
            final String[] final_states,
            final String str,
            final int idx) {
        // States array and alphabet array are implicit in the transitions
        if (idx == str.length())
            return Arrays.asList(final_states).contains(start_state);
        final char current_transition = str.charAt(idx);
        if (!transitions.containsKey(start_state) || !transitions.get(start_state).containsKey(current_transition))
            return false;
        boolean found = false;
        for (String state : transitions.get(start_state).get(current_transition))
            found |= fsa(transitions, state, final_states, str, idx + 1);
        return found;
    }

    private static boolean fsa(
            final HashMap<String, HashMap<Character, String[]>> transitions,
            final String start_state,
            final String[] final_states,
            final String str) {
        return fsa(transitions, start_state, final_states, str, 0);
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
                }
            }
        }

        writer.close();
        reader.close();
    }
}
