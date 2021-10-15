import frc.robot.value_tuner.ValueTuner;
import org.junit.Test;

public class Asjkfk {
    @Test
    public void asdkjwkj() {
        System.out.println(easeOutCubic(0.5));
        ValueTuner valueTuner = new ValueTuner("hello", 0);
    }

    private double easeOutCubic(double x) {
        return 1 - Math.pow(1 - x, 3);
    }
}
