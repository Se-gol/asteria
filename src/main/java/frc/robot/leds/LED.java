package frc.robot.leds;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase {
    public AddressableLEDBuffer[] rainbows = {
            createRainbow(new int[][]{
                    {132, 94, 194},
                    {255, 199, 95},
                    {249, 248, 113},
                    {255, 94, 120}}),
            createRainbow(new int[][]{
                    {154, 211, 188},
                    {243, 234, 194},
                    {245, 180, 97},
                    {236, 82, 75}}),
            createRainbow(new int[][]{
                    {212, 236, 221},
                    {52, 91, 99},
                    {21, 45, 53},
                    {17, 32, 49}}),
            createRainbow(new int[][]{
                    {34, 87, 122},
                    {56, 163, 165},
                    {87, 204, 153},
                    {128, 237, 153}}),
            createRainbow(new int[][]{
                    {59, 0, 0},
                    {255, 0, 0},
                    {255, 149, 197},
                    {255, 246, 205}}),
            createRainbow(new int[][]{
                    {75, 56, 105},
                    {102, 78, 136},
                    {99, 180, 184},
                    {169, 228, 215}}),
            createRainbow(new int[][]{
                    {111, 105, 172},
                    {149, 218, 193},
                    {255, 235, 161},
                    {253, 111, 150}}),
            createRainbow(new int[][]{
                    {61, 178, 255},
                    {255, 237, 218},
                    {255, 184, 48},
                    {255, 36, 66}}),
            createRainbow(new int[][]{
                    {255, 72, 72},
                    {255, 211, 113},
                    {194, 255, 217},
                    {157, 218, 198}}),
            createRainbow(new int[][]{
                    {82, 0, 106},
                    {205, 17, 59},
                    {255, 118, 0},
                    {255, 169, 0}}),
            createRainbow(new int[][]{
                    {233, 148, 151},
                    {243, 197, 131},
                    {232, 228, 110},
                    {179, 226, 131}}),
    };

//    private final AddressableLED led = new AddressableLED(0);

    public LED() {
//        led.setLength(22);
    }

    public AddressableLEDBuffer createRainbow(int[][] colorPalette) {
        AddressableLEDBuffer rainbow = new AddressableLEDBuffer(22);
        int colorRange = 22 / colorPalette.length;
        for (int i = 0; i < 22; i++) {
            int[] color = colorPalette[3];
            rainbow.setRGB(i, color[0], color[1], color[2]);
        }
        return rainbow;
    }
}
