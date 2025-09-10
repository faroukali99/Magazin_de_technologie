package com.secdecompagny.demo.controller;

import com.secdecompagny.demo.model.Product;
import com.secdecompagny.demo.model.ProductType;
import com.secdecompagny.demo.repository.IProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
//
//@RestController
@Controller
@RequiredArgsConstructor
public class ProductController {
//    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final IProductRepository productRepository;
//
//    // Page d'accueil - liste des produits
//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("products", productRepository.findAll());
//        return "index";
//    }
//
//  // Exemple de méthode dans ProductController.java
//  @GetMapping("/product/form")
//  public String showProductForm(Model model) {
//      logger.info("Affichage du formulaire d'ajout de produit");
//      logger.info("Types de produits disponibles: {}", Arrays.toString(ProductType.values()));
//
//      model.addAttribute("product", new Product());
//      model.addAttribute("productTypes", Arrays.asList(ProductType.values()));
//      return "product_form";
//  }
//
//    // Sauvegarder le produit (formulaire HTML)
//    @PostMapping("/product/save")
//    public String saveProductPostman(@Valid @ModelAttribute("product") Product product,
//                              BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("productTypes", ProductType.values());
//            return "product_form";
//        }
//        productRepository.save(product);
//        return "redirect:/";
//    }

    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }

    @GetMapping("/products/lists")
    public String getAllProductsFromPostman(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @PostMapping("/products")
    public Product saveProductPostman(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

//    @DeleteMapping("/products/{id}")
//    public void deleteProduct(@PathVariable Long id) {
//        productRepository.deleteById(id);
//    }

    @DeleteMapping("/products")
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
//
//    @GetMapping("/product/form")
//    public String showProductForm(Model model) {
//        model.addAttribute("product", new Product());
//        model.addAttribute("productTypes", Arrays.asList(ProductType.values()));
//        return "product_form";
//    }
//
//    @PostMapping("/product/save")
//    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("productTypes", ProductType.values());
//            return "product_form";
//        }
//        productRepository.save(product);
//        return "redirect:/products";
//    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAllByOrderByIdAsc();
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProductFromIndexWithId(@PathVariable Long id) {
        try {
            productRepository.deleteById(id);
            return "redirect:/products/lists?deleteSuccess=true";
        } catch (Exception e) {
            return "redirect:/products/lists?deleteError=true";
        }
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<String> handleOptimisticLockingFailure(OptimisticLockingFailureException ex) {
        return ResponseEntity.status(409).body("Le produit a été modifié ou supprimé par un autre utilisateur.");
    }

}