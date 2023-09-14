import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to set the red and blue to 0 */
  public void greenOnly()
  {
    Pixel[][] pixels=this.getPixels2D();
    for(int r=0;r<pixels.length;r++){
      for(int c=0;c<pixels[r].length;c++){
        pixels[r][c].setBlue(0);
        pixels[r][c].setRed(0);
      }
    }
  }
 
  /** Method to set the red,green,blue to (255 - its original value)  */
  public void negate()
  {
    Pixel[][] pixels=this.getPixels2D();
    for(int r=0;r<pixels.length;r++){
      for(int c=0;c<pixels[r].length;c++){
        pixels[r][c].setRed(255-pixels[r][c].getRed());
        pixels[r][c].setBlue(255-pixels[r][c].getBlue());
        pixels[r][c].setGreen(255-pixels[r][c].getGreen());
      }
    }
  }
 
  /** Method to set all red,green,blue to the average of the three values  */
  public void grayscale()
  {
    Pixel[][] pixels=this.getPixels2D();
    for(int r=0;r<pixels.length;r++){
      for(int c=0;c<pixels[r].length;c++){
        int average=(int)((pixels[r][c].getAverage()*100+0.5)/100.0);
        pixels[r][c].setRed(average);
        pixels[r][c].setBlue(average);
        pixels[r][c].setGreen(average);
       
      }
    }
  }

  
  /** Method to make the shape of the fish stand out  */
  public void enhanceFish()
  {
    Pixel[][] pixels = this.getPixels2D();
    Color fishColor=new Color(12,163,194);
    Color fishColor2=new Color(17,159,160);
    Color fishColor3=new Color(19,163,174);

    for(int x=0;x<150;x++){
      for(int y=150;y<pixels[x].length;y++){
        if(pixels[x][y].colorDistance(fishColor)<15||pixels[x][y].colorDistance(fishColor2)<10||pixels[x][y].colorDistance(fishColor3)<10){
          pixels[x][y].setColor(Color.WHITE);
        }
      }
    }

  }
  
  /** Method to highlight the edges of object in a picture by checking large changes in color */
  public void edgeDetection()
  {
    Pixel[][]pixels=this.getPixels2D();
    Pixel[][]ref=pixels.clone();

    for(int x=0;x<pixels.length-1;x++){
      for(int y=0;y<pixels[x].length-1;y++){
        if(ref[x][y].colorDistance(ref[x][y+1].getColor())>15||ref[x][y].colorDistance(ref[x+1][y].getColor())>15){
          pixels[x][y].setColor(Color.BLACK);
        }
        else{
          pixels[x][y].setColor(Color.WHITE);
        }
      }
    }
  }
  
  /** Method to mirror the picture around a vertical line in the center of the picture from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels=this.getPixels2D();
    for(int r=0;r<pixels.length;r++){
      for(int c=0;c<164;c++){
        pixels[r][328-c].setColor(pixels[r][c].getColor());
      }
    }
  }
  
  /** Method to mirror around a diagonal line from bottom left to top right */
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    int min=Math.min(pixels[0].length,pixels.length);
    for(int x=0;x<min;x++){
      for(int y=x;y<min;y++){
        pixels[x][y].setColor(pixels[y][x].getColor());
      }
    }

  }
    
  /** Method to mirror just part of a picture of a temple to fix the broken part of the temple */
  public void mirrorTemple()
  {
    Pixel[][] pixels=this.getPixels2D();
    for(int r=0;r<97;r++){
      for(int c=0;c<283;c++){
        pixels[r][567-c].setColor(pixels[r][c].getColor());
      }
    }
  }
  
  /** Method to mirror just part of a picture of a snowman, so that it will have four arms instead of two */
  public void mirrorArms()
  {
    Pixel[][] pixels=this.getPixels2D();
    for(int r=160;r<195;r++){
      for(int c=100;c<295;c++){
        if(c<170||c>237){
          pixels[225-r+160][c].setColor(pixels[r][c].getColor());       
        }
      }
    }
}
  
  /** Method to copy the gull in the picture to another location of the picture */
  public void copyGull()
  {
    Pixel[][] pixels=this.getPixels2D();
    for(int r=235;r<320;r++){
      for(int c=238;c<340;c++){
        if(r>246&&c<248||r<242&&c<248){
          continue;
        }
        else if(pixels[r][c].colorDistance(pixels[r][700-c].getColor())>30){
        pixels[r][700-c].setColor(pixels[r][c].getColor());
      }
    }
  }
}
     
  
  /** Method to replace the blue background with the pixels in the newBack picture
    * @param newBack the picture to copy from
    */
  public void chromakey(Picture newBack)
  {
    Pixel[][] pixels=this.getPixels2D();
    Pixel[][] picturePix=newBack.getPixels2D();
    for(int r=0;r<pixels.length;r++){
      for(int c=0;c<pixels[r].length;c++){
        if(pixels[r][c].colorDistance(Color.BLUE)<237||pixels[r][c].colorDistance(Color.BLACK)<20){
          pixels[r][c].setColor(picturePix[r][c].getColor());
        }
      }
    }
  }
  
  /** Method to decode a message hidden in the red value of the current picture */
  public void decode()
  {
    Pixel[][] pixels=this.getPixels2D();
    for(int r=0;r<pixels.length;r++){
      for(int c=0;c<pixels[r].length;c++){
        if(pixels[r][c].getRed()%2==0){
          pixels[r][c].setColor(Color.BLACK);
        }
        else{
          pixels[r][c].setColor(Color.WHITE);
        }
      }
    }
  }
  
  /** Hide a black and white message in the current picture by changing the green to even and then setting it to odd if the message pixel is black 
    * @param messagePict the picture with a message
    */
  public void encodeGreen(Picture messagePict)
  {
    Pixel[][] pixels=this.getPixels2D();
    Pixel[][] messagePix=messagePict.getPixels2D();
    for(int r=0;r<messagePix.length;r++){
      for(int c=0;c<messagePix[r].length;c++){
        if(messagePix[r][c].getRed()==0&&messagePix[r][c].getBlue()==0&&messagePix[r][c].getGreen()==0){
          if(pixels[r][c].getGreen()%2==0){
            pixels[r][c].setGreen(pixels[r][c].getGreen()+1);
          }
        }
        else{
          if(pixels[r][c].getGreen()%2==1){
            pixels[r][c].setGreen(pixels[r][c].getGreen()-1);
          }
        }
      }
    }
  }

  /** Your own customized method*/
  public void customized()
  {
    Pixel[][] pixels=this.getPixels2D();
    int radius=Math.min(pixels.length,pixels[0].length)/2;
    for(int x=0;x<pixels.length;x++){
      for(int y=0;y<pixels[x].length;y++){
        double distance=Math.sqrt(Math.pow(y-pixels[0].length/2,2)+Math.pow(x-pixels.length/2,2));
        if(distance<=radius){
          if(x<=pixels.length/2&&y<=pixels[0].length/2){
            pixels[x][y].setGreen(0);
            pixels[x][y].setBlue(0);
          }
          else if(x<=pixels.length/2&&y>pixels[0].length/2){
            pixels[x][y].setRed(0);
            pixels[x][y].setBlue(0);
          }
          else if(x>pixels.length/2&&y<=pixels[0].length/2){
            pixels[x][y].setRed(0);
            pixels[x][y].setGreen(0);
          }
          else if(x>pixels.length/2&&y>pixels[0].length/2){
            pixels[x][y].setRed(255-pixels[x][y].getRed());
            pixels[x][y].setGreen(255-pixels[x][y].getGreen());
            pixels[x][y].setBlue(255-pixels[x][y].getBlue());
          }
        }
        else{
          pixels[x][y].setColor(Color.WHITE);
        }
      }
    }
  }
} 