import java.awt.*;
import javax.swing.*;

public class HeaderPanel extends JPanel {

    public HeaderPanel(String message, String profilePicturePath) {
        setBackground(new Color(184, 225, 161)); // Set background color to light green
        setLayout(new BorderLayout());

        // Create profile image label
        JLabel profileImage = new JLabel();
        profileImage.setIcon(new ImageIcon(profilePicturePath));
        profileImage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(profileImage, BorderLayout.WEST);

        // Create message label
        JLabel messageLabel = new JLabel(message);
        messageLabel.setForeground(Color.WHITE); // Set text color to white
        messageLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 1), // Add a black border around the text
            BorderFactory.createEmptyBorder(10, 10, 10, 10) // Add some padding around the text
        ));
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(messageLabel, BorderLayout.CENTER);
    }
}