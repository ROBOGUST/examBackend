package com.robogust.examBackend.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity delete(long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);

        }else {
            throw new RuntimeException("user does not exist");
        }
        return null;
    }

    public ResponseEntity<User> update(String username, Role role, long id) {
        try {
            User user = userRepository.findById(id).get();
            user.setUsername(username);
            user.setRole(role);
            return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }

    }
}
