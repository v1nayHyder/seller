//package com.tech.vinay.seller.security;
//
//import com.tech.vinay.seller.entities.User;
//import com.tech.vinay.seller.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.Optional;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//
////    private @Autowired UserRepository userRepository;
////
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        Optional<User> user = userRepository.findById(Long.valueOf(username));
////        return user.map(u -> UserDetailsSpringWebSecurityModel.builder()
////                        .userId(String.valueOf(u.getId())).password(u.getPassword()).domain(u.getDomain()).build())
////                .orElseThrow(() -> new UsernameNotFoundException("Invalid Username"));
////    }
////    }
//}
