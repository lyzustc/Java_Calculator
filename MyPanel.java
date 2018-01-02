package courseProject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.*;

public class MyPanel extends JPanel implements ActionListener
{
	JTextField TextFlow;
	String Text;
	Stack<String> lastText = new Stack<>();
    Font ffont=new Font("黑体", 1, 26);
	
    JButton btClear = new JButton("clear");
	JButton btDelete = new JButton("delete");
	
	JButton btAdd = new JButton("+");
	JButton btSub = new JButton("-");
	JButton btMulti = new JButton("*");
	JButton btDiv = new JButton("/");
	JButton btPoint = new JButton(".");
	JButton btEqu = new JButton("=");
	JButton btPow = new JButton("^");
	JButton btLn = new JButton("ln");
	JButton btLg = new JButton("lg");
	JButton btLeft = new JButton("(");
	JButton btRight = new JButton(")");
	JButton btLog = new JButton("log");
    ArrayList<JButton> btNum = new ArrayList<>();
    
    JButton source;
    
    
    public MyPanel() {
	  TextFlow = new JTextField(50);
	  TextFlow.setFont(ffont);
	  Text = "";
	  TextFlow.setText(Text);
	  add(TextFlow);
	  TextFlow.setBounds(90,20,1000,40);
	  ButtonSet();
    }
    
    void ButtonSet() {
    	setLayout(null);
    	
    	
    	for(int i = 0; i < 10; i ++)
    		btNum.add(new JButton(String.valueOf(i)));
    	
    	btClear.setBounds(90,75,466,50);
    	btClear.setFont(ffont);
    	add(btClear);
    	btDelete.setBounds(622,75,466,50);
    	btDelete.setFont(ffont);
    	add(btDelete);
    	
    	btNum.get(9).setBounds(90,140,200,50);
    	btNum.get(9).setFont(ffont);
    	add(btNum.get(9));
    	btNum.get(8).setBounds(356,140,200,50);
    	btNum.get(8).setFont(ffont);
    	add(btNum.get(8));
    	btNum.get(7).setBounds(622,140,200,50);
    	btNum.get(7).setFont(ffont);
    	add(btNum.get(7));
    	btAdd.setBounds(888,140,200,50);
    	btAdd.setFont(ffont);
    	add(btAdd);
    	
    	btNum.get(6).setBounds(90,205,200,50);
    	btNum.get(6).setFont(ffont);
    	add(btNum.get(6));
    	btNum.get(5).setBounds(356,205,200,50);
    	btNum.get(5).setFont(ffont);
    	add(btNum.get(5));
    	btNum.get(4).setBounds(622,205,200,50);
    	btNum.get(4).setFont(ffont);
    	add(btNum.get(4));
    	btSub.setBounds(888,205,200,50);
    	btSub.setFont(ffont);
    	add(btSub);
    	
    	btNum.get(3).setBounds(90,270,200,50);
    	btNum.get(3).setFont(ffont);
    	add(btNum.get(3));
    	btNum.get(2).setBounds(356,270,200,50);
    	btNum.get(2).setFont(ffont);
    	add(btNum.get(2));
    	btNum.get(1).setBounds(622,270,200,50);
    	btNum.get(1).setFont(ffont);
    	add(btNum.get(1));
    	btMulti.setBounds(888,270,200,50);
    	btMulti.setFont(ffont);
    	add(btMulti);
    	
        btPoint.setBounds(90,335,200,50);
        btPoint.setFont(ffont);
        add(btPoint);
        btNum.get(0).setBounds(356,335,200,50);
    	btNum.get(0).setFont(ffont);
    	add(btNum.get(0));
    	btEqu.setBounds(622,335,200,50);
        btEqu.setFont(ffont);
        add(btEqu);
        btDiv.setBounds(888,335,200,50);
        btDiv.setFont(ffont);
        add(btDiv);
    	
        btPow.setBounds(90,400,200,50);
        btPow.setFont(ffont);
        add(btPow);
        btLog.setBounds(356,400,200,50);
    	btLog.setFont(ffont);
    	add(btLog);
        btLn.setBounds(622,400,200,50);
    	btLn.setFont(ffont);
    	add(btLn);
    	btLg.setBounds(888,400,200,50);
        btLg.setFont(ffont);
        add(btLg);
        
        btLeft.setBounds(90,465,200,50);
        btLeft.setFont(ffont);
        add(btLeft);
        btRight.setBounds(356,465,200,50);
        btRight.setFont(ffont);
        add(btRight);
        
        btClear.addActionListener(this);
        btDelete.addActionListener(this);
        for(int i = 0; i < 10; i ++)
          btNum.get(i).addActionListener(this);
        btAdd.addActionListener(this);
        btSub.addActionListener(this);
        btMulti.addActionListener(this);
        btDiv.addActionListener(this);
        btPoint.addActionListener(this);
        btEqu.addActionListener(this);
        btPow.addActionListener(this);
        btLog.addActionListener(this);
        btLn.addActionListener(this);
        btLg.addActionListener(this);
        btLeft.addActionListener(this);
        btRight.addActionListener(this);
    }
    
   public void actionPerformed(ActionEvent event)
    {  
        source = (JButton)event.getSource();
        if(source == btClear)
        {
        	Text = "";
        	while(lastText.empty() == false)
        		lastText.pop();
        }
        else if(source == btDelete)
        {
        	if(lastText.empty() == false) {
        	   int last = Text.lastIndexOf(lastText.pop());
        	   Text = Text.substring(0, last);
        	}
        }
        else
        {
            Text += source.getText();
            if(source.getText().equals("="))
            {
                ArithCal ArithCalResult = new ArithCal(Text);
                try {
                double CalResult = ArithCalResult.ArithCalculate();
            	Text += CalResult;
            	  lastText.push("=");
            	lastText.push(CalResult + "");
                }
                catch(EmptyStackException e){
                	Text = "表达式错误：输入过多的运算符";
                }
                catch(StringIndexOutOfBoundsException e) {
                	Text = "表达式错误：缺少右括号";
                }
                catch(NumberFormatException e) {
                	Text = "表达式错误：输入过多的小数点";
                }
                catch(MyException e) {
                	Text = "表达式错误：缺少左括号";
                }
            }
            else	
                lastText.push(source.getText());
        }
        TextFlow.setText(Text);
    }
}
