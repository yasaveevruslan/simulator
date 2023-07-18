package cases;

public class BlackLineSensor implements IState
{

    private float distanceY;
    private int position;
    private String name;

    public BlackLineSensor(String name, float distanceY, int position)
    {
        this.name = name;
        this.distanceY = distanceY;
        this.position = position;
    }

    @Override
    public boolean execute()
    {
        return true;
    }
}
