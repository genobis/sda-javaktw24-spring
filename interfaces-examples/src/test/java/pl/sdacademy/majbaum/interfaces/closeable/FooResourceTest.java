package pl.sdacademy.majbaum.interfaces.closeable;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FooResourceTest {
    @Test
    void shouldBeClosedOnSuccess() {
        //WHEN
        final FooResource fooResource = new FooResource();
        fooResource.success();
        fooResource.close();

        //THEN
        assertThat(fooResource.isOpen()).isFalse();
    }

    @Test
    void shouldBeClosedOnFailure() {
        //Tradycyjne try-catch-finally
        /*
        //WHEN
        FooResource fooResource = null;
        try {
            fooResource = new FooResource();
            fooResource.failure();
        }
        catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
        finally {
            if (fooResource != null) {
                fooResource.close();
            }
        }

        //THEN
        assertThat(fooResource.isOpen()).isFalse();
        */

        //try-with-resources działa dzięki implementacji Autocloseable
        try(final FooResource fooResource = new FooResource()) {
            fooResource.failure();
        }
        catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}
