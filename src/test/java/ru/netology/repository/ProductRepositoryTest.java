package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book firstBook = new Book(1, "Alice’s Adventures in Wonderland", 228, "Lewis Carroll");
    private Book secondBook = new Book(2, "Through the Looking-Glass, and What Alice Found There", 307, "Lewis Carroll");

    @BeforeEach
    void setUp() {
        repository.save(firstBook);
        repository.save(secondBook);
    }

    @Test
    public void shouldFindAll() {
        Product[] expected = new Product[]{firstBook, secondBook};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByID() {
        repository.removeByID(1);
        Product[] expected = new Product[]{secondBook};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}