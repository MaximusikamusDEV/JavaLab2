package Jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.*;

public class CalcFrame extends JFrame {

    private static final int width = 500;
    private static final int height = 800;

    private final Box imBox1 = Box.createHorizontalBox();
    private final Box imBox2 = Box.createHorizontalBox();

    private final JTextField fieldX;
    private final JTextField fieldY;
    private final JTextField fieldZ;
    private final JTextField result;
    private final JTextField ResMem1;
    private final JTextField ResMem2;
    private final JTextField ResMem3;

    private final ButtonGroup radioButtonsF = new ButtonGroup();
    private final ButtonGroup radioButtonsMem = new ButtonGroup();
    private final Box VBoxMem = Box.createVerticalBox();
    private final Box HBoxF = Box.createHorizontalBox();

    private int formulaId = 1;
    private int memId = 1;


    private void AddRadioButtonF(String name, int formulaId)
    {
        JRadioButton button = new JRadioButton(name);

        button.addActionListener(e -> {
            CalcFrame.this.formulaId = formulaId;
            if(formulaId == 1) {
                imBox2.setVisible(false);
                imBox1.setVisible(true);
            }
            else {
                imBox1.setVisible(false);
                imBox2.setVisible(true);
            }
        });

        radioButtonsF.add(button);
        HBoxF.add(button);
        HBoxF.add(Box.createVerticalStrut(2));
    }

    private void AddRadioButtonMem(String name, int memId)
    {
        JRadioButton button = new JRadioButton(name);

        button.addActionListener(e -> CalcFrame.this.memId = memId);

        radioButtonsMem.add(button);
        VBoxMem.add(button);
        VBoxMem.add(Box.createVerticalStrut(3));
    }

    private double F1(double x, double y, double z)
    {
        return sin(log(y) + sin(3.14 * y * y)) * pow((x * x + sin(z) + exp(cos(z))), 0.25);
    }

    private double F2(double x, double y, double z)
    {
        return pow((cos(exp(x)) + log(pow((1+y), 2)) + pow((exp(cos(x) + pow(sin(3.14 * z), 2))), 0.5)
                + pow((1/x), 0.5) + cos(y * y)), sin(z));
    }


