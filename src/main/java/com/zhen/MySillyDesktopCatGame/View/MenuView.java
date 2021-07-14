package com.zhen.MySillyDesktopCatGame.View;

import com.zhen.MySillyDesktopCatGame.Action.SwitchScreenToAction;
import com.zhen.MySillyDesktopCatGame.Controller.MainController;
import com.zhen.MySillyDesktopCatGame.Controller.MenuController;
import com.zhen.MySillyDesktopCatGame.Model.GameState;
import com.zhen.MySillyDesktopCatGame.Type.GameStateType;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuView extends JPanel implements View, MouseListener {

    private static MenuView instance;
    private MainController mainController;
    private MenuController menuController;
    private JButton playButton;
    private JButton exitButton;
    private JLabel title;
    private Image titleImage;

    public MenuView(MainController mainController) {
        this.mainController = mainController;
        menuController = mainController.getMenuController();

        playButton = new JButton("Play");
        playButton.setBounds(20,20, 100,20);
        playButton.addMouseListener(this);

        exitButton = new JButton("Exit");
        exitButton.setBounds(20,60, 100,20);
        exitButton.addMouseListener(this);

//        title = new JLabel();
//        title.setIcon(new ImageIcon(titleImage));
//        title.setBounds(250,50,300,300);
        initView();
        mainController.subscribe(this);
    }

    @Override
    public void tick() {

    }

    public void initView()
    {
        this.setBackground(Color.LIGHT_GRAY);
        this.add(playButton);
        this.add(exitButton);
//        this.add(title);
        this.setLayout(null);
    }

    public static synchronized MenuView getInstance(MainController mainController)
    {
        if(instance == null)
        {
            instance = new MenuView(mainController);
        }
        return instance;
    }

    @Override
    public void updateView(GameState gameState) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object o = e.getSource();
        JButton b = null;
        String buttonText = "";
        if(o instanceof JButton)
            b = (JButton)o;

        switch(b.getText())
        {
            case "Play":
                System.out.println("PLAY PRESSED");
                mainController.performAction(new SwitchScreenToAction(GameStateType.SILLY_CAT_GAME));
                break;

            case "Exit":
                System.out.println("EXIT PRESSED");
                System.exit(1);
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
