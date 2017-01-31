import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by olehe on 1/22/2017.
 * adasdasd
 */
public class Calc extends JFrame implements ActionListener{
    private JButton nok;
    private JButton sek;
    private JLabel result;
    private JTextField input;

    private Calc(String titel) {
        setTitle(titel);
        setLayout(new BorderLayout(10,10));
        setSize(500,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel top = new JPanel();
        top.add(new JLabel("Beløp: "),BorderLayout.LINE_START);
        input = new JTextField("",10);
        top.add(input,BorderLayout.LINE_START);

        JPanel mid = new JPanel();
        result = new JLabel("Resultat kommer her");
        mid.add(result);

        JPanel bot = new JPanel();
        nok = new JButton("Til NOK");
        sek = new JButton("Til SEK");
        nok.addActionListener(this);
        sek.addActionListener(this);
        bot.add(nok);
        bot.add(sek);

        add(top,BorderLayout.PAGE_START);
        add(mid,BorderLayout.CENTER);
        add(bot,BorderLayout.PAGE_END);
        pack();
    }

    private static double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    public static void main(String[] args){
        Calc c = new Calc("NOK/SEK");
        c.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==nok){
            try {
                float value = Float.parseFloat(input.getText());
                result.setText(""+round(value*0.9404)+",- NOK");
            } catch (Exception ex){
                result.setText("Bruk et gyldig tall!");
            }
        } else if (e.getSource()==sek){
            try {
                float value = Float.parseFloat(input.getText());
                result.setText(""+round(value/0.9404f)+",- SEK");
            } catch (Exception ex){
                result.setText("Bruk et gyldig tall!");
            }
        }
    }
}