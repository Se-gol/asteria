package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Turret;

import static frc.robot.Constants.Turret.DEADBAND;


public class TurretCommands {
    private static final Turret turret = Turret.getInstance();
    private static final XboxController xboxController = RobotContainer.xboxController;

    public static class TurretControl extends CommandBase {

        public TurretControl() {
            addRequirements(turret);
        }

        @Override
        public void initialize() {
            super.initialize();
        }

        @Override
        public void execute() {
            double joystickX = xboxController.getX(GenericHID.Hand.kRight);
            double joystickY = xboxController.getY(GenericHID.Hand.kRight);

            if (Math.abs(joystickX) < DEADBAND) joystickX = 0;
            if (Math.abs(joystickY) < DEADBAND) joystickY = 0;

            if (Math.hypot(joystickX, joystickY) >= 0.7)
                turret.setPosition(joystickX, joystickY);
        }

        @Override
        public void end(boolean interrupted) {
            super.end(interrupted);
        }

        @Override
        public boolean isFinished() {
            return super.isFinished();
        }
    }

    public static class BasicTurretControl extends CommandBase {
        public BasicTurretControl() {
            addRequirements(turret);
        }

        @Override
        public void initialize() {
            super.initialize();
        }

        @Override
        public void execute() {
            double joystickX = xboxController.getX(GenericHID.Hand.kRight);

            if (Math.abs(joystickX) < DEADBAND) joystickX = 0;

            turret.setPower(joystickX);
        }

        @Override
        public void end(boolean interrupted) {
            turret.setPower(0);
        }

        @Override
        public boolean isFinished() {
            return super.isFinished();
        }
    }
}
