//Eoin McMahon 20387436
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class next extends JButton {
    private Viewer viewer;
    public next(Viewer viewer) {
        super("Next Level");
        this.viewer = viewer;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.resetGame();
                if (!viewer.IsLevel2Selected() && !viewer.IsLevel3Selected()) {
                    MainWindow.resetGame();
                    viewer.setLevel2Selected(true);
                    viewer.drawLevel2(viewer.getGraphics());
                    viewer.repaint();
                    SoundPlayer.stopSound();
                    SoundPlayer.playSound("res/level_2.wav");
                }
                else if (viewer.IsLevel2Selected()) {
                    MainWindow.resetGame();
                    viewer.setLevel2Selected(false);
                    viewer.setLevel3Selected(true);
                    viewer.drawLevel3(viewer.getGraphics());
                    viewer.repaint();
                    SoundPlayer.stopSound();
                    SoundPlayer.playSound("res/level_3.wav");
                }
                setVisible(false);

            }
        });
        setVisible(false);

    }
}
