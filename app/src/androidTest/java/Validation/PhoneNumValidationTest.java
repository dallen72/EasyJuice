package Validation;

import com.example.value.instantresume.test.BuildConfig;

import org.junit.Test;

import static com.example.value.instantresume.MainFragment.validatePhoneNum;

/**
 * Created by value on 3/13/17.
 */

public class PhoneNumValidationTest {

    @Test
    public void PhoneNumValidationTest() {
        if (BuildConfig.DEBUG && ! ( ! validatePhoneNum("123456789a") )) { throw new AssertionError(); }
        if (BuildConfig.DEBUG && ! ( ! validatePhoneNum("12-4567890") )) { throw new AssertionError(); }
        if (BuildConfig.DEBUG && ! ( ! validatePhoneNum("124434567890123") )) { throw new AssertionError(); }
        if (BuildConfig.DEBUG && ! ( ! validatePhoneNum("1267") )) { throw new AssertionError(); }

        if (BuildConfig.DEBUG && ! ( validatePhoneNum("1234567890") )) { throw new AssertionError(); }
    }
}
