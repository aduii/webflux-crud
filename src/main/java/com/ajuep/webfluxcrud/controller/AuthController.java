package com.ajuep.webfluxcrud.controller;

import com.ajuep.webfluxcrud.entity.User;
import com.ajuep.webfluxcrud.repository.ReqRespModel;
import com.ajuep.webfluxcrud.service.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collection;

@RestController
public class AuthController {

    final ReactiveUserDetailsService users;
    final JWTService jwtService;

    final PasswordEncoder encoder;

    public AuthController(ReactiveUserDetailsService users, JWTService jwtService, PasswordEncoder encoder) {
        this.users = users;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    @GetMapping("/auth")
    public Mono<ResponseEntity<ReqRespModel<String>>> auth(){
        return Mono.just(
                ResponseEntity.ok(
                        new ReqRespModel<>("Welcome","")
                )
        );
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<ReqRespModel<String>>> login(@RequestBody User user){
        Mono<UserDetails> foundUser = users.findByUsername(user.getUsername()).defaultIfEmpty(new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        });

        return foundUser.flatMap(u->{
            if (u.getUsername() != null){
                if(encoder.matches(user.getPassword(),u.getPassword())){
                    return Mono.just(
                            ResponseEntity.ok(
                                    new ReqRespModel<>(jwtService.generate(u.getUsername()),"Success")
                            )
                    );
                }
                return Mono.just(
                        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ReqRespModel<>("","Invalid Credentials"))
                );
            }
            return Mono.just(
                    ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ReqRespModel<>("","User not found, Please register"))
            );
        });
    }
}
