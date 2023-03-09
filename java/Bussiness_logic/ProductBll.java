package Bussiness_logic;

import Data_access.ProductDAO;
import Model.Product;

import java.util.List;

/***
 * In aceasta clasa apelam metodele de insert, update si delete din Clasa ProductDAO
 * @author Ioneasa Cristina
 */
public class ProductBll {

    private ProductDAO product;

    public ProductBll(){
        product = new ProductDAO();
    }

    public Product findProductById(int ID){
        Product p = (Product) product.findById(ID);
        return p;
    }

    public List<Product> findAll(){
        List<Product> products = product.findAll();
        return products;
    }

    public Product insertProduct(Product p){

        return product.insert(p);
    }

    public void updateProduct( Product p, int id){
        Product existentProduct = product.findById(id);
        if (existentProduct != null) {
            p.setID(id);
            product.update(p);
        }
    }

    public void deleteProduct(int id){
        Product p = product.findById(id);
        if(p != null)
            product.delete(p);
    }

    public ProductDAO getProduct() {
        return product;
    }

    public void setProduct(ProductDAO product) {
        this.product = product;
    }

}

