package br.com.senac.cantinaserver.controller;

import br.com.senac.cantinaserver.model.Product;
import br.com.senac.cantinaserver.dao.ProductDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    @Autowired
    private ProductDAO productDao;

    @ResponseBody
    @RequestMapping(value = "/api/v1/product/list", method = RequestMethod.POST)
    public List<Product> getAll() {
        List<Product> products = new ArrayList<Product>();
        try {
            products = productDao.getAll();
        } catch (Exception ex) {
            System.out.println("Error updating the product: " + ex.toString());
        }
        return products;
    }    
    
    @ResponseBody
    @RequestMapping(value = "/api/v1/product/createDB", method = RequestMethod.GET)
    public void createDB() {
        try {
            productDao.initDB();
        } catch (Exception ex) {
            System.out.println("Error inserting the products: " + ex.toString());
        }
    }   
}
