import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        PendingRequestsQueue requestsQueue = new PendingRequestsQueue();
        LibrarianInterface librarianInterface = new LibrarianInterface(catalog, requestsQueue);
        MemberInterface memberInterface = new MemberInterface(catalog, requestsQueue);

        initializeCatalogWithBooks(catalog);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMainMenu();
            int userTypeChoice = getUserInput(scanner);

            switch (userTypeChoice) {
                case 1:
                    librarianInterface.startInterface();
                    break;
                case 2:
                    // Member signup or login
                    LibraryMember member = handleMemberAuthentication(scanner);
                    if (member != null) {
                        memberInterface.startInterface(member);
                    }
                    break;
                case 0:
                    System.out.println("Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void initializeCatalogWithBooks(Catalog catalog) {
        Book book1 = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Classic");
        Book book2 = new Book(2, "To Kill a Mockingbird", "Harper Lee", "Fiction");
        Book book3 = new Book(3, "1984", "George Orwell", "Dystopian");
        Book book4 = new Book(4, "The Hobbit", "J.R.R. Tolkien", "Fantasy");
        Book book5 = new Book(5, "Pride and Prejudice", "Jane Austen", "Romance");

        catalog.addBook(book1);
        catalog.addBook(book2);
        catalog.addBook(book3);
        catalog.addBook(book4);
        catalog.addBook(book5);
    }

    private static void printMainMenu() {
        System.out.println("\nChoose User Type:");
        System.out.println("1. Librarian");
        System.out.println("2. Member");
        System.out.println("0. Exit");
    }

    private static int getUserInput(Scanner scanner) {
        int userInput;

        while (true) {
            try {
                System.out.print("Enter your choice: ");
                userInput = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return userInput;
    }

    private static LibraryMember handleMemberAuthentication(Scanner scanner) {
        System.out.println("\nMember Authentication:");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("0. Go Back");

        int choice = getUserInput(scanner);

        switch (choice) {
            case 1:
                return handleMemberLogin(scanner);
            case 2:
                return handleMemberSignUp(scanner);
            case 0:
                return null;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                return null;
        }
    }

    private static LibraryMember handleMemberLogin(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        for (LibraryMember member : LibraryMember.getAllMembers()) {
            if (member.getUsername().equals(username) && member.authenticate(password)) {
                System.out.println("Login successful. Welcome back, " + member.getName() + "!");
                return member;
            }
        }

        System.out.println("Login failed. Incorrect username or password.");
        return null;
    }

    private static LibraryMember handleMemberSignUp(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your contact info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Choose a username: ");
        String username = scanner.nextLine();
        System.out.print("Choose a password: ");
        String password = scanner.nextLine();

        LibraryMember newMember = new LibraryMember(name, contactInfo, username, password);
        System.out.println("Sign up successful. Welcome to the library, " + newMember.getName() + "!");
        return newMember;
    }
}
