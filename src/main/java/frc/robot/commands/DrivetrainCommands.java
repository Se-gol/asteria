package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

import static frc.robot.Constants.Drivetrain.DEADBAND;
import static frc.robot.Constants.Drivetrain.SPEED_MULTIPLIER;

public class DrivetrainCommands {
    private static final Drivetrain drivetrain = Drivetrain.getInstance();
    private static final XboxController xboxController = RobotContainer.xboxController;

    public static class TankDrive extends CommandBase {

        public TankDrive() {
            addRequirements(drivetrain);
        }

        @Override
        public void initialize() {
            super.initialize();
        }

        @Override
        public void execute() {
            double rightY = -xboxController.getY(GenericHID.Hand.kRight);
            double leftY = -xboxController.getY(GenericHID.Hand.kLeft);

            if (Math.abs(rightY) < DEADBAND) rightY = 0;
            if (Math.abs(leftY) < DEADBAND) leftY = 0;

            rightY = drivetrain.calmEasingFunction(rightY);
            leftY = drivetrain.calmEasingFunction(leftY);

            drivetrain.setVelocity(rightY * SPEED_MULTIPLIER, true);
            drivetrain.setVelocity(leftY * SPEED_MULTIPLIER, false);

//            drivetrain.setPower(rightY, true);
//            drivetrain.setPower(leftY, false);

            xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, (rightY + leftY) / 2);
        }

        @Override
        public void end(boolean interrupted) {
            drivetrain.setPower(0, true);
            drivetrain.setPower(0, false);
        }

        @Override
        public boolean isFinished() {
            return super.isFinished();
        }
    }


    public static class TankDriveGasPadals extends CommandBase {

        public TankDriveGasPadals() {
            addRequirements(drivetrain);
        }

        @Override
        public void initialize() {
            super.initialize();
        }

        @Override
        public void execute() {
            // rt lt rb lb for backwards
            double rt = xboxController.getTriggerAxis(GenericHID.Hand.kRight);
            double lt = xboxController.getTriggerAxis(GenericHID.Hand.kLeft);

            if (Math.abs(rt) < DEADBAND) rt = 0;
            if (Math.abs(lt) < DEADBAND) lt = 0;

            boolean rb = xboxController.getBumper(GenericHID.Hand.kRight);
            boolean lb = xboxController.getBumper(GenericHID.Hand.kLeft);

            double rightValue = rb ? -0.5 : rt;
            double leftValue = lb ? -0.5 : lt;

            rightValue = drivetrain.calmEasingFunction(rightValue);
            leftValue = drivetrain.calmEasingFunction(leftValue);

            drivetrain.setVelocity(rightValue * SPEED_MULTIPLIER, true);
            drivetrain.setVelocity(leftValue * SPEED_MULTIPLIER, false);

//            drivetrain.setPower(rightValue, true);
//            drivetrain.setPower(leftValue, false);


            xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, (rightValue + leftValue) / 2);
        }

        @Override
        public void end(boolean interrupted) {
            drivetrain.setPower(0, true);
            drivetrain.setPower(0, false);
        }

        @Override
        public boolean isFinished() {
            return super.isFinished();
        }
    }

    public static class FollowPath extends CommandBase {
        private final Trajectory trajectory;
        private final RamseteController controller = new RamseteController(0, 0);
        private final Timer timer = new Timer();

        public FollowPath(Trajectory trajectory) {
            this.trajectory = trajectory;
            addRequirements(drivetrain);
        }

        @Override
        public void initialize() {
            timer.start();
        }

        @Override
        public void execute() {
            drivetrain.setChassisSpeeds(controller.calculate(
                    drivetrain.getPose(),
                    trajectory.sample(timer.get())
            ));
        }

        @Override
        public void end(boolean interrupted) {
            timer.reset();
            drivetrain.setChassisSpeeds(new ChassisSpeeds());
        }

        @Override
        public boolean isFinished() {
            return super.isFinished();
        }
    }

    public static class DriveAndSteer extends CommandBase {
        public DriveAndSteer() {
            addRequirements(drivetrain);
        }

        @Override
        public void initialize() {
            super.initialize();
        }

        @Override
        public void execute() {
            double y = -xboxController.getY(GenericHID.Hand.kLeft);
            double x = xboxController.getX(GenericHID.Hand.kRight);

            if (Math.abs(x) < DEADBAND) x = 0;
            if (Math.abs(y) < DEADBAND) y = 0;

            x *= 0.5;
            x = drivetrain.calmEasingFunction(x);
            y = drivetrain.calmEasingFunction(y);

            drivetrain.setVelocity((y - x) * SPEED_MULTIPLIER, true);
            drivetrain.setVelocity((y + x) * SPEED_MULTIPLIER, false);

//            drivetrain.setPower(y - x, true);
//            drivetrain.setPower(y + x, false);
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

    public static class OneJoystickDrive extends CommandBase {
        public OneJoystickDrive() {
            addRequirements(drivetrain);
        }

        @Override
        public void initialize() {
            super.initialize();
        }

        @Override
        public void execute() {
            double y = -xboxController.getY(GenericHID.Hand.kLeft);
            double x = xboxController.getX(GenericHID.Hand.kLeft);

            if (Math.abs(x) < DEADBAND) x = 0;
            if (Math.abs(y) < DEADBAND) y = 0;

            x *= 0.5;
            x = drivetrain.calmEasingFunction(x);
            y = drivetrain.calmEasingFunction(y);

            drivetrain.setVelocity((y - x) * SPEED_MULTIPLIER, true);
            drivetrain.setVelocity((y + x) * SPEED_MULTIPLIER, false);

//            drivetrain.setPower(y - x, true);
//            drivetrain.setPower(y + x, false);
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


}
