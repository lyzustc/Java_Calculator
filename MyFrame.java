package courseProject;
import java.awt.*;
import javax.swing.JFrame;

public class MyFrame extends JFrame{
    Toolkit tk = Toolkit.getDefaultToolkit();
    MyPanel Panel = new MyPanel();
    
    public static void main(String[] args) {
		new MyFrame();
	}
	public MyFrame() {
		
		setTitle("MyCalculator");
		
		FrameSizeSet();
		
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ProImageSet();
	    this.setVisible(true);
	
	    this.add(Panel);
	}
	
	void FrameSizeSet() {
		Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        
        setSize((int)(screenWidth*0.9), (int)(screenHeight*0.8));
        
        setResizable(false);
	}
	
	void ProImageSet(){
		Image ProImage= tk.getImage(getClass().getResource("ProImage.jpg"));
        setIconImage(ProImage);
	}
}
