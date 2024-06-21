package customer.com.profile;

import customer.com.profile.model.User;
import customer.com.profile.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@SpringBootApplication
public class ProfileApplication {

	@Autowired
	UserRepository userRepository;

	@PostConstruct
	public void initUsers(){
		List<User> user= Stream.of(
				new User(1, "user", "pwd"),
				new User(2, "user1", "pwd1"),
				new User(3, "user2", "pwd2")
		).collect(Collectors.toList());
		userRepository.saveAll(user);
		System.out.println(userRepository.findAll());
	}
	public static void main(String[] args) {
		SpringApplication.run(ProfileApplication.class, args);
	}

}
