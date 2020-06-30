package cardgametwo;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Mantas
 */
public class Button {
    
    private String filePath;
    private Image image;
    
    //the 4 button points
    private int x;
    private int y;
    private int x1;
    private int y1;
    private int width;
    private int height;
    
    
    public Button(int x, int y, String filePath){
        this.filePath = filePath;
        this.x = x;
        this.y = y;
        loadImage(filePath);
        getDimensions();
    }
    
    public void loadImage(String filePath){
        ImageIcon icon = new ImageIcon(filePath);
        image = icon.getImage();
    }
    
    public void getDimensions(){
        x1 = x + image.getWidth(null);
        y1 = y + image.getHeight(null);
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    
    public Image getImage(){
        return image;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getX1(){
        return x1;
    }
    
    public int getY1(){
        return y1;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setX1(int x1){
        this.x1 = x1;
    }
    
}
