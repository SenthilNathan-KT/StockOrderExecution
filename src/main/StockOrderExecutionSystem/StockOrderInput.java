class StockOrderInput
{
    private String side;
    private String companyName;
    private int quantity;

    StockOrderInput(String side, String companyName, int quantity)
    {
        this.side = side;
        this.companyName = companyName;
        this.quantity = quantity;
    }

    public String getSide()
    {
        return side;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public int getQuantity()
    {
        return quantity;
    }
}