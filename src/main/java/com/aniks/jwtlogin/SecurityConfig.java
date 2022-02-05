package com.aniks.jwtlogin;

import com.aniks.jwtlogin.filters.JwtRequestFilter;
import com.aniks.jwtlogin.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetails;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetails);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/login", "/save").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // ensures that our created filter is called before the latter
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    };
}

/*
* {
  "category": "For chest",
  "description": "Push ups",
  "durationInDays": 60,
  "durationInHoursPerDay": 4,
  "image": "calf",
  "price": 30000,
  "productName": "Russian Press-up",
  "productType": "SERVICE",
  "stock": 10

}
*
*
* public Page<UserProductDto> getAllProducts(int pageNumber) {
         List<UserProductDto> dtoList = getDtoList();

        int pageSize = 10;
        int skipCount = (pageNumber - 1) * pageSize;

        List<UserProductDto> activityPage = dtoList
                .stream()
                .skip(skipCount)
                .limit(pageSize)
                .collect(Collectors.toList());

        Pageable productPage = PageRequest.of(pageNumber, pageSize, Sort.by("productName").ascending());

        return new PageImpl<>(activityPage, productPage, dtoList.size());
    }
    *
 public Page<OrderResponse> getOrdersByStatus(ORDER_STATUS status, int pageNo) {
        int pageSize = 10;
        int skipCount = (pageNo - 1) * pageSize;

        List<OrderResponse> orderList = orderRepository.findAllByOrderStatus(status)
                .stream()
                .map(x -> modelMapper.map(x, OrderResponse.class))
                .collect(Collectors.toList())
                .stream()
                .skip(skipCount)
                .limit(pageSize)
                .collect(Collectors.toList());

        Pageable orderPage = PageRequest.of(pageNo, pageSize, Sort.by("productName").ascending());

        return new PageImpl<>(orderList, orderPage, orderList.size());
   }
* */