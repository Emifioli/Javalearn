import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TimeMachine
{
    public static void main(String[] args)
    {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame jFrame = new MyFrame();
                jFrame.setLocation(500,300);
                jFrame.setTitle("Кидала");
                jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                jFrame.setVisible(true);
                jFrame.setResizable(false);

            }
        });
    }
}
class MyFrame extends JFrame {
    private JPanel z;
    private JToggleButton[] mas;
    private static int Knock = -1;
    private static int Lol = 1;
    private JLabel lj;

    public MyFrame() {
        //Размер окна при запуске
        int SIZE_WIDTH = 500;
        int SIZE_HEIGHT = 300;
        setSize(SIZE_WIDTH,SIZE_HEIGHT);
        //Внешний вид
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(MyFrame.this);
        }catch (Exception e){}
        //Центральная панель
        lj=new JLabel("Кубик не выбран, Выберите кубик!!!");

        lj.setFont(new Font("Arial", Font.TRUETYPE_FONT,20));
        lj.setBounds(55,210,400,55);
        add(lj);
        //Кнопочная панель
        z=new JPanel();
        //Действие на главную клавишу
        Kidok kidok = new Kidok("Кинуть");
        JButton jb=new JButton(kidok);
        InputMap imap = z.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        imap.put(KeyStroke.getKeyStroke("ENTER"), "Kidok");
        ActionMap amap = z.getActionMap();
        amap.put("Kidok",kidok);
        //Размер, шрифт на главной клавишы
        jb.setFont(new Font("Arial", Font.ITALIC,12));
        jb.setPreferredSize(new Dimension(getWidth(),getHeight()/3));

        //Массив кнопок по выбору дайсов

        mas = CreateJTB(4,6,8,10,12,20,100);
        addButton(z, mas, jb);

        add(z, BorderLayout.CENTER);

        addWindowListener( new AreYouSure());

        for(JToggleButton x : mas){
            x.setBackground(getRandomColor());
        }
        z.setBackground(getRandomColor());

    }

    private JToggleButton[] CreateJTB(int... size){
        JToggleButton[] mas = new JToggleButton[size.length];
        for(int i=0;i<mas.length;i++) {
            JToggleButton as=new JToggleButton();
            UnClick un = new UnClick("Кубик на " + size[i],as,size[i]);
            as.setAction(un);
            mas[i] = as;
        }
        return mas;
    }

    private Color getRandomColor(){
        int r = (int) (Math.random() * 251);
        int b = (int) (Math.random() * 251);
        int g = (int) (Math.random() * 251);
        return new Color(r,g,b);
    }

    private void addButton(JPanel jPanel,AbstractButton[] s,AbstractButton... r) {
        for(AbstractButton x : r)
            jPanel.add(x);
       for(AbstractButton x : s)
           jPanel.add(x);
    }

    private class AreYouSure extends WindowAdapter {
        public void windowClosing( WindowEvent e ) {
            int option = JOptionPane.showOptionDialog(
                    MyFrame.this,
                    "Хотите выйти?",
                    "Выход", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null,
                    null );
            if( option == JOptionPane.YES_OPTION ) {
                System.exit( 0 );
            }
        }
    }

    private class UnClick extends AbstractAction  {
        private JToggleButton e;
        int t;

        public UnClick(String name, JToggleButton z, int k)
        {
            putValue(Action.NAME, name);
            e=z;
            t=k;
        }

        public void actionPerformed(ActionEvent event)
        {
             boolean se = e.isSelected();
             if(!se){
                 e.setSelected(false);
                 Knock=-1;
                 lj.setBounds(55,210,400,55);
                 lj.setText("Кубик не выбран, Выберите кубик!!!");
             }else {
                 for(JToggleButton y1 : mas) {
                     y1.setSelected(false);
                 }
                 e.setSelected(true);
                 Knock=t;
                 lj.setBounds(180,210,400,55);
                 lj.setText("Кубик на "+Knock);
             }
        }
    }

    private class Kidok extends AbstractAction{

        public Kidok(String name) {
            putValue(Action.NAME, name);
            putValue(Action.SHORT_DESCRIPTION, "Enter");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(Knock==-1){
                lj.setText("Кубик не выбран, Выберите кубик!!!");
                lj.setBounds(55,210,400,55);
            }
            else {
                int result = (int)(Math.random() * Knock + 1);
                lj.setBounds(220,210,400,55);
                lj.setText(""+result);
            }
        }
    }

}