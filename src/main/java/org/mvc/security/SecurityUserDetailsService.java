package org.mvc.security;

import org.mvc.lesson.Person;
import org.mvc.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Person person = personRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));
        return person;
    }

    public void createUser(Person user) {
        personRepository.save(user);
    }
}
