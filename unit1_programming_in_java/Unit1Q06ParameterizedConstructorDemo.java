public class Unit1Q06ParameterizedConstructorDemo {
    public static void main(String[] args) {
        Book book = new Book("Effective Java", "Joshua Bloch");
        System.out.println(book);
        System.out.println("done by Krish Devkota");
    }

    static class Book {
        private final String title;
        private final String author;

        Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        @Override
        public String toString() {
            return "Book{title='" + title + "', author='" + author + "'}";
        }
    }
}

