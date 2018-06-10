package service;

import dto.Book;
import dto.Employee;
import rx.Observable;

public class BookFinderService {

    public Observable<Book> recommendFor(Employee employee) {
        if (employee == null) {
            return Observable.error(new IllegalArgumentException("Employee doesn't exists"));
        }

        // Find recommended books for employee
        // ...
        return Observable.empty(); // mock
    }

    public Observable<Book> bestSeller() {
        // Find best sellers
        // ...
        return Observable.just(
                new Book("9780156012195", "The Little Prince"),
                new Book("9780692625477", "Peter Pan"),
                new Book("9781503214132", "Anne of Green Gables")
        ); // mock
    }
}
