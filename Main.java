import com.mysql.cj.jdbc.NonRegisteringDriver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main {

    private static final String url = "jdbc:mysql://localhost:3306/mysql";
    private static final String user = "root";
    private static final String password = "12345678";
    private static Statement statement;
    private static int id=1;
    public static void main(String[] args){

        JFrame frame = new JFrame("Paint program");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final PaintPanel panel = new PaintPanel();
        frame.add(panel);

        JMenuBar menubar = new JMenuBar();
        JMenu colorMenu=new JMenu("Color");
        JMenuItem redItem=new JMenuItem("Red");
        JMenuItem greenItem=new JMenuItem("Green");
        JMenuItem blackItem=new JMenuItem("Black");
        colorMenu.add(redItem);
        colorMenu.add(greenItem);
        colorMenu.add(blackItem);
        menubar.add(colorMenu);
        frame.setJMenuBar(menubar);

        redItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setColor(Color.red);
                System.out.println("Color changed to red.");
            }
        });

        blackItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setColor(Color.black);
                System.out.println("Color changed to black.");
            }
        });

        greenItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setColor(Color.green);
                System.out.println("Color changed to green.");
            }
        });



        frame.pack();
        frame.setVisible(true);

        Connection connection;
        Driver driver;

        try{
            driver=new NonRegisteringDriver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection(url, user, password);

            statement=connection.createStatement();
            statement.execute("DELETE FROM mousetable.mouse WHERE id>0");

            panel.addListener(new MouseEventListener() {
                public void mouseKlicked(MyMouseEvent e) {
                     panel.drawPoint(e);
                }
            });
            panel.addListener(new MouseEventListener() {
                public void mouseKlicked(MyMouseEvent e) {
                    String str="INSERT INTO mousetable.mouse values ("+id+", "+e.getxCoordinate()+ ", "+e.getyCoordinate()+", '"+panel.getColorName()+ "')";
                      // System.out.println(str);
                       id++;
                   try {
                       statement.execute(str);
                    }
                    catch (SQLException eq){
                        eq.printStackTrace();
                    }

                }
            });



        } catch (SQLException e){
            e.printStackTrace();
            return;
        }


           /* try {
                if(connection!=null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

*/
    }
}
