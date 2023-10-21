package Jframe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private final BufferedImage img;

    public ImagePanel(String path)
    {
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);



        if(img != null)
            g.drawImage(img, 0, 0, getWidth(), 70, Color.black, null);


    }



}
