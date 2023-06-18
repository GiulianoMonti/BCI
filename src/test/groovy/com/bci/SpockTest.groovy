package com.bci

import com.bci.controller.dto.LoginDto
import com.bci.controller.dto.PhoneDto
import com.bci.controller.dto.UserDTO
import com.bci.entity.PhoneEntity
import com.bci.entity.RoleEntity
import com.bci.entity.UserEntity
import com.bci.repository.RoleRepository
import com.bci.repository.UserRepository
import com.bci.security.JwtTokenProvider
import com.bci.service.impl.UserServiceImpl
import org.codehaus.groovy.transform.SourceURIASTTransformation
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification
import spock.lang.Subject

import java.util.stream.Collectors

import static org.mockito.ArgumentMatchers.any
import static org.mockito.BDDMockito.given

class SpockTest extends Specification {


    @Subject
    private UserServiceImpl userService;

    private UserRepository userRepository = Mock()

    private PasswordEncoder passwordEncoder = Mock()

    private RoleRepository roleRepository = Mock()

    private JwtTokenProvider tokenProvider = Mock()

    UserEntity user;
    UserDTO userRequest;
    LoginDto loginDto;
    RoleEntity roles

    def setup() {
        userService = new UserServiceImpl(userRepository, passwordEncoder, roleRepository, tokenProvider)
        PhoneEntity phones = new PhoneEntity();

        roles = new RoleEntity();
        roles.setName("ROLE_ADMIN");
        roles.setId(1L);
        phones.setCityCode(123);
        phones.setCountryCode("ABC");
        phones.setId(1L);
        phones.setNumber(1234);

        user = UserEntity.builder().id("string").email("global@logic.co").password("globaLLaog14").username("global")
                .token("123").phones(Collections.singleton(phones)).roles(Collections.singleton(roles))
                .lastLogin(new Date()).created(new Date()).isActive(true).build();

        userRequest = UserDTO.builder().email("global@logic.com").password("globaLLaog14").username("global")
                .phones(user.getPhones()
                        .stream().map(PhoneDto::mapping).collect(Collectors.toSet())).build();

        loginDto = LoginDto.builder().username("global").password("globaLLaog14").build();
    }


    def "test anything"() {
        when:
        def result = 1
        then:
        result == 1
    }

    def "given user then check if its not null"() {
        given: "mock repositories"
        // lo q va a ser mockeado
        roleRepository.findByName(_ as String) >> Optional.of(roles)
        tokenProvider.generateTokens(userRequest.getUsername()) >> "token"
        passwordEncoder.encode(userRequest.getPassword()) >> "password" // checkear

        userRepository.save(_ as UserEntity) >> user

        when: // llamar al metodo que va a ser testeado
        def result = userService.createUser(userRequest)

        then:
        //1 * roleRepository.findByName("ROLE_ADMIN") // invocacion 1 vez
        1 * tokenProvider.generateTokens(userRequest.getUsername())
       // 1 * passwordEncoder.encode(userRequest.getPassword())
        1 * userRepository.save(_)

        result != null
        result.username == userRequest.getUsername()
        result.email == userRequest.getEmail()
        result.password == "password"
        result.isActive
    }

}
