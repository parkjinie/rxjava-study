package rx.chapter4;

import dto.Book;
import dto.Employee;
import dto.EmployeeType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import rx.Observable;
import service.BookFinderService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ErrorHandlingOperationTest {

    @InjectMocks
    private ErrorHandlingOperation errorHandlingOperation;

    @Mock
    private BookFinderService bookFinderService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findRecommendedBooks() {
        Book book1 = new Book("9780134685991", "Effective Java");
        Book book2 = new Book("9780132350884", "Clean Code");
        Book book3 = new Book("9781617291999", "Java 8 in Action");
        Book book4 = new Book("9780596009205", "Head First Java");

        when(bookFinderService.recommendFor(any(Employee.class))).thenReturn(Observable.just(book1, book2));
        when(bookFinderService.bestSeller()).thenReturn(Observable.just(book2, book3, book4));

        assertThat(errorHandlingOperation.onErrorResumeNext(new Employee(1L, "Jake", EmployeeType.Outworkers)))
                .containsExactly(book1, book2);
    }

    @Test
    public void getBestSellers_because_cannot_find_recommend_books() {
        Book book1 = new Book("9780134685991", "Effective Java");
        Book book2 = new Book("9780132350884", "Clean Code");
        Book book3 = new Book("9781617291999", "Java 8 in Action");

        when(bookFinderService.recommendFor(any(Employee.class))).thenReturn(Observable.error(new Exception("Error!")));
        when(bookFinderService.bestSeller()).thenReturn(Observable.just(book1, book2, book3));

        assertThat(errorHandlingOperation.onErrorResumeNext(new Employee(1L, "Ann", EmployeeType.FullTime)))
                .containsExactly(book1, book2, book3);
    }
}