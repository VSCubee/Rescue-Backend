package com.rescue.vscube.agency;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.rescue.vscube.security.dtos.CredentialsDto;
import com.rescue.vscube.security.dtos.SignUpDto;
import com.rescue.vscube.security.dtos.UserDto;
import com.rescue.vscube.security.exceptions.AppException;
import com.rescue.vscube.security.mappers.UserMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AgencyService {

    private final AgencyRepository agencyRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final EntityManager entityManager;

    public UserDto login(CredentialsDto credentialsDto) {
        Agency agency = agencyRepository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown agency", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), agency.getPassword())) {
            return userMapper.toUserDto(agency);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<Agency> optionalUser = agencyRepository.findByLogin(userDto.login());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        Agency agency = userMapper.signUpToUser(userDto);
        agency.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

        Agency savedAgency = agencyRepository.save(agency);

        return userMapper.toUserDto(savedAgency);
    }

    public UserDto findByLogin(String login) {
        Agency agency = agencyRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown agency", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(agency);
    }

    public List<Agency> filter(String name, String description, String location, String startDate, String endDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Agency> criteriaQuery = criteriaBuilder.createQuery(Agency.class);
        Root<Agency> root = criteriaQuery.from(Agency.class);
        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }

        if (location != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("registeredLocation")), "%" + location.toLowerCase() + "%"));
        }

        if (description != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + description.toLowerCase() + "%"));
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (startDate != null) {
            try {
                Date parsedStartDate = dateFormat.parse(startDate);
                Timestamp startTimestamp = new Timestamp(parsedStartDate.getTime());

                Timestamp endTimestamp;

                if (endDate != null) {
                    Date parsedEndDate = dateFormat.parse(endDate);
                    endTimestamp = new Timestamp(parsedEndDate.getTime());
                } else {
                    endTimestamp = new Timestamp(System.currentTimeMillis());
                }
                predicates.add(criteriaBuilder.between(root.get("lastUpdated"), startTimestamp, endTimestamp));

            } catch (ParseException e) {
                // Handle parsing errors if the date strings are not in the expected format
                e.printStackTrace();
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public void updateActivity(Long agencyId){
        Agency agency = agencyRepository.findById(agencyId).get();
        agency.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        agencyRepository.save(agency);
    }
}
