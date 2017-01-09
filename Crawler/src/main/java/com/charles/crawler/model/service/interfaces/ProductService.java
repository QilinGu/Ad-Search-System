package com.charles.crawler.model.service.interfaces;

        import com.charles.crawler.model.entity.PageLinkProductElement;
        import com.charles.crawler.model.entity.Product;

        import java.util.List;

/**
 * Created by ChenCheng on 1/3/2017.
 */
public interface ProductService {
    public List<Product> getAllProducts();

    void insertAll(List<Product> products);

    void addProduct(Product product);

    Product getProductById(String id);

    public void addProductByElement(PageLinkProductElement productElement);

    public void printProductToCsvFileByDefault();
}
