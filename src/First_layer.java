import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class First_layer extends JFrame {
    private JButton memberButton;
    private JButton librarianButton;

    public First_layer() {
        setTitle("Library Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        memberButton = new JButton("Member Interface");
        librarianButton = new JButton("Librarian Interface");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(memberButton);
        add(librarianButton);

        memberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Member Interface
                openMemberInterface();
            }
        });

        librarianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Librarian Interface
                openLibrarianInterface();
            }
        });
    }

    private void openMemberInterface() {
        MemberInterfaceLayer memberInterfaceLayer = new MemberInterfaceLayer(new Catalog(), new PendingRequestsQueue());
        memberInterfaceLayer.showMemberInterface();
    }

    private void openLibrarianInterface() {
        LibrarianInterfaceLayer librarianInterfaceLayer = new LibrarianInterfaceLayer(new Catalog(), new PendingRequestsQueue());
        librarianInterfaceLayer.showLibrarianInterface();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new First_layer().setVisible(true);
            }
        });
    }
}
