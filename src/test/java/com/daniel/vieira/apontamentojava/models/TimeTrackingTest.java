package com.daniel.vieira.apontamentojava.models;

import com.daniel.vieira.apontamentojava.exceptions.RequiredFiledsIsMissing;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDateTime;

public class TimeTrackingTest {

    @Test
    public void shouldReturnTrue() throws RequiredFiledsIsMissing {
        TimeTracking tt = new TimeTracking("Teste","8","Teste project","Teste Service",
                "Teste Description", "Teste Manager", LocalDateTime.now());
        Assert.assertTrue(tt.validate());
    }

    @Test
    public void shouldReturnPersonalizedException(){
        TimeTracking tt = new TimeTracking("","","",null, null, null, null);
        assertThrows(RequiredFiledsIsMissing.class, tt::validate);
    }

    @Test
    public void shouldReturnPersonalizedExceptionWithMessageWhenCustomerNull(){
        TimeTracking tt = new TimeTracking(null,"8","Teste project","Teste Service", "Teste Description", "Teste Manager", LocalDateTime.now());
        RequiredFiledsIsMissing ex = assertThrows(RequiredFiledsIsMissing.class, tt::validate);
        Assert.assertEquals("Customer is expected to Save/Update this object", ex.getMessage());
    }

    @Test
    public void shouldReturnPersonalizedExceptionWithMessageWhenCustomerEmpty(){
        TimeTracking tt = new TimeTracking("","8","Teste project",
                "Teste Service", "Teste Description", "Teste Manager", LocalDateTime.now());
        RequiredFiledsIsMissing ex = assertThrows(RequiredFiledsIsMissing.class, tt::validate);
        Assert.assertEquals("Customer is expected to Save/Update this object", ex.getMessage());
    }

    @Test
    public void shouldReturnPersonalizedExceptionWithMessageWhenProjectEmpty(){
        TimeTracking tt = new TimeTracking("Teste","8","","Teste Service",
                "Teste Description", "Teste Manager", LocalDateTime.now());
        RequiredFiledsIsMissing ex = assertThrows(RequiredFiledsIsMissing.class, tt::validate);
        Assert.assertEquals("Project is expected to Save/Update this object", ex.getMessage());
    }

    @Test
    public void shouldReturnPersonalizedExceptionWithMessageWhenProjectNull(){
        TimeTracking tt = new TimeTracking("Teste","8",null,"Teste Service",
                "Teste Description", "Teste Manager", LocalDateTime.now());
        RequiredFiledsIsMissing ex = assertThrows(RequiredFiledsIsMissing.class, tt::validate);
        Assert.assertEquals("Project is expected to Save/Update this object", ex.getMessage());
    }

    @Test
    public void shouldReturnPersonalizedExceptionWithMessageWhenHourNull(){
        TimeTracking tt = new TimeTracking("Teste",null,"Teste Project","Teste Service",
                "Teste Description", "Teste Manager", LocalDateTime.now());
        RequiredFiledsIsMissing ex = assertThrows(RequiredFiledsIsMissing.class, tt::validate);
        Assert.assertEquals("How many hours you worked is expected to Save/Update this object", ex.getMessage());
    }

    @Test
    public void shouldReturnPersonalizedExceptionWithMessageWhenHourEmpty(){
        TimeTracking tt = new TimeTracking("Teste","","Teste Project","Teste Service",
                "Teste Description", "Teste Manager", LocalDateTime.now());
        RequiredFiledsIsMissing ex = assertThrows(RequiredFiledsIsMissing.class, tt::validate);
        Assert.assertEquals("How many hours you worked is expected to Save/Update this object", ex.getMessage());
    }

    @Test
    public void shouldReturnPersonalizedExceptionWithMessageWhenServiceNull(){
        TimeTracking tt = new TimeTracking("Teste","8","Teste Project",null,
                "Teste Description", "Teste Manager", LocalDateTime.now());
        RequiredFiledsIsMissing ex = assertThrows(RequiredFiledsIsMissing.class, tt::validate);
        Assert.assertEquals("Service is expected to Save/Update this object", ex.getMessage());
    }

    @Test
    public void shouldReturnPersonalizedExceptionWithMessageWhenServiceEmpty(){
        TimeTracking tt = new TimeTracking("Teste","8","Teste Project","",
                "Teste Description", "Teste Manager", LocalDateTime.now());
        RequiredFiledsIsMissing ex = assertThrows(RequiredFiledsIsMissing.class, tt::validate);
        Assert.assertEquals("Service is expected to Save/Update this object", ex.getMessage());
    }

    @Test
    public void shouldReturnPersonalizedExceptionWithMessageWhenDescriptionNull(){
        TimeTracking tt = new TimeTracking("Teste","8","Teste Project","Teste Service",
                null, "Teste Manager", LocalDateTime.now());
        RequiredFiledsIsMissing ex = assertThrows(RequiredFiledsIsMissing.class, tt::validate);
        Assert.assertEquals("Description is expected to Save/Update this object", ex.getMessage());
    }

    @Test
    public void shouldReturnPersonalizedExceptionWithMessageWhenDescriptionEmpty(){
        TimeTracking tt = new TimeTracking("Teste","8","Teste Project","Teste Service",
                "", "Teste Manager", LocalDateTime.now());
        RequiredFiledsIsMissing ex = assertThrows(RequiredFiledsIsMissing.class, tt::validate);
        Assert.assertEquals("Description is expected to Save/Update this object", ex.getMessage());
    }

    @Test
    public void shouldReturnPersonalizedExceptionWithMessageWhenDateNull(){
        TimeTracking tt = new TimeTracking("Teste","8","Teste Project","Teste Service",
                "Teste Description", "Teste Manager", null);
        RequiredFiledsIsMissing ex = assertThrows(RequiredFiledsIsMissing.class, tt::validate);
        Assert.assertEquals("Date is expected to Save/Update this object", ex.getMessage());
    }

}
