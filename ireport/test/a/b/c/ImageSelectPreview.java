package a.b.c;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class ImageSelectPreview extends JPanel implements PropertyChangeListener {
     private JFileChooser jfc;
     private Image img;

     public ImageSelectPreview(JFileChooser jfc) {
          this.jfc = jfc;
          Dimension sz = new Dimension(200,200);
          setPreferredSize(sz);
     }

     public void propertyChange(PropertyChangeEvent evt) {
         try {
               File file = jfc.getSelectedFile();
               updateImage(file);
              } catch (IOException ex) {
                   System.out.println(ex.getMessage());
                   ex.printStackTrace();
             }
     }
     
     public void updateImage(File file) throws IOException {
      if(file == null) {
           return;
      }
      img = ImageIO.read(file);
          repaint();
     }
     public void paintComponent(Graphics g) {
           g.setColor(Color.gray);
           g.fillRect(0,0,getWidth(),getHeight());
      if(img != null) {
           int w = img.getWidth(null);
           int h = img.getHeight(null);
           int side = Math.max(w,h);
           double scale = 200.0/(double)side;
           w = (int)(scale * (double)w);
           h = (int)(scale * (double)h);
           //将这个Image画出来
           g.drawImage(img,0,0,w,h,null);
   
           String dim = w + " x " + h;
           g.setColor(Color.black);
           g.drawString(dim,31,196);
           g.setColor(Color.white);
           g.drawString(dim,30,195);
           g.setColor(Color.black);
           //这句随便你加不加显示在预览窗口的
           g.drawString("Swing如此漂亮",30,100);
      }
 }
      public static void main(String[] args) {
           JFileChooser jfc = new JFileChooser();
           ImageSelectPreview preview = new ImageSelectPreview(jfc);
           jfc.addPropertyChangeListener(preview);
           jfc.setAccessory(preview);
           jfc.showOpenDialog(null);
      }

}
