package cardgametwo;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Mantas
 */
public class Card {
    
    private String name;
    private String type;
    private String imagePath;
    
    private String color;
    private boolean isRoyal;
    
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    

    
    public Card(String name, String type, String imagePath){
        this.name = name;
        this.type = type;
        this.imagePath = imagePath;
        setCorrectType();
        setAttributes();
        loadImage(imagePath);       
    }    
    
    public void getDimensions(){
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    
    public void loadImage(String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        image = icon.getImage();
    }
    
    public String getColor(){
        return color;
    }
    
    public boolean getIsRoyal(){
        return isRoyal;
    }
  
    public String getName(){
        return name;
    }
    
    public String getType(){
        return type;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
    
    public Image getImage(){
        return image;
    }
    
    public void setNewImagePath(String path){
        this.imagePath = path;
    }
    
    private void setCorrectType(){
        if(type.equals("10"))
            setType("Jack");
        if(type.equals("11"))
            setType("Queen");
        if(type.equals("12"))
            setType("King");
    }
    
    private void setAttributes(){
        if(name.equals("Diamonds") || name.equals("Hearts"))
            color = "Red";
        if(name.equals("Spades") || name.equals("Clubs"))
            color = "Black";
        if(type.equals("Jack") || type.equals("Queen") || type.equals("King"))
            isRoyal = true;
    }
    
    @Override
    public String toString(){
        String line = name + " " + type;
        return line;
    }
    
    
}
