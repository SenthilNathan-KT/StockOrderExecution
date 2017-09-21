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

    // This method is used to update the stock order output list based on the current stock input.
    public static void updateStockOrderOutputList(ArrayList<StockOrderOutput> stockOrderOutputList, StockOrderInput currentStock, CompanyStock currentCompanyStock)
    {
        String currentStockSide = currentStock.getSide();
        int quantity= currentStock.getQuantity();
        int currentActionQuantity = (currentStockSide.equalsIgnoreCase("BUY") ? currentCompanyStock.getBuyQuantity() : currentCompanyStock.getSellQuantity());

        //System.out.println(" LOG_Size_of_stockOrderOutputList is " + stockOrderOutputList.size());
        for(int j = 0; j < stockOrderOutputList.size(); j++)
        {
            StockOrderOutput outputObj = stockOrderOutputList.get(j);
            //System.out.println("LOG_OuPUT C Name " + outputObj.getCompanyName() + " CNAME " + companyName + " CAQ " + currentActionQuantity);
            if(outputObj.getCompanyName().equalsIgnoreCase(currentStock.getCompanyName()))
            {
                if(currentActionQuantity > 0)
                {
                    if(!currentStockSide.equalsIgnoreCase(outputObj.getSide()))
                    {
                        setStatusAndRemainingQuantityForStockOutputOrder(outputObj, "CLOSED", 0);
                    }
                }
                else
                {
                    if(!currentStockSide.equalsIgnoreCase(outputObj.getSide()))
                    {
                        //System.out.println("LOG_RQ in output" + outputObj.getRemainingQuantity() + " and quantity " + quantity);
                        int remainingQuantityToBeupdated = (outputObj.getRemainingQuantity() - quantity);
                        remainingQuantityToBeupdated = remainingQuantityToBeupdated > 0 ? remainingQuantityToBeupdated : 0;
                        setStatusAndRemainingQuantityForStockOutputOrder(outputObj, "OPEN", remainingQuantityToBeupdated);
                    }
                }
            }
        }
        StockOrderOutput outputForCurrentStockObj = getNewCurrentStockObj(currentStock, currentStockSide, currentActionQuantity);
        stockOrderOutputList.add(outputForCurrentStockObj);
    }

    //This method will create and return the stock object for the current input stock value.
    private static StockOrderOutput getNewCurrentStockObj(StockOrderInput currentStock, String side, int currentActionQuantity) {
        StockOrderOutput outputForCurrentStockObj = new StockOrderOutput(side, currentStock.getCompanyName(), currentStock.getQuantity());
        if(currentActionQuantity > 0)
        {
            setStatusAndRemainingQuantityForStockOutputOrder(outputForCurrentStockObj, "OPEN", currentActionQuantity);
        }
        else
        {
            setStatusAndRemainingQuantityForStockOutputOrder(outputForCurrentStockObj, "CLOSED", 0);
        }
        return outputForCurrentStockObj;
    }


    //This method is used to set remaining quantity and status in stock output order -- THIS METHOD IS WRITTEN TO AVOID CODE DUPLICATION.
    private static void setStatusAndRemainingQuantityForStockOutputOrder(StockOrderOutput outputForCurrentStockObj, String status, int remainingQuantity) {
        outputForCurrentStockObj.setStatus(status);
        outputForCurrentStockObj.setRemainingQuantity(remainingQuantity);
    }


    //This method is used to print the overall stock order from the first input to current.
    public static void printStockOrderOutputList(ArrayList<StockOrderOutput> stockOrderOutputList)
    {
        System.out.println("Side \t CName \t quantity \t Remining Quantity \t Status \t");
        for(int i = 0; i < stockOrderOutputList.size(); i++)
        {
            StockOrderOutput outputObj = stockOrderOutputList.get(i);
            String side = outputObj.getSide();
            String small_space_for_output = side.equalsIgnoreCase("BUY") ? "\t\t" : "\t";
            System.out.println(side + small_space_for_output + outputObj.getCompanyName() + "\t\t " + outputObj.getQuantity() + "\t\t\t\t" + outputObj.getRemainingQuantity() + "\t\t\t\t " + outputObj.getStatus());
        }
    }

}