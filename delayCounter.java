
package MatchingGame;

import javax.swing.*;
import java.awt.*;
import static java.lang.Thread.*;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class delayCounter extends Thread
{
    private int count;
    private int time;
    private MatchingGame screen;
    
    delayCounter(MatchingGame screen, int time)
    {  
        this.time = time;
        this.screen = screen;
        count = 0;
    }
    
    public synchronized void wake()
    {
        this.notifyAll();
    }
    
    @Override
    public synchronized void run()
    {       
        try 
        {
            wait();
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(delayCounter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true)
        {
            if(count >= time)
            {
                count = 0;            
            }

            count += 100;

            try
            {            
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {
               System.out.println(e);
            }

            if(count % time == 0)  
            {
                if(!screen.checkIsFirst())
                {
                    if(screen.checkSelectedEqual())
                    {   

                        screen.setcorrect();               
                    }
                    else
                    {                  
                        screen.setFalse();
                    }
                }
                
                try 
                {
                    wait();
                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(delayCounter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
