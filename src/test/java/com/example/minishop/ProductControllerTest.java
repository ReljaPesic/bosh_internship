package com.example.minishop;

import com.example.minishop.product.Product;
import com.example.minishop.product.ProductController;
import com.example.minishop.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {
  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private ProductController productController;

  private Product product;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    product = new Product();
    product.setId(1L);
    product.setName("Test Product");
  }

  @Test
  void testGetAll_NoName() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<Product> page = new PageImpl<>(List.of(product));
    when(productRepository.findAll(pageable)).thenReturn(page);

    Page<Product> result = productController.getAll(null, pageable);
    assertNotNull(result);
    assertEquals(1, result.getContent().size());
    assertEquals("Test Product", result.getContent().get(0).getName());
  }

  @Test
  void testGetAll_WithName() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<Product> page = new PageImpl<>(List.of(product));
    when(productRepository.findByName("Test Product", pageable)).thenReturn(page);

    Page<Product> result = productController.getAll("Test Product", pageable);
    assertNotNull(result);
    assertEquals(1, result.getContent().size());
    assertEquals("Test Product", result.getContent().get(0).getName());
  }

  @Test
  void testGetById_Found() {
    when(productRepository.findById(1L)).thenReturn(Optional.of(product));

    Product result = productController.getById(1L);
    assertNotNull(result);
    assertEquals(1L, result.getId());
  }

  @Test
  void testGetById_NotFound() {
    when(productRepository.findById(2L)).thenReturn(Optional.empty());

    Product result = productController.getById(2L);
    assertNull(result);
  }

  @Test
  void testCreate() {
    when(productRepository.save(product)).thenReturn(product);

    Product result = productController.create(product);
    assertNotNull(result);
    assertEquals("Test Product", result.getName());
    verify(productRepository, times(1)).save(product);
  }
}
