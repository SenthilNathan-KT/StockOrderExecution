import java.util.HashMap;

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

    //This method is used to update the company remaining stock in CompanyStock object which have the total count of both Buy and Sell quantities.
    public static CompanyStock updateCompanyRemainingStock(HashMap<String, CompanyStock> companyStockMap, StockOrderInput obj)
    {
        CompanyStock stockObj;
        String companyName = obj.getCompanyName();
        if(companyStockMap.containsKey(companyName))
        {
            stockObj = companyStockMap.get(companyName);
        }
        else
        {
            stockObj = new CompanyStock();
        }
        stockObj.updateRemainingStock(obj);
        stockObj.setCompanyName(companyName); // This is used for integrity check purpose alone.
        companyStockMap.put(companyName, stockObj);
        //System.out.println("LOG_Stocks remaining  is " + stockObj.getSellQuantity() + " , " + stockObj.getBuyQuantity());
        return stockObj;
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