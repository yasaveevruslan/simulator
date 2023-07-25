package training;

public class Elements
{

    private static final int X = 290, Y = 290, Z = 90;

    public static float positionX = 0, positionY = 0;
    public static float LastPositionX = X, LastPositionY = Y;
    public static double angle = 0, lastAngle = Z;

    public static double frontUS = 0, rightUS = 0, frontIR = 0, backIR = 0;

    // позиция картинки в окне, ее координаты
    public static int positionXRobot = X, positionYRobot = Y;
    public static float positionZRobot = Z;

    public static int newPositionXRobot = X, newPositionYRobot = Y;
    public static int newPosXRobot = X, newPosYRobot = Y;
    public static int lastNewPosX = X, lastNewPosY = Y;
    // позиция робота на какие координаты он перемещается
    public static float positionXonSimulator, positionYonSimulator, lastPosX, lastPosY;

    public static boolean Errors, errorPosX, errorPosY;

    public static double disUp, disDown, disRight, disLeft;

    public static int Xmin, Xmax, Ymin, Ymax;
    public static int XminDefault = 48, XmaxDefault = 957, YminDefault = 54, YmaxDefault = 457;

//    Errors = !Function.InRangeBool(newPositionXRobot, 48, 955) || !Function.InRangeBool(newPositionYRobot, 54, 455);


    // увеличение координат робота
    public void setAxisSpeed(float x, float y, float z)
    {

        if (GUI.stopClicked || GUI.resetClicked)
        {
            positionXonSimulator = 0;
            positionYonSimulator = 0;
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
            if (!Function.InRangeBool(positionXRobot, Xmin, Xmax))
            {
                newPosXRobot += Function.InRange(positionXonSimulator, -0.3f, 0.3f);
            }
            else
            {
                newPosXRobot = (int)Function.InRange(newPosXRobot, Xmin, Xmax);
            }
        }


        newPositionYRobot += positionYonSimulator;

        if (!errorPosY)
        {
            newPosYRobot += positionYonSimulator;
        }
        else
        {
            if (!Function.InRangeBool(positionYRobot, Ymin, Ymax))
            {
                newPosYRobot += Function.InRange(positionYonSimulator, -0.3f, 0.3f);
            }
            else
            {
                newPosYRobot = (int)Function.InRange(newPosYRobot, Ymin, Ymax);
            }

        }

    }

    // работа датчиков
    public void calculateSensors(){

        double up = disUp != 0.0 ? disUp : positionYRobot - YminDefault + 45;
        double down = disDown != 0.0 ? disDown : YmaxDefault - positionYRobot + 45;
        double right = disRight != 0.0 ? disRight : XmaxDefault  - positionXRobot + 45;
        double left = disLeft != 0.0 ? disLeft : positionXRobot - XminDefault + 45;

        int element = -1;

        System.out.println(disUp);

        if (Function.InRangeBool((positionZRobot - 90) % 360, -45, 45))
        {
            frontIR = (up - 25) * 0.4;
            backIR = (up - 25) * 0.4;

            frontUS = (right - 45) * 0.4;
            rightUS = (down - 10) * 0.4;
            element = 0;
        }
        else if (Function.InRangeBool((positionZRobot - 90) % 360, 45, 135) || Function.InRangeBool((positionZRobot - 90) % 360, -315, -225))
        {
            frontIR = (right - 25) * 0.4;
            backIR = (right - 25) * 0.4;

            frontUS = (down - 45) * 0.4;
            rightUS = (left - 10) * 0.4;
            element = 90;

        }
        else if (Function.InRangeBool((positionZRobot - 90) % 360, 135, 225) || Function.InRangeBool((positionZRobot - 90) % 360, -225, -135))
        {
            frontIR = (down - 25) * 0.4;
            backIR = (down - 25) * 0.4;

            frontUS = (left - 45) * 0.4;
            rightUS = (up - 10) * 0.4;
            element = 180;

        }
        else if (Function.InRangeBool((positionZRobot - 90) % 360, 225, 315) || Function.InRangeBool((positionZRobot - 90) % 360, -135, -45))
        {
            frontIR = (left - 25) * 0.4;
            backIR = (left - 25) * 0.4;

            frontUS = (up - 45) * 0.4;
            rightUS = (right - 10) * 0.4;
            element = 270;

        }
        else
        {
            frontIR = (up - 25) * 0.4;
            backIR = (up - 25) * 0.4;

            frontUS = (right - 45) * 0.4;
            rightUS = (down - 10) * 0.4;
            element = 0;

        }
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

            positionXonSimulator = 0;
            positionYonSimulator = 0;

        }

    }

    public void checkErrorPosition()
    {

//        System.out.println("UP: " + disUp + " DOWN: " + disDown + " RIGHT: " + disRight + " LEFT: " + disLeft);

//        Xmax = disRight != 0.0 ? (int) (newPosXRobot + 47 + (disRight)) : XmaxDefault;
//        Xmin = disLeft != 0.0 ? (int) (newPosXRobot - 47 - (disLeft)) : XminDefault;
//        Ymax = disDown != 0.0 ? (int) (newPosYRobot + (disDown - 47)) : YmaxDefault;
//        Ymin = disUp != 0.0 ? (int) (newPosYRobot - (disUp - 47)) : YminDefault;


//        System.out.println("X min: " + Xmin + " X max: " + Xmax + " Y min: " + Ymin + " Y max: " + Ymax);

        Xmax = XmaxDefault;
        Xmin = XminDefault;
        Ymax = YmaxDefault;
        Ymin = YminDefault;

        Errors = !Function.InRangeBool(newPositionXRobot, Xmin, Xmax) || !Function.InRangeBool(newPositionYRobot, Ymin, Ymax);


        errorPosX = ((newPosXRobot >= Xmax && positionXonSimulator >= 0) || (newPosXRobot <= Xmin && positionXonSimulator <= 0));
        errorPosY = ((newPosYRobot >= Ymax && positionYonSimulator >= 0) || (newPosYRobot <= Ymin && positionYonSimulator <= 0));

    }

    public void setPositionOnWindow()
    {

        if (Function.InRangeBool(newPosXRobot, Xmin, Xmax)){
            positionXRobot += newPosXRobot - lastNewPosX;
            lastNewPosX = newPosXRobot;
        }


        if (Function.InRangeBool(newPosYRobot, Ymin, Ymax)){
            positionYRobot += newPosYRobot - lastNewPosY;
            lastNewPosY = newPosYRobot;
        }


    }
}
