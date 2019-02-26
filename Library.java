import java.util.*;

public class Library{
    HashMap<Integer, book> books = new HashMap <Integer, book>();
    ArrayList<book> lib = new ArrayList <book>();
    Scanner sc = new Scanner (System.in);
    class book{
        int regNumber;
        int numCopies;
        int numIssued;
        private String name;
        String author;
        Date issuedDate;
        ArrayList<Date> issued = new ArrayList<Date>(numIssued);
        book(int r, String n, String a, int numCop)
        {
            numIssued = 0;
            regNumber = r;
            name = n;
            author = a;
            numCopies = numCop;
            books.put(regNumber, this);
            lib.add (this);
        }
        book(int r)
        {
            regNumber = r;
        }
        String name()
        {
            return this.name;
        }
        
    }
    void view()
    {
        sc.nextLine();
        System.out.println ("REGISTRATION" + "\tNAME" + "\tAUTHOR" + "\tCOPIES" + "\tISSUED");
        for (int i = 0; i<lib.size(); i++)
        {
            book temp = lib.get(i);
            System.out.println("\t" + temp.regNumber + "\t" + temp.name() + "\t" + temp.author + "\t" + temp.numCopies + "\t" + temp.numIssued);
        }
        menu();
    }
    void menu()
    {
        System.out.println ("1)View Existing Books");
        System.out.println ("2)Add new book");
        System.out.println ("3)Add new copies");
        System.out.println ("4)Issue book");
        System.out.println ("5)Return book");
        System.out.println ("6)Delete book");
        System.out.println ("7)Exit");
        int a = sc.nextInt();
        switch(a)
        {
            case 1:
            view();
            break;
            case 2:
            addBook();
            break;
            case 3:
            plusCopies();
            break;
            case 4:
            issue();
            break;
            case 5:
            returnBook();
            break;
            case 6:
            delBook();
            break;
            case 7:
            System.exit(0);
            break;
            default:
        }
    }
    void returnBook()
    {
        System.out.println ("Enter the registration number of the book to be returned");
        int sno = sc.nextInt();
        book retBook = books.get(sno);
        retBook.numIssued -= 1;
        retBook.issued.remove(retBook.issuedDate);
        menu();
    }
    void delBook()
    {
        System.out.println("Enter the registration number of the book to be deleted");
        int sno = sc.nextInt();
        book delBook = books.get(sno);
        System.out.println("WARNING: All copies will be deleted. Confirm? (y/n)");
        char finDel = sc.next().charAt(0);
        if (finDel == 'y')
        {
            lib.remove(books.get(sno));
            books.remove(sno);
        }
        menu();
    }
    void issue()
    {
        System.out.println ("Enter the registration number of the book to be issued");
        int sno = sc.nextInt();
        if (!nullBookCheck(sno)){
            System.out.println ("Invalid registration number.");
            issue();
        }
        book issBook = books.get(sno);
        issBook.numIssued += 1;
        issBook.issuedDate = new Date();
        issBook.issued.add(issBook.issuedDate);
        menu();
    }
    boolean alert()
    {
        for (int i = 0; i<lib.size(); i++)
        {
            book temp = lib.get(i);
            Date present = new Date();
            for (int j = 0; j<temp.issued.size(); j++)
            {
                if ((present.compareTo(temp.issued.get(i))) >= 7)
                {
                    return true;
                }
            }
        }
        return false;
    }
    void plusCopies()
    {
        System.out.println("Enter reg. no. of the book and the number of new copies");
        int r = sc.nextInt();
        int n = sc.nextInt();
        book b = books.get(r);
        b.numCopies += n;
        menu();    
    }
    void addBook()
    {
        System.out.println("\nEnter registration number");
        int r = sc.nextInt();    
        book b = new book (r);
        if (checkBook(b) == false)
        {
            System.out.println("Another book has this registration number.");
            addBook();
        }
          
        System.out.println("\nEnter name");
        sc.nextLine();
        String n = sc.nextLine();
        System.out.println("\nEnter Author");
        String a = sc.nextLine();
        System.out.println("\nEnter the number of copies");
        int num = sc.nextInt();
        sc.nextLine();
        book paper = new book(r, n, a, num);
        menu();
    }
    boolean nullBookCheck(int r)
    {
        if (books.get(r) != null)
            return true;
        return false;
    }
    boolean checkBook(book a)
    {
        for (int i = 0; i<books.size(); i++)
        {
            if (books.get(a.regNumber) != null)
                return false;
        }
        return true;
    }
    public static void main (String[]args)
    {
        Scanner sc = new Scanner (System.in);
        Library ob = new Library();
        ob.menu();
        
    }
}
