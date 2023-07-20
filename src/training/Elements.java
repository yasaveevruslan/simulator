package training;

public class Elements
{

    public static float positionX = 0, positionY = 0;
    public static float LastPositionX = 290, LastPositionY = 290;
    public static double angle = 0, lastAngle = 90;

    public static double frontUS = 0, rightUS = 0, frontIR = 0, backIR = 0;

    // позиция картинки в окне, ее координаты
    public static int positionXRobot = 290, positionYRobot = 290;
    public static float positionZRobot = 90;

    // позиция робота на какие координаты он перемещается
    public static float positionXonSimulator = 0, positionYonSimulator = 0, lastPosX = 0, lastPosY = 0;

    public static boolean Errors = false, errorPosX = false, errorPosY = false;



    // увеличение координат робота
    public void setAxisSpeed(float x, float y, float z)
    {

        if (GUI.stopClicked || GUI.resetClicked)
        {
            positionXonSimulator += 0;
            positionYonSimulator += 0;
            positionZRobot += 0;
        }else
        {
            positionXonSimulator += x / 4;
            positionYonSimulator += y / 4;
            positionZRobot += z / 4;
        }

    }


    public void calculateCoordinates()
    {

        angle += positionZRobot - lastAngle;
        lastAngle = positionZRobot;

        float noneX = (positionXRobot - LastPositionX) * 4;
        float noneY = (positionYRobot - LastPositionY) * 4;

        positionX += noneX;
        positionY += noneY;

        LastPositionX = positionXRobot;
        LastPositionY = positionYRobot;

        if (!errorPosX)
        {
            positionXRobot += positionXonSimulator - lastPosX;
            lastPosX = positionXonSimulator;
        }

        if (!errorPosY)
        {
            positionYRobot += positionYonSimulator - lastPosY;
            lastPosY = positionYonSimulator;
        }



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
        lastAngle = positionZRobot;
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

    public void checkErrorPosition()
    {
        Errors = !Function.InRangeBool(positionXRobot, 50, 955) || !Function.InRangeBool(positionYRobot, 50, 455);

        System.out.println("error: " + Errors);

        errorPosX = !Function.InRangeBool(positionXRobot, 50, 950);

        errorPosY = !Function.InRangeBool(positionYRobot, 50, 450);
    }

    public void setPositionOnWindow()
    {
        if (errorPosX)
        {
            positionXRobot += 0;
        }
        else
        {
            positionXRobot += positionXonSimulator - lastPosX;
            lastPosX = positionXonSimulator;
        }

        if (errorPosX)
        {
            positionYRobot += 0;
        }
        else
        {
            positionYRobot += positionYonSimulator - lastPosY;
            lastPosY = positionYonSimulator;
        }
    }
}
