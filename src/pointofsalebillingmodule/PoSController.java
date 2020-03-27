/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pointofsalebillingmodule;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author sanjeet
 */
public class PoSController {

    private ArrayList<Product> storeProducts;
    private ArrayList<Product> billItems;
   
    public PoSController() {
        this.storeProducts = new ArrayList<>(); 
    }
    
    /*********************
     * Method for adding products
     * @param productCode
     * @param productName
     * @param productPrice 
     ************************/
    
    private void askProducts(){
        System.out.println("Follow the instruction below to add the products.");
        if(addProducts()){
            System.out.println("Do you want to add another product (y/n)?");
            Scanner inp = new Scanner(System.in);
            String choice = inp.nextLine();
            System.out.println("Your choice is " + choice);
            if(choice.equals("y") || choice.equals("Y") || choice.equals("yes")){
                System.out.println("You opted for adding more product.");
               askProducts();
            }else{
                System.out.println("Going to main menu.");
               displayMenu();
            }
        }
    }
    
    private boolean addProducts(){
        
        Scanner productCodeScanner  = new Scanner(System.in);
        Scanner productNameScanner = new Scanner(System.in);
        Scanner productPriceScanner = new Scanner(System.in);
        
        int productCode;
        String productName;
        float productPrice;
        
       
        System.out.println("Enter the product code: ");
        productCode = productCodeScanner.nextInt(); 
            if(productCodeChecker(productCode)){      //Check Product Code Already exist or not
                System.out.printf("Product code %d already exist, please enter unique one.\n", productCode);
                addProducts();
            }       
      
            
        System.out.printf("Enter the product name for product code %d: ", productCode);
            productName = productNameScanner.nextLine(); 
           
        System.out.printf("\nEnter the price for %s: ", productName);
            productPrice = productPriceScanner.nextFloat(); 
            
        this.storeProducts.add(new Product(productCode, productName, productPrice));
        System.out.println("Product added successfully!");
        
        return true;
    }

    /**************
     * Method for removing products
     * @param productCode 
     * To delete more than one product from the
     * storeProducts arrayList
     ***********************/
    
    private void removeProduct(int productCode){
        //store admin should be able to delete product
        int indexOfProductCode = findIndexByProductCode(productCode);
        this.storeProducts.remove(indexOfProductCode); //Removes the product from the arrayList
        System.out.printf("\nProduct %s with product code %d deleted successfully!\n",
                this.storeProducts.get(indexOfProductCode).getProductName(),
                this.storeProducts.get(indexOfProductCode).getProductId());
    }
    
    private boolean askProductIdsToRemove(){
       Scanner productCodeScanner2  = new Scanner(System.in);
       int productCode;
       System.out.println("Enter the product code to remove: ");
       productCode = productCodeScanner2.nextInt(); 
            if(!productCodeChecker(productCode)){      //Check Product Code Already exist or not
                removeProduct(productCode);
            }else{
                System.out.printf("Product code %d don't exist, please enter valid product code.\n", productCode);
                askProductIdsToRemove();
            }       
       
        return true;
    }
    
    private void askToDeleteProduct(){
        if(askProductIdsToRemove()){
            System.out.println("Do you want to remove another product (y/n)?");
            Scanner inp = new Scanner(System.in);
            String choice = inp.nextLine();
            if(choice.equals("y") || choice.equals("Y") || choice.equals("yes")){
                System.out.println("You opted for removing more products.");
               askProducts();
            }else{
               System.out.println("Going to main menu.");
               displayMenu();
            }
        }
    }
    
    private void printProducts(){
        System.out.println("******************************************");
        System.out.println("| Id\t | Name\t\t | Price\t|");
        System.out.println("******************************************");
        this.storeProducts.forEach(product -> {
             System.out.printf("\n| %d\t | %s\t | $%.2f\t|\n",
                     product.getProductId(),
                     product.getProductName(),
                     product.getPrice());
        });
        System.out.println("******************************************");
        System.out.println("******************************************\n\n");
        
         displayMenu();
    }
    
    private boolean productCodeChecker(int productCode){
        if(findIndexByProductCode(productCode) != -1){
            return true;
        }
        return false;
    }
    
    
    /************************
     * Methods for Billing Operations
     * newBill - to initialize new bill ArrayList (Lets for generating new bill)
     * addBillItem - to add bill items in the bill ArrayList
     * removeBillItem - to remove bill item from the bill ArrayList
     ***********************/
    
    private void newBill(){
         this.billItems = new ArrayList<>();
    }
    
    private void addBillItem(){
           
    }
    
    private int findIndexByProductCode(int productCode){
      for(int i = 0; i < this.storeProducts.size(); i++){
          if(this.storeProducts.get(i).getProductId() == productCode){
              System.out.println("Found index " + productCode);
              return i; //Returning index value
          }
      }
      return -1; //Else return -1 as there cannot be -1 in array, array start from 0
    }
    
    private void removeBillItem(){
    
    }
    
    /************************
     * Menu in the beginning
     * would be like
     * : Please select the operation
     * 1. Add products
     * 2. Delete Products
     * 3. View Products
     * 4. Update Tax 
     * 5. Generate Bill
     ***********************/
    
    public void start(){ //do the similar Operation just for ease remembering the name
        displayMenu();
    }
    
    private void displayMenu(){
        System.out.println("****************************");
        System.out.println("PoS Billing Module v0.1");
        System.out.println("****************************");
        System.out.println("select the below operations");
        System.out.println("\t1. Add Products");
        System.out.println("\t2. Remove Products");
        System.out.println("\t3. View Products");
        System.out.println("\t4. Update Tax");
        System.out.println("\t5. Generate Bill");
        System.out.println("****************************");
        System.out.println("****************************");
        System.out.println("Enter your choice: ");
        //Execute operation execution for taking inputs
        
        operationExec();
    
    }
    
    private void operationExec(){
        int choice; //would contains the operation choice
        Scanner inputChoice = new Scanner(System.in);
      
        //Added try catch statement in case of exception such as user try to enter 
        //input other then integer

            choice = inputChoice.nextInt();
            System.out.println("choice is " + choice);
            
            if(choice > 0 && choice <= 5){
                //Execute operation Switcher
                operationSwitcher(choice);
            }else{
                 System.out.println("Else statement Please enter valid option [1-5].");
                 inputChoice.nextInt();
            }
    }
    //To switch between operations
    private void operationSwitcher(int choice){
    
       if(choice == 1){
          askProducts();
       }
       
       if(choice == 2){
          askToDeleteProduct();
       }
       
       if(choice == 3){
          printProducts();
       }
       
       if(choice == 4){
           
       }
       
       if(choice == 5){
       
       }
       
    }
    
}
