/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto1lines;
import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JFrame;
/**
 *
 * @author Daniel Arboleda, Cristobal Ocampo
 */
public class Reto1Lines extends JPanel {
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.black);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.orange);
        Dimension size = getSize();//Tamaño de la ventana
        Insets insets = getInsets();//Vordes y titulos de la ventana
        int w =  size.width - insets.left - insets.right;
        int h =  size.height - insets.top - insets.bottom;
        int x,y,xT,yT;
        int radio = 100;
        int alpha=0;
        int increment = 4;//Para medir la separacion entre cada linea
        int n = 3; //Numero de circulos que deceo pintar
        //Este for es para pintar en numero de circulos que queramos. 
        for (int i=0;i<n;i++){
            for (alpha = 0;alpha < 360;alpha +=increment){
                double alphaRad = alpha * Math.PI/180d;
                x= (int)(radio * Math.cos(alphaRad));
                y= (int)(radio * Math.sin(alphaRad));
                xT = x-y;
                yT = y + x;
                //Se llama al algoritmo de Bresenham
                Bresenham(g, x+w/2,h/2-y,xT+w/2,h/2-yT);
                //Se Realiza un rando para generar colores aleatorios
                int R = (int)(Math.random()*256);
                int G = (int)(Math.random()*256);
                int B= (int)(Math.random()*256);
                g2d.setColor(new Color(R, G, B));
            }
            radio +=60;
        }
    }
    
    public void Bresenham(Graphics g,int x0,int y0,int x1,int y1){
       int x, y, dx, dy, p, incE, incNE, stepx, stepy;
        dx = (x1 - x0);
        dy = (y1 - y0);
       /* determinar que punto usar para empezar, cual para terminar */
        if (dy < 0) { 
          dy = -dy; 
          stepy = -1; 
        } 
        else stepy = 1;
        if (dx < 0) {  
          dx = -dx;  
          stepx = -1; 
        } 
        else stepx = 1;

        x = x0;
        y = y0;
        g.drawLine( x0, y0, x0, y0);
       /* se cicla hasta llegar al extremo de la línea */
        if(dx>dy){
            p = 2*dy - dx;
            incE = 2*dy;
            incNE = 2*(dy-dx);
            while (x != x1){
              x = x + stepx;
              if (p < 0){
                p = p + incE;
              }
              else {
                y = y + stepy;
                p = p + incNE;
              }
              g.drawLine( x, y, x, y);
            }
          }
          else{
            p = 2*dx - dy;
            incE = 2*dx;
            incNE = 2*(dx-dy);
            while (y != y1){
              y = y + stepy;
              if (p < 0){
                p = p + incE;
              }
              else {
                x = x + stepx;
                p = p + incNE;
              }
              g.drawLine( x, y, x, y);
              
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Reto1 Lineas");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      frame.add(new Reto1Lines());
      // Asignarle tamaño
      frame.setSize(800, 800);
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
    }
    
}
