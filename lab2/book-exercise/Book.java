/**
 * A class that maintains information on a book.
 * This might form part of a larger application such
 * as a library system, for instance.
 *
 * @author (Insert your name here.)
 * @version (Insert today's date here.)
 */
class Book
{
    // The fields.
    private String author;
    private String title;
    private int pages;
    private String refNumber;
    private int borrowed;
    private boolean courseText;

    /**
     * Set the author and title fields when this object
     * is constructed.
     */
    public Book(String bookAuthor, String bookTitle, int bookPages, boolean isCourseText)
    {
        author = bookAuthor;
        title = bookTitle;
        pages = bookPages;
        refNumber = "";
        borrowed = 0;
        courseText = isCourseText;
    }

    // Add the methods here ...
    /**
     * @Return pages
     */
    public int getPages() {
        return pages;
    }
    
    /**
     * @Return author
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * @Return courseText
     */
    public boolean isCourseText() {
        return courseText;
    }
    
    /**
     * @Return refNumber
     */
    public String getRefNumber() {
        return refNumber;
    }
    
    /**
     * Sets refNumber
     */
    public void setRefNumber(String ref) {
        if (ref.length() > 2) {
            refNumber = ref;
        } else {
            System.out.println("Error: refNumber must contain at least 3 characters");
            System.out.println();
        }
    }
   
    /**
     * @Return borrowed
     */
    public int getBorrowed() {
        return borrowed;
    }
    
    /**
     * Increase borrowed 
     */
    public void borrow() {
        borrowed ++;
    }
    
    /**
     * @Return title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Print the author to the terminal.
     */
    public void printAuthor()
    {
        System.out.println("##################");
        System.out.println("Author: " + author);
        System.out.println("##################");
        System.out.println();
    }

    /**
     * Print the title to the terminal.
     */
    public void printTitle()
    {
        System.out.println("##################");
        System.out.println("Title: " + title);
        System.out.println("##################");
        System.out.println();
    }

    /**
     * Print Book details
     */
    public void printDetails() {
        String ref = "ZZZ";
        
        if (refNumber.length() > 0) {
            ref = refNumber;
        }
        
        System.out.println("Title: " + title + ", Author: " + author + ", Pages: " + pages + ", Ref.Number: " + ref);
        System.out.println("Borrowed " + borrowed + " times");
        System.out.println();
    }
    
    
}
