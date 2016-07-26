package com.dataBytes.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.dataBytes.controller.EmployeeController;
import com.dataBytes.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:odcAccess-servlet.xml"})
@ActiveProfiles("test")
public class EmployeeControllerTest {
	private EmployeeService employeeServiceMock;

    private EmployeeController employeeController;

    public EmployeeControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    	employeeController = new EmployeeController();

    	employeeServiceMock = mock(EmployeeService.class);
        ReflectionTestUtils.setField(employeeController, "employeeService", employeeServiceMock);
    }

    @After
    public void tearDown() {

    }
    
    
}
