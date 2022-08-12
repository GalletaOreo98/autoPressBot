package autopressbot;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
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
    private boolean isKey = true;
    private int keyCode = 81;
    private boolean hasMov = false;
    private boolean apagar = false;

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

        if (isKey) {
            if (hasMov) {
                while (contadorInteraciones < numeroIteraciones) {
                    Toolkit t = Toolkit.getDefaultToolkit();
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int w = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
                    int h = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
                    int xMov = (int) (w * 0.5);
                    int yMov = (int) (h * 0.75);
                    robot.mouseMove(xMov, yMov);
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);
                    contadorInteraciones++;
                    jfBot.getJlblIteraciones().setText("Iteraciones: " + contadorInteraciones);
                    jfBot.getJlblTiempoRestante().setText("Iteraciones restantes: " + (numeroIteraciones - contadorInteraciones));
                    jfBot.getjProgressBarBot().setValue(jfBot.getjProgressBarBot().getValue() + 1);
                    try {
                        Thread.sleep(tiempoIteracion);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AutoPressBot.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                while (contadorInteraciones < numeroIteraciones) {
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);
                    contadorInteraciones++;
                    jfBot.getJlblIteraciones().setText("Iteraciones: " + contadorInteraciones);
                    jfBot.getJlblTiempoRestante().setText("Iteraciones restantes: " + (numeroIteraciones - contadorInteraciones));
                    jfBot.getjProgressBarBot().setValue(jfBot.getjProgressBarBot().getValue() + 1);
                    try {
                        Thread.sleep(tiempoIteracion);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AutoPressBot.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            if (hasMov) {
                while (contadorInteraciones < numeroIteraciones) {
                    Toolkit t = Toolkit.getDefaultToolkit();
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int w = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
                    int h = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
                    int xMov = (int) (w * 0.5);
                    int yMov = (int) (h * 0.75);
                    robot.mouseMove(xMov, yMov);
                    robot.mousePress(keyCode);
                    robot.mouseRelease(keyCode);
                    contadorInteraciones++;
                    jfBot.getJlblIteraciones().setText("Iteraciones: " + contadorInteraciones);
                    jfBot.getJlblTiempoRestante().setText("Iteraciones restantes: " + (numeroIteraciones - contadorInteraciones));
                    jfBot.getjProgressBarBot().setValue(jfBot.getjProgressBarBot().getValue() + 1);
                    try {
                        Thread.sleep(tiempoIteracion);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AutoPressBot.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                while (contadorInteraciones < numeroIteraciones) {
                    robot.mousePress(keyCode);
                    robot.mouseRelease(keyCode);
                    contadorInteraciones++;
                    jfBot.getJlblIteraciones().setText("Iteraciones: " + contadorInteraciones);
                    jfBot.getJlblTiempoRestante().setText("Iteraciones restantes: " + (numeroIteraciones - contadorInteraciones));
                    jfBot.getjProgressBarBot().setValue(jfBot.getjProgressBarBot().getValue() + 1);
                    try {
                        Thread.sleep(tiempoIteracion);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AutoPressBot.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        jfBot.getJlblTrabajando().setText("Finalizado :)");
        jfBot.getJlblImagenPicoDiamante().setIcon(new javax.swing.ImageIcon(getClass().getResource("/autopressbot/data/Diamante.png")));
   
        if (apagar) {
            apagarEquipo();
        }
    }

    public void apagarEquipo() {

        String comando = "";
        if (System.getProperty("os.name").equals("Windows 10")) {
            comando = "shutdown -s";
        }
        if (System.getProperty("os.name").equals("Linux")) {
            comando = "halt";
        }
        try {
            Runtime.getRuntime().exec(comando);
        } catch (Exception e) {
            System.out.println(System.getProperty("os.name")+ " " + e);
        }
        return;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public boolean isIsKey() {
        return isKey;
    }

    public void setIsKey(boolean isKey) {
        this.isKey = isKey;
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

    public boolean isHasMov() {
        return hasMov;
    }

    public void setHasMov(boolean hasMov) {
        this.hasMov = hasMov;
    }

    public boolean isApagar() {
        return apagar;
    }

    public void setApagar(boolean apagar) {
        this.apagar = apagar;
    }

}
