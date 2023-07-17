package training;

public class Elements {

    public static float positionX = 0, positionY = 0;
    public static float LastPositionX = 0, LastPositionY = 0;
    public static double angle = 0, lastAngle = 0;

    public static double frontUS = 0, rightUS = 0, frontIR = 0, backIR = 0;

    public static float positionXRobot, positionYRobot, positionZRobot;
    public static float positionXonSimulator, positionYonSimulator, lastPosX, lastPosY;


    public void setAxisSpeed(float x, float y, float z){

        positionXonSimulator += x / 4;
        positionYonSimulator += y / 4;
        angle += z / 4;
    }

    public void calculateCoordinates(){

        double radians = Math.toRadians(angle);

        float noneX = positionXonSimulator - LastPositionX;
        float noneY = positionYonSimulator - LastPositionY;

        float thetaR = (float)(Math.atan2(noneY, noneX) + radians);
        float r = (float)(Math.sqrt(noneY * noneY + noneX * noneX));

        positionX += (float)(r * Math.cos(thetaR));
        positionY += (float)(r * Math.sin(thetaR));

        LastPositionX = positionXonSimulator;
        LastPositionY = positionYonSimulator;



        positionXRobot += positionX - lastPosX;
        lastPosX = positionX;

        positionYRobot += positionY - lastPosY;
        lastPosY = positionY;

        positionZRobot += angle - lastAngle;
        lastAngle = angle;

        System.out.println("X: " + positionXRobot + " Y: " + positionYRobot + " Z: " + positionZRobot);
    }

    public void calculateSensors(){

        frontIR = 0;
        backIR = 0;

        frontUS = 0;
        rightUS = 0;

    }

    public void resetCoordinates(float x, float y){

        LastPositionX = positionXonSimulator;
        LastPositionY = positionYonSimulator;

        positionX = x;
        positionY = y;
    }

    public void resetGyro(float z){
        angle = z;
        lastAngle = z;
    }
}
