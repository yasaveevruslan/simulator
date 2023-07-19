package training;

public class Elements
{

    public static float positionX = 0, positionY = 0;
    public static float LastPositionX = 290, LastPositionY = 290;
    public static double angle = 0, lastAngle = 0;

    public static double frontUS = 0, rightUS = 0, frontIR = 0, backIR = 0;

    // позиция картинки в окне, ее координаты
    public static int positionXRobot = 290, positionYRobot = 290, positionZRobot = 90;

    // позиция робота на какие координаты он перемещается
    public static float positionXonSimulator = 0, positionYonSimulator = 0, lastPosX = 0, lastPosY = 0;


    // увеличение координат робота
    public void setAxisSpeed(float x, float y, float z)
    {

        if (GUI.stopClicked || GUI.resetClicked)
        {
            positionXonSimulator += 0;
            positionYonSimulator += 0;
            angle += 0;
        }else
        {
            positionXonSimulator += x / 4;
            positionYonSimulator += y / 4;
            angle += z / 4;
        }

    }

    // подсчет координат
    public void calculateCoordinates()
    {

        double radians = Math.toRadians(angle);

        float noneX = (positionXRobot - LastPositionX) * 4;
        float noneY = (positionYRobot - LastPositionY) * 4;

        float thetaR = (float)(Math.atan2(noneY, noneX) + radians);
        float r = (float)(Math.sqrt(noneY * noneY + noneX * noneX));

        positionX += (float)(r * Math.cos(thetaR));
        positionY += (float)(r * Math.sin(thetaR));

        LastPositionX = positionXRobot;
        LastPositionY = positionYRobot;

        positionXRobot += positionXonSimulator - lastPosX;
        lastPosX = positionXonSimulator;

        positionYRobot += positionYonSimulator - lastPosY;
        lastPosY = positionYonSimulator;

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

        LastPositionX = positionXRobot;
        LastPositionY = positionYRobot;

        positionX = x;
        positionY = y;
    }


    // сброс угла поворота (не затрагиваем угол картинки)
    public void resetGyro(float z){
        angle = z;
        lastAngle = z;
    }

    public void resetButton(boolean reset)
    {
        if (reset)
        {
            positionXRobot = 290;
            positionYRobot = 290;
            positionZRobot = 90;
        }

    }
}
