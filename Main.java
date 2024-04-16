import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
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
                        String regex = "^((a*)|(a*bb*))$";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.matches()) {
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
