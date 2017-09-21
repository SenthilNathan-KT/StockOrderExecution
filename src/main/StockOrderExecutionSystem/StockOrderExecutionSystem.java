import java.util.*;


public class StockOrderExecutionSystem
{

    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        StockManagement stockManagementObj = new StockManagement();
	while(true)
        {
            StockOrderInput[] inputStockOrderArray = getValidInputForStockOrderArray(kb);
            for (int i = 0; i < inputStockOrderArray.length; i++)
            {
                StockOrderInput currentStock = inputStockOrderArray[i];
                CompanyStock currentCompanyStock = stockManagementObj.updateCompanyRemainingStock(currentStock);
                stockManagementObj.updateStockOrderOutputList(currentStock, currentCompanyStock);
            }
            stockManagementObj.printStockOrderOutputList();
            System.out.println("To continue enter 1");
            if(kb.nextInt() != 1)
            {
                break;
            }
        }
        System.out.println("Press 1 to view the integrity check");
        if(kb.nextInt() == 1)
        {
            integrityCheckForCompanyStock(stockManagementObj.companyStockMap);
        }
    }

    private static void integrityCheckForCompanyStock(HashMap<String, CompanyStock> companyStockMap)
    {
        Set companyStockKeySet = companyStockMap.keySet();
        Iterator keySetIterator = companyStockKeySet.iterator();
        System.out.println("1) Both buy and sell quantity of an company cannot have values.\n2) Buy/sell quantity cannot be less than 0.");
        boolean isIntegritySuccess = true;
        while(keySetIterator.hasNext())
        {
            CompanyStock companyStockObj = companyStockMap.get(keySetIterator.next());
            int buyQuantity = companyStockObj.getBuyQuantity();
            int sellQuantity = companyStockObj.getSellQuantity();
            System.out.println("Company Name - " + companyStockObj.getCompanyName() + ", buy quantity - " + buyQuantity + ", sell quantity - " + sellQuantity);
            //Both buy and sell quantity should not have value.
            if(buyQuantity != 0 && sellQuantity != 0)
            {
                isIntegritySuccess = false;
                System.out.println("Integrity check failed as both Buy and Sell quantities of an company cannot have values.");
            }
            else if(buyQuantity < 0 || sellQuantity < 0)
            {
                isIntegritySuccess = false;
                System.out.println("Integrity check failed as Buy/Sell quantity cannot be less than 0");
            }
        }
        if(isIntegritySuccess)
        {
            System.out.println("All companies have been successfully passed the integrity checks.");
        }
    }

    //This method will take care to get the valid input from the user.
    private static StockOrderInput[] getValidInputForStockOrderArray(Scanner kb)
    {
        System.out.println("Enter number of stock orders you are going to enter now");
        int totalNoOfStocks = kb.nextInt();
        StockOrderInput[] inputStockOrderArray = new StockOrderInput[totalNoOfStocks];
        for(int i = 0; i < totalNoOfStocks; i++)
        {
            System.out.println("Please enter a valid input for Side(BUY/SELL),Company Name, total no of quantities with comma seperated for stock number " + (i+1));
            String[] inputArr = getinputStockOrderArray(kb);
            boolean canSkip = false;
            while(!canSkip)
            {
                String side = inputArr[0];
                if(!(side.equalsIgnoreCase("BUY") || side.equalsIgnoreCase("SELL")))
                {
                    canSkip = false;
                    System.out.println("Enter valid Side");
                }
                else
                {
                    canSkip= true;
                }
                String companyName = inputArr[1];
                String value = inputArr[2];
                int quantity = 0;
                if (quantity <= 0)
                {
                    try
                    {
                        quantity = Integer.parseInt(value);
                        canSkip = true;
                    }
                    catch (Exception e)
                    {
                        canSkip = false;
                        System.out.println("Enter a valid quantity");
                    }
                }

                if(!canSkip)
                {
                    inputArr = getinputStockOrderArray(kb);
                }
                else
                {
                    inputStockOrderArray[i] = new StockOrderInput(side, companyName, quantity);
                    //System.out.println("LOG_Values in inputStockOrderArray is : " + inputStockOrderArray[i].quantity + ", " + inputStockOrderArray[i].companyName + " ," + inputStockOrderArray[i].quantity);
                    break;
                }
            }
        }
        return inputStockOrderArray;
    }

    //This will check that only 3 inputs should be given by the user in comma separated format.
    private static String[] getinputStockOrderArray(Scanner kb)
    {
        String[] inputArr = kb.next().split(",");
        while(inputArr.length != 3)
        {
            System.out.println("Please enter correct number of inputs");
            inputArr = getinputStockOrderArray(kb);
        }
        return inputArr;
    }
}
