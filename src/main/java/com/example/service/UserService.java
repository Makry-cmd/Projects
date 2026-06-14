package com.example.service;

import com.example.dto.UserEvent;
import com.example.dto.UserDto;
import com.example.model.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
	private final KafkaTemplate<String, UserEvent> kafkaTemplate;
	
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    @Transactional
	public UserDto createUser(UserDto dto) {

		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());


		User savedUser = userRepository.save(user);

		
		kafkaTemplate.send("user-events", new UserEvent(savedUser.getEmail(), "CREATE"));

		UserDto responseDto = new UserDto();
		responseDto.setId(savedUser.getId());
		responseDto.setName(savedUser.getName());
		responseDto.setEmail(savedUser.getEmail());
    
		return responseDto;
	}

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return convertToDto(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        kafkaTemplate.send("user-events", new UserEvent(user.getEmail(), "DELETE"));
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
