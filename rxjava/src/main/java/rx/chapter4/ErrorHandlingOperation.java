package rx.chapter4;

import dto.Book;
import dto.Employee;
import rx.Observable;
import service.BookFinderService;

import java.util.List;

class ErrorHandlingOperation {

    private BookFinderService bookFinderService = new BookFinderService();

    List<Book> onErrorResumeNext(Employee employee) {
        return recommendFor(employee).onErrorResumeNext(bestSeller())
                                     .toList()
                                     .toBlocking()
                                     .single();
    }

    private Observable<Book> recommendFor(Employee employee) {
        return bookFinderService.recommendFor(employee);
    }

    private Observable<Book> bestSeller() {
        return bookFinderService.bestSeller();
    }
}
