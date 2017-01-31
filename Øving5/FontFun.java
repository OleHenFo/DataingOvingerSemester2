import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by olehe on 1/22/2017.
 * asd
 */
public class FontFun extends JFrame implements ActionListener {
    private ArrayList<JButton> b = new ArrayList<JButton>();
    private ArrayList<Font> f = new ArrayList<Font>();
    private JLabel text = new JLabel("Lorem Ipsum Bla Bla Bla");

    private FontFun() {
        BorderLayout layout = new BorderLayout();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        f.add(new Font("SansSerif", Font.BOLD, 20));
        f.add(new Font("Serif", Font.ITALIC, 20));
        f.add(new Font("Monospaced", Font.BOLD, 20));
        f.add(new Font("Dialog", Font.BOLD + Font.ITALIC, 18));
        b.add(new JButton("SansSerif"));
        b.add(new JButton("Serif"));
        b.add(new JButton("Monospaced"));
        b.add(new JButton("Dialog"));

        text.setFont(f.get(0));

        p1.add(text);
        for (JButton jb : b){
            jb.addActionListener(this);
            p2.add(jb);
        }

        this.setLayout(layout);
        this.add(p1,BorderLayout.PAGE_START);
        this.add(p2,BorderLayout.CENTER);
    }

    public static void main(String[] args){
        JFrame frame = new FontFun();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setSize(500,150);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0;i<b.size();i++){
            if (e.getSource()==b.get(i)){
                text.setFont(f.get(i));
            }
        }
    }
}
