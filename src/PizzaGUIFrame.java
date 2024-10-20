import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PizzaGUIFrame extends JFrame {

    JPanel mainPnl;
    JPanel menuPnl;
    JPanel crustPnl;
    JPanel crustTypePnl;
    JPanel sizePnl;
    JPanel topPnl;
    JPanel topTypePnl; // Top
    JPanel displayPnl; // Center
    JPanel controlPnl; // Bottom

    JTextArea displayTA;
    JScrollPane scroller;

    JLabel crustTF;
    JRadioButton thin;
    JRadioButton regular;
    JRadioButton deep;
    ButtonGroup bg;

    JLabel sizeTF;
    JComboBox size;

    JLabel topTF;
    JCheckBox pep;
    JCheckBox sausage;
    JCheckBox cheese;
    JCheckBox pepper;
    JCheckBox mushrooms;
    JCheckBox bacon;

    JButton orderBtn;
    JButton clearBtn;
    JButton quitBtn;


    ArrayList<String> Toppings = new ArrayList<String>();
    String Size;
    String Crust;
    double sizeprice;
    double subtotal;
    double tax;
    double total;

    boolean dupe = true;
    DecimalFormat df = new DecimalFormat("0.00");



    public PizzaGUIFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        menuPnl = new JPanel();
        menuPnl.setLayout(new GridLayout(1,3));
        menuPnl.setPreferredSize(new Dimension(810,150));



        createCrustPanel();
        menuPnl.add(crustPnl);

        createSizePanel();
        menuPnl.add(sizePnl);

        createTopPanel();
        menuPnl.add(topPnl);

        createDisplayPanel();
        mainPnl.add(displayPnl, BorderLayout.CENTER);

        createControlPanel();
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(menuPnl, BorderLayout.NORTH);
        add(mainPnl);
        setSize(810, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private void createCrustPanel()
    {
        crustPnl = new JPanel();
        crustPnl.setLayout(new BorderLayout());

        crustTypePnl = new JPanel();
        crustTypePnl.setLayout(new GridLayout(3,1));

        crustTF = new JLabel("Crust Type");
        thin = new JRadioButton("Thin");
        regular = new JRadioButton("Regular");
        deep = new JRadioButton("Deep-Dish");
        bg = new ButtonGroup();

        bg.add(thin);
        bg.add(regular);
        bg.add(deep);

        crustTypePnl.add(thin);
        crustTypePnl.add(regular);
        crustTypePnl.add(deep);

        crustPnl.add(crustTF, BorderLayout.NORTH);
        crustPnl.add(crustTypePnl, BorderLayout.CENTER);

    }

    private void createSizePanel()
    {
        sizePnl = new JPanel();
        sizePnl.setLayout(new BorderLayout());
        String sizes[] = {"Small", "Medium", "Large", "Super"};
        size = new JComboBox(sizes);

        sizeTF = new JLabel("Size");

        sizePnl.add(sizeTF, BorderLayout.NORTH);
        sizePnl.add(size, BorderLayout.CENTER);

    }

    private void createTopPanel()
    {
        topPnl = new JPanel();
        topPnl.setLayout(new BorderLayout());

        topTypePnl = new JPanel();
        topTypePnl.setLayout(new GridLayout(2,3));

        topTF = new JLabel("Toppings");

        pep = new JCheckBox("Pepperoni");
        sausage = new JCheckBox("Sausage");
        cheese = new JCheckBox("Cheese");
        pepper = new JCheckBox("Pepper");
        mushrooms = new JCheckBox("Mushrooms");
        bacon = new JCheckBox("Bacon");

        topTypePnl.add(pep);
        topTypePnl.add(sausage);
        topTypePnl.add(cheese);
        topTypePnl.add(pepper);
        topTypePnl.add(mushrooms);
        topTypePnl.add(bacon);

        topPnl.add(topTF, BorderLayout.NORTH);
        topPnl.add(topTypePnl, BorderLayout.CENTER);



    }

    private void createDisplayPanel()
    {
        displayPnl = new JPanel();
        displayTA = new JTextArea(25, 40);
        displayTA.setFont(new Font("Georgia", Font.PLAIN, 14));
        displayTA.setEditable(false);
        scroller = new JScrollPane(displayTA);
        displayPnl.add(scroller);
    }


    private void createControlPanel()
    {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 4));

        orderBtn = new JButton("Order");
        orderBtn.setFont(new Font("Verdana", Font.PLAIN, 20));
        orderBtn.addActionListener((ActionEvent ae) ->
        {
            Order();
            Receipt();

        });

        clearBtn = new JButton("Clear");
        clearBtn.setFont(new Font("Verdana", Font.PLAIN, 20));
        clearBtn.addActionListener((ActionEvent ae) ->
        {
            subtotal = 0;
            tax = 0;
            total = 0;
            sizeprice = 0;

            Toppings.clear();
            displayTA.setText("");

            dupe = true;

        });


        quitBtn = new JButton("Quit!");
        quitBtn.setFont(new Font("Verdana", Font.PLAIN, 20));
        quitBtn.addActionListener((ActionEvent ae) ->
        {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure?",null,JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        });

        controlPnl.add(orderBtn);
        controlPnl.add(clearBtn);
        controlPnl.add(quitBtn);


    }

    public void Order() {
        if (thin.isSelected()) {
            Crust = "Thin";
        } else if (regular.isSelected()) {
            Crust = "Regular";
        } else if (deep.isSelected()) {
            Crust = "Deep-Dish";
        } else {
            return;
        }

        if (size.getSelectedItem().equals("Small")) {
            Size = "Small";
            sizeprice = 8;
        } else if (size.getSelectedItem().equals("Medium")) {
            Size = "Medium";
            sizeprice = 12;
        } else if (size.getSelectedItem().equals("Large")) {
            Size = "Large";
            sizeprice = 16;
        } else if (size.getSelectedItem().equals("Super")) {
            Size = "Super";
            sizeprice = 20;
        }

        if (pep.isSelected()) {
            Toppings.add("Pepperoni");
            subtotal++;
        }
        if (sausage.isSelected()) {
            Toppings.add("Sausage  ");
            subtotal++;
        }
        if (cheese.isSelected()) {
            Toppings.add("Cheese   ");
            subtotal++;
        }
        if (pepper.isSelected()) {
            Toppings.add("Pepper   ");
            subtotal++;
        }
        if (mushrooms.isSelected()) {
            Toppings.add("Mushrooms");
            subtotal++;
        }
        if (bacon.isSelected()) {
            Toppings.add("Bacon    ");
            subtotal++;
        }

        subtotal = sizeprice + subtotal;
        tax = subtotal * .07;
        total = tax + subtotal;

    }

    public void Receipt() {

       if (dupe) {
           displayTA.append("=========================================\n");
           displayTA.append("Type of Crust and Size          Price\n");

           displayTA.append(Crust);

           if (Size == "Small") {
               displayTA.append("\nSmall                                        $" + sizeprice + "\n");
           } else if (Size == "Medium") {
               displayTA.append("\nMedium                                       $" + sizeprice + "\n");
           } else if (Size == "Large") {
               displayTA.append("\nLarge                                        $" + sizeprice + "\n");
           } else if (Size == "Super") {
               displayTA.append("\nSuper                                       $" + sizeprice + "\n");
           }

           displayTA.append("Ingredient                      Price\n");

           for (int i = 0; i < Toppings.size(); i++) {
               displayTA.append(Toppings.get(i) + "                       $1.00\n");
           }

           displayTA.append("Sub-Total                       $" + df.format(subtotal) + "\n");
           displayTA.append("Tax                             $" + df.format(tax) + "\n");
           displayTA.append("-----------------------------------------\n");
           displayTA.append("Total                           $" + df.format(total) + "\n");



           displayTA.append("=========================================\n");

           dupe = false;

       }

    }



}
