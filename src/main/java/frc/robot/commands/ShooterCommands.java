package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterCommands {
    private static final Shooter shooter = Shooter.getInstance();

    public static class Shoot extends CommandBase {
        private final double velocity;

        public Shoot(double velocity) {
            this.velocity = velocity;
            addRequirements(shooter);
        }

        @Override
        public void initialize() {
            super.initialize();
        }

        @Override
        public void execute() {
            shooter.setVelocity(velocity);
//            shooter.setPower(velocity / 100);
        }

        @Override
        public void end(boolean interrupted) {
            shooter.setPower(0);
        }

        @Override
        public boolean isFinished() {
            return super.isFinished();
        }
    }
}

