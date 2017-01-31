import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteOrder;

/**
 * Created by olehe on 1/23/2017.
 *
 */
public class Calc extends JFrame implements ListSelectionListener{
    private Valuta[] valutaliste = {
        new Valuta("Euro", 8.10, 1),
        new Valuta("US Dollar", 6.23, 1),
        new Valuta("Britiske pund", 12.27, 1),
        new Valuta("Svenske kroner", 88.96, 100),
        new Valuta("Danske kroner", 108.75, 100),
        new Valuta("Yen", 5.14, 100),
        new Valuta("Islandske kroner", 9.16, 100),
        new Valuta("Norske kroner", 100, 100)
    };
    private int[] lastIndexesFra = new int[2];
    private int[] lastIndexesTil = new int[2];
    private DefaultListModel listeM = new DefaultListModel();
    private JList listeFra;
    private JList listeTil;
    private Valuta fra = null;
    private Valuta til = null;

    private Calc() {
        setTitle("Valuta kalkulator");
        setLayout(new BorderLayout(10,10));
        setSize(400,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        String[] listeObjects = new String[valutaliste.length+1];
        listeM.addElement("Ny Valuta");
        for (int i =1; i < valutaliste.length;i++){
            listeM.addElement(valutaliste[i].getNavn());
        }
        listeFra = new JList(listeM);
        listeTil = new JList(listeM);
        listeFra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listeTil.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listeFra.addListSelectionListener(this);
        listeTil.addListSelectionListener(this);
        drawGui();
        pack();
    }

    public static void main(String[] args){
        Calc f = new Calc();
        f.setVisible(true);
    }

    private void drawGui(){
        JPanel lists = new JPanel();
        JScrollPane scrollFra = new JScrollPane();
        JScrollPane scrollTil = new JScrollPane();
        scrollFra.setViewportView(listeFra);
        scrollTil.setViewportView(listeTil);
        lists.add(scrollFra,BorderLayout.LINE_START);
        lists.add(scrollTil,BorderLayout.LINE_END);

        JLabel text = new JLabel("Velg fravaluta og tilvaluta fra listene:");
        JLabel fraText = new JLabel("Fra valuta:");
        JLabel tilText = new JLabel("Til valuta:");

        this.add(text, BorderLayout.PAGE_START);
        this.add(tilText, BorderLayout.LINE_END);
        this.add(fraText, BorderLayout.LINE_START);
        this.add(lists,BorderLayout.PAGE_END);
    }

    private void addValuta() {
        String navn;
        double kurs;
        int enhet;
        try {
            navn = JOptionPane.showInputDialog(null,"Oppgi navn på valuta:");
            kurs = Double.parseDouble(JOptionPane.showInputDialog(null,"Oppgi kurs: "));
            enhet = Integer.parseInt(JOptionPane.showInputDialog(null,"Oppgi enhet: "));
            incrementList();
            valutaliste[valutaliste.length - 1] = new Valuta(navn, kurs, enhet);
            listeM.addElement(valutaliste[valutaliste.length-1].getNavn());
            pack();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Bruk et gyldig tall!");
        }
    }

    private void incrementList(){
        Valuta[] nyTab = new Valuta[this.valutaliste.length + 1];
        System.arraycopy(valutaliste,0,nyTab,0,valutaliste.length);
        this.valutaliste = nyTab;
    }

    private static double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        double input;
        double output;
        int indexFra;
        int indexTil;
        Object s = e.getSource();

        if (s.equals(listeFra)) {
            indexFra = listeFra.getSelectedIndex();
            if (indexFra>0) {
                fra = valutaliste[indexFra];
            } else if (indexFra==0){
                fra = null;
                addValuta();
            }
        } else if (s.equals(listeTil)){
            indexTil = listeTil.getSelectedIndex();
            if (indexTil>0) {
                til = valutaliste[indexTil+1-1];
            } else if (indexTil==0){
                til = null;
                addValuta();
            }
        }

        if (fra!=null&&til!=null){
            try {
                input = Double.parseDouble(JOptionPane.showInputDialog(null,"Fra "+fra.getNavn()+"\nTil "+til.getNavn()+"\nBeløp: "));
                output = input*((fra.getKurs()/fra.getEnhet())/(til.getKurs()/til.getEnhet()));
                JOptionPane.showMessageDialog(null,fra.getNavn()+": "+round(input)+"\n"+til.getNavn()+": "+round(output));
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null,"Bruk et gyldig tall!");
            }
            fra = null;
            til = null;
        }
    }
}
