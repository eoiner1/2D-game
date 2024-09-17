//Eoin McMahon 20387436
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayButton extends JButton {
    private Viewer viewer;
    public ReplayButton(Viewer viewer) {
        super("Replay");
        this.viewer = viewer;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.resetGame();
                setVisible(false);
                if (!viewer.IsLevel2Selected() && !viewer.IsLevel3Selected()) {
                    SoundPlayer.stopSound();
                    SoundPlayer.playSound("res/level1_music.wav");
                }
                else if (viewer.IsLevel2Selected()) {
                    SoundPlayer.stopSound();
                    SoundPlayer.playSound("res/level_2.wav");
                }
                else {
                    SoundPlayer.stopSound();
                    SoundPlayer.playSound("res/level_3.wav");
                }
            }
        });

        setVisible(false);
    }
}
