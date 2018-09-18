import javafx.beans.property.DoubleProperty;

public class XYProperties
{
    private DoubleProperty x;
    private DoubleProperty y;

    public XYProperties(DoubleProperty x, DoubleProperty y)
    {
        this.x = x;
        this.y = y;
    }

    public DoubleProperty xProperty()
    {
        return x;
    }


    public DoubleProperty yProperty()
    {
        return y;
    }


}
