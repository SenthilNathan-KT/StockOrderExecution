
class CompanyStock
{
    private int buyQuantity;
    private int sellQuantity;
    private String companyName;

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public int getBuyQuantity()
    {
        return buyQuantity;
    }

    public int getSellQuantity()
    {
        return sellQuantity;
    }

    public void setBuyQuantity(int quantity)
    {
	this.buyQuantity = quantity;
    }

    public void setSellQuantity(int quantity)
    {
	this.sellQuantity = quantity;
    }
    
    //This method will have the implementation to update the remaining stock quantities.
    public void updateRemainingStock(StockOrderInput stockOrderInput)
    {
        int inputQuantity = stockOrderInput.getQuantity();
        while(inputQuantity != 0)
        { 
            if (stockOrderInput.getSide().equalsIgnoreCase("BUY"))
            {
                if (sellQuantity > 0)
                {
                    if (sellQuantity > inputQuantity)
                    {
                        sellQuantity -= inputQuantity;
                        inputQuantity = 0;
                    }
                    else
                    {
                        inputQuantity -= sellQuantity;
                        sellQuantity = 0;
                    }
                }
                else
                {
                    buyQuantity += inputQuantity;
                    inputQuantity = 0;
                }
            }
            else
            {
                if (buyQuantity > 0)
                {
                    if (buyQuantity > inputQuantity)
                    {
                        buyQuantity -= inputQuantity;
                        inputQuantity = 0;
                    }
                    else
                    {
                        inputQuantity -= buyQuantity;
                        buyQuantity = 0;
                    }
                }
                else
                {
                    sellQuantity += inputQuantity;
                    inputQuantity = 0;
                }
            }
        }
    }
}
