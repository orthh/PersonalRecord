package com.orthh.backend.service;

import com.orthh.backend.domain.User;
import com.orthh.backend.dto.user.UserJoinReqDto;
import com.orthh.backend.dto.user.UserLoginReqDto;
import com.orthh.backend.dto.user.UserLoginResDto;
import com.orthh.backend.repository.UserRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
 * jwt인증으로 변경중입니다. 아직은 사용.
 */
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /*
   * post 회원가입
   */
  public Long join(UserJoinReqDto request) {
    User user = userRepository.findByEmail(request.getEmail());
    if (user != null) {
      return null;
    } else {
      String encodedPassword = passwordEncoder.encode(request.getPassword());
      User newUser =
          User.builder()
              .email(request.getEmail())
              .password(encodedPassword)
              .nickname(request.getNickname())
              .build();
      userRepository.save(newUser);
      return userRepository.findByEmail(request.getEmail()).getId();
    }
  }

  /*
   * post 로그인
   */
  public Long authenticate(UserLoginReqDto request) {
    User user = userRepository.findByEmail(request.getEmail());
    if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      return user.getId();
    }
    return null;
  }
}
