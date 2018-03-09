import java.awt.*;
import javax.swing.*;
import java.lang.Math.*;
import java.awt.event.*;

public class SummerScene  
{
	public static void main (String[] args)
	{
		JFrame frame = new JFrame();
    	frame.setTitle("Cool Penguin");
    	frame.setSize(500, 500);
    	
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	Summer s = new Summer ();
    	s.addMouseListener(s);
    	frame.add(s);
    	frame.setVisible(true);
	}
}

class Summer extends JComponent  implements MouseListener 
{
	int mouseX=0;
    int mouseY=0;
    int clicks=0;
    boolean nose = false;
	TimerListener t = new TimerListener();

  
    Timer timer = new Timer(100, t);
	
	int xshift=-450;
    int yshift=0;    
	protected void paintComponent(Graphics g)
	{
		final Graphics2D g2 = (Graphics2D) g;
			timer.start();
		
		
	Color sky = new Color (55,150,185);
	
	int B=0;
	
	for(int y =0;y<500;y++)
	{
        //draw horizontal lines with varying red values
        //create gradient for sunset
		Color sunset = new Color(200,(y/2+50)%250,50);
		g2.setColor(sunset);
		g2.drawLine(0,y,500,y);
	}
    //amplitude of wave
	int amp=20;
	
        
		for(double x=-450;x<=450;x++)
        {
            //if cosine is not 0 
        	if(Math.cos((x+xshift)*(3)*(3.1415/180))<=.001&&Math.cos((x+xshift)*(3)*(3.1415/180))>=-.001)
			{
                //random amplitude between 10 and 30
				amp=(int)(Math.random()*20+10 );
			}
        	
            double y = (amp) * Math.cos((x+xshift)*(3)*(3.1415/180));
            int Y = (int)y;
            int X = (int)x;
            B=Math.abs(X)%250;
            //color for wave gradient
            Color wave = new Color (B+5,200,200);
            g2.setColor(wave);
            //draw vertical lines to make gradient
            g2.drawLine(240+X,200-Y,240+X,600-Y);
        	
            
        }
       //penguin black
        Color penguin = new Color (20,20,30);
        
        //surfboard
        g2.setColor(Color.red);
        g2.fillOval(330+xshift,420,200,35);
        
        //surfboard line
        g2.setColor(Color.white);
        g2.drawLine(330+xshift,437,530+xshift,437);
        
        
        //penguin 
        g2.setColor(penguin);
        //head
        g2.fillOval(400+xshift,300,50,50);
        //torso
        g2.fillOval(388+xshift,335,75,100);
        
        
        //rotation for left arm
        g2.rotate(.5*Math.sin(xshift*3*(3.1415/180))/20);
        //draw left arm
        g2.fill(new Arc2D.Double(345+xshift, 360,70,30,0, 180,Arc2D.PIE));
        g2.rotate(-.5*Math.sin(xshift*3*(3.1415/180))/20);
        //rotation for right arm
        g2.rotate(.5*Math.cos(xshift*3*(3.1415/180))/20);
        //draw right arm
        g2.fill(new Arc2D.Double(435+xshift, 360,70,30,0, 180,Arc2D.PIE));       
        g2.rotate(-.5*Math.cos(xshift*3*(3.1415/180))/20);
        
        
        g2.setColor(Color.white);
        //stomach
        g2.fillOval(400+xshift,350,50,75);
        
        //eyes
        g2.fillOval(410+xshift,315,10,10);
        g2.fillOval(430+xshift,315,10,10);
        
        g2.setColor(Color.black);
        //pupils
        g2.fillOval(412+xshift,317,5,5);
        g2.fillOval(432+xshift,317,5,5);
        
       
        g2.setColor(Color.orange);
        //beak
        g2.fillOval(415+xshift,325,20,10);
        //feet
        g2.fillOval(390+xshift,430,30,10);
        g2.fillOval(430+xshift,430,30,10);
        
        
        //sunglasses
        g2.setColor(Color.black);
        g2.fillOval(400+xshift,yshift,25,15);
        g2.fillOval(425+xshift,yshift,25,15);
        //sunglass rims
        g2.setColor(Color.red);
        g2.drawOval(400+xshift,yshift,25,15);
        g2.drawOval(425+xshift,yshift,25,15);
        
        g2.setColor(Color.black);
        g2.setFont(new Font("Comic Sans", Font.BOLD, 10)); 
        g2.drawString("noot", mouseX,mouseY);
        
        
        //when mouse is pressed draw longer nose
        if(nose)
        {
        	g2.setColor(Color.orange);
        
       		g2.fillRect(422+xshift,326,40,9);
       		if(xshift%3==0)
       			nose=false;
       		
        }

        g2.setColor(Color.black);

		
		
		
	}
	
	class TimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //manipulation of instance fields
            int direction = 1;
            //move right by 4
            if(xshift<50)
    		xshift=xshift+4;
    		//reset to left edge
    		else
    		{
    			yshift = 0;
    			xshift=-450;
    		}
    		if(yshift<310)
    			yshift+=8;
    		
            repaint();
        }
    }
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {
				
			 }
		public void mousePressed(MouseEvent e) {
				clicks++;
				mouseX=e.getX();
				mouseY=e.getY();
                
                //draw long nose if mouse is pressed
				nose=true;
				repaint();
				}
}
