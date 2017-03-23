package Assign4;

import BasicIO.*;

public class Knapsack
{
  private ASCIIDataFile file;
  private BasicForm form;
  String prod;
  int price, totprice;
  Node aList, bList;
  
  public Knapsack()
  {
    file = new ASCIIDataFile();
    form = new BasicForm("Browse","Buy","Quit");
    int fbutton;
    inForm();
    
    for (;;)
    {
      fbutton = form.accept();
      if (fbutton == 2)
      {
        form.close();
        break;
      }
      if (fbutton == 0)
      {
        readFile();
      }
      if (fbutton == 1)
      {
        aList = fullList(file);
        partList(targ, aList);
      }
    }
  }
  
  private void inForm()
  {
    form.setTitle("Store");
    form.addTextField("targ","Target",10,10,570);
    form.addTextArea("totinv","Status",30,100,10,10);
  }
  
  private void fillForm(int price, String prod)
  {
    totprice = 9387;
    form.writeInt("targ", totprice);
    form.writeString("totinv", prod);
    form.writeString("totinv", price+"\n");
  }
    
  private void readFile()
  {
    for(;;)
    {
      prod = file.readString();
      price = file.readInt();
      Product p = new Product(prod, price);
      if (file.isEOF()) break;
      fillForm(price, prod);
    }
    form.writeString("totinv","---------"+"\n"+"\n");
    form.writeString("totinv","Products Selected:"+"\n");
    form.writeString("totinv","Placeholder"+"\n");
    form.writeString("totinv","---------"+"\n"+"\n");
    form.writeString("totinv","No product selection to purchase"+"\n");
    form.writeString("totinv","---------"+"\n"+"\n");
  }
  
  private Node fullList( ASCIIDataFile from )
  {
    prod = file.readString();
    price = file.readInt();
    if (file.isEOF())
    {
      return null;
    }
    else
    {
      Product p = new Product(prod,price);
      return new Node(p,fullList(from));
    }
   }
  
  private Node partList(int cost, Node partList)
  {
    if (partList.item <= cost)
    {
      if (bList==null)
      {
        bList = new Node(partList.item,null);
      } else
      {
        Node tempList=bList;
        while (tempList.next!=null)
        {
          tempList = tempList.next;
        }
        tempList.next = new Node(partList.item,null);
      }
      return partList(partList.item,partList.next);
    }
    
  }
  
  public static void main ( String[] args ) 
  {
    Knapsack s = new Knapsack(); 
  }
}

