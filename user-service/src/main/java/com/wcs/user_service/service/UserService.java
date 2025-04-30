package com.wcs.user_service.service;
import com.wcs.user_service.Entity.Product;
import com.wcs.user_service.Entity.User;
import com.wcs.user_service.Entity.UserDTORequest;
import com.wcs.user_service.Entity.UserDTOResponse;
import com.wcs.user_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    String request;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<UserDTOResponse> updateUser(UserDTORequest userDTOReq){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", "reqres-free-v1");
        HttpEntity<UserDTORequest> requestEntity = new HttpEntity<>(userDTOReq, headers);
        request = "https://reqres.in/api/users/2";
       return  restTemplate.exchange(request,HttpMethod.PUT,requestEntity, UserDTOResponse.class);

    }

    public ResponseEntity<String> deleteUser(){
        request  = "https://reqres.in/api/users/2";
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", "reqres-free-v1");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(request, HttpMethod.DELETE, requestEntity, String.class);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseEntity<Object> fetchUserProductById(Long userId, Long productId) {
        request = "http://localhost:8085/fetchProductById?productId=" + productId;
        Product product = restTemplate.getForObject(request, Product.class);
        if (product != null) {
            if (product.getUserId().equals(userId)) {
                return ResponseEntity.ok(product);
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("user product not found");
    }


    public ResponseEntity<Object> saveUserProduct(Long userId, Product product) {

        if (userRepository.existsById(userId)) {

            request = "http://localhost:8085/saveProduct";
            return ResponseEntity.ok(restTemplate.postForEntity(request, product, Product.class));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("User Id not Found");

    }
}
