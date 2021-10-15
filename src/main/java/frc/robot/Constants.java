package frc.robot;

import frc.robot.value_tuner.ValueTuner;

public class Constants {
    public static class ColourWheel {
        public static final double POWER = 0.5;
    }

    public static class Conveyor {
        public static final double POWER = 0.5;
        public static final double FUNNEL_POWER = 0.5;
    }

    public static class Drivetrain {
        public static final int TICKS_PER_METER = (int) (2048. * (2500 / 126.) / (6 * 0.0254 * Math.PI));
        public static final double SPEED_MULTIPLIER = 3;
        public static final double DEADBAND = 0.01;

        public static final double kP = 0.1;
        public static final double kI = 0;
        public static final double kD = 0.1;
        public static final ValueTuner value = new ValueTuner("myValue", 1);
    }

    public static class Intake {
        public static final double POWER = 0.5;
    }

    public static class Shooter {
        public static final int TICKS_PER_REVOLUTION = 4096;

        public static final double kP = 1;
        public static final double kI = 0;
        public static final double kD = 1.5;
    }

    public static class Turret {
        public static final int MIN_TICKS = 0;
        public static final int MAX_TICKS = 2000;
        public static final int TICKS_PER_DEGREE = 4096 / 360;
        public static final double DEADBAND = 0.1;

        public static final double kP = 0.4;
        public static final double kI = 0;
        public static final double kD = 0.04;
    }
}