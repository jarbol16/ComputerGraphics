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
 * @author Daniel Arboleda
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
        int increment = 3;//Para medir la separacion entre cada linea
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
        /**
     * Implementación del Algoritmo de Bresenham
     * @see <a href="http://members.chello.at/easyfilter/bresenham.html">Bresenham Algorithm</a>
     * @param g  Una ventana Java de tipo Graphics
     * @param x0 Coordenada x del primer punto
     * @param y0 Coordenada y del primer punto
     * @param x1 Coordenada x del segundo punto
     * @param y1 Coordenada y del segundo punto
     */
    public void Bresenham(Graphics g,int x0,int y0,int x1,int y1){
        Graphics2D g2d = (Graphics2D) g;
        int dx = Math.abs(x1 - x0);
        int dy = -Math.abs(y1 - y0);
        int err = dx + dy, e2;
        int sx, sy;
        if (x0 < x1) {
            sx = 1;
        } else {
            sx = -1;
        }
        if (y0 < y1) {
            sy = 1;
        } else {
            sy = -1;
        }
        for (;;) {
            g2d.drawLine(x0, y0, x0, y0);
            if (x0 == x1 && y0 == y1) {
                break;
            }
            e2 = 2 * err;
            if (e2 >= dy) {
                err += dy;
                x0 += sx;
            }
            if (e2 <= dx) {
                err += dx;
                y0 += sy;
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
