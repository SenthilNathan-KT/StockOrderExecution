import java.util.ArrayList;
import java.util.HashMap;

class StockManagement
{
	ArrayList<StockOrderOutput> stockOrderOutputList = new ArrayList<>();
 	HashMap<String, CompanyStock> companyStockMap = new HashMap<>(); 

	// This method is used to update the stock order output list based on the current stock input.
    public void updateStockOrderOutputList(StockOrderInput currentStock, CompanyStock currentCompanyStock)
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
                        setStatusAndRemainingQuantityForStockOutputOrder(outputObj, Status.CLOSED.getStatus(), 0);
                    }
                }
                else
                {
                    if(!currentStockSide.equalsIgnoreCase(outputObj.getSide()))
                    {
                        //System.out.println("LOG_RQ in output" + outputObj.getRemainingQuantity() + " and quantity " + quantity);
                        int remainingQuantityToBeupdated = (outputObj.getRemainingQuantity() - quantity);
                        remainingQuantityToBeupdated = remainingQuantityToBeupdated > 0 ? remainingQuantityToBeupdated : 0;
                        setStatusAndRemainingQuantityForStockOutputOrder(outputObj, Status.OPEN.getStatus(), remainingQuantityToBeupdated);
                    }
                }
            }
        }
        StockOrderOutput outputForCurrentStockObj = getNewCurrentStockObj(currentStock, currentStockSide, currentActionQuantity);
        stockOrderOutputList.add(outputForCurrentStockObj);
    }

    private StockOrderOutput getNewCurrentStockObj(StockOrderInput currentStock, String side, int currentActionQuantity) {
        StockOrderOutput outputForCurrentStockObj = new StockOrderOutput(side, currentStock.getCompanyName(), currentStock.getQuantity());
        if(currentActionQuantity > 0)
        {
            setStatusAndRemainingQuantityForStockOutputOrder(outputForCurrentStockObj, Status.OPEN.getStatus(), currentActionQuantity);
        }
        else
        {
            setStatusAndRemainingQuantityForStockOutputOrder(outputForCurrentStockObj, Status.CLOSED.getStatus(), 0);
        }
        return outputForCurrentStockObj;
    }


    private void setStatusAndRemainingQuantityForStockOutputOrder(StockOrderOutput outputForCurrentStockObj, String status, int remainingQuantity) {
        outputForCurrentStockObj.setStatus(status);
        outputForCurrentStockObj.setRemainingQuantity(remainingQuantity);
    }

    public void printStockOrderOutputList()
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

     //This method is used to update the company remaining stock in CompanyStock object which have the total count of both Buy and Sell quantities.
    public CompanyStock updateCompanyRemainingStock(StockOrderInput obj)
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
}
