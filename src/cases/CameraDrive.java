package cases;

public class CameraDrive implements IState
{
    private float coordinateX, coordinateY, coordinateZ = 0;
    private int command = 0;

    public CameraDrive(float distanceY, int command)
    {
        this.coordinateY = distanceY;
        this.command = command;
    }

    @Override
    public boolean execute()
    {
        return true;
    }
}
