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

    public static int newPositionXRobot = 290, newPositionYRobot = 290;
    public static int newPosXRobot = 290, newPosYRobot = 290;
    public static int lastNewPosX = 290, lastNewPosY = 290;
    // позиция робота на какие координаты он перемещается
    public static float positionXonSimulator, positionYonSimulator, lastPosX, lastPosY;

    public static boolean Errors, errorPosX, errorPosY;



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
            positionXonSimulator = x / 4;
            positionYonSimulator = y / 4;
            positionZRobot += z / 4;
        }
        System.out.println();

    }


    public void calculateCoordinates()
    {

        angle += positionZRobot - lastAngle;
        lastAngle = positionZRobot;

        float noneX = (newPositionXRobot - LastPositionX) * 4;
        float noneY = (newPositionYRobot - LastPositionY) * 4;

        positionX += noneX;
        positionY += noneY;

        LastPositionX = newPositionXRobot;
        LastPositionY = newPositionYRobot;



        newPositionXRobot += positionXonSimulator;

        if (!errorPosX)
        {
            newPosXRobot += positionXonSimulator;
        }
        else
        {
            newPosXRobot = (int)Function.InRange(newPosXRobot, 48, 957);
        }
        lastPosX = positionXonSimulator;

        newPositionYRobot += positionYonSimulator;

        if (!errorPosY)
        {
            newPosYRobot += positionYonSimulator;
        }
        else
        {
            newPosYRobot = (int)Function.InRange(newPosYRobot, 54, 457);
        }
        lastPosY = positionYonSimulator;

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

        LastPositionX = newPositionXRobot;
        LastPositionY = newPositionYRobot;

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

            newPositionXRobot = 290;
            newPositionYRobot = 290;

            newPosXRobot = 290;
            newPosYRobot = 290;

            positionZRobot = 90;
        }

    }

    public void checkErrorPosition()
    {
        Errors = !Function.InRangeBool(newPositionXRobot, 48, 955) || !Function.InRangeBool(newPositionYRobot, 54, 455);


        errorPosX = ((newPosXRobot >= 957 && positionXonSimulator >= 0) || (newPosXRobot <= 48 && positionXonSimulator <= 0));
        errorPosY = ((newPosYRobot >= 457 && positionYonSimulator >= 0) || (newPosYRobot <= 54 && positionYonSimulator <= 0));

        System.out.println("errorX: " + errorPosX + " errorY: " + errorPosY);
    }

    public void setPositionOnWindow()
    {
//        if (!errorPosX)
//        {
        if (Function.InRangeBool(newPosXRobot, 48, 957)){
            positionXRobot += newPosXRobot - lastNewPosX;
            lastNewPosX = newPosXRobot;
        }

        if (Function.InRangeBool(newPosYRobot, 54, 457)){
            positionYRobot += newPosYRobot - lastNewPosY;
            lastNewPosY = newPosYRobot;
        }




    }
}
