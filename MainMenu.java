import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame{
    private JTextField nameTextField;
    private JTextField speedTextField;
    private static String name;
    private static int speed;


    public MainMenu(){
        createComponents();
        setSize(600, 400);
        setVisible(true);
    }

    class ClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean speedInputCorrect = isSpeedInputCorrect();
            boolean nameInput = isInputEmpty(nameTextField.getText());
            boolean speedInput = isInputEmpty(speedTextField.getText());
            boolean nameTooLong = isNameTooLong(nameTextField.getText());

            if(!speedInput)
                JOptionPane.showMessageDialog(MainMenu.this, "Speed can't be empty!");

            else if(!speedInputCorrect)
                JOptionPane.showMessageDialog(MainMenu.this, "Speed should only be digits!");

            if(!nameInput)
                JOptionPane.showMessageDialog(MainMenu.this, "Name can't be empty!");
            
            else if(!nameTooLong)
                JOptionPane.showMessageDialog(MainMenu.this, "Name can't be longet than 9 characters!");

            if(speedInput && speedInputCorrect && nameInput && nameTooLong){
                setVisible(false);
                name = nameTextField.getText();
                speed = Integer.valueOf(speedTextField.getText());
                new GameFrame(Integer.valueOf(speedTextField.getText()), nameTextField.getText(), MainMenu.this);
            }
        }

    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    private void createComponents(){
        nameTextField = new JTextField();
        speedTextField = new JTextField();

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel speedlLabel = new JLabel("Speed: ");
        speedlLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton starButton = new JButton("Start");
        ActionListener listener = new ClickListener();
        starButton.addActionListener(listener);

        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        JPanel namePanel = new JPanel(new GridLayout(1, 2));
        JPanel speedPanel = new JPanel(new GridLayout(1, 2));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
        
        buttonPanel.add(starButton);
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
        speedPanel.add(speedlLabel);
        speedPanel.add(speedTextField);

        mainPanel.add(namePanel);
        mainPanel.add(speedPanel);
        mainPanel.add(buttonPanel);

        add(mainPanel);
    }

    public static boolean isNumeric(String str){ 
        try{  
            Double.parseDouble(str);  
            return true;
        } 
        catch(NumberFormatException e){  
            return false;  
        }
    }

    public boolean isInputEmpty(String input){
        boolean result = true;
        if(input.length() == 0)
            result = false;

        return result;
    }

    public boolean isNameTooLong(String input){
        boolean result = true;
        if(input.length() > 9)
            result = false;

        return result;
    }
    
    public boolean isSpeedInputCorrect(){
        boolean result = true;
        String speedInput = speedTextField.getText();
        result = isNumeric(speedInput);

        return result;
    }


}