    CalcFrame()
    {
        super("Вычисление формулы 3х переменных");

        String path1 = "C:\\Users\\HP\\Desktop\\JavaLabs\\Lab2\\src\\res\\f1.bmp";
        ImagePanel im1 = new ImagePanel(path1);
        imBox1.add(im1);
        String path2 = "C:\\Users\\HP\\Desktop\\JavaLabs\\Lab2\\src\\res\\f2.bmp";
        ImagePanel im2 = new ImagePanel(path2);
        imBox2.add(im2);
        Box imBox = Box.createHorizontalBox();
        imBox.add(imBox1);
        imBox.add(imBox2);
        imBox2.setVisible(false);

        setSize(width, height);

        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - width)/2, (kit.getScreenSize().height - height)/2);


        HBoxF.add(Box.createHorizontalGlue());
        AddRadioButtonF("Формула 1", 1);
        AddRadioButtonF("Формула 2", 2);
        radioButtonsF.setSelected(radioButtonsF.getElements().nextElement().getModel(), true);
        HBoxF.setBorder(BorderFactory.createLineBorder(Color.red));




        JButton MC = new JButton("MC");

        MC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(memId == 1)
                    ResMem1.setText("0");
                else
                    if(memId == 2)
                      ResMem2.setText("0");
                else
                    {
                        ResMem3.setText("0");
                    }
            }
        });

        JButton Mpl = new JButton("M+");

        Mpl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double mem;

                if(memId == 1) {
                    mem = Double.parseDouble(ResMem1.getText());
                    mem += Double.parseDouble(result.getText());
                    ResMem1.setText(Double.toString(mem));
                }
                else
                if(memId == 2) {
                    mem = Double.parseDouble(ResMem2.getText());
                    mem += Double.parseDouble(result.getText());
                    ResMem2.setText(Double.toString(mem));
                }
                else
                {
                    mem = Double.parseDouble(ResMem3.getText());
                    mem += Double.parseDouble(result.getText());
                    ResMem3.setText(Double.toString(mem));
                }
            }
        });


        Box AllMemBox = Box.createHorizontalBox();

        Box Mmbox = Box.createVerticalBox();
        Mmbox.add(MC);
        Mmbox.add(Box.createVerticalStrut(3));
        Mmbox.add(Mpl);


        AddRadioButtonMem("1" ,1);
        AddRadioButtonMem("2", 2);
        AddRadioButtonMem("3", 3);
        radioButtonsMem.setSelected(radioButtonsMem.getElements().nextElement().getModel(), true);


        JPanel memPanel = new JPanel();

        Box MemBox = Box.createHorizontalBox();
        JLabel lblMem1 = new JLabel("Mem1: ");
        ResMem1 = new JTextField("0", 10);
        ResMem1.setMaximumSize(ResMem1.getPreferredSize());
        MemBox.add(lblMem1);
        MemBox.add(ResMem1);
        MemBox.add(Box.createHorizontalStrut(3));
        JLabel lblMem2 = new JLabel("Mem2: ");
        ResMem2 = new JTextField("0", 10);
        ResMem2.setMaximumSize(ResMem2.getPreferredSize());
        MemBox.add(lblMem2);
        MemBox.add(ResMem2);
        MemBox.add(Box.createHorizontalStrut(3));
        JLabel lblMem3 = new JLabel("Mem3: ");
        ResMem3 = new JTextField("0", 10);
        ResMem3.setMaximumSize(ResMem3.getPreferredSize());
        MemBox.add(lblMem3);
        MemBox.add(ResMem3);

        memPanel.add(MemBox);


        AllMemBox.add(Box.createHorizontalGlue());
        AllMemBox.add(Mmbox);
        AllMemBox.add(VBoxMem);

        AllMemBox.setBorder(BorderFactory.createLineBorder(Color.red));




        Box fieldBox = Box.createHorizontalBox();
        fieldBox.add(Box.createHorizontalGlue());

        fieldX = new JTextField("0", 5);
        fieldX.setMaximumSize(fieldX.getPreferredSize());
        fieldY = new JTextField("0", 5);
        fieldY.setMaximumSize(fieldY.getPreferredSize());
        fieldZ = new JTextField("0", 5);
        fieldZ.setMaximumSize(fieldZ.getPreferredSize());




        JLabel lblX = new JLabel("X: ");
        JLabel lblY = new JLabel("Y: ");
        JLabel lblZ = new JLabel("Z: ");

        fieldBox.add(lblX);
        fieldBox.add(fieldX);
        fieldBox.add(Box.createVerticalStrut(10));
        fieldBox.add(lblY);
        fieldBox.add(fieldY);
        fieldBox.add(Box.createVerticalStrut(10));
        fieldBox.add(lblZ);
        fieldBox.add(fieldZ);
        fieldBox.add(Box.createHorizontalGlue());

        fieldBox.setBorder(BorderFactory.createLineBorder(Color.black));


        Box resBox = Box.createHorizontalBox();

        JLabel lblRes = new JLabel("Результат: ");
        result = new JTextField("0", 10);
        result.setMaximumSize(result.getPreferredSize());

        resBox.add(Box.createHorizontalGlue());
        resBox.add(lblRes);
        resBox.add(result);
        resBox.add(Box.createHorizontalGlue());



        Box resButtBox = Box.createHorizontalBox();

        resButtBox.add(Box.createHorizontalGlue());

        JButton res = new JButton("Calculate");
        JButton clr = new JButton("Clear");

        res.addActionListener(e -> {

            double x, y, z;
            x = Double.parseDouble(fieldX.getText());
            y = Double.parseDouble(fieldY.getText());
            z = Double.parseDouble(fieldZ.getText());

            if(formulaId == 1)
                    result.setText(Double.toString(F1(x, y, z)));
                else
                    result.setText(Double.toString(F2(x, y, z)));
        });

        clr.addActionListener(e -> result.setText("0"));

        resButtBox.add(res);
        resButtBox.add(Box.createVerticalStrut(10));
        resButtBox.add(clr);
        resButtBox.add(Box.createHorizontalGlue());


        Box contentBox = Box.createVerticalBox();
        contentBox.add(HBoxF);
        contentBox.add(Box.createHorizontalStrut(5));
        contentBox.add(imBox);
        contentBox.add(Box.createHorizontalStrut(5));
        contentBox.add(AllMemBox);
        contentBox.add(Box.createHorizontalStrut(5));
        contentBox.add(memPanel);
        contentBox.add(Box.createHorizontalStrut(5));
        contentBox.add(fieldBox);
        contentBox.add(Box.createHorizontalStrut(5));
        contentBox.add(resBox);
        contentBox.add(Box.createHorizontalStrut(5));
        contentBox.add(resButtBox);
        contentBox.add(Box.createHorizontalStrut(5));
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(Box.createHorizontalStrut(5));

        add(contentBox);
    }



}
