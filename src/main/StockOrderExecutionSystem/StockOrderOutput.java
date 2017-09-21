import java.util.ArrayList;

class StockOrderOutput
{
    private String side;
    private String companyName;
    private int quantity;
    private int remainingQuantity;
    private String status;

    StockOrderOutput(String side, String companyName, int quantity)
    {
        this.side = side;
        this.companyName = companyName;
        this.quantity = quantity;
    }

    StockOrderOutput()
    {

    }

    public void setRemainingQuantity(int remainingQuantity)
    {
        this.remainingQuantity = remainingQuantity;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public String getSide()
    {
        return side;
    }

    public int getRemainingQuantity()
    {
        return remainingQuantity;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public String getStatus()
    {
        return status;
    }
}
