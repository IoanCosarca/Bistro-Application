package com.ntt.bistroapplication.service;

import com.ntt.bistroapplication.model.Ingredient;
import com.ntt.bistroapplication.model.Product;
import com.ntt.bistroapplication.exception.NonexistentProductException;
import com.ntt.bistroapplication.mapper.ProductMapper;
import com.ntt.bistroapplication.model.IngredientDTO;
import com.ntt.bistroapplication.model.ProductDTO;
import com.ntt.bistroapplication.model.ProductSetDTO;
import com.ntt.bistroapplication.repository.IngredientRepository;
import com.ntt.bistroapplication.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the Product Service.
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              IngredientRepository ingredientRepository)
    {
        this.productRepository = productRepository;
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Retrieves all the products in the database.
     * @return set of products
     */
    @Override
    public ProductSetDTO getProducts()
    {
        Set<ProductDTO> products = new HashSet<>();
        productRepository.findAll().iterator().forEachRemaining(p -> {
            ProductDTO product = productMapper.productToProductDTO(p);
            products.add(product);
        });
        return new ProductSetDTO(products);
    }

    /**
     * Retrieves the product with the specified id.
     * @param id id of the product to be found
     * @return DTO of the product
     * @throws NonexistentProductException when the product with the provided id doesn't exist
     */
    @Override
    public ProductDTO getByID(Long id) throws NonexistentProductException
    {
        Optional<Product> productOptional = productRepository.findById(id);
        final ProductDTO[] foundProduct = { new ProductDTO() };
        productOptional.ifPresentOrElse(
                p -> foundProduct[0] = productMapper.productToProductDTO(p),
                () -> {
                    throw new NonexistentProductException(
                            "The product with the given ID couldn't be found");
                }
        );
        return foundProduct[0];
    }

    /**
     * Retrieves the product with the specified name.
     * @param name name of the product to be found
     * @return DTO of the product
     * @throws NonexistentProductException when the product with the provided name doesn't exist
     */
    @Override
    public ProductDTO getDTOByName(String name) throws NonexistentProductException
    {
        Optional<Product> productOptional = productRepository.findByName(name);
        final ProductDTO[] foundProduct = { new ProductDTO() };
        productOptional.ifPresentOrElse(
                p -> foundProduct[0] = productMapper.productToProductDTO(p),
                () -> {
                    throw new NonexistentProductException(name + " is not a valid product name!");
                }
        );
        return foundProduct[0];
    }

    /**
     * Inserts a new product in the table.
     * @param newProduct product entry to be inserted
     */
    @Override
    public void addProduct(ProductDTO newProduct)
    {
        Optional<Product> optionalProduct = productRepository.findByName(newProduct.getName());
        if (optionalProduct.isEmpty())
        {
            setProductDTOPrice(newProduct);
            Product product = productMapper.productDTOtoProduct(newProduct);
            product.setIngredients(computeIngredients(newProduct));
            productRepository.save(product);
        }
    }

    /**
     * Update the price of a product given by its id.
     * @param id       id of the product whose price must be changed
     * @param newPrice the new product price
     */
    @Override
    public void updatePrice(Long id, BigDecimal newPrice)
    {
        ProductDTO productDTO = getByID(id);
        if (productDTO != null)
        {
            productDTO.setPrice(newPrice);
            Product product = productMapper.productDTOtoProduct(productDTO);
            product.setIngredients(computeIngredients(productDTO));
            product.setId(id);
            productRepository.save(product);
        }
    }

    /**
     * Deletes a product from the table.
     * @param id identifier of the customer to be deleted
     */
    @Override
    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Constructs a set of ingredients from a given ProductDTO.
     * @param productDTO entry from where to extract the information to compute the ingredients
     * @return set of ingredients
     */
    private Set<Ingredient> computeIngredients(ProductDTO productDTO)
    {
        Set<IngredientDTO> ingredientDTOS = productDTO.getIngredients();
        Set<Ingredient> ingredients = new HashSet<>();
        for (IngredientDTO i : ingredientDTOS)
        {
            Ingredient ingredient = ingredientRepository.findByName(i.getName()).orElseThrow();
            ingredients.add(ingredient);
        }

        return ingredients;
    }

    /**
     * Calculates the price of a ProductDTO.
     * @param productDTO instance whose price must be calculated
     */
    public void setProductDTOPrice(ProductDTO productDTO)
    {
        BigDecimal price = BigDecimal.ZERO;
        Set<IngredientDTO> ingredients = productDTO.getIngredients();
        for (IngredientDTO ingredient : ingredients) {
            price = price.add(ingredient.getCost());
        }
        productDTO.setPrice(price);
    }

    /**
     * Calculates the price of a Product.
     * @param product instance whose price must be calculated
     */
    public void setProductPrice(Product product)
    {
        BigDecimal price = BigDecimal.ZERO;
        Set<Ingredient> ingredients = product.getIngredients();
        for (Ingredient ingredient : ingredients) {
            price = price.add(ingredient.getCost());
        }
        product.setPrice(price);
    }
}
