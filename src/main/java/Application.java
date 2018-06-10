import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


public class Application {

    public static void main(String... args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String result = restTemplate.getForObject("http://localhost:8080/api/product", String.class);
        ObjectMapper mapper = new ObjectMapper();
        Product[] products = mapper.readValue(result, Product[].class);
        System.out.println("List of all Products in Coffee Shop");
        for (Product product : products) {
            System.out.println("Product Id: " + product.getId() + " Product Name: " + product.getProductName() +
                    " Description: " + product.getDescription()
                    + " Price: " + product.getPrice() + " Product Type: " + product.getProductType());
        }

        result = restTemplate.getForObject("http://localhost:8080/api/product/" + 3, String.class);
        System.out.println("Get Product By ID from Coffee Shop");
        Product product  = mapper.readValue(result, Product.class);
        System.out.println("Product Id: " + product.getId() + " Product Name: " + product.getProductName() +
                " Description: " + product.getDescription()
                + " Price: " + product.getPrice() + " Product Type: " + product.getProductType());
    }

}
