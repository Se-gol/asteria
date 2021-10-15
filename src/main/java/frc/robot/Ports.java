package frc.robot;

public class Ports {
    public static class ColourWheel {
        public static final int MOTOR = 0;
        public static final boolean MOTOR_INVERTED = false;
    }

    public static class Conveyor {
        public static final int MOTOR = 21;
        public static final int FUNNEL = 19;
        public static final int PROXIMITY_SENSOR = 0;
        public static final int PROXIMITY_SENSOR_2 = 0;
        public static final int PISTON = 2;

        public static final boolean MOTOR_INVERTED = false;
        public static final boolean FUNNEL_INVERTED = true;
    }

    public static class Drivetrain {
        public static final int RIGHT_MASTER = 12;
        public static final int RIGHT_AUXILIARY = 13;
        public static final int LEFT_MASTER = 10;
        public static final int LEFT_AUXILIARY = 11;

        public static final boolean RIGHT_MASTER_INVERTED = true;
        public static final boolean RIGHT_AUXILIARY_INVERTED = true;
        public static final boolean LEFT_MASTER_INVERTED = false;
        public static final boolean LEFT_AUXILIARY_INVERTED = false;

        public static final boolean RIGHT_SENSOR_INVERTED = true;
        public static final boolean LEFT_SENSOR_INVERTED = false;
    }


    public static class Intake {
        public static final int MOTOR = 20;
        public static final int PISTON = 1;

        public static final boolean MOTOR_INVERTED = false;
    }


    public static class Shooter {
        public static final int MOTOR = 23;
        public static final int AUXILIARY = 24;
        public static final int AUXILIARY_2 = 25;

        public static final boolean MOTOR_INVERTED = false;
        public static final boolean AUXILIARY_INVERTED = false;
        public static final boolean AUXILIARY_2_INVERTED = false;

        public static final boolean SENSOR_INVERTED = false;
        public static final boolean AUXILIARY_SENSOR_INVERTED = false;
        public static final boolean AUXILIARY_2_SENSOR_INVERTED = false;
    }

    public static class Turret {
        public static final int MOTOR = 22;

        public static final boolean MOTOR_INVERTED = false;
        public static final boolean SENSOR_INVERTED = true;
    }
}
