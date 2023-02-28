package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductManagerTest {

    @Mock
    private ProductRepository repository;
    @InjectMocks
    private ProductManager manager;
    private Book firstBook = new Book(1, "Alice’s Adventures in Wonderland", 228, "Lewis Carroll");
    private Book secondBook = new Book(2, "Through the Looking-Glass, and What Alice Found There", 307, "Lewis Carroll");
    private Smartphone firstSmartphone = new Smartphone(3, "8.3", 49_990, "Nokia");
    private Smartphone secondSmartphone = new Smartphone(4, "5.3 4/64", 15_990, "Nokia");

    @Test
    public void shouldGetAll() {
        Product[] returned = new Product[]{firstBook, secondBook, firstSmartphone, secondSmartphone};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{firstBook, secondBook, firstSmartphone, secondSmartphone};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);

        verify(repository).findAll();
    }

    @Test
    public void shouldFindSeveralByBookAuthor() {
        Product[] returned = new Product[]{firstBook, secondBook};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{firstBook, secondBook};
        Product[] actual = manager.searchBy("lEwIs cArRoLl");
        assertArrayEquals(expected, actual);

        verify(repository).findAll();
    }

    @Test
    public void shouldFindByBookTitle() {
        Product[] returned = new Product[]{firstBook};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{firstBook};
        Product[] actual = manager.searchBy("Alice’s Adventures in Wonderland");
        assertArrayEquals(expected, actual);

        verify(repository).findAll();
    }

    @Test
    public void shouldFindSeveralBySmartphoneManufacturer() {
        Product[] returned = new Product[]{firstSmartphone, secondSmartphone};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{firstSmartphone, secondSmartphone};
        Product[] actual = manager.searchBy("NOKIA");
        assertArrayEquals(expected, actual);

        verify(repository).findAll();
    }

    @Test
    public void shouldFindBySmartphoneTitle() {
        Product[] returned = new Product[]{firstSmartphone};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[]{firstSmartphone};
        Product[] actual = manager.searchBy("8.3");
        assertArrayEquals(expected, actual);

        verify(repository).findAll();
    }

    @Test
    public void shouldNotFind() {
        Product[] returned = new Product[]{firstBook, secondBook, firstSmartphone, secondSmartphone};
        doReturn(returned).when(repository).findAll();

        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("Keep calm");
        assertArrayEquals(expected, actual);

        verify(repository).findAll();
    }

    @Test
    public void shouldAddProduct() {
        Product[] returned = new Product[]{firstBook, secondBook, firstSmartphone, secondSmartphone};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).save(secondSmartphone);

        manager.add(secondSmartphone);
        Product[] expected = new Product[]{firstBook, secondBook, firstSmartphone, secondSmartphone};
        Product[] actual = manager.getAll();

        assertArrayEquals(expected, actual);

        verify(repository).findAll();
        verify(repository).save(secondSmartphone);
    }

    @Test
    public void shouldRemoveByID() {
        Product[] returned = new Product[]{firstBook, secondBook, firstSmartphone};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).removeByID(4);

        manager.removeByID(4);
        Product[] expected = new Product[]{firstBook, secondBook, firstSmartphone};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);

        verify(repository).findAll();
        verify(repository).removeByID(4);
    }
}