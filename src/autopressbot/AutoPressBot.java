package autopressbot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class AutoPressBot extends Thread {

private long tiempoParaIniciar = 10;
private long tiempoIteracion = 1;
private long numeroIteraciones;
private long tiempoDeFuncion = 3600;
private long contadorInteraciones = 0;
private JFrameBot jfBot;

    public AutoPressBot() {
        
    }

    public void run() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(AutoPressBot.class.getName()).log(Level.SEVERE, null, ex);
        }

        numeroIteraciones = tiempoDeFuncion / tiempoIteracion;

        tiempoParaIniciar *= 1000;
        tiempoIteracion *= 1000;

        try {
            this.sleep(tiempoParaIniciar);
        } catch (InterruptedException ex) {
            Logger.getLogger(AutoPressBot.class.getName()).log(Level.SEVERE, null, ex);
        }

        jfBot.getJlblTrabajando().setText("Trabajando...");
        
        while (contadorInteraciones < numeroIteraciones) {
            robot.keyPress(KeyEvent.VK_Q);
            robot.keyRelease(KeyEvent.VK_Q);
            contadorInteraciones++;
            jfBot.getJlblIteraciones().setText("Iteraciones: " + contadorInteraciones);
            jfBot.getJlblTiempoRestante().setText("Iteraciones restantes: " + (numeroIteraciones-contadorInteraciones));
            jfBot.getjProgressBarBot().setValue(jfBot.getjProgressBarBot().getValue()+1);
            try {
                Thread.sleep(tiempoIteracion);
            } catch (InterruptedException ex) {
                Logger.getLogger(AutoPressBot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jfBot.getJlblTrabajando().setText("Finalizado :)");
        jfBot.getJlblImagenPicoDiamante().setIcon(new javax.swing.ImageIcon(getClass().getResource("/autopressbot/data/Diamante.png")));
    }

    public long getTiempoParaIniciar() {
        return tiempoParaIniciar;
    }

    public void setTiempoParaIniciar(long tiempoParaIniciar) {
        this.tiempoParaIniciar = tiempoParaIniciar;
    }

    public long getTiempoIteracion() {
        return tiempoIteracion;
    }

    public void setTiempoIteracion(long tiempoIteracion) {
        this.tiempoIteracion = tiempoIteracion;
    }

    public long getNumeroIteraciones() {
        return numeroIteraciones;
    }

    public void setNumeroIteraciones(long numeroIteraciones) {
        this.numeroIteraciones = numeroIteraciones;
    }

    public long getTiempoDeFuncion() {
        return tiempoDeFuncion;
    }

    public void setTiempoDeFuncion(long tiempoDeFuncion) {
        this.tiempoDeFuncion = tiempoDeFuncion;
    }

    public long getContadorInteraciones() {
        return contadorInteraciones;
    }

    public void setContadorInteraciones(long contadorInteraciones) {
        this.contadorInteraciones = contadorInteraciones;
    }

    public JFrameBot getJfBot() {
        return jfBot;
    }

    public void setJfBot(JFrameBot jfBot) {
        this.jfBot = jfBot;
    }

    
}
