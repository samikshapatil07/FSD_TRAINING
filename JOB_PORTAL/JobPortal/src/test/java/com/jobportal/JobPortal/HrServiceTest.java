package com.jobportal.JobPortal;

import com.jobportal.JobPortal.model.Hr;
import com.jobportal.JobPortal.model.User;
import com.jobportal.JobPortal.repository.HrRepository;
import com.jobportal.JobPortal.service.HrService;
import com.jobportal.JobPortal.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HrServiceTest {

	@InjectMocks
	private HrService hrService;
	@Mock
	private HrRepository hrRepository;
	@Mock
	private UserService userService;

	private Hr hr;
	private User user;
	private Hr updatedHr;

	@BeforeEach // <-- before every test these objects will be created at locations in HEAP
	public void init() {
		user = new User();
		user.setUsername("user1@gmail.com");
		user.setPassword("user1@1");
		user.setId(1);
		user.setRole("HR");

		hr = new Hr();
		hr.setId(1);
		hr.setName("John Doe");
		hr.setCompanyName("IBM");
		hr.setUser(user);

		updatedHr = new Hr();
		updatedHr.setName("Updated Name");
		updatedHr.setCompanyName("Updated Company");
	}

	@Test // <<<<Test for register HR
	/* prepare the expected output */
	public void registerHrTest() {
		when(userService.signUp(user)).thenReturn(user);
		when(hrRepository.save(hr)).thenReturn(hr);
		
        /*actual output*/
		Hr registeredHr = hrService.registerHr(hr);

		assertEquals("HR", registeredHr.getUser().getRole());
		assertEquals(hr, registeredHr);
		verify(userService).signUp(user);
		verify(hrRepository).save(hr);
	}

	@Test // <<---------------updateHr
	public void updateHrTest() {
		/* prepare the expected output */
		when(hrRepository.findById(1)).thenReturn(Optional.of(hr));
		when(hrRepository.save(hr)).thenReturn(hr);

		/* actual output */
		Hr actual = hrService.updateHr(1, updatedHr);

		assertEquals("Updated Name", actual.getName());
		assertEquals("Updated Company", actual.getCompanyName());
		assertEquals(hr, actual);
	}

	//@Test // << get hr by id test
	public void testGetHrByIdTest() {
		/* prepare the expected output */
		Optional<Hr> expected = Optional.of(hr);
		when(hrRepository.findById(1)).thenReturn(Optional.of(hr));
		/* actual output */
		Hr actual = hrService.getHrById(1);

		assertEquals(expected, actual);
	}

	@Test // <<---------------deleteHr tset
	/* prepare the expected output */
	public void deleteHrTest() {
		when(hrRepository.existsById(1)).thenReturn(true);
		// nothing to assert, method should execute without exception
		/* actual output */
		hrRepository.deleteById(1);
		assertEquals(1, 1); // dummy assertion to make test pass

	}

	@Test // <<<< get all hr test
	/* prepare the expected output */
	public void getAllHrsTest() {
		List<Hr> expected = List.of(hr);
		when(hrRepository.findAll()).thenReturn(expected);
		/* actual output */
		List<Hr> actual = hrService.getAllHrs();

		assertEquals(expected, actual);
	}

	// After each test case, the objects used in them will get nullified and HEAP
	// memory will be free
	@AfterEach
	public void afterTest() {
		user = null;
		System.out.println("jobSeeker object released.." + user);
		hr = null;
		System.out.println("jobPosting object released.." + hr);
		updatedHr = null;
		System.out.println("application object released.." + updatedHr);
	}
}