//package com.clinic.Clinic.management.model;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import lombok.AllArgsConstructor;
//
//@Service
//@AllArgsConstructor
//public class MyAppUserService implements UserDetailsService{
//
//    @Autowired
//    private MyAppUserRepository repository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<MyAppUser> user = repository.findByUsername(username);
//        if (user.isPresent()) {
//            var userObj = user.get();
//            return User.builder()
//                    .username(userObj.getUsername())
//                    .password(userObj.getPassword())
//                    .build();
//        }else{
//            throw new UsernameNotFoundException(username);
//        }
//    }
//
//    @Autowired
//    private MyAppUserRepository userRepository;
//
//    public List<MyAppUser> getAllUsers() {
//        // Fetch all users from the repository (database)
//        return userRepository.findAll();
//    }
//
//}
//
////package com.clinic.Clinic.management.model;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////
////@Service
////public class MyAppUserService {
////
////    @Autowired
////    private MyAppUserRepository userRepository;
////
////    public List<MyAppUser> getAllUsers() {
////        // Fetch all users from the repository (database)
////        return userRepository.findAll();
////    }
////
////    public MyAppUser save(MyAppUser user) {
////        return userRepository.save(user);
////    }
////}

///////// THIS IS NECESSARY PART ==============================================================================

package com.clinic_system.clinic_alshifa.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyAppUserService implements UserDetailsService {

    @Autowired
    private MyAppUserRepository userRepository;

    // Fetch all users
    public List<MyAppUser> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete a user by ID
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Get user by ID for editing
    public MyAppUser getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Find user by username
    public MyAppUser findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Update user details
    public void updateUser(Long id, MyAppUser updatedUser) {
        MyAppUser existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setDateOfBirth(updatedUser.getDateOfBirth());
        existingUser.setRole(updatedUser.getRole());

        updatedUser.setRole("ROLE_" + updatedUser.getRole());

        // Optionally update password
        if (!updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(updatedUser.getPassword());  // Ensure to encrypt the password
        }

        userRepository.save(existingUser);
    }

        @Autowired
    private MyAppUserRepository repository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<MyAppUser> user = repository.findByUsername(username);
//        if (user.isPresent()) {
//            var userObj = user.get();
//            return User.builder()
//                    .username(userObj.getUsername())
//                    .password(userObj.getPassword())
//                    .build();
//        }else{
//            throw new UsernameNotFoundException(username);
//        }
//    }
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<MyAppUser> user = repository.findByUsername(username);
    if (user.isPresent()) {
        var userObj = user.get();

        // Add the role with "ROLE_" prefix
        String role = userObj.getRole().toUpperCase(); // Ensure role is uppercase and prefixed

        return User.builder()
                .username(userObj.getUsername())
                .password(userObj.getPassword())
                .roles(role)  // Adds the role to UserDetails
                .build();
    } else {
        throw new UsernameNotFoundException("User not found: " + username);
    }
}


//    //Pagination
//    public Page<MyAppUser> getUsersPage(Pageable pageable) {
//        return userRepository.findAll(pageable);
//    }
public Page<MyAppUser> getUsersPage(PageRequest pageRequest) {
    return userRepository.findAll(pageRequest);
}

    //Edit and Delete
//    @DeleteMapping("/admin/users/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUserById(id);
//    }
//
//    @PutMapping("/admin/users/{id}")
//    public void updateUser(@PathVariable Long id, @RequestBody MyAppUser user) {
//        userService.updateUser(id, user);
//    }


}
