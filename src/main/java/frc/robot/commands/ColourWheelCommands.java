package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColourWheel.ColourType;
import frc.robot.subsystems.ColourWheel;

import javax.swing.plaf.IconUIResource;

import static frc.robot.Constants.ColourWheel.*;

public class ColourWheelCommands {
    private static final ColourWheel colourWheel = ColourWheel.getInstance();

    public static class ReachColour extends CommandBase {
        private final ColourType colour;
        private int sign = 1;

        public ReachColour(ColourType colour) {
            this.colour = colour;
            addRequirements(colourWheel);
        }

        @Override
        public void initialize() {
            ColourType initialColour = colourWheel.getColour();
            /*          if (initialColour == ColourType.RED && colour == ColourType.GREEN) sign = -1;
            if (initialColour == ColourType.RED && colour == ColourType.BLUE) sign = 1;
            if (initialColour == ColourType.RED && colour == ColourType.YELLOW) sign = 1;
            if (initialColour == ColourType.GREEN && colour == ColourType.RED) sign = 1;
            if (initialColour == ColourType.GREEN && colour == ColourType.BLUE) sign = -1;
            if (initialColour == ColourType.GREEN && colour == ColourType.YELLOW) sign = 1;
            if (initialColour == ColourType.BLUE && colour == ColourType.RED) sign = 1;
            if (initialColour == ColourType.BLUE && colour == ColourType.GREEN) sign = 1;
            if (initialColour == ColourType.BLUE && colour == ColourType.YELLOW) sign = -1;
            if (initialColour == ColourType.YELLOW && colour == ColourType.RED) sign = -1;
            if (initialColour == ColourType.YELLOW && colour == ColourType.GREEN) sign = 1;
            if (initialColour == ColourType.YELLOW && colour == ColourType.BLUE) sign = 1;*/
            if (initialColour == ColourType.RED && colour == ColourType.GREEN) sign = -1;
            if (initialColour == ColourType.GREEN && colour == ColourType.BLUE) sign = -1;
            if (initialColour == ColourType.BLUE && colour == ColourType.YELLOW) sign = -1;
            if (initialColour == ColourType.YELLOW && colour == ColourType.RED) sign = -1;
        }

        @Override
        public void execute() {
            colourWheel.setPower(POWER * sign);
        }

        @Override
        public void end(boolean interrupted) {
            colourWheel.setPower(0);
        }

        @Override
        public boolean isFinished() {
            return colourWheel.getColour() == colour;
        }
    }

    public static class Spin extends CommandBase {
        private double spins;
        private ColourType colour;
        private ColourType lastColour;
        private PIDController pidController;

        public Spin(int spins) {
            this.spins = spins;
            pidController = new PIDController(1.0 / spins, 0, 0);
            addRequirements(colourWheel);
        }

        @Override
        public void initialize() {
            colour = colourWheel.getColour();
            lastColour = colour;
        }

        @Override
        public void execute() {
            ColourType current = colourWheel.getColour();
            if (current != lastColour) {
                lastColour = current;
                if (current == colour) {
                    spins -= -0.5;
                }
            }
            colourWheel.setPower(pidController.calculate(0, spins));
        }

        @Override
        public void end(boolean interrupted) {
            colourWheel.setPower(0);
        }

        @Override
        public boolean isFinished() {
            return spins == 0;
        }
    }
}
