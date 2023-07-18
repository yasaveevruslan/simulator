package training;

public class Elements
{

    public static float positionX = 0, positionY = 0;
    public static float LastPositionX = 0, LastPositionY = 0;
    public static double angle = 0, lastAngle = 0;

    public static double frontUS = 0, rightUS = 0, frontIR = 0, backIR = 0;

    // позиция картинки в окне, ее координаты
    public static float positionXRobot, positionYRobot, positionZRobot;

    // позиция робота на какие координаты он перемещается
    public static float positionXonSimulator, positionYonSimulator, lastPosX, lastPosY;


    // увеличение координат робота
    public void setAxisSpeed(float x, float y, float z)
    {

        positionXonSimulator += x;
        positionYonSimulator += y;
        angle += z;
    }

    // подсчет координат
    public void calculateCoordinates()
    {

        double radians = Math.toRadians(angle);

        float noneX = positionXonSimulator - LastPositionX;
        float noneY = positionYonSimulator - LastPositionY;

        float thetaR = (float)(Math.atan2(noneY, noneX) + radians);
        float r = (float)(Math.sqrt(noneY * noneY + noneX * noneX));

        positionX += (float)(r * Math.cos(thetaR));
        positionY += (float)(r * Math.sin(thetaR));

        LastPositionX = positionXonSimulator;
        LastPositionY = positionYonSimulator;



        positionXRobot += positionX / 4 - lastPosX;
        lastPosX = positionX / 4;

        positionYRobot += positionY / 4 - lastPosY;
        lastPosY = positionY / 4;

        positionZRobot += angle - lastAngle;
        lastAngle = angle;

        System.out.println("X: " + positionX + " Y: " + positionY + " Z: " + angle); // вывод координат робота
        System.out.println("X: " + positionXRobot + " Y: " + positionYRobot + " Z: " + positionZRobot); // вывод позиции картинки в окне
    }

    // работа датчиков
    public void calculateSensors(){

        frontIR = 0;
        backIR = 0;

        frontUS = 0;
        rightUS = 0;

    }

    // сброс координат робота (не затрагиваем позицию картинки)
    public void resetCoordinates(float x, float y){

        LastPositionX = positionXonSimulator;
        LastPositionY = positionYonSimulator;

        positionX = x;
        positionY = y;
    }


    // сброс угла поворота (не затрагиваем угол картинки)
    public void resetGyro(float z){
        angle = z;
        lastAngle = z;
    }
}
