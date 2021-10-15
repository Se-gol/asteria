package frc.robot.value_tuner;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ValueTuner extends SubsystemBase {

    private File file = new File("C:\\Users\\saarz\\Desktop\\Everything\\asteria\\src\\main\\java\\frc\\robot\\value_tuner\\values.txt");
    private final String key;
    private double value;

    public ValueTuner(String key, double value) {
        this.key = key;
        this.value = value;
        try {
            addValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addValue() throws IOException {
        File fileToBeModified = new File("C:\\Users\\saarz\\Desktop\\Everything\\asteria\\src\\main\\java\\frc\\robot\\value_tuner\\values.txt");
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        reader = new BufferedReader(new FileReader(fileToBeModified));
        String line = reader.readLine();
        while (line != null) {
            oldContent = oldContent + line + System.lineSeparator();

            line = reader.readLine();
        }
        String newContent = oldContent + System.lineSeparator() + key + ": " + value;
        writer = new FileWriter(fileToBeModified);
        writer.write(newContent);
        reader.close();
        writer.close();
    }

    private void updateValue() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains(this.key)) {
                if (line.indexOf(':') + 2 < line.length())
                    value = Double.parseDouble(line.substring(line.indexOf(':') + 2));
            }
            System.out.println(line);
        }
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public void periodic() {
        try {
            updateValue();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
