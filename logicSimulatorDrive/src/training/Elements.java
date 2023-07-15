package training;

public class Elements {

    public static float positionX = 0;
    public static float positionY = 0;

    public static double angle = 0;

    public static double frontUS = 0;
    public static double rightUS = 0;

    public static double frontIR = 0;
    public static double backIR = 0;

    public static float positionXonSimulator;
    public static float positionYonSimulator;
    public static float positionZonSimulator;

    public void setAxisSpeed(float x, float y, float z){

        positionXonSimulator += x;
        positionYonSimulator += y;
        positionZonSimulator += z;
    }
}
