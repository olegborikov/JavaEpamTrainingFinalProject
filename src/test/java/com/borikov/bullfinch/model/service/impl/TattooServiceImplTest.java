package com.borikov.bullfinch.model.service.impl;

import com.borikov.bullfinch.exception.DaoException;
import com.borikov.bullfinch.exception.ServiceException;
import com.borikov.bullfinch.exception.TransactionException;
import com.borikov.bullfinch.model.builder.TattooBuilder;
import com.borikov.bullfinch.model.dao.TattooDao;
import com.borikov.bullfinch.model.dao.TransactionManager;
import com.borikov.bullfinch.model.dao.impl.TattooDaoImpl;
import com.borikov.bullfinch.model.entity.Tattoo;
import com.borikov.bullfinch.model.service.TattooService;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class TattooServiceImplTest {
    private TattooDao tattooDao;
    private TransactionManager transactionManager;
    private TattooService tattooService;

    @BeforeClass
    public void setUp() {
        tattooDao = mock(TattooDaoImpl.class);
        transactionManager = mock(TransactionManager.class);
        Whitebox.setInternalState(TattooDaoImpl.class, "INSTANCE", tattooDao);
        Whitebox.setInternalState(TransactionManager.class, "INSTANCE", transactionManager);
        tattooService = new TattooServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        tattooDao = null;
        transactionManager = null;
        tattooService = null;
    }

    @Test
    public void addTattooPositiveTest() {
        try {
            when(transactionManager.addImageAndTattoo(any(Tattoo.class))).thenReturn(true);
            boolean actual = tattooService.addTattoo("Moon", "cool", "100",
                    "7e505ced-f395-49dd-9cd6-169d920ec320", "oleg");
            assertTrue(actual);
        } catch (ServiceException | TransactionException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void addTattooNegativeTest() {
        try {
            when(transactionManager.addImageAndTattoo(any(Tattoo.class))).thenReturn(true);
            boolean actual = tattooService.addTattoo("Moon", "cool", "100",
                    "7e505ced-f395-49dd-9cd6-169d920ec320", "");
            assertFalse(actual);
        } catch (ServiceException | TransactionException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void removeTattooPositiveTest() {
        try {
            when(transactionManager.removeTattooAndImage(any(Long.class), any(Long.class))).thenReturn(true);
            boolean actual = tattooService.removeTattoo("1", "2");
            assertTrue(actual);
        } catch (ServiceException | TransactionException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void removeTattooNegativeTest() {
        try {
            when(transactionManager.removeTattooAndImage(any(Long.class), any(Long.class))).thenReturn(true);
            boolean actual = tattooService.removeTattoo(" ", "2");
            assertFalse(actual);
        } catch (ServiceException | TransactionException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void editTattooPositiveTest() {
        try {
            when(tattooDao.update(any(Tattoo.class))).thenReturn(true);
            boolean actual = tattooService.editTattoo("1", "Moon", "cool", "100");
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void editTattooNegativeTest() {
        try {
            when(tattooDao.update(any(Tattoo.class))).thenReturn(true);
            boolean actual = tattooService.editTattoo("", "Moon", "cool", "100");
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void offerTattooPositiveTest() {
        try {
            when(transactionManager.offerImageAndTattoo(any(Tattoo.class))).thenReturn(true);
            boolean actual = tattooService.offerTattoo("Moon", "cool", "100",
                    "7e505ced-f395-49dd-9cd6-169d920ec320", "oleg");
            assertTrue(actual);
        } catch (ServiceException | TransactionException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void offerTattooNegativeTest() {
        try {
            when(transactionManager.offerImageAndTattoo(any(Tattoo.class))).thenReturn(true);
            boolean actual = tattooService.offerTattoo("Moon", "", "100",
                    "7e505ced-f395-49dd-9cd6-169d920ec320", "oleg");
            assertFalse(actual);
        } catch (ServiceException | TransactionException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void allowTattooPositiveTest() {
        try {
            String id = "1";
            when(tattooDao.allow(any(Long.class))).thenReturn(true);
            boolean actual = tattooService.allowTattoo(id);
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void allowTattooNegativeTest() {
        try {
            String id = "fdsa";
            when(tattooDao.allow(any(Long.class))).thenReturn(true);
            boolean actual = tattooService.allowTattoo(id);
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void archiveTattooPositiveTest() {
        try {
            String id = "1";
            when(tattooDao.archive(any(Long.class))).thenReturn(true);
            boolean actual = tattooService.archiveTattoo(id);
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void archiveTattooNegativeTest() {
        try {
            String id = "fdsa";
            when(tattooDao.archive(any(Long.class))).thenReturn(true);
            boolean actual = tattooService.archiveTattoo(id);
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void unarchiveTattooPositiveTest() {
        try {
            String id = "1";
            when(tattooDao.unarchive(any(Long.class))).thenReturn(true);
            boolean actual = tattooService.unarchiveTattoo(id);
            assertTrue(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void unarchiveTattooNegativeTest() {
        try {
            String id = "fdsa";
            when(tattooDao.unarchive(any(Long.class))).thenReturn(true);
            boolean actual = tattooService.unarchiveTattoo(id);
            assertFalse(actual);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllTattoosPositiveTest() {
        try {
            List<Tattoo> expected = new ArrayList<>();
            when(tattooDao.findAll()).thenReturn(new ArrayList<>());
            List<Tattoo> actual = tattooService.findAllTattoos();
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllTattoosNegativeTest() {
        try {
            List<Tattoo> expected = null;
            when(tattooDao.findAll()).thenReturn(new ArrayList<>());
            List<Tattoo> actual = tattooService.findAllTattoos();
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findTattooByIdPositiveTest() {
        try {
            String id = "1";
            Tattoo expected = new TattooBuilder().getTattoo();
            when(tattooDao.findById(any(Long.class))).thenReturn(Optional.of(new TattooBuilder().getTattoo()));
            Optional<Tattoo> actual = tattooService.findTattooById(id);
            assertEquals(actual.get(), expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findTattooByIdNegativeTest() {
        try {
            String id = "d";
            Optional<Tattoo> expected = Optional.of(new TattooBuilder().getTattoo());
            when(tattooDao.findById(any(Long.class))).thenReturn(Optional.of(new TattooBuilder().getTattoo()));
            Optional<Tattoo> actual = tattooService.findTattooById(id);
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findTattoosByNameSubstringPositiveTest() {
        try {
            String nameSubstring = "moon";
            List<Tattoo> expected = new ArrayList<>();
            when(tattooDao.findByNameSubstring(any(String.class))).thenReturn(new ArrayList<>());
            List<Tattoo> actual = tattooService.findTattoosByNameSubstring(nameSubstring);
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findTattoosByNameSubstringNegativeTest() {
        try {
            String nameSubstring = "moon";
            List<Tattoo> expected = null;
            when(tattooDao.findByNameSubstring(any(String.class))).thenReturn(new ArrayList<>());
            List<Tattoo> actual = tattooService.findTattoosByNameSubstring(nameSubstring);
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findTattoosByAllowedPositiveTest() {
        try {
            boolean isAllowed = true;
            List<Tattoo> expected = new ArrayList<>();
            when(tattooDao.findByAllowed(any(Boolean.class))).thenReturn(new ArrayList<>());
            List<Tattoo> actual = tattooService.findTattoosByAllowed(isAllowed);
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findTattoosByAllowedNegativeTest() {
        try {
            boolean isAllowed = true;
            List<Tattoo> expected = null;
            when(tattooDao.findByAllowed(any(Boolean.class))).thenReturn(new ArrayList<>());
            List<Tattoo> actual = tattooService.findTattoosByAllowed(isAllowed);
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findTattoosByArchivedPositiveTest() {
        try {
            boolean isArchived = true;
            List<Tattoo> expected = new ArrayList<>();
            when(tattooDao.findByArchived(any(Boolean.class))).thenReturn(new ArrayList<>());
            List<Tattoo> actual = tattooService.findTattoosByArchived(isArchived);
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findTattoosByArchivedNegativeTest() {
        try {
            boolean isArchived = true;
            List<Tattoo> expected = null;
            when(tattooDao.findByArchived(any(Boolean.class))).thenReturn(new ArrayList<>());
            List<Tattoo> actual = tattooService.findTattoosByArchived(isArchived);
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllTattoosCatalogPositiveTest() {
        try {
            List<Tattoo> expected = new ArrayList<>();
            when(tattooDao.findAllCatalog()).thenReturn(new ArrayList<>());
            List<Tattoo> actual = tattooService.findAllTattoosCatalog();
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllTattoosCatalogNegativeTest() {
        try {
            List<Tattoo> expected = null;
            when(tattooDao.findAllCatalog()).thenReturn(new ArrayList<>());
            List<Tattoo> actual = tattooService.findAllTattoosCatalog();
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void testFindTattooByIdCatalogPositiveTest() {
        try {
            String id = "1";
            Tattoo expected = new TattooBuilder().getTattoo();
            when(tattooDao.findByIdCatalog(any(Long.class)))
                    .thenReturn(Optional.of(new TattooBuilder().getTattoo()));
            Optional<Tattoo> actual = tattooService.findTattooByIdCatalog(id);
            assertEquals(actual.get(), expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void testFindTattooByIdCatalogNegativeTest() {
        try {
            String id = "d";
            Optional<Tattoo> expected = Optional.of(new TattooBuilder().getTattoo());
            when(tattooDao.findByIdCatalog(any(Long.class)))
                    .thenReturn(Optional.of(new TattooBuilder().getTattoo()));
            Optional<Tattoo> actual = tattooService.findTattooByIdCatalog(id);
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findTattoosByNameSubstringCatalogPositiveTest() {
        try {
            String nameSubstring = "moon";
            List<Tattoo> expected = new ArrayList<>();
            when(tattooDao.findByNameSubstringCatalog(any(String.class))).thenReturn(new ArrayList<>());
            List<Tattoo> actual = tattooService.findTattoosByNameSubstringCatalog(nameSubstring);
            assertEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findTattoosByNameSubstringCatalogNegativeTest() {
        try {
            String nameSubstring = "moon";
            List<Tattoo> expected = null;
            when(tattooDao.findByNameSubstringCatalog(any(String.class))).thenReturn(new ArrayList<>());
            List<Tattoo> actual = tattooService.findTattoosByNameSubstringCatalog(nameSubstring);
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }
}
