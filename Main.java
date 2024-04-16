import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    private static boolean dfa(
            HashMap<String, HashMap<Character, String>> transitions,
            String start_state,
            String[] final_states,
            String str) {
        String current_state = start_state;
        for (char chr : str.toCharArray()) {
            if (!transitions.containsKey(current_state)
                    || !transitions.get(current_state).containsKey(chr)) {
                return false;
            } else {
                current_state = transitions.get(current_state).get(chr);
            }
        }

        return Arrays.asList(final_states).contains(current_state);
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
                        HashMap<String, HashMap<Character, String>> transitions = new HashMap<>() {
                            {
                                put("A", new HashMap<>() {
                                    {
                                        put('a', "A");
                                        put('b', "B");
                                    }
                                });
                                put("B", new HashMap<>() {
                                    {
                                        put('a', "C");
                                        put('b', "B");
                                    }
                                });
                                put("C", new HashMap<>() {
                                    {
                                        put('a', "C");
                                        put('b', "C");
                                    }
                                });
                            }
                        };
                        if (dfa(transitions, "A", new String[] { "A", "B" }, line)) {
                            writer.write("True\n");
                        } else {
                            writer.write("False\n");
                        }
                        break;
                    }
                    case "2": {
                        HashMap<String, HashMap<Character, String>> transitions = new HashMap<>() {
                            {
                                put("A", new HashMap<>() {
                                    {
                                        put('0', "B");
                                        put('1', "D");
                                    }
                                });
                                put("B", new HashMap<>() {
                                    {
                                        put('0', "C");
                                        put('1', "E");
                                    }
                                });
                                put("C", new HashMap<>() {
                                    {
                                        put('0', "B");
                                        put('1', "D");
                                    }
                                });
                                put("D", new HashMap<>() {
                                    {
                                        put('0', "E");
                                        put('1', "E");
                                    }
                                });
                                put("E", new HashMap<>() {
                                    {
                                        put('0', "E");
                                        put('1', "E");
                                    }
                                });
                            }
                        };
                        if (dfa(transitions, "A", new String[] { "D" }, line)) {
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
