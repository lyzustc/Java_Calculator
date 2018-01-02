package courseProject;
import java.util.*;
public class ArithCal {
    private String ArithText;
    private Stack<Double> Numbers;
    private Stack<String> Operators;
    private int StrPtr, OpeLevel;
    
    public ArithCal(String Text) {
    	ArithText = Text;
    	Numbers = new Stack<>();
    	Operators = new Stack<>();
    	Operators.push("end");
    	StrPtr = 0;
    	OpeLevel = 0;
    }
    
	public double ArithCalculate() throws MyException {
		while(ArithText.charAt(StrPtr) != '=')
		{
			if('0' <= ArithText.charAt(StrPtr) && ArithText.charAt(StrPtr) <= '9') {
				Numbers.push(GetNextNum());
			}
			else {
				String ope = GetNextOpe();
				if(ope.equals("("))
				{
					ArithCal subArithText = new ArithCal(ArithText.substring(StrPtr, ArithText.lastIndexOf(')')) + "=");
					Numbers.push(subArithText.ArithCalculate());
					StrPtr = ArithText.lastIndexOf(')') + 1;
					continue;
				}
				else if(ope.equals("+") || ope.equals("-"))
				{
					while(Operators.peek().equals("end") == false)
					  Calculate();
					OpeLevel = 0;
				}
				else if(ope.equals("*") || ope.equals("/"))
				{
					if(OpeLevel > 0)
					  while(OpeLevel != 0)
					    Calculate();
					OpeLevel = 1;
				}
				else if(ope.equals("^"))
				{
					if(OpeLevel > 1)
					  while(OpeLevel >= 2)
					    Calculate();
					OpeLevel = 2;
				}
				else if( (ope.equals("lg") || ope.equals("log") || ope.equals("ln")))
				{
					OpeLevel = 3;
				}
				else
					throw new MyException();
				Operators.push(ope);
			}
		}
		while(Operators.peek().equals("end")==false)
			Calculate();
		return Numbers.pop();
	}
	
	Double GetNextNum() {
		int start = StrPtr;
		while('0' <= ArithText.charAt(StrPtr) && ArithText.charAt(StrPtr) <= '9'||ArithText.charAt(StrPtr) == '.')
		{
			StrPtr ++;
		}
		
		return Double.parseDouble(ArithText.substring(start, StrPtr));
	}
	
	String GetNextOpe() {
		int start = StrPtr;
		if(ArithText.charAt(StrPtr) < 'a' || ArithText.charAt(StrPtr) > 'z')
			StrPtr ++;
		else
		while(ArithText.charAt(StrPtr) < 'z' && ArithText.charAt(StrPtr) > 'a')
		{
			StrPtr ++;
		}
		
		return ArithText.substring(start, StrPtr);
	}
	
	void Calculate() {
		switch(OpeLevel) {
		  case 0: MathCal(Operators.pop());
		          break;
		  case 1: MathCal(Operators.pop());
		          OpeLevel = 0;
		          break;
		  case 2: MathCal(Operators.pop());
		          if(Operators.peek().equals("*") || Operators.peek().equals("/"))
		        	  OpeLevel = 1;
		          else OpeLevel = 0;
		          break;
		  case 3: MathCal(Operators.pop());
		          if(Operators.peek().equals("^"))
		        	  OpeLevel = 2;
		          else if(Operators.peek().equals("*") || Operators.peek().equals("/"))
		        	  OpeLevel = 1;
		          else OpeLevel = 0;
                  break;		
		}
	}
	
	void MathCal(String Ope) {
		if(Ope.equals("+"))
		{
			Double num1 = Numbers.pop(), num2 = Numbers.pop();
			Numbers.push(num1 + num2);
		}
		else if(Ope.equals("-"))
		{
			Double num1 = Numbers.pop(), num2 = Numbers.pop();
			Numbers.push(num2 - num1);
		}
		else if(Ope.equals("*"))
		{
			Double num1 = Numbers.pop(), num2 = Numbers.pop();
			Numbers.push(num1 * num2);
		}
		else if(Ope.equals("/"))
		{
			Double num1 = Numbers.pop(), num2 = Numbers.pop();
			Numbers.push(num2 / num1);
		}
		else if(Ope.equals("^"))
		{
			Double num1 = Numbers.pop(), num2 = Numbers.pop();
			Numbers.push(Math.pow(num2, num1));
		}
		else if(Ope.equals("lg"))
		{
			Double num1 = Numbers.pop();
			Numbers.push(Math.log10(num1));
		}
		else if(Ope.equals("ln"))
		{
			Double num1 = Numbers.pop();
			Numbers.push(Math.log(num1));
		}
		else if(Ope.equals("log"))
		{
			Double num1 = Numbers.pop();
			Numbers.push(Math.log(num1)/Math.log(2));
		}
		else
			Operators.push(Ope);
	}
}

class MyException extends Exception{};