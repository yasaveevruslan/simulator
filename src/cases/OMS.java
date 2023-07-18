package cases;

public class OMS implements IState
{

    private int indexIn;

    public OMS(int indexIn)
    {
        this.indexIn = indexIn;

    }

    @Override
    public boolean execute()
    {
        return true;
    }
}
