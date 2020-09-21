package com.app.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.dao.ProductDAO;
import com.dao.connection.MySqlConnection;
import com.dao.impl.mysql.ProductDAOMySqlImpl;
import com.entity.Category;
import com.entity.Product;

public class ProductFunction {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public ProductFunction() {
    }


    public void showDetailProductView(Product product) {
        System.out.println("Product Name\t\t: " + product.getProductName());
        System.out.println("Stock \t: " + product.getStock());
        System.out.println("Price \t: " + product.getPrice());
        System.out.println("Color \t: " + product.getColor());
        System.out.println("Guarantee \t: " + product.getGuarantee());
        System.out.println("Description \t: " + product.getDescription());
        System.out.println("Category \t: " + product.getCategoryId().getCategory());
        System.out.println("Created Date \t: " + product.getCreatedDate());

    }

    public void editProductView(Product product) {
        System.out.println("1. Product Name\t\t: " + product.getProductName());
        System.out.println("2. Stock \t: " + product.getStock());
        System.out.println("3. Price \t: " + product.getPrice());
        System.out.println("4. Color \t: " + product.getColor());
        System.out.println("5. Guarantee \t: " + product.getGuarantee());
        System.out.println("6. Description \t: " + product.getDescription());
    }

    public Product editProductProcess(Product product, String nomorEdit, String valueEdit) {
        int status = 0;

        switch (nomorEdit) {
            case "1":
                product.setProductName(valueEdit);
                break;
            case "2":
                product.setStock(Integer.parseInt(valueEdit));
                break;
            case "3":
                product.setPrice(Double.parseDouble(valueEdit));
                break;
            case "4":
                product.setColor(valueEdit);
                break;
            case "5":
                product.setGuarantee(valueEdit);
                break;
            case "6":
                product.setDescription(valueEdit);
                break;
        }

        ProductDAO dao = new ProductDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            status = dao.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (status == 0) {
            try {
                product = dao.getProduct(product.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    public void showListProductView() {
        List<Product> listProduct = new ArrayList();
        ProductDAO dao = new ProductDAOMySqlImpl(MySqlConnection.getConnection());

        try {
            listProduct = dao.getAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Id\t\t Nama Product\t\t Stock\t\t Price\t\t Color\t\t Category");
        for(int x=0;x<listProduct.size();x++){
            System.out.println(((Product)listProduct.get(x)).getId()
            +"\t\t"+((Product)listProduct.get(x)).getproductName()
            +"\t\t"+((Product)listProduct.get(x)).getStock()
            +"\t\t"+((Product)listProduct.get(x)).getPrice()
            +"\t\t"+((Product)listProduct.get(x)).getColor()
            +"\t\t"+((Product)listProduct.get(x)).getCategoryId().getCategory());
        }

    }

    public void addNewProductView(){
        String menuChoose = "";
        Product product = new Product();
        System.out.println("CREATE NEW PRODUCT");

        System.out.println("Product Name : ");
        try {
            product.setProductName(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Stock : ");
        try {
            product.setStock(Integer.parseInt(input.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Price : ");
        try {
            product.setPrice(Double.parseDouble(input.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Color : ");
        try {
            product.setColor(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Desctiption : ");
        try {
            product.setDescription(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Guarantee : ");
        try {
            product.setGuarantee(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        CategoryFunction categoryFc = new CategoryFunction();
        categoryFc.showListCategoryView();
        System.out.println("Category ID : ");
        try {
            String kategoryId = input.readLine();
            Category kategori = new Category();
            kategori = categoryFc.getCategoryById(Integer.parseInt(kategoryId));
            product.setCategoryId(kategori);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Save");
        System.out.println("2. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                ProductDAO dao2 = new ProductDAOMySqlImpl(MySqlConnection.getConnection());
                try {
                    dao2.addProduct(product);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                break;
        }
    }
    
    public int deleteProductView(int idProduct){
        int status=0;
        ProductDAO dao = new ProductDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            dao.deleteProduct(idProduct);
            status=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
