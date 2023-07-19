package cases;

public class Camera implements IState
{

    private String name;

    public Camera(String name)
    {
        this.name = name;
    }

    @Override
    public boolean execute()
    {
        return true;
    }
}
