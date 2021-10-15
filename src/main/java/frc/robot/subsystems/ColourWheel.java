package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Ports.ColourWheel.*;

public class ColourWheel extends SubsystemBase {
    private final VictorSPX motor = new VictorSPX(MOTOR);
    private final ColorSensorV3 colourSensor = new ColorSensorV3(I2C.Port.kOnboard);
    private final ColorMatch colourMatcher = new ColorMatch();
    private final static ColourWheel INSTANCE = new ColourWheel();

    @SuppressWarnings("WeakerAccess")
    public static ColourWheel getInstance() {
        return INSTANCE;
    }

    private ColourWheel() {
        motor.setInverted(MOTOR_INVERTED);
        colourMatcher.addColorMatch(ColourType.RED.value);
        colourMatcher.addColorMatch(ColourType.GREEN.value);
        colourMatcher.addColorMatch(ColourType.BLUE.value);
        colourMatcher.addColorMatch(ColourType.YELLOW.value);
        colourMatcher.addColorMatch(ColourType.BLACK.value);
    }

    public void setPower(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    public ColourType getColour() {
        ColorMatchResult result = colourMatcher.matchClosestColor(colourSensor.getColor());
        if (result.color == ColourType.RED.value) return ColourType.RED;
        else if (result.color == ColourType.GREEN.value) return ColourType.GREEN;
        else if (result.color == ColourType.BLUE.value) return ColourType.BLUE;
        else if (result.color == ColourType.YELLOW.value) return ColourType.YELLOW;
        else return ColourType.BLACK;
    }

    public enum ColourType {
        RED(Color.kRed), GREEN(Color.kGreen), BLUE(Color.kBlue), YELLOW(Color.kYellow), BLACK(Color.kBlack);

        private final Color value;

        ColourType(Color value) {
            this.value = value;
        }
    }
}
