/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.util.Random;

public class Reto2_CSA extends JPanel{
     private static  int yMax = 125;
    private static  int yMin = -125;
    private static  int xMax = 125;
    private static  int xMin = -125;
    private static final int INSIDE = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 4;
    private static final int TOP = 8;

    private int h, w;
    private Graphics2D g2d;

    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g2d = (Graphics2D) g;
       setBackground(Color.YELLOW);
       // size es el tamaño de la ventana.
       Dimension size = getSize();
       // Insets son los bordes y los títulos de la ventana.
       Insets insets = getInsets();
       w =  500;
       h =  500;
       paintM(w);
       paintLines();
       
    }
        /**
     * Pintar el rectangulo de 250 x 250
     * @param w longitid
     */
    public void paintM(int w){
        g2d.setColor(Color.BLACK);
        g2d.drawLine(w/4,w/4,w-w/4,w/4);
        g2d.drawLine(w/4,w-w/4,w/4,w/4);
        g2d.drawLine(w/4,w-w/4,w-w/4,w-w/4);
        g2d.drawLine(w-w/4,w/4,w-w/4,w-w/4);
    }

    public void paintLines(){
        long tiempoInicial = System.currentTimeMillis();
        for (int i = 0; i<10000; i++) {
            int xp0 = (int) (Math.random() * w - w/2);
            int yp0 = (int) (Math.random() * h - h/2);
            int xp1 = (int) (Math.random() * w - w/2);
            int yp1 = (int) (Math.random() * h - h/2);
            CohenSutherland(xp0,yp0, xp1,yp1);
        }
        System.out.println("Cohen–Sutherland algorithm: " + (System.currentTimeMillis() - tiempoInicial) + " milisegundos");
    }

    /*    
     * Valida en que posicion se encuentra cada punto 
     * (inicial-final) de la linea 
     * @param x punto inicial 
     * @param y punto final
     * @return codigo de ubicacion
     *Tomado de https://en.wikipedia.org/wiki/Cohen%E2%80%93Sutherland_algorithm
     */

    public int COutCode(int x, int y){
        int code = INSIDE;
        if (x < xMin)
            code |= LEFT;
        else if (x > xMax)
            code |= RIGHT;
        if (y < yMin)
            code |= BOTTOM;
        else if (y > yMax)
            code |= TOP;

        return code;
    }

    public void CohenSutherland(int x0, int y0, int x1, int y1){
        int code0 = COutCode(x0, y0);
        int code1 = COutCode(x1, y1);

        while(true){
            if((code0 | code1) == 0){
                g2d.setColor(Color.BLUE);
                g2d.drawLine(x0 + w/2,h/2 - y0,x1 + w/2,h/2 - y1);
                break;
            }else if ((code0 & code1) != 0 ) {
                g2d.setColor(Color.RED);
                g2d.drawLine(x0 + w/2,h/2 - y0,x1 + w/2,h/2 - y1);
                break;
            }else{
                int  x = 0, y = 0;
                int outCodeOut = code0 !=0 ? code0 : code1;

                if ((outCodeOut & TOP) != 0) {
                    x = x0 + (x1-x0) * (yMax-y0) / (y1-y0);
                    y = yMax;
                } else if ((outCodeOut & BOTTOM) != 0) { // point is below the clip rectangle
                    x = x0 + (x1 - x0) * (yMin - y0) / (y1 - y0);
                    y = yMin;
                } else if ((outCodeOut & RIGHT) != 0) {  // point is to the right of clip rectangle
                    y = y0 + (y1 - y0) * (xMax - x0) / (x1 - x0);
                    x = xMax;
                } else if ((outCodeOut & LEFT) != 0) {   // point is to the left of clip rectangle
                    y = y0 + (y1 - y0) * (xMin - x0) / (x1 - x0);
                    x = xMin;
                }

                if (outCodeOut == code0) {
                    g2d.setColor(Color.RED);
                    g2d.drawLine(x0 + w/2,h/2 - y0,x + w/2,h/2 - y);
                    x0 = x;
                    y0 = y;
                    code0 = COutCode(x0, y0);
                } else {
                    g2d.setColor(Color.RED);
                    g2d.drawLine(x1 + w/2,h/2 - y1,x + w/2,h/2 - y);
                    x1 = x;
                    y1 = y;
                    code1 = COutCode(x1, y1);
                }
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Cohen Sutherland Algorithm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Reto2_CSA());
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
    }


}