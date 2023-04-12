import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HeaderPanel extends JPanel {
	private JPanel mainPanel;
	private JLabel titleLabel;
	private JLabel profileLabel;
	private JPopupMenu profileMenu;
	private JMenuItem accountSettingsItem;
	private JButton backButton;
	private String titleText = "";
	
	public void setTitleText(String str) {
		titleText = str;
		titleLabel.setText(titleText);
	}
	
	public void showBackButton(boolean b) {
		backButton.setVisible(b);
	}
	
	public JButton getBackButton() {
		return backButton;
	}
	
	public HeaderPanel(JPanel parent, JFrame frameToClose) {
        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 582, 50);
        mainPanel.setBackground(new Color(22, 102, 43));
        mainPanel.setLayout(null);
        parent.add(mainPanel);

        profileLabel = new JLabel("");
        profileLabel.setForeground(Color.WHITE);
        profileLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        profileLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        profileLabel.setIcon(new ImageIcon("profile.png"));
        profileLabel.setVerticalTextPosition(SwingConstants.CENTER);
        profileLabel.setVerticalAlignment(SwingConstants.CENTER);
        profileLabel.setBounds(511, 6, 39, 39);
        mainPanel.add(profileLabel);

        profileLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    showProfileMenu();
                }
            }
        });

        titleLabel = new JLabel();
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBounds(82, 6, 392, 37);
        mainPanel.add(titleLabel);

        backButton = new JButton("<");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle back button action
            }
        });
        backButton.setBounds(6, 6, 59, 37);
        mainPanel.add(backButton);
        
        backButton.setVisible(false);
        titleLabel.setText("");
        
        profileMenu = new JPopupMenu();
        
        /*JMenuItem accountSettingsItem = new JMenuItem("Account Settings");
        accountSettingsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle account settings action
            }
        });
        profileMenu.add(accountSettingsItem);

        JMenuItem manageGroupItem = new JMenuItem("Manage Group");
        manageGroupItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle manage group action
            }
        });
        profileMenu.add(manageGroupItem);
		*/
        JMenuItem logoutItem = new JMenuItem("Log Out");
        logoutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameToClose.setVisible(false);
            	LoginFrame newLoginFrame = new LoginFrame();
        		newLoginFrame.setVisible(true);
            }
        });
        profileMenu.add(logoutItem);
    }
    
    private void showProfileMenu() {
        profileMenu.show(profileLabel, 0, profileLabel.getHeight());
    }
}
