package flightapp.gui.navigation;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class NavigationController {
    private Stack<String> navigationStack;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton backButton;

    public NavigationController(JPanel cardPanel, CardLayout cardLayout, JButton backButton) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.backButton = backButton;
        this.navigationStack = new Stack<>();
    }

    public void navigateTo(String panelName) {
        if (navigationStack.isEmpty() || !navigationStack.peek().equals(panelName)) {
            navigationStack.push(panelName);
            backButton.setEnabled(true); // Enable back button as we navigate away from main menu
        }
        cardLayout.show(cardPanel, panelName);
    }

    public void goBack() {
        if (navigationStack.size() > 1) {
            navigationStack.pop();
            String previousPanel = navigationStack.peek();
            cardLayout.show(cardPanel, previousPanel);

            if (navigationStack.size() == 1) {
                backButton.setEnabled(false); // Disable back button if at main menu
            }
        }
    }

    public void initialize(String initialPanel) {
        navigationStack.push(initialPanel);
        backButton.setEnabled(false);
    }
}