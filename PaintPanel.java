import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PaintPanel extends JPanel {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;
    private  Color color=Color.BLACK;
    private List<MouseEventListener> listenerList;
    private List<Coords> coords;

    public PaintPanel() {
        setLayout(new BorderLayout());
        initMouseEvent();
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        listenerList = new ArrayList<MouseEventListener>();
        coords=new ArrayList<Coords>();
    }


    public void drawPoint(MyMouseEvent e){
        coords.add(new Coords(e.getxCoordinate(), e.getyCoordinate(), e.getColor()));
       repaint();
    }

    public void addListener(MouseEventListener listener)
    {
        this.listenerList.add(listener);
    }
    public List<MouseEventListener> getListenerList() {
        return listenerList;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private void initMouseEvent()
    {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                    MyMouseEvent event = new MyMouseEvent(e, e.getX(), e.getY(), color);
                    List<MouseEventListener> list = getListenerList();
                    for(MouseEventListener l:list)
                    {
                        l.mouseKlicked(event);
                    }

            }
        });
    }

    public void paintComponent(Graphics g){
        for(Coords cur: coords){
            g.setColor(cur.color);
            g.fillOval(cur.x, cur.y, 5, 5);
        }
    }

    public String getColorName(){
      if(color==Color.black) return "black";
      if(color==Color.red) return "red";
      if(color==Color.green) return "green";
      return "black";
    }
    public class Coords{
        int x;
        int y;
        Color color;

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coords(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
    